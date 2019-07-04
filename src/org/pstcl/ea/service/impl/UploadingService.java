package org.pstcl.ea.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.pstcl.ea.model.CMRIFileDataModel;
import org.pstcl.ea.model.FileModel;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.FileMaster;
import org.pstcl.ea.security.UserRole;
import org.pstcl.ea.service.impl.parallel.DataReaderThread;
import org.pstcl.ea.service.impl.parallel.DataService;
import org.pstcl.ea.util.EAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service("uploadingService")
public class UploadingService extends FileServiceUtil {

	//
	// [DAILY_SURVEY]
	// SurveyStartTime 02/09/2018 00:00:00
	// SurveyInterval 86400
	// StartRecord 849

	// PlantNumber HT01130038

	// [METER_INFO]
	// SerialNumber 212323997
	// SiteName
	// SiteId
	// PlantNumber HT01130038



	//	public CMRIDataModel processUploadedFile(FileModel fileModel) {
	//
	//		MultipartFile multipartFile = fileModel.getFile();
	//		String oldFileName = multipartFile.getOriginalFilename();
	//		String newFileName = new Date().getMonth() + "_" + new Date().getYear() + "_"
	//				+ multipartFile.getOriginalFilename();
	//
	//		File file = new File(EAUtil.CRMI_TXT_FILE_REPOSITORY + newFileName);
	//		try {
	//			FileCopyUtils.copy(multipartFile.getBytes(), file);
	//
	//		} catch (IOException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//
	//		return processTXTFile(file);
	//	}

	//	public FileModel processZipUploadedFile(FileModel fileModel) {
	//
	//		// File sourceZip = new File("D:\\METER_DATA\\CMRI_TEST_ZIP\\01130158.zip");
	//
	//		File zipUploaded=null;
	//		
	//		Calendar cal = Calendar.getInstance();
	//		String monthName= new SimpleDateFormat("MMM").format(cal.getTime());
	//		File zipRepository=new File(EAUtil.CRMI_ZIP_FILE_REPOSITORY+monthName+"/");
	//		
	//		if (!zipRepository.exists()) {
	//			zipRepository.mkdirs();
	//		}
	//
	//		try {
	//			zipUploaded = new File(zipRepository, fileModel.getFile().getOriginalFilename().replace(' ' , '_'));
	//			FileCopyUtils.copy(fileModel.getFile().getBytes(), zipUploaded);
	//		
	//		} catch (IOException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		FileMaster fileDetails = extractDecodeZip(zipUploaded);
	//		getFileMetaData(fileDetails);
	//		if (null != fileDetails.getMeterMaster()) {
	//			saveFileDetails(fileDetails);
	//		}
	//		fileModel.getFilesUploadedDetail().add(fileDetails);
	//		return fileModel;
	//	}


	@Autowired
	private ApplicationContext context;

	@Autowired
	private TaskExecutor taskExecutor; 


