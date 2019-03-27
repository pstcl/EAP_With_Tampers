package org.pstcl.ea.model;

import java.util.Date;

import org.pstcl.ea.model.entity.CircleMaster;
import org.pstcl.ea.model.entity.DivisionMaster;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.MeterLocationMap;
import org.pstcl.ea.model.entity.MeterMaster;
import org.pstcl.ea.model.entity.SubstationMaster;

public class ChangeMeterSnippet {

	//values that will be passed as such
	private MeterLocationMap oldMeterLocationMap;
	
	
	private String crcode;
    private MeterMaster meterMaster;
	
	//new values
	private CircleMaster circle;
	private DivisionMaster division;
	private SubstationMaster substation;
	private LocationMaster location;
	private Date startDate;
	private Date endDate;
	
	
	
	
	
	public ChangeMeterSnippet() {
		super();
		// TODO Auto-generated constructor stub
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







	public CircleMaster getCircle() {
		return circle;
	}







	public void setCircle(CircleMaster circle) {
		this.circle = circle;
	}







	public DivisionMaster getDivision() {
		return division;
	}







	public void setDivision(DivisionMaster division) {
		this.division = division;
	}







	public SubstationMaster getSubstation() {
		return substation;
	}







	public void setSubstation(SubstationMaster substation) {
		this.substation = substation;
	}







	public LocationMaster getLocation() {
		return location;
	}







	public void setLocation(LocationMaster location) {
		this.location = location;
	}







	public String getCrcode() {
		return crcode;
	}







	public void setCrcode(String crcode) {
		this.crcode = crcode;
	}
	
	
	
}
