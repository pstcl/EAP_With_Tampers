package org.pstcl.ea.model;

import java.util.List;

import org.pstcl.ea.model.entity.BoundaryTypeMaster;
import org.pstcl.ea.model.entity.DeviceTypeMaster;
import org.pstcl.ea.model.entity.FeederMaster;

public class LocationMasterList {

	
	private List<BoundaryTypeMaster> boundaryTypeMaster;
	private List<String> utiltiyName;
	private List<FeederMaster> feederMaster;
	private List<String> voltageLevel;
	private List<DeviceTypeMaster> deviceTypeMaster;
	
	private List<String> meterMake;
	private List<String> meterCategory;
	private List<String> meterType;
	
	public List<String> getMeterMake() {
		return meterMake;
	}
	public void setMeterMake(List<String> meterMake) {
		this.meterMake = meterMake;
	}
	public List<String> getMeterCategory() {
		return meterCategory;
	}
	public void setMeterCategory(List<String> meterCategory) {
		this.meterCategory = meterCategory;
	}
	public List<String> getMeterType() {
		return meterType;
	}
	public void setMeterType(List<String> meterType) {
		this.meterType = meterType;
	}
	public List<BoundaryTypeMaster> getBoundaryTypeMaster() {
		return boundaryTypeMaster;
	}
	public void setBoundaryTypeMaster(List<BoundaryTypeMaster> boundaryTypeMaster) {
		this.boundaryTypeMaster = boundaryTypeMaster;
	}
	public List<String> getUtiltiyName() {
		return utiltiyName;
	}
	public void setUtiltiyName(List<String> utiltiyName) {
		this.utiltiyName = utiltiyName;
	}
	public List<FeederMaster> getFeederMaster() {
		return feederMaster;
	}
	public void setFeederMaster(List<FeederMaster> feederMaster) {
		this.feederMaster = feederMaster;
	}
	public List<String> getVoltageLevel() {
		return voltageLevel;
	}
	public void setVoltageLevel(List<String> voltageLevel) {
		this.voltageLevel = voltageLevel;
	}
	public List<DeviceTypeMaster> getDeviceTypeMaster() {
		return deviceTypeMaster;
	}
	public void setDeviceTypeMaster(List<DeviceTypeMaster> deviceTypeMaster) {
		this.deviceTypeMaster = deviceTypeMaster;
	}
}
