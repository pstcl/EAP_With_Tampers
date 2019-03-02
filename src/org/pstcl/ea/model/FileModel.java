package org.pstcl.ea.model;

import java.util.List;

import org.pstcl.ea.model.entity.FileMaster;
import org.pstcl.ea.model.entity.MeterMaster;
import org.springframework.web.multipart.MultipartFile;

public class FileModel {

	private MultipartFile file;

	private MultipartFile[] files;

	private List<MeterMaster> meterList;

	private List<FileMaster> filesUploadedDetail;

	
	public List<MeterMaster> getMeterList() {
		return meterList;
	}

	public void setMeterList(List<MeterMaster> meterList) {
		this.meterList = meterList;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public List<FileMaster> getFilesUploadedDetail() {
		return filesUploadedDetail;
	}

	public void setFilesUploadedDetail(List<FileMaster> filesUploadedDetail) {
		this.filesUploadedDetail = filesUploadedDetail;
	}
}
