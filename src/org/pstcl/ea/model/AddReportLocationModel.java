package org.pstcl.ea.model;

import java.util.List;

import org.pstcl.ea.model.entity.LocationMaster;

public class AddReportLocationModel {

	private int month;

	private int year;
	
	private List<LocationMaster> locations;
	
	private List<LocationMaster> addingLocations;

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<LocationMaster> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationMaster> locations) {
		this.locations = locations;
	}

	public List<LocationMaster> getAddingLocations() {
		return addingLocations;
	}

	public void setAddingLocations(List<LocationMaster> addingLocations) {
		this.addingLocations = addingLocations;
	}

}
