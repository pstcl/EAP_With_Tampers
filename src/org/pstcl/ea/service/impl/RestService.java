
package org.pstcl.ea.service.impl;

import java.util.List;

import org.pstcl.ea.dao.ILocationMasterDao;
import org.pstcl.ea.dao.IMeterMasterDao;
import org.pstcl.ea.dao.MeterLocationMapDao;
import org.pstcl.ea.dao.SubstationUtilityDao;
import org.pstcl.ea.model.ChangeMeterSnippet;
import org.pstcl.ea.model.entity.CircleMaster;
import org.pstcl.ea.model.entity.DivisionMaster;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.MeterLocationMap;
import org.pstcl.ea.model.entity.SubstationMaster;
import org.pstcl.model.FilterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("restService")
public class RestService {
	
	
	
	@Autowired
	MeterLocationMapDao mtrLocMapDao;


	@Autowired
	ILocationMasterDao locationMasterDao;
	
	public MeterLocationMap getMeterDetails(int id) {
       return mtrLocMapDao.findById(id);
	
	}

	public SubstationMaster getSubstationMeterDetails(int ssCode) {
		// TODO Auto-generated method stub
		return locationDao.findSubstationByID(ssCode);
	}

	public void saveDetails(ChangeMeterSnippet changeMeterSnippet) {
		// TODO Auto-generated method stub
		MeterLocationMap oldMtrLocMap = changeMeterSnippet.getOldMeterLocationMap();
		oldMtrLocMap.setEndDate(changeMeterSnippet.getEndDate());
		//mtrLocMapDao.update(oldMtrLocMap, null);
		
		MeterLocationMap newMtrLocMap = new MeterLocationMap();
		newMtrLocMap.setLocationMaster(locationMasterDao.findById(changeMeterSnippet.getLocation().getLocationId()));
		newMtrLocMap.setMeterMaster(changeMeterSnippet.getMeterMaster());
		newMtrLocMap.setStartDate(changeMeterSnippet.getStartDate());
		//mtrLocMapDao.save(newMtrLocMap, null);
		
		return;
	}
	@Autowired
	protected IMeterMasterDao meterDao;


	@Autowired SubstationUtilityDao  locationDao;

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

	public FilterModel getLocationModel(Integer circle, Integer divCode, Integer substationCode) {
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
		locationModel.setCircleList(getCircleList(locationModel));
		locationModel.setDivisionList(getDivisionList(locationModel));
		locationModel.setSubstationList(getSubstationList(locationModel));
		return locationModel;
	}


}
