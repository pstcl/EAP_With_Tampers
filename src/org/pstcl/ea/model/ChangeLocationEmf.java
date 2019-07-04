package org.pstcl.ea.model;

import java.util.Date;

import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.mapping.LocationMFMap;
import org.springframework.format.annotation.DateTimeFormat;

public class ChangeLocationEmf {

	private LocationMaster locationMaster;
	private LocationMFMap oldLocationEmf;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date endDate;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date startDate;
    private String externalMF;
	private String setNewEmf;
	private Integer netWHSign;
	
	public LocationMFMap getOldLocationEmf() {
		return oldLocationEmf;
	}


	public void setOldLocationEmf(LocationMFMap oldLocationEmf) {
		this.oldLocationEmf = oldLocationEmf;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public String getExternalMF() {
		return externalMF;
	}


	public void setExternalMF(String externalMF) {
		this.externalMF = externalMF;
	}


	public Integer getNetWHSign() {
		return netWHSign;
	}


	public void setNetWHSign(Integer netWHSign) {
		this.netWHSign = netWHSign;
	}


	public LocationMaster getLocationMaster() {
		return locationMaster;
	}


	public void setLocationMaster(LocationMaster locationMaster) {
		this.locationMaster = locationMaster;
	}


	public String getSetNewEmf() {
		return setNewEmf;
	}


	public void setSetNewEmf(String setNewEmf) {
		this.setNewEmf = setNewEmf;
	}


}
