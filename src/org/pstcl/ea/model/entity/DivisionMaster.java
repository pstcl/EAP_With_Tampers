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
 * The persistent class for the division_master database table.
 * 
 */
@Entity
@Table(name="division_master")
@NamedQuery(name="DivisionMaster.findAll", query="SELECT d FROM DivisionMaster d")
public class DivisionMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer divCode;
	private String divisionname;
	private CircleMaster circleMaster;
	@JsonIgnore
	private Set<SubstationMaster> substationMasters;

	public DivisionMaster() {
	}


	@Id
	@Column(name="DIV_CODE", unique=true, nullable=false)
	public Integer getDivCode() {
		return this.divCode;
	}

	public void setDivCode(Integer divCode) {
		this.divCode = divCode;
	}


	@Column(length=45)
	public String getDivisionname() {
		return this.divisionname;
	}

	public void setDivisionname(String divisionname) {
		this.divisionname = divisionname;
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


	//bi-directional many-to-one association to SubstationMaster
	@OneToMany(mappedBy="divisionMaster")
	public Set<SubstationMaster> getSubstationMasters() {
		return this.substationMasters;
	}

	public void setSubstationMasters(Set<SubstationMaster> substationMasters) {
		this.substationMasters = substationMasters;
	}

	public SubstationMaster addSubstationMaster(SubstationMaster substationMaster) {
		getSubstationMasters().add(substationMaster);
		substationMaster.setDivisionMaster(this);

		return substationMaster;
	}

	public SubstationMaster removeSubstationMaster(SubstationMaster substationMaster) {
		getSubstationMasters().remove(substationMaster);
		substationMaster.setDivisionMaster(null);

		return substationMaster;
	}

}