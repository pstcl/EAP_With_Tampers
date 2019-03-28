package org.pstcl.model;

import java.util.List;

import org.pstcl.ea.model.EAModel;
import org.pstcl.ea.model.entity.CircleMaster;
import org.pstcl.ea.model.entity.DivisionMaster;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.SubstationMaster;

public class FilterModel  {

	private CircleMaster selectedCircle;
	private DivisionMaster selectedDivision;
	private SubstationMaster selectedSubstation;
	private LocationMaster selectedLocation;
	
	public LocationMaster getSelectedLocation() {
		return selectedLocation;
	}
	public void setSelectedLocation(LocationMaster selectedLocation) {
		this.selectedLocation = selectedLocation;
	}
	public List<LocationMaster> getLocationList() {
		return locationList;
	}
	public void setLocationList(List<LocationMaster> locationList) {
		this.locationList = locationList;
	}
	private List< CircleMaster >  circleList;
	private List< DivisionMaster >  divisionList;
	private List< SubstationMaster >  substationList;
	private List <LocationMaster> locationList;
	
	public CircleMaster getSelectedCircle() {
		return selectedCircle;
	}
	public void setSelectedCircle(CircleMaster selectedCircle) {
		
		this.selectedCircle = selectedCircle;
	}
	public DivisionMaster getSelectedDivision() {
		return selectedDivision;
	}
	public void setSelectedDivision(DivisionMaster selectedDivision) {
		this.selectedDivision = selectedDivision;
	}
	public SubstationMaster getSelectedSubstation() {
		return selectedSubstation;
	}
	public void setSelectedSubstation(SubstationMaster selectedSubstation) {
		this.selectedSubstation = selectedSubstation;
	}
	public List<CircleMaster> getCircleList() {
		return circleList;
	}
	public void setCircleList(List<CircleMaster> circleList) {
		this.circleList = circleList;
	}
	public List<DivisionMaster> getDivisionList() {
		return divisionList;
	}
	public void setDivisionList(List<DivisionMaster> divisionList) {
		this.divisionList = divisionList;
	}
	public List<SubstationMaster> getSubstationList() {
		return substationList;
	}
	public void setSubstationList(List<SubstationMaster> substationList) {
		this.substationList = substationList;
	}
	
	
	
	
	
	
}
