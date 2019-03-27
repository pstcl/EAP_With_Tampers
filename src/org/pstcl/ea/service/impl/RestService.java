package org.pstcl.ea.service.impl;

import java.util.List;

import org.pstcl.ea.dao.ILocationMasterDao;
import org.pstcl.ea.dao.IMeterMasterDao;
import org.pstcl.ea.dao.SubstationUtilityDao;
import org.pstcl.ea.model.entity.CircleMaster;
import org.pstcl.ea.model.entity.DivisionMaster;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.SubstationMaster;
import org.pstcl.model.FilterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("restService")
public class RestService {

	@Autowired
	protected IMeterMasterDao meterDao;

	@Autowired
	ILocationMasterDao iLocationMasterDao;

	@Autowired SubstationUtilityDao  locationDao;

	public LocationMaster getMeterDeatils(String locationid) {
		return iLocationMasterDao.findById(locationid);
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
