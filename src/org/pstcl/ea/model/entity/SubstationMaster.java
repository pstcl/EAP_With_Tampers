package org.pstcl.ea.model.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the substation_master database table.
 * 
 */
@Entity
@Table(name="substation_master")
@NamedQuery(name="SubstationMaster.findAll", query="SELECT s FROM SubstationMaster s")
public class SubstationMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer ssCode;
	private String stationName;
	@JsonIgnore
	private Set<LocationMaster> locationMasters;

	private CircleMaster circleMaster;
	private DivisionMaster divisionMaster;
	private Integer voltageLevel;
	
	private String substationContactNo;
	@Column(name = "SS_CONTACT_NO")
	public String getSubstationContactNo() {
		return substationContactNo;
	}

	public void setSubstationContactNo(String substationContactNo) {
		this.substationContactNo = substationContactNo;
	}
	public SubstationMaster() {
	}


	@Id
	@Column(name="SS_CODE", unique=true, nullable=false)
	public Integer getSsCode() {
		return this.ssCode;
	}

	public void setSsCode(Integer ssCode) {
		this.ssCode = ssCode;
	}


	@Column(length=45)
	public String getStationName() {
		return this.stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}


	//bi-directional many-to-one association to LocationMaster
	@OneToMany(mappedBy="substationMaster",fetch=FetchType.EAGER)
	public Set<LocationMaster> getLocationMasters() {
		return this.locationMasters;
	}

	public void setLocationMasters(Set<LocationMaster> locationMasters) {
		this.locationMasters = locationMasters;
	}

	public LocationMaster addLocationMaster(LocationMaster locationMaster) {
		getLocationMasters().add(locationMaster);
		locationMaster.setSubstationMaster(this);

		return locationMaster;
	}

	public LocationMaster removeLocationMaster(LocationMaster locationMaster) {
		getLocationMasters().remove(locationMaster);
		locationMaster.setSubstationMaster(null);

		return locationMaster;
	}


	//bi-directional many-to-one association to CircleMaster
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CR_CODE")
	public CircleMaster getCircleMaster() {
		return this.circleMaster;
	}

	public void setCircleMaster(CircleMaster circleMaster) {
		this.circleMaster = circleMaster;
	}


	//bi-directional many-to-one association to DivisionMaster
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="DIV_CODE")
	public DivisionMaster getDivisionMaster() {
		return this.divisionMaster;
	}

	public void setDivisionMaster(DivisionMaster divisionMaster) {
		this.divisionMaster = divisionMaster;
	}


	@Column(name="voltage_level")
	public Integer getVoltageLevel() {
		return voltageLevel;
	}


	public void setVoltageLevel(Integer voltageLevel) {
		this.voltageLevel = voltageLevel;
	}



}