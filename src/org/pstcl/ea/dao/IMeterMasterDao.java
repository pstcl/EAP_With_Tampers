package org.pstcl.ea.dao;

import java.util.List;

import org.pstcl.ea.model.EAFilter;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.MeterMaster;


public interface IMeterMasterDao {
	MeterMaster findByMeterSrNo(String meterNo);
	void deleteById(String id);
	List<MeterMaster> findAllMeterMasters();
	List<MeterMaster> findAllMeterMasters(EAFilter filterModel);
	void save(MeterMaster meter, EAUser user);
	MeterMaster findMeterForMonth(String locationid, int month, int year);
}
