package org.pstcl.ea.model.entity;

import java.io.Serializable;

import java.sql.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * The persistent class for the meter_master database table.
 * 
 */
@Entity
@Table(name="meter_master")
@NamedQuery(name="MeterMaster.findAll", query="SELECT m FROM MeterMaster m")
public class MeterMaster implements Serializable {
	

	private static final long serialVersionUID = 1L; 	
	private String CTAccuracy; 	
	private Date deactivateddate;	
	
	
// externalCTRation externalMF externalPTRation

	//added in august list
	private Integer gridLossFactor;


	private Integer id;


	private Date installedDate;


	private String internalCTRatio;


	private Double internalMF;


	private String internalPTRatio ;

	private LocationMaster locationMaster;
	private String meterCategory;
	private String meterMake;
	private String meterSrNo;
	private String meterType;
	private String PTAccuracy;
	public MeterMaster() {
	}
	@Column(length=45)
	public String getCTAccuracy() {
		return this.CTAccuracy;
	}
	@Column(name="deactivationdate")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getDeactivateddate() {
		return this.deactivateddate;
	}
	
	@Column
	public Integer getGridLossFactor() {
		return gridLossFactor;
	}
	@Column(nullable=false)
	public Integer getId() {
		return this.id;
	}



	@Column(name="installationDate")
	@DateTimeFormat( pattern = "dd/MM/yyyy")
	public Date getInstalledDate() {
		return this.installedDate;
	}


	@Column
	public String getInternalCTRatio() {
		return internalCTRatio;
	}

	@Column
	public Double getInternalMF() {
		return internalMF;
	}


	@Column
	public String getInternalPTRatio() {
		return internalPTRatio;
	}

	//bi-directional one-to-one association to LocationMaster
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="LocationId", unique=true)
	public LocationMaster getLocationMaster() {
		return this.locationMaster;
	}


	@Column(length=45)
	public String getMeterCategory() {
		return this.meterCategory;
	}

	@Column(length=45)
	public String getMeterMake() {
		return this.meterMake;
	}


	@Id
	@Column(unique=true, nullable=false, length=45)
	public String getMeterSrNo() {
		return this.meterSrNo;
	}

	@Column(length=45)
	public String getMeterType() {
		return this.meterType;
	}


	@Column(length=45)
	public String getPTAccuracy() {
		return this.PTAccuracy;
	}

	public void setCTAccuracy(String CTAccuracy) {
		this.CTAccuracy = CTAccuracy;
	}


	public void setDeactivateddate(Date deactivateddate) {
		this.deactivateddate = deactivateddate;
	}

	


	public void setGridLossFactor(Integer gridLossFactor) {
		this.gridLossFactor = gridLossFactor;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public void setInstalledDate(Date installedDate) {
		this.installedDate = installedDate;
	}

	public void setInternalCTRatio(String internalCTRatio) {
		this.internalCTRatio = internalCTRatio;
	}


	public void setInternalMF(Double internalMF) {
		this.internalMF = internalMF;
	}
	//Below are the fields in JULY list

	public void setInternalPTRatio(String internalPTRatio) {
		this.internalPTRatio = internalPTRatio;
	}


	public void setLocationMaster(LocationMaster locationMaster) {
		this.locationMaster = locationMaster;
	}

	public void setMeterCategory(String meterCategory) {
		this.meterCategory = meterCategory;
	}


	public void setMeterMake(String meterMake) {
		this.meterMake = meterMake;
	}

	public void setMeterSrNo(String meterSrNo) {
		this.meterSrNo = meterSrNo;
	}


	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	public void setPTAccuracy(String PTAccuracy) {
		this.PTAccuracy = PTAccuracy;
	}

}