	public FileModel processMultiZipUploadedFile(FileModel fileModel) {

		MultipartFile[] multipartFiles = fileModel.getFiles();
		fileModel.setFilesUploadedDetail(new ArrayList());

		Calendar cal = Calendar.getInstance();
		String uploadDate= new SimpleDateFormat("MMM_yyyy_dd").format(cal.getTime());
		
		File zipRepository=new File(EAUtil.CRMI_ZIP_FILE_REPOSITORY+uploadDate+"/");

		if (!zipRepository.exists()) {
			zipRepository.mkdirs();
		}

		List<File> uploadedZipFilesList=new ArrayList<File>();

		for (MultipartFile multipartFile : multipartFiles) {
			if(multipartFile.getSize()>0)
			{
				File zipUploaded=null;
				try {
					zipUploaded = new File(zipRepository , replaceNullChars(multipartFile.getOriginalFilename()));

					while(zipUploaded.exists())
					{
						zipUploaded = new File(zipRepository,'_'+zipUploaded.getName());

					}
					FileCopyUtils.copy(multipartFile.getBytes(), zipUploaded);

					uploadedZipFilesList.add(zipUploaded);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		
		
		//Files have been uploaded status update via socket
		
		
		
		DataReaderThread dataReaderThread= context.getBean(DataReaderThread.class);

		for (File file : uploadedZipFilesList) {
			FileMaster fileDetails = extractDecodeZip(file);

			if(null!=fileDetails)
			{

				if(fileDetails.getTxtfileName()!=null && !fileDetails.getTxtfileName().isEmpty())
				{

					dataReaderThread.getFileMetaData(fileDetails);
					if(null==fileDetails.getTransactionDate())
					{
						fileDetails.setTransactionDate(new Date());
					}
				}
				setFileActionStatus(fileDetails);
				saveFileDetails(fileDetails);
				fileModel.getFilesUploadedDetail().add(fileDetails);
			}
		}

		return fileModel;
	}

	private void setFileActionStatus(FileMaster fileDetails) {
		EAUser eaUser=getLoggedInUser();
		if(userService.hasRole(UserRole.SS_AE.getUserRoleName())||userService.hasRole(UserRole.SS_JE.getUserRoleName())||userService.hasRole(UserRole.DIV_ASE.getUserRoleName()))
		{
			fileDetails.setFileActionStatus(EAUtil.FILE_ACTION__JE_SAVED_APPROVAL_PENDING);
			fileDetails.setUploadedBy(eaUser);
		}

		else if(userService.hasRole(UserRole.SLDC_ADMIN.getUserRoleName())||userService.hasRole(UserRole.SLDC_USER.getUserRoleName())||userService.hasRole(UserRole.CIRCLE_SE.getUserRoleName()))
		{
			fileDetails.setFileActionStatus(EAUtil.FILE_ACTION__APPROVED_AE);
			fileDetails.setUploadedBy(eaUser);
			fileDetails.setApprovedBy(eaUser);
		}



	}

	private String replaceNullChars(String name)
	{
		return name.replace(' ' , '_').replace('(' , '_').replace(')' , '_');

	}


	@Autowired
	DataService dataService;



	public CMRIFileDataModel changeFileActionStatus(Integer id,Integer action) {

		FileMaster fileMaster=fileMasterDao.findById(id);
		fileMaster.setFileActionStatus(action);
		if(action.equals(EAUtil.FILE_ACTION__APPROVED_AE))
		{
			fileMaster.setApprovedBy(getLoggedInUser());
		}
		else
			if(action.equals(EAUtil.FILE_ACTION__DELETED))
			{
				fileMaster.setProcessingStatus(EAUtil.FILE_ERROR_FILE_DELETED);
				fileMaster.setDeletedBy(getLoggedInUser());

			}
		saveFileDetails(fileMaster);
		CMRIFileDataModel cmriFileDataModel=dataService.viewRepoFileData(id);
		return	cmriFileDataModel;
	}

	public String getTextFilePath(Integer id) {

		FileMaster fileMaster=fileMasterDao.findById(id);

		return	fileMaster.getTxtfileName();
	}


	public FileModel processUploadedFile(FileModel fileModel) {

		MultipartFile multipartFile = fileModel.getFile();
		String oldFileName = multipartFile.getOriginalFilename();
		String newFileName = "TXT_UPLOADED_"+new Date().getMonth() + "_" + new Date().getYear() + "_"
				+ multipartFile.getOriginalFilename();

		File file = new File(EAUtil.CRMI_TXT_FILE_REPOSITORY + newFileName);

		while(file.exists())
		{
			newFileName="_"+newFileName;
			file = new File(EAUtil.CRMI_TXT_FILE_REPOSITORY+newFileName);

		}
		try {
			FileCopyUtils.copy(multipartFile.getBytes(), file);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FileMaster fileDetails = new FileMaster();

		fileDetails.setDailyRecordCount(-1);
		fileDetails.setSurveyRecordCount(-1);

		fileDetails.setUserfileName(file.getName());
		fileDetails.setTxtfileName(file.getAbsolutePath());
		fileDetails.setProcessingStatus(EAUtil.FILE_ZIP_EXTRACTED);
		DataReaderThread dataReaderThread= context.getBean(DataReaderThread.class);

		dataReaderThread.getFileMetaData(fileDetails);


		if(null==fileDetails.getTransactionDate())
		{
			fileDetails.setTransactionDate(new Date());
		}

		setFileActionStatus(fileDetails);


		saveFileDetails(fileDetails);

		fileModel.setFilesUploadedDetail(new ArrayList<FileMaster>());

		fileModel.getFilesUploadedDetail().add(fileDetails);
		return fileModel;
	}


	@org.springframework.transaction.annotation.Transactional(value = "sldcTxnManager")
	public void saveFileDetails(FileMaster fileDetails) {
		DataReaderThread dataReaderThread= context.getBean(DataReaderThread.class);

		dataReaderThread.saveFileDetails(fileDetails);	
	}

}
