package org.pstcl.ea.dao;

import java.util.List;

import org.pstcl.ea.model.EAModel;
import org.pstcl.ea.model.entity.CircleMaster;
import org.pstcl.ea.model.entity.DivisionMaster;
import org.pstcl.ea.model.entity.SubstationMaster;

public interface SubstationUtilityDao {
	
	List<CircleMaster> listCircles(EAModel eaFilter);
	List<DivisionMaster> listDivisions(EAModel eaFilter);
	List<SubstationMaster> listSubstations(EAModel eaFilter);
	
	
	CircleMaster findCircleByID(Integer code);
	DivisionMaster findDivisionByID(Integer code);
	SubstationMaster findSubstationByID(Integer code);

}
