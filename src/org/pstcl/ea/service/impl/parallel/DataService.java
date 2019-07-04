package org.pstcl.ea.service.impl.parallel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.pstcl.ea.model.CMRIFileDataModel;
import org.pstcl.ea.model.FileFilter;
import org.pstcl.ea.model.FileModel;
import org.pstcl.ea.model.LocationFileModel;
import org.pstcl.ea.model.LocationSurveyDataModel;
import org.pstcl.ea.model.entity.DailyTransaction;
import org.pstcl.ea.model.entity.FileMaster;
import org.pstcl.ea.model.entity.InstantRegisters;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.MeterMaster;
import org.pstcl.ea.model.entity.TamperLogTransaction;
import org.pstcl.ea.service.impl.EnergyAccountsService;
import org.pstcl.ea.service.impl.IlossReportService;
import org.pstcl.ea.util.DateUtil;
import org.pstcl.ea.util.EAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;


@Service("dataService")
@EnableAsync
public class DataService extends EnergyAccountsService{

	@Autowired
	private ApplicationContext context;

	@Autowired
	private TaskExecutor taskExecutor; 


	public CMRIFileDataModel viewRepoFileData(Integer id) {

		FileMaster fileMaster=fileMasterDao.findById(id);
		DataReaderThread dataReaderThread= context.getBean(DataReaderThread.class);

		CMRIFileDataModel cmriFileDataModel=new CMRIFileDataModel();
		cmriFileDataModel.setFileMaster(fileMaster);
		dataReaderThread.extractTransactionDataFromFile(cmriFileDataModel);

		return	cmriFileDataModel;
	}



	public CMRIFileDataModel processRepoFileSerial(Integer id) {


		FileMaster fileMaster=fileMasterDao.findById(id);
		CMRIFileDataModel cmriDataModel=new CMRIFileDataModel();

		//File textFile=new File(fileMaster.getTxtfileName());

		processFileSerial(fileMaster);
		//

		//processFilesAsync(fileMaster);

		//FileMaster filedetails =dataReaderThread.processTXTFile(textFile);


		Integer year = 2018;
		Integer monthOfYear = 9;

		if(null!=fileMaster.getTransactionDate())
		{
			Calendar cal = Calendar.getInstance();
			cal.setTime(fileMaster.getTransactionDate());
			year = cal.get(Calendar.YEAR);
			monthOfYear = cal.get(Calendar.MONTH)-1;
		}

		List<DailyTransaction> dailyTransactions=dailyTransactionDao.getDailyTransactionsByMonth(fileMaster.getLocation(),  monthOfYear,year);

		cmriDataModel.setDailyTransactions(dailyTransactions);

		return cmriDataModel;
	}


	public CMRIFileDataModel processRepoFile(Integer id) {


		FileMaster fileMaster=fileMasterDao.findById(id);
		CMRIFileDataModel cmriDataModel=new CMRIFileDataModel();


		processFilesAsync(fileMaster);


		Integer year = 2018;
		Integer monthOfYear = 9;

		if(null!=fileMaster.getTransactionDate())
		{
			Calendar cal = Calendar.getInstance();
			cal.setTime(fileMaster.getTransactionDate());
			year = cal.get(Calendar.YEAR);
			monthOfYear = cal.get(Calendar.MONTH)-1;
		}

		List<DailyTransaction> dailyTransactions=dailyTransactionDao.getDailyTransactionsByMonth(fileMaster.getLocation(),  monthOfYear,year);

		cmriDataModel.setDailyTransactions(dailyTransactions);

		return cmriDataModel;
	}

	public CMRIFileDataModel processRepoFileForTamper(Integer id) {


		FileMaster fileMaster=fileMasterDao.findById(id);
		CMRIFileDataModel cmriDataModel=new CMRIFileDataModel();


		processTamperAsync(fileMaster);


		Integer year = 2018;
		Integer monthOfYear = 9;

		if(null!=fileMaster.getTransactionDate())
		{
			Calendar cal = Calendar.getInstance();
			cal.setTime(fileMaster.getTransactionDate());
			year = cal.get(Calendar.YEAR);
			monthOfYear = cal.get(Calendar.MONTH)-1;
		}

		List<TamperLogTransaction> tamperLogs=tamperLogTransactionDao.findTamperLogByDayAndLocation(fileMaster.getLocation(),null , monthOfYear,year);

		cmriDataModel.setTamperLogs(tamperLogs);

		return cmriDataModel;
	}



	private void processFileSerial(FileMaster textFile) {
		DataReaderThread dataReaderThread= context.getBean(DataReaderThread.class);

		//dataReaderThread.setTextFile(textFile);
		CMRIFileDataModel cmriFileDataModel=new CMRIFileDataModel();
		cmriFileDataModel.setFileMaster(textFile);

		dataReaderThread.extractTransactionDataFromFile(cmriFileDataModel);

		dataReaderThread.saveLoadDailyFileDataToDB(cmriFileDataModel);
	}



