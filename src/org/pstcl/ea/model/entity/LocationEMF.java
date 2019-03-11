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
@Table(name="location_mf_map")
@NamedQuery(name="LocationMFMap.findAll", query="SELECT m FROM LocationMFMap m")
public class LocationEMF {
	
	
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	
	private LocationMaster locationMaster;
	
	@Column(precision=14,scale=2)
	private BigDecimal externalMF;
	

	private Date startDate;
	private Date endDate;
	
	
	public Date getEndDate() {
		return endDate;
	}
	public BigDecimal getExternalMF() {
		return externalMF;
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
	public void setExternalMF(BigDecimal externalMF) {
		this.externalMF = externalMF;
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
