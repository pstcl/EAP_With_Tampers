package org.pstcl.ea.dao;

import java.util.List;

import org.pstcl.ea.model.entity.DeviceTypeMaster;

public interface IDeviceTypeMasterDao {

	DeviceTypeMaster findById(String id);

	List<DeviceTypeMaster> findAllDeviceTypes();

}
