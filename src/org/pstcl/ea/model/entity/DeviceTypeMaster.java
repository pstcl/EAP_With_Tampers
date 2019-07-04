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
 * The persistent class for the device_type_master database table.
 * 
 */
@Entity
@Table(name="device_type_master")
@NamedQuery(name="DeviceTypeMaster.findAll", query="SELECT d FROM DeviceTypeMaster d")
public class DeviceTypeMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	private String deviceId;
	private String deviceType;
	
	@JsonIgnore
	private Set<LocationMaster> locationMasters;

	public DeviceTypeMaster() {
	}


	@Id
	@Column(name="DEVICE_ID", unique=true, nullable=false, length=45)
	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


	@Column(name="DEVICE_TYPE", length=45)
	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}


	//bi-directional many-to-one association to LocationMaster
	@OneToMany(mappedBy="deviceTypeMaster")
	public Set<LocationMaster> getLocationMasters() {
		return this.locationMasters;
	}

	public void setLocationMasters(Set<LocationMaster> locationMasters) {
		this.locationMasters = locationMasters;
	}

	public LocationMaster addLocationMaster(LocationMaster locationMaster) {
		getLocationMasters().add(locationMaster);
		locationMaster.setDeviceTypeMaster(this);

		return locationMaster;
	}

	public LocationMaster removeLocationMaster(LocationMaster locationMaster) {
		getLocationMasters().remove(locationMaster);
		locationMaster.setDeviceTypeMaster(null);

		return locationMaster;
	}

}