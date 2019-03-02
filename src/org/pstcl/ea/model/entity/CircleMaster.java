package org.pstcl.ea.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;


/**
 * The persistent class for the circle_master database table.
 * 
 */
@Entity
@Table(name="circle_master")
@NamedQuery(name="CircleMaster.findAll", query="SELECT c FROM CircleMaster c")
public class CircleMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer crCode;
	private String circleName;
	@JsonIgnore
	private Set<DivisionMaster> divisionMasters;
	@JsonIgnore
	private Set<SubstationMaster> substationMasters;

	public CircleMaster() {
	}


	@Id
	@Column(name="CR_CODE", unique=true, nullable=false)
	public Integer getCrCode() {
		return this.crCode;
	}

	public void setCrCode(Integer crCode) {
		this.crCode = crCode;
	}


	@Column(length=45)
	public String getCircleName() {
		return this.circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}


	//bi-directional many-to-one association to DivisionMaster
	@OneToMany(mappedBy="circleMaster")
	public Set<DivisionMaster> getDivisionMasters() {
		return this.divisionMasters;
	}

	public void setDivisionMasters(Set<DivisionMaster> divisionMasters) {
		this.divisionMasters = divisionMasters;
	}

	public DivisionMaster addDivisionMaster(DivisionMaster divisionMaster) {
		getDivisionMasters().add(divisionMaster);
		divisionMaster.setCircleMaster(this);

		return divisionMaster;
	}

	public DivisionMaster removeDivisionMaster(DivisionMaster divisionMaster) {
		getDivisionMasters().remove(divisionMaster);
		divisionMaster.setCircleMaster(null);

		return divisionMaster;
	}


	//bi-directional many-to-one association to SubstationMaster
	@OneToMany(mappedBy="circleMaster")
	public Set<SubstationMaster> getSubstationMasters() {
		return this.substationMasters;
	}

	public void setSubstationMasters(Set<SubstationMaster> substationMasters) {
		this.substationMasters = substationMasters;
	}

	public SubstationMaster addSubstationMaster(SubstationMaster substationMaster) {
		getSubstationMasters().add(substationMaster);
		substationMaster.setCircleMaster(this);

		return substationMaster;
	}

	public SubstationMaster removeSubstationMaster(SubstationMaster substationMaster) {
		getSubstationMasters().remove(substationMaster);
		substationMaster.setCircleMaster(null);

		return substationMaster;
	}

}