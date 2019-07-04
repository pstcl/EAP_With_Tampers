package org.pstcl.ea.model.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the feeder_master database table.
 * 
 */
@Entity
@Table(name="feeder_master")
@NamedQuery(name="FeederMaster.findAll", query="SELECT f FROM FeederMaster f")
public class FeederMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String feederName;
	@JsonIgnore
	private Set<LocationMaster> locationMasters;

	public FeederMaster() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(length=45)
	public String getFeederName() {
		return this.feederName;
	}

	public void setFeederName(String feederName) {
		this.feederName = feederName;
	}


	//bi-directional many-to-one association to LocationMaster
	@JsonIgnore
	@OneToMany(mappedBy="feederMaster")
	public Set<LocationMaster> getLocationMasters() {
		return this.locationMasters;
	}

	public void setLocationMasters(Set<LocationMaster> locationMasters) {
		this.locationMasters = locationMasters;
	}

	public LocationMaster addLocationMaster(LocationMaster locationMaster) {
		getLocationMasters().add(locationMaster);
		locationMaster.setFeederMaster(this);

		return locationMaster;
	}

	public LocationMaster removeLocationMaster(LocationMaster locationMaster) {
		getLocationMasters().remove(locationMaster);
		locationMaster.setFeederMaster(null);

		return locationMaster;
	}

}