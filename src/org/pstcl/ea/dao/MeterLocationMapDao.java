package org.pstcl.ea.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.MeterLocationMap;
import org.pstcl.ea.model.entity.MeterMaster;

public interface MeterLocationMapDao {

	//ss

	MeterLocationMap findById(int id);

	void deleteById(String id);

	void save(MeterLocationMap txn, EAUser user);

	void update(MeterLocationMap txn, EAUser user);


	List<MeterLocationMap> getLocationByMeterAndDate(MeterMaster meterMaster, Date current);

	MeterLocationMap findMeterLocationMapByDate(String locationId, Date current);



	List<MeterLocationMap> findMeterLocationMapByLoc(String locationId);

	MeterLocationMap findById(String id);

	boolean find(MeterLocationMap newMtrLocMap);

	List<MeterLocationMap> findLocations(MeterMaster meterMaster);

	

	


	
}
