package org.pstcl.ea.model;

import java.util.Date;
import java.util.List;

import org.pstcl.ea.model.entity.DailyTransaction;
import org.pstcl.ea.model.entity.LoadSurveyTransaction;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.MeterMaster;

public class LocationSurveyDataModel {
	
	private List<DailyTransaction> dailyTransactions;
	
	
	private Date endDate;
	private List<LoadSurveyTransaction> loadSurveyTransactions;
	private LocationMaster locationMaster;
	private MeterMaster meterMaster;
	
	private Integer month;
	private Date startDate;
	private Integer year;
	public List<DailyTransaction> getDailyTransactions() {
		return dailyTransactions;
	}
	public Date getEndDate() {
		return endDate;
	}
	public List<LoadSurveyTransaction> getLoadSurveyTransactions() {
		return loadSurveyTransactions;
	}
	public LocationMaster getLocationMaster() {
		return locationMaster;
	}
	public Integer getMonth() {
		return month;
	}
	public Date getStartDate() {
		return startDate;
	}


	public Integer getYear() {
		return year;
	}
	public void setDailyTransactions(List<DailyTransaction> dailyTransactions) {
		this.dailyTransactions = dailyTransactions;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setLoadSurveyTransactions(List<LoadSurveyTransaction> loadSurveyTransactions) {
		this.loadSurveyTransactions = loadSurveyTransactions;
	}
	public void setLocationMaster(LocationMaster locationMaster) {
		this.locationMaster = locationMaster;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public MeterMaster getMeterMaster() {
		return meterMaster;
	}
	public void setMeterMaster(MeterMaster meterMaster) {
		this.meterMaster = meterMaster;
	}

}
