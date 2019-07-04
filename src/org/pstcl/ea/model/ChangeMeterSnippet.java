package org.pstcl.ea.model;

import java.util.Date;

import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.MeterMaster;
import org.pstcl.ea.model.mapping.MeterLocationMap;
import org.springframework.format.annotation.DateTimeFormat;


public class ChangeMeterSnippet {

	//values that will be passed as such
	private MeterLocationMap oldMeterLocationMap ;
	
	
	private MeterMaster meterMaster;
	
	//new values

	private LocationMaster location;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date startDate;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date endDate;
	private String error;
	private String setNewMeter;
	
	
	
	public ChangeMeterSnippet() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	
	
	
	public String getSetNewMeter() {
		return setNewMeter;
	}







	public void setSetNewMeter(String setNewMeter) {
		this.setNewMeter = setNewMeter;
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
	public MeterMaster getMeterMaster() {
		return meterMaster;
	}
	public void setMeterMaster(MeterMaster meterMaster) {
		this.meterMaster = meterMaster;
	}
	public MeterLocationMap getOldMeterLocationMap() {
		return oldMeterLocationMap;
	}
	
	public void setOldMeterLocationMap(MeterLocationMap oldMeterLocationMapId) {
		this.oldMeterLocationMap = oldMeterLocationMapId;
	}
	public void setOldValues(MeterLocationMap meterDetails) {
		// TODO Auto-generated method stub
		this.oldMeterLocationMap=meterDetails;
		this.meterMaster=meterDetails.getMeterMaster();
	}
	public LocationMaster getLocation() {
		return location;
	}

	public void setLocation(LocationMaster location) {
		this.location = location;
	}







	public String getError() {
		return error;
	}







	public void setError(String error) {
		this.error = error;
	}







	

}
