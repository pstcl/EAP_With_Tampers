package org.pstcl.ea.dao;

import java.util.Date;
import java.util.List;

import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.LocationEMF;
import org.pstcl.ea.model.entity.MeterLocationMap;


public interface ILocationEMFDao {
	
	//ss

	LocationEMF findById(int id);



	void deleteById(String id);

	void save(LocationEMF txn, EAUser user);

	void update(LocationEMF txn, EAUser user);

	void save(List<LocationEMF> locationEMFs, EAUser loggedInUser);




	List<LocationEMF> findLocationEmfByDate(String locationId, Date current);



	List<LocationEMF> findLocationEmfByLocAndDate(List<MeterLocationMap> mtrLocMapList, Date startDateOfMonth);




}
