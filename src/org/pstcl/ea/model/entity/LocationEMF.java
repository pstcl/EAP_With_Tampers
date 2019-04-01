package org.pstcl.ea.model.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.pstcl.ea.util.EAUtil;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="location_mf_map")
@NamedQuery(name="LocationEMF.findAll", query="SELECT m FROM LocationEMF m")
public class LocationEMF {
	
	
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@JsonIgnore
	@ManyToOne 
	@JoinColumn(name = "LOC_ID")
	private LocationMaster locationMaster;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_MF,scale=EAUtil.DECIMAL_SCALE_MF)
	private BigDecimal externalMF;
	

	private Integer netWHSign;


	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date startDate;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
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
	public Integer getNetWHSign() {
		return netWHSign;
	}
	public void setNetWHSign(Integer netWHSign) {
		this.netWHSign = netWHSign;
	}
	
}
