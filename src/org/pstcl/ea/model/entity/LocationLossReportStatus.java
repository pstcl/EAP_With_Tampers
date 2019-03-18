package org.pstcl.ea.model.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="LOSS_REPORT_LOCATIONS")
@NamedQuery(name="LocationLossReportStatus.findAll", query="SELECT m FROM LocationLossReportStatus m")
public class LocationLossReportStatus {
	
	
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	
	private LocationMaster locationMaster;
	
	@Column(length=45,name="LOSS_REPORT_CRITERIA")
	private String lossReportCriteria;

	@Column(name = "LOSS_REPORT_INCLUSION")
	private Integer lossReportInclusion;
	
	public String getLossReportCriteria() {
		return lossReportCriteria;
	}

	public void setLossReportCriteria(String lossReportCriteria) {
		this.lossReportCriteria = lossReportCriteria;
	}


	public void setLossReportInclusion(Integer lossReportInclusion) {
		this.lossReportInclusion = lossReportInclusion;
	}



	public Integer getLossReportInclusion() {
		return lossReportInclusion;
	}
	
	private Date startDate;
	private Date endDate;
	
	
	public Date getEndDate() {
		return endDate;
	}
	public int getId() {
		return id;
	}
	public LocationMaster getLocationMaster() {
		return locationMaster;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	public void setLocationMaster(LocationMaster locationMaster) {
		this.locationMaster = locationMaster;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
}
