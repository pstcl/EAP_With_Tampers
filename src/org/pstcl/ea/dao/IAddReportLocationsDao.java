package org.pstcl.ea.dao;

import java.util.List;

import org.pstcl.ea.model.entity.AddReportLocations;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.LocationMaster;

public interface IAddReportLocationsDao {

	AddReportLocations findById(int id);

	void deleteById(int id);

	void save(AddReportLocations txn, EAUser user);

	void update(AddReportLocations txn, EAUser user);

	List<LocationMaster> findByMonthAndYear(int month, int year);

	void delete(int month, int year, List<LocationMaster> locationMaster);

	

	

	

	

}
