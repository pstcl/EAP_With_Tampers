package org.pstcl.ea.dao;

import java.util.List;

import org.pstcl.ea.model.EAModel;
import org.pstcl.ea.model.entity.CircleMaster;
import org.pstcl.ea.model.entity.DivisionMaster;
import org.pstcl.ea.model.entity.SubstationMaster;
import org.pstcl.model.FilterModel;

public interface SubstationUtilityDao {
	
	List<CircleMaster> listCircles(EAModel eaFilter);
	List<DivisionMaster> listDivisions(EAModel eaFilter);
	List<SubstationMaster> listSubstations(EAModel eaFilter);
	
	
	List<CircleMaster> listCircles(FilterModel eaFilter);
	List<DivisionMaster> listDivisions(FilterModel eaFilter);
	List<SubstationMaster> listSubstations(FilterModel eaFilter);
	
	
	CircleMaster findCircleByID(Integer code);
	DivisionMaster findDivisionByID(Integer code);
	SubstationMaster findSubstationByID(Integer code);

}
