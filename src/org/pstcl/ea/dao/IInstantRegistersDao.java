package org.pstcl.ea.dao;

import java.util.List;

import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.InstantRegisters;
import org.pstcl.ea.model.entity.LocationMaster;


public interface IInstantRegistersDao {
	
	void save(InstantRegisters instantRegisters, EAUser user);
	void update(InstantRegisters txn, EAUser user);
	InstantRegisters findInstantRegistersByDayAndLocation(String locationId, Integer month, Integer year);
	void deleteById(String id);
	void save(List<InstantRegisters> instantRegisters, EAUser loggedInUser);
	InstantRegisters findById(int id);
}
