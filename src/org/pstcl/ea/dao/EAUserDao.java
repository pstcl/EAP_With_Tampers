package org.pstcl.ea.dao;
import java.util.List;

import org.pstcl.ea.model.EAFilter;
import org.pstcl.ea.model.entity.EAUser;

public interface EAUserDao {

	EAUser findById(String id);
	List<EAUser> findAllUsers();
	List<EAUser> listSubDivisions(EAUser user);
	List<EAUser> listDivisions(EAUser user);
	List<EAUser> findUserHistory(EAFilter model);
	void save(EAUser eaUser);
	
}