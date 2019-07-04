package org.pstcl.ea.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.io.FilenameUtils;
import org.pstcl.ea.model.entity.FileMaster;
import org.pstcl.ea.service.impl.parallel.TimeoutProcessKiller;
import org.pstcl.ea.util.EAUtil;
import org.springframework.util.FileCopyUtils;

public abstract class FileServiceUtil extends EnergyAccountsService {

	public FileServiceUtil() {
		super();
	}
	


	
	public FileMaster extractDecodeZip(File zipUploaded) {

		FileMaster fileDetails = new FileMaster();

		fileDetails.setDailyRecordCount(-1);
		fileDetails.setSurveyRecordCount(-1);
		fileDetails.setProcessingStatus(EAUtil.FILE_ERROR);

		String oldFileName = FilenameUtils.getBaseName(zipUploaded.getName()) + ".ZIP";// .getOriginalFilename();

		String tempDirectory = EAUtil.TEMP_FILE_OPEN_DIR + FilenameUtils.getBaseName(zipUploaded.getName());

		String exeBaseDir = EAUtil.CRMI_EXE_FILE_REPOSITORY;

		File tempDirectoryForExploding = new File(tempDirectory);
		if (!tempDirectoryForExploding.exists()) {
			tempDirectoryForExploding.mkdirs();
			// If you require it to make the entire directory path including parents,
			// use directory.mkdirs(); here instead.
		}

		// File zipUploaded = new File(EAUtil.CRMI_ZIP_FILE_REPOSITORY + oldFileName);
		File tempZipfile = new File(tempDirectory, oldFileName);

		File tempDecodeExe = new File(tempDirectory, "DECODE.EXE");
		File tempZzExe = new File(tempDirectory, "7z.exe");

		File decodeExe = new File(exeBaseDir, "DECODE.EXE");
		File zzExe = new File(exeBaseDir, "7z.exe");

		try {
			FileCopyUtils.copy(zipUploaded, tempZipfile);
			FileCopyUtils.copy(decodeExe, tempDecodeExe);
			FileCopyUtils.copy(zzExe, tempZzExe);
			fileDetails.setZipfileName(zipUploaded.getAbsolutePath());
			fileDetails.setUserfileName(zipUploaded.getName());
			fileDetails.setProcessingStatus(EAUtil.FILE_ZIP_EXTRACTED);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String command = "cmd.exe /C start "+EAUtil.TEMP_FILE_OPEN_DIR+"DECODE.exe";
		String command = "cmd.exe /C  \"" + tempDirectory + "/DECODE.exe\"";
		//
		// Running the above command

		// Process process = Runtime.getRuntime().exec( "cmd.exe /C start
		// C:/Users/123/Desktop/nlp.exe" );

		Runtime run = Runtime.getRuntime();
		try {
			Process proc = run.exec(command, null, tempDirectoryForExploding);

			// single shared timer instance
			Timer t = new Timer();

			TimerTask killer = new TimeoutProcessKiller(proc);
			t.schedule(killer, 30000);



			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));


			// read the output from the command
		//	System.out.println("Here is the standard output of the command:\n");
			String consoleInputLine = "";
			String consoleErrorLine = "";
			while (consoleInputLine!=null&&consoleErrorLine!=null) {
				
				
				//consoleErrorLine = stdError.readLine();
				if(consoleInputLine.contains("Error")||consoleInputLine.contains("No files to process"))
				{
					proc.destroy();
					fileDetails.setProcessingStatus(EAUtil.FILE_ERROR);
					return fileDetails;
				}
		//		System.out.println("Input:-"+consoleInputLine+"\nError:"+consoleErrorLine);
				consoleInputLine = stdInput.readLine();
			}
//			while (() {
//				proc.destroy();
//				fileDetails.setProcessingStatus(EAUtil.FILE_ERROR);
//				return fileDetails;
//			}
			// ... read output stream as before ...
			int returnVal = proc.waitFor();
			killer.cancel();


		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	System.out.println("TEST");
		String txtFileExtractedName = FilenameUtils.getBaseName(tempZipfile.getName()) + ".txt";

		File tempTextFile = new File(tempDirectoryForExploding, txtFileExtractedName);


		//Try finding txt file with different name from Zip
		if (!tempTextFile.exists()) {
			try {

				File[] filesExtracted = tempDirectoryForExploding.listFiles();
				for (File file : filesExtracted) {
					if(FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("txt"))
					{
						tempTextFile=file;
					}
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}


		//When txt file with different name from Zip also doesnt exist
		if (!tempTextFile.exists()) {
			fileDetails.setProcessingStatus(EAUtil.FILE_ERROR);
		}

		Calendar cal = Calendar.getInstance();
		String uploadDate = new SimpleDateFormat("MMM_yyyy_dd").format(cal.getTime());


		File txtRepository = new File(EAUtil.CRMI_TXT_FILE_REPOSITORY + uploadDate+ "/");


		if (!txtRepository.exists()) {
			txtRepository.mkdirs();
		}

		File txtfile = new File(txtRepository, FilenameUtils.getBaseName(zipUploaded.getName()) + ".txt");

		try {
			FileCopyUtils.copy(tempTextFile, txtfile);
			fileDetails.setTxtfileName(txtfile.getAbsolutePath());

			fileDetails.setProcessingStatus(EAUtil.FILE_ZIP_EXTRACTED);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			File toDeleteZipFile = tempZipfile;
			File toDeleteBinFile = new File(tempDirectoryForExploding,
					FilenameUtils.getBaseName(tempZipfile.getName()) + ".bin");
			File toDeleteIniFile = new File(tempDirectoryForExploding,
					FilenameUtils.getBaseName(tempZipfile.getName()) + ".ini");
			File toDeleteTxtFile = tempTextFile;

			boolean zipDeleted = Files.deleteIfExists(toDeleteZipFile.toPath());

			boolean binDeleted = Files.deleteIfExists(toDeleteBinFile.toPath());

			boolean iniDeleted = Files.deleteIfExists(toDeleteIniFile.toPath());

			boolean txtDeleted = Files.deleteIfExists(toDeleteTxtFile.toPath());

//			System.out.println("zipDeleted" + zipDeleted);
//
//			System.out.println("binDeleted" + binDeleted);
//
//			System.out.println("iniDeleted" + iniDeleted);
//
//			System.out.println("txtDeleted" + txtDeleted);
			deleteFolder(tempDirectoryForExploding);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileDetails;
	}

	public static boolean deleteFolder(File dir) {

		if (dir.isDirectory()) {
			File[] children = dir.listFiles();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteFolder(children[i]);
				if (!success) {
					return false;
				}
			}
		} // either file or an empty directory
		// System.out.println("removing file or directory : " + dir.getName());
		return dir.delete();
	}

	

	
}