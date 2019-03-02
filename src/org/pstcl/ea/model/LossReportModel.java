package org.pstcl.ea.model;

import java.util.Date;
import java.util.List;

import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.LossReportEntity;

public class LossReportModel {
	private String criteria;
	private Date startDate;
	private Date endDate;
	private LossReportEntity sumEntity;

	private List<LocationMaster> manualEntryLocations;
	

	private List<LocationMaster> totalLocations;
	
	private Integer pointsCountDataAvailable;
	private List<LossReportEntity> lossReportEntities;
	
	public List<LossReportEntity> getLossReportEntities() {
		return lossReportEntities;
	}
	public void setLossReportEntities(List<LossReportEntity> lossReportEntities) {
		this.lossReportEntities = lossReportEntities;
	}
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
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
	public LossReportEntity getSumEntity() {
		return sumEntity;
	}
	public void setSumEntity(LossReportEntity sumEntity) {
		this.sumEntity = sumEntity;
	}
	
	public List<LocationMaster> getManualEntryLocations() {
		return manualEntryLocations;
	}
	public void setManualEntryLocations(List<LocationMaster> manualEntryLocations) {
		this.manualEntryLocations = manualEntryLocations;
	}
	public List<LocationMaster> getTotalLocations() {
		return totalLocations;
	}
	public void setTotalLocations(List<LocationMaster> totalLocations) {
		this.totalLocations = totalLocations;
	}
	public Integer getPointsCountDataAvailable() {
		return pointsCountDataAvailable;
	}
	public void setPointsCountDataAvailable(Integer pointsCountDataAvailable) {
		this.pointsCountDataAvailable = pointsCountDataAvailable;
	}
	
	
	

}
