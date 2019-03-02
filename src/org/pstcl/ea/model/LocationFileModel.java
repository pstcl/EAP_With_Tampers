package org.pstcl.ea.model;

import java.util.Date;
import java.util.List;

import org.pstcl.ea.model.entity.FileMaster;
import org.pstcl.ea.model.entity.LocationMaster;

public class LocationFileModel {
	
	private LocationMaster locationMaster;
	private List<FileMaster> fileMasters;
	private Date startDate;
	private Date endDate;
	
	
	public LocationMaster getLocationMaster() {
		return locationMaster;
	}
	public void setLocationMaster(LocationMaster locationMaster) {
		this.locationMaster = locationMaster;
	}
	public List<FileMaster> getFileMasters() {
		return fileMasters;
	}
	public void setFileMasters(List<FileMaster> fileMasters) {
		this.fileMasters = fileMasters;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
