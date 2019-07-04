package org.pstcl.ea.model;

import java.util.List;

import org.pstcl.ea.model.entity.SubstationMaster;
import org.pstcl.ea.model.mapping.MeterLocationMap;

public class SubstationMeter {

	private SubstationMaster substationMaster;
	private List<MeterLocationMap> mtrLocMap;
	public List<MeterLocationMap> getMtrLocMap() {
		return mtrLocMap;
	}
	public void setMtrLocMap(List<MeterLocationMap> mtrLocMap) {
		this.mtrLocMap = mtrLocMap;
	}
	public SubstationMaster getSubstationMaster() {
		return substationMaster;
	}
	public void setSubstationMaster(SubstationMaster substationMaster) {
		this.substationMaster = substationMaster;
	}
	
}