	private void processFilesAsync(FileMaster fileDetails) {
		taskExecutor.execute(new Runnable() {

			@Override
			public void run() {

				DataReaderThread dataReaderThread= context.getBean(DataReaderThread.class);

				CMRIFileDataModel cmriFileDataModel=new CMRIFileDataModel();
				cmriFileDataModel.setFileMaster(fileDetails);

				dataReaderThread.extractTransactionDataFromFile(cmriFileDataModel);
				dataReaderThread.saveLoadDailyFileDataToDB(cmriFileDataModel);

			}
		} );
	}

	private void processTamperAsync(FileMaster fileDetails) {
		taskExecutor.execute(new Runnable() {

			@Override
			public void run() {

				DataReaderThread dataReaderThread= context.getBean(DataReaderThread.class);

				CMRIFileDataModel cmriFileDataModel=new CMRIFileDataModel();
				cmriFileDataModel.setFileMaster(fileDetails);

				dataReaderThread.extractTransactionDataFromFile(cmriFileDataModel);
				dataReaderThread.saveTamperData(cmriFileDataModel);

			}
		} );
	}


	public FileModel processZipFiles(Integer month, Integer year2)
	{
		FileFilter filter=new FileFilter();
		filter.setTransactionDateFrom(DateUtil.startDateTimeForDailySurveyRecs(month, year2));
		filter.setTransactionDateTo(DateUtil.endDateTimeForDailySurveyRecs(month, year2));
		filter.setProcessingStatus(EAUtil.FILE_ZIP_EXTRACTED);


		filter.setFileActionStatus(EAUtil.FILE_ACTION__APPROVED_AE);
		List<FileMaster> fileMasters=fileMasterDao.filterFiles(filter);
		for (FileMaster fileMaster : fileMasters) {
			try {
				processRepoFile(fileMaster.getTxnId());
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		FileModel fileModel=new FileModel();
		fileModel.setFilesUploadedDetail(fileMasters);
		return fileModel;
	}

	public FileModel processZipFilesForInstantRegisters(Integer month, Integer year2)
	{
		FileFilter filter=new FileFilter();
		filter.setTransactionDateFrom(DateUtil.startDateTimeForDailySurveyRecs(month, year2));
		filter.setTransactionDateTo(DateUtil.endDateTimeForDailySurveyRecs(month, year2));
		filter.setProcessingStatus(EAUtil.FILE_TXT_PROCESSED);

		List<FileMaster> fileMasters=fileMasterDao.filterFiles(filter);
		for (FileMaster fileMaster : fileMasters) {
			try {
				processRepoFileForInstantRegisters(fileMaster.getTxnId());
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		FileModel fileModel=new FileModel();
		fileModel.setFilesUploadedDetail(fileMasters);
		return fileModel;
	}




	public FileModel getCorruptRepoFiles(Integer month, Integer year) {

		FileFilter filter=new FileFilter();
		if(null!=month&&null!=year)
		{
			filter.setTransactionDateFrom(DateUtil.startDateTimeForDailySurveyRecs(month, year));
			filter.setTransactionDateTo(DateUtil.endDateTimeForDailySurveyRecs(month, year));
		}
		List<FileMaster> fileMasters=fileMasterDao.findCorruptFiles(filter);
		FileModel fileModel=new FileModel();
		fileModel.setFilesUploadedDetail(fileMasters);
		return fileModel;
	}


	public FileModel processCorruptRepoFiles(Integer month, Integer year) {

		FileModel fileModel=getCorruptRepoFiles(month, year);

		for (FileMaster fileMaster : fileModel.getFilesUploadedDetail()) 
		{
			processRepoFile(fileMaster.getTxnId());
		}

		return fileModel;
	}


	public FileModel getPendingRepoFiles(Integer month, Integer year) {

		FileFilter filter=new FileFilter();
		filter.setTransactionDateFrom(DateUtil.startDateTimeForDailySurveyRecs(month, year));
		filter.setTransactionDateTo(DateUtil.endDateTimeForDailySurveyRecs(month, year));
		filter.setProcessingStatus(EAUtil.FILE_ZIP_EXTRACTED);
		filter.setFileActionStatus(EAUtil.FILE_ACTION__APPROVED_AE);
		List<FileMaster> fileMasters=fileMasterDao.filterFiles(filter);
		FileModel fileModel=new FileModel();
		fileModel.setFilesUploadedDetail(fileMasters);
		return fileModel;
	}



	//	public DailyProjectionModel getReport(String criteria,int month, int year) {
	//
	//		DailyProjectionModel dailyProjectionModel=new DailyProjectionModel();
	//		criteria = EAUtil.LOSS_REPORT_CRITERIA_G_T ;
	//		dailyProjectionModel.setCriteria("G-T");
	//		dailyProjectionModel.setDailySurveyTableProjections(dailyTransactionDao.getDailyTransactionsProjection(criteria, month, year));
	//		return dailyProjectionModel ;
	//	}






	@Autowired
	IlossReportService lossReportService;

	public LocationSurveyDataModel getReportTransactions(String locationId, int month, int year) {

		LocationSurveyDataModel locationSurveyDataModel=new LocationSurveyDataModel();
		LocationMaster locationMaster=locationMasterDao.findById(locationId);
		MeterMaster  meterMaster=meterDao.findMeterForMonth(locationId, month, year);
		locationSurveyDataModel.setLocationMaster(locationMaster);
		locationSurveyDataModel.setMeterMaster(meterMaster);
		locationSurveyDataModel.setDailyTransactions(dailyTransactionDao.getDailyTransactionsByMonth(locationMaster, month, year));
		locationSurveyDataModel.setTamperLogTransactions(tamperLogTransactionDao.getTamperLogTransactionsByMonth(locationMaster, month, year));
		return locationSurveyDataModel;
	}

	public LocationSurveyDataModel getTamperReportTransactions(String locationId, int month, int year) {

		LocationSurveyDataModel locationSurveyDataModel=new LocationSurveyDataModel();
		if(locationId!=null) {
			LocationMaster locationMaster=locationMasterDao.findById(locationId);
			MeterMaster  meterMaster=meterDao.findMeterForMonth(locationId, month, year);
			locationSurveyDataModel.setLocationMaster(locationMaster);
			locationSurveyDataModel.setMeterMaster(meterMaster);
			//locationSurveyDataModel.setDailyTransactions(dailyTransactionDao.getDailyTransactionsByMonth(locationMaster, month, year));
			locationSurveyDataModel.setTamperLogTransactions(tamperLogTransactionDao.getTamperLogTransactionsByMonth(locationMaster, month, year));
		}
		else {
			locationSurveyDataModel.setTamperLogTransactions(tamperLogTransactionDao.getTamperLogTransactionsByMonth(null,month, year));
		}
		return locationSurveyDataModel;
	}

	public List<LocationFileModel> getPendingLossReportLocation(Integer month, Integer year) {

		List<LocationFileModel> list =new ArrayList<LocationFileModel>();
		Date startDate=DateUtil.startDateTimeForDailySurveyRecs(month, year);
		Date endDate=DateUtil.endDateTimeForDailySurveyRecs(month, year);
		//locationMasterDao.findPendingLossReportLocations1(startDate,endDate);
		//List <LocationMaster> locations =lossReportDao.getIncompleteDailyEntryLocations(null,startDate,endDate);

		List <LocationMaster> locations =lossReportDao.findPendingLossReportLocations(startDate,endDate);
		FileFilter filter=new FileFilter();
		filter.setTransactionDateFrom(DateUtil.startDateTimeForDailySurveyRecs(month+1, year));
		filter.setTransactionDateTo(DateUtil.endDateTimeForDailySurveyRecs(month+1, year));

		for (LocationMaster locationMaster : locations) {
			LocationFileModel locationFileModel=new LocationFileModel();
			locationFileModel.setLocationMaster(locationMaster);
			filter.setLocation(locationMaster);
			filter.setFileActionStatus(EAUtil.FILE_ACTION__APPROVED_AE);
			List<FileMaster> fileMasters=fileMasterDao.filterFiles(filter);
			locationFileModel.setFileMasters(fileMasters);
			list.add(locationFileModel);
		}

		return list;
	}



	//added by Leevansha
	public CMRIFileDataModel processRepoFileForInstantRegisters(Integer id) {
		// TODO Auto-generated method stub

		FileMaster fileMaster=fileMasterDao.findById(id);
		CMRIFileDataModel cmriDataModel=new CMRIFileDataModel();


		processInstantRegisterAsync(fileMaster);


		Integer year = 2018;
		Integer monthOfYear = 9;

		if(null!=fileMaster.getTransactionDate())
		{
			Calendar cal = Calendar.getInstance();
			cal.setTime(fileMaster.getTransactionDate());
			year = cal.get(Calendar.YEAR);
			monthOfYear = cal.get(Calendar.MONTH)-1;
		}

		InstantRegisters instantRegistersDetails =instantRegistersDao.findInstantRegistersByDayAndLocation(fileMaster.getLocation().getLocationId(),monthOfYear,year);

		cmriDataModel.setInstantRegistersDetails(instantRegistersDetails);

		return cmriDataModel;

	}

	private void processInstantRegisterAsync(FileMaster fileDetails) {
		taskExecutor.execute(new Runnable() {

			@Override
			public void run() {

				DataReaderThread dataReaderThread= context.getBean(DataReaderThread.class);

				CMRIFileDataModel cmriFileDataModel=new CMRIFileDataModel();
				cmriFileDataModel.setFileMaster(fileDetails);

				dataReaderThread.extractTransactionDataFromFile(cmriFileDataModel);
				dataReaderThread.saveInstantRegisterData(cmriFileDataModel);

			}
		} );
	}



}
