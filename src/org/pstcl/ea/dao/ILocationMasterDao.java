package org.pstcl.ea.dao;

import java.util.List;

import org.pstcl.ea.model.EAFilter;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.LocationMaster;


public interface ILocationMasterDao {
	void deleteById(String id);
	List<LocationMaster> findAllLocationMasters();
	List<LocationMaster> findAllLocationMasters(EAFilter filterModel);
	void save(LocationMaster meter, EAUser user);
	LocationMaster findById(String id);
	
	List<String> findDistinctUtiltiyName();
	List<String> findDistinctVoltageLevel();
}
