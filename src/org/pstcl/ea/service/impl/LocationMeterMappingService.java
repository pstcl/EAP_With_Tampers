package org.pstcl.ea.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.pstcl.ea.dao.IMeterMasterDao;
import org.pstcl.ea.dao.MeterLocationMapDao;
import org.pstcl.ea.model.SubstationMeter;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.MeterLocationMap;
import org.pstcl.ea.model.entity.MeterMaster;
import org.pstcl.ea.model.entity.SubstationMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("locationMeterMappingService")
public class LocationMeterMappingService {

	@Autowired
	SubstationDataServiceImpl substationDataService;

	@Autowired
	MeterLocationMapDao mtrLocMapDao;
	
	@Autowired
	IMeterMasterDao meterMasterDao;
	
	public List<SubstationMeter> findSubstationEnergyMeters() {
		List <SubstationMaster> submasters = substationDataService.getSubstationList(null);
		List <SubstationMeter> substationMeterList = new ArrayList<SubstationMeter>();
		for(SubstationMaster submaster:submasters)
		{

			SubstationMeter substationMeter = new SubstationMeter();
			List <MeterLocationMap> mtrLocMap = new ArrayList<MeterLocationMap>();;
			Set<LocationMaster> locMtrs= submaster.getLocationMasters();
			for(LocationMaster locMtr : locMtrs) {
				List<MeterLocationMap> mlml = mtrLocMapDao.findMeterLocationMapByLoc(locMtr.getLocationId());
				if(mlml!=null && mlml.size()>0) {
					for(MeterLocationMap mlm : mlml)
						if(mlm!=null)
							mtrLocMap.add(mlm);

				}

			}
			substationMeter.setMtrLocMap(mtrLocMap);
			substationMeter.setSubstationMaster(submaster);
			
			try {
				substationMeterList.add(substationMeter);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

		return substationMeterList;
	}

	public List<MeterMaster> findNotMappedMeters() {
		// TODO Auto-generated method stub
		return meterMasterDao.findMeterWithNoMapping();
	}
}
