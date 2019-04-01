
package org.pstcl.ea.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.pstcl.ea.dao.ILocationEMFDao;
import org.pstcl.ea.dao.ILocationMasterDao;
import org.pstcl.ea.dao.IMeterMasterDao;
import org.pstcl.ea.dao.MeterLocationMapDao;
import org.pstcl.ea.dao.SubstationUtilityDao;
import org.pstcl.ea.model.ChangeLocationEmf;
import org.pstcl.ea.model.ChangeMeterSnippet;
import org.pstcl.ea.model.entity.CircleMaster;
import org.pstcl.ea.model.entity.DivisionMaster;
import org.pstcl.ea.model.entity.LocationEMF;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.MeterLocationMap;
import org.pstcl.ea.model.entity.MeterMaster;
import org.pstcl.ea.model.entity.SubstationMaster;
import org.pstcl.model.FilterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("restService")
public class RestService {
	
	@Autowired
	ILocationEMFDao locEmfDao;
	
	@Autowired
	MeterLocationMapDao mtrLocMapDao;


	@Autowired
	ILocationMasterDao locationMasterDao;
	
	public MeterLocationMap getMeterDetails(int id) {
       return mtrLocMapDao.findById(id);
	
	}

	@Autowired
	protected IMeterMasterDao meterDao;


	@Autowired SubstationUtilityDao  locationDao;
	
	public SubstationMaster getSubstationMeterDetails(int ssCode) {
		// TODO Auto-generated method stub
		return locationDao.findSubstationByID(ssCode);
	}

	public boolean saveDetails(ChangeMeterSnippet changeMeterSnippet) {
		// TODO Auto-generated method stub
		
		MeterLocationMap oldMtrLocMap = changeMeterSnippet.getOldMeterLocationMap();
	try {
		oldMtrLocMap.setEndDate(changeMeterSnippet.getEndDate());
		mtrLocMapDao.update(oldMtrLocMap, null);
		System.out.println(oldMtrLocMap.getMeterMaster().getMeterSrNo());
		System.out.println(oldMtrLocMap.getLocationMaster().getLocationId());
		MeterLocationMap newMtrLocMap = new MeterLocationMap();
		newMtrLocMap.setLocationMaster(changeMeterSnippet.getLocation());
		
		newMtrLocMap.setMeterMaster(changeMeterSnippet.getMeterMaster());
		newMtrLocMap.setStartDate(changeMeterSnippet.getStartDate());
		if(mtrLocMapDao.find(newMtrLocMap)==false)
		mtrLocMapDao.save(newMtrLocMap, null);
		
		return true;
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return false;
	}


	public LocationMaster getMeterDeatils(String locationid) {
		return locationMasterDao.findById(locationid);
	}



	public CircleMaster findCircleById(Integer code) {
		return this.locationDao.findCircleByID(code);
	}

	public DivisionMaster findDivisionById(Integer code) {
		return this.locationDao.findDivisionByID(code);
	}

	public SubstationMaster findSubstationById(Integer code) {
		return this.locationDao.findSubstationByID(code);
	}


	public List<CircleMaster> getCircleList(FilterModel locationModel) {

		return this.locationDao.listCircles(locationModel);
	}

	public List<DivisionMaster> getDivisionList(FilterModel locationModel) {
		return this.locationDao.listDivisions(locationModel);
	}

	public List<SubstationMaster> getSubstationList(FilterModel locationModel) {
		return this.locationDao.listSubstations(locationModel);
	}

	public List<LocationMaster> getLocationList(FilterModel locationModel) {
		return this.locationDao.listLocations(locationModel);
	}

	public FilterModel getLocationModel(Integer circle, Integer divCode, Integer substationCode,String Locationid) {
		FilterModel locationModel=new FilterModel();
		if(circle!=null)
		{
			locationModel.setSelectedCircle(findCircleById(circle));
		}
		if(divCode!=null)
		{
			locationModel.setSelectedDivision(findDivisionById(divCode));
		}
		if(substationCode!=null)
		{
			locationModel.setSelectedSubstation(findSubstationById(substationCode));
		}
		if(Locationid!=null) {
			locationModel.setSelectedLocation(findLocationBYId(Locationid));
		}
		locationModel.setCircleList(getCircleList(locationModel));
		locationModel.setDivisionList(getDivisionList(locationModel));
		locationModel.setSubstationList(getSubstationList(locationModel));
		locationModel.setLocationList(getLocationList(locationModel));
		return locationModel;
	}

	public LocationMaster findLocationBYId(String locationid) {
		return this.locationDao.findLocationByID(locationid);
	}

	public List<MeterLocationMap> findLocations(MeterMaster meterMaster) {
		return mtrLocMapDao.findLocations(meterMaster);
	}

	public LocationEMF getLocationRecentEmfByLocid(String locationId) {
		return locEmfDao.findLocationRecentEmf(locationId);
	}

	public boolean saveDetailsOfLocationEmf(ChangeLocationEmf changeLocationEmf) {
		try {
		if(changeLocationEmf.getOldLocationEmf()!=null) {
			LocationEMF updateEmf= changeLocationEmf.getOldLocationEmf();
			updateEmf.setEndDate(changeLocationEmf.getEndDate());
			locEmfDao.update(updateEmf, null);
		}
		if(changeLocationEmf.getSetNewEmf().equals("yes")) {
			LocationEMF newEmf = new LocationEMF();
			newEmf.setLocationMaster(changeLocationEmf.getLocationMaster());
			newEmf.setStartDate(changeLocationEmf.getStartDate());
			newEmf.setExternalMF(new BigDecimal(changeLocationEmf.getExternalMF()));
			if(locEmfDao.find(newEmf)==false)
		    locEmfDao.save(newEmf, null);	
		    System.out.println(newEmf);
		}
		
		}catch(Exception e) {	
			e.printStackTrace();
		return false;
		}
		
		return true;
	}

	public List<LocationEMF> getLocationEmfListByLocid(String locationId) {
		// TODO Auto-generated method stub
		return locEmfDao.findLocationEmfByDate(locationId, null);
	}


}
