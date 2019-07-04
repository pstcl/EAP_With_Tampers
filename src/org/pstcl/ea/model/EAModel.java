package org.pstcl.ea.model;

import org.pstcl.ea.model.entity.CircleMaster;
import org.pstcl.ea.model.entity.DivisionMaster;
import org.pstcl.ea.model.entity.SubstationMaster;

public class EAModel {
	private CircleMaster circle;
	private DivisionMaster division;
	private SubstationMaster substation;
	
	
	public CircleMaster getCircle() {
		return circle;
	}
	public void setCircle(CircleMaster circle) {
		this.circle = circle;
	}
	public DivisionMaster getDivision() {
		return division;
	}
	public void setDivision(DivisionMaster division) {
		this.division = division;
	}
	public SubstationMaster getSubstation() {
		return substation;
	}
	public void setSubstation(SubstationMaster substation) {
		this.substation = substation;
	}
	
}
