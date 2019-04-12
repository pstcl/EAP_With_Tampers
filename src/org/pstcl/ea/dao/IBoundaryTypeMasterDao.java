package org.pstcl.ea.dao;
import java.util.List;

import org.pstcl.ea.model.EAFilter;
import org.pstcl.ea.model.entity.BoundaryTypeMaster;

public interface IBoundaryTypeMasterDao {

	BoundaryTypeMaster findById(String id);
	List<BoundaryTypeMaster> findAllUsers();
	List<BoundaryTypeMaster> listSubDivisions(BoundaryTypeMaster user);
	List<BoundaryTypeMaster> listDivisions(BoundaryTypeMaster user);
	List<BoundaryTypeMaster> findUserHistory(EAFilter model);
	void save(BoundaryTypeMaster eaUser);
	BoundaryTypeMaster findBoundaryById(Integer id);
	
}