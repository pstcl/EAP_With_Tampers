package org.pstcl.ea.service.impl;

import org.pstcl.ea.dao.ILocationMasterDao;
import org.pstcl.ea.dao.IMeterMasterDao;
import org.pstcl.ea.model.entity.LocationMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("restService")
public class RestService {
	
	@Autowired
	protected IMeterMasterDao meterDao;
	
	@Autowired
	ILocationMasterDao iLocationMasterDao;

	public LocationMaster getMeterDeatils(String locationid) {
		return iLocationMasterDao.findById(locationid);
	}

}
