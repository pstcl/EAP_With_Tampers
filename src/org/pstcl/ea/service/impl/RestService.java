
package org.pstcl.ea.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.pstcl.ea.dao.IAddReportLocationsDao;
import org.pstcl.ea.dao.IBoundaryTypeMasterDao;
import org.pstcl.ea.dao.IDeviceTypeMasterDao;
import org.pstcl.ea.dao.IFeederMasterDao;
import org.pstcl.ea.dao.ILocationEMFDao;
import org.pstcl.ea.dao.ILocationMasterDao;
import org.pstcl.ea.dao.IMeterMasterDao;
import org.pstcl.ea.dao.MeterLocationMapDao;
import org.pstcl.ea.dao.SubstationUtilityDao;
import org.pstcl.ea.model.AddReportLocationModel;
import org.pstcl.ea.model.ChangeLocationEmf;
import org.pstcl.ea.model.ChangeMeterSnippet;
import org.pstcl.ea.model.LocationMasterList;
import org.pstcl.ea.model.entity.AddReportLocations;
import org.pstcl.ea.model.entity.CircleMaster;
import org.pstcl.ea.model.entity.DivisionMaster;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.MeterMaster;
import org.pstcl.ea.model.entity.SubstationMaster;
import org.pstcl.ea.model.mapping.LocationMFMap;
import org.pstcl.ea.model.mapping.MeterLocationMap;
import org.pstcl.model.FilterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("restService")
public class RestService {

	@Autowired
	IDeviceTypeMasterDao deviceTypeMasterDao;
	
	@Autowired
	ILocationEMFDao locEmfDao;

	@Autowired
	MeterLocationMapDao mtrLocMapDao;

	@Autowired
	ILocationMasterDao locationMasterDao;
	
	@Autowired
	IFeederMasterDao feederMasterDao;
	
	@Autowired
	IBoundaryTypeMasterDao boundaryTypeMasterDao;
	
	@Autowired 
	IAddReportLocationsDao addReportLocationsDao; 

	public MeterLocationMap getMeterDetails(int id) {
		return mtrLocMapDao.findById(id);

	}

	@Autowired
	protected IMeterMasterDao meterDao;

	@Autowired
	SubstationUtilityDao locationDao;

	public SubstationMaster getSubstationMeterDetails(int ssCode) {
		// TODO Auto-generated method stub
		return locationDao.findSubstationByID(ssCode);
	}
/**
 * Check Save details of changed location meter mapping
 * @param changeMeterSnippet
 * @return
 */
	public boolean saveDetails(ChangeMeterSnippet changeMeterSnippet) {
		// TODO Auto-generated method stub

		MeterLocationMap oldMtrLocMap = changeMeterSnippet.getOldMeterLocationMap();
		try {
			if(oldMtrLocMap!=null) {
			oldMtrLocMap.setEndDate(changeMeterSnippet.getEndDate());
			mtrLocMapDao.update(oldMtrLocMap, null);
			System.out.println(oldMtrLocMap.getMeterMaster().getMeterSrNo());
			System.out.println(oldMtrLocMap.getLocationMaster().getLocationId());
			}

		
		if(changeMeterSnippet.getSetNewMeter().equals("yes")) {
			MeterLocationMap newMtrLocMap = new MeterLocationMap();
			newMtrLocMap.setLocationMaster(changeMeterSnippet.getLocation());

			newMtrLocMap.setMeterMaster(changeMeterSnippet.getMeterMaster());
			newMtrLocMap.setStartDate(changeMeterSnippet.getStartDate());
			if (mtrLocMapDao.find(newMtrLocMap) == false)
				mtrLocMapDao.save(newMtrLocMap, null);
		}
		
		return true;
		} catch (Exception e) {
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

	
	/**
	 * Generate interlinked select options on selecting circle,substation ,division
	 * @param circle
	 * @param divCode
	 * @param substationCode
	 * @param Locationid
	 * @return
	 */
	public FilterModel getLocationModel(Integer circle, Integer divCode, Integer substationCode, String Locationid) {
		FilterModel locationModel = new FilterModel();
		if (circle != null) {
			locationModel.setSelectedCircle(findCircleById(circle));
		}
		if (divCode != null) {
			locationModel.setSelectedDivision(findDivisionById(divCode));
		}
		if (substationCode != null) {
			locationModel.setSelectedSubstation(findSubstationById(substationCode));
		}
		if (Locationid != null) {
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

	public LocationMFMap getLocationRecentEmfByLocid(String locationId) {
		return locEmfDao.findLocationRecentEmf(locationId);
	}
/**
 * Check and save details of location and emf mapping
 * @param changeLocationEmf
 * @return
 */
	public boolean saveDetailsOfLocationEmf(ChangeLocationEmf changeLocationEmf) {
		try {
			if (changeLocationEmf.getOldLocationEmf() != null) {
				LocationMFMap updateEmf = changeLocationEmf.getOldLocationEmf();
				updateEmf.setEndDate(changeLocationEmf.getEndDate());
				locEmfDao.update(updateEmf, null);
			}
			if (changeLocationEmf.getSetNewEmf().equals("yes")) {
				LocationMFMap newEmf = new LocationMFMap();
				newEmf.setLocationMaster(changeLocationEmf.getLocationMaster());
				newEmf.setStartDate(changeLocationEmf.getStartDate());
				newEmf.setExternalMF(new BigDecimal(changeLocationEmf.getExternalMF()));
				if (locEmfDao.find(newEmf) == false)
					locEmfDao.save(newEmf, null);
				System.out.println(newEmf);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/*
	 * display mapped emf with location on successful changed details
	 */
	public List<LocationMFMap> getLocationEmfListByLocid(String locationId) {
		// TODO Auto-generated method stub
		return locEmfDao.findLocationEmfByDate(locationId, null);
	}

	/**
	 * Check and save details of added new meter
	 * @param meter
	 */
	public void saveMeterDetails(MeterMaster meter) {
		//ask conditions of same meter
		if(meterDao.findByMeterSrNo(meter.getMeterSrNo())==null)
		meterDao.save(meter, null);
		return;
	}

	public void setOnlyMeter(ChangeMeterSnippet chgMtr, String meterId) {
		chgMtr.setMeterMaster(meterDao.findByMeterSrNo(meterId));
		
	}

	/**
	 * Service to generate options in adding - location form 
	 * @return
	 */
	public LocationMasterList getLocationMasterListModel() {
		// TODO Auto-generated method stub
		LocationMasterList list = new LocationMasterList();
		list.setUtiltiyName(locationMasterDao.findDistinctUtiltiyName());
		list.setVoltageLevel(locationMasterDao.findDistinctVoltageLevel());
		list.setBoundaryTypeMaster(boundaryTypeMasterDao.findAllUsers());
		list.setFeederMaster(feederMasterDao.findAllFeeders());
		list.setDeviceTypeMaster(deviceTypeMasterDao.findAllDeviceTypes());
		return list;
	}
	
	/**
	 * check and save details of added location in adding new location form
	 * @param locationMaster
	 */
	public void saveLocationMasterDetails(LocationMaster locationMaster) {
		//ask conditions of same meter
		if(locationMasterDao.findById(locationMaster.getLocationId())==null) {
		locationMasterDao.save(locationMaster, null);
		SubstationMaster substationMaster = locationMaster.getSubstationMaster();
		Set <LocationMaster> list = substationMaster.getLocationMasters();
		list.add(locationMaster);
		substationMaster.setLocationMasters(list);
		locationDao.update(substationMaster);
		}
		return;
		
	}
/**
 * To generate options with fixed values in adding new meter form 
 * @return
 */
	public LocationMasterList MeterListModel() {
		// TODO Auto-generated method stub
		LocationMasterList list = new LocationMasterList();
        list.setMeterCategory(meterDao.findDistinctMeterCategory());
        list.setMeterMake(meterDao.findDistinctMeterMake());
        list.setMeterType(meterDao.findDistinctMeterType());
		return list;
	}

	/**
	 * Service to generate options of added and can be added locations for selected month
	 * @param addReportLocations
	 * @return
	 */
	public List<LocationMaster> selectReportLocations(AddReportLocationModel addReportLocations) {
		 
		int month = addReportLocations.getMonth();
		int year = addReportLocations.getYear();
		List <LocationMaster> pendingLocation = locationMasterDao.findAllLocationMasters();
		System.out.print((int) pendingLocation.size());
		List<LocationMaster> list =addReportLocationsDao.findByMonthAndYear(month,year);
		if(list!=null)
		{
			addReportLocations.setLocations(list);
			pendingLocation.removeAll(list);
		}
		
		//addReportLocations.setAddingLocations(pendingLocation);
		return pendingLocation;
	}
/**
 * Save details of changes made for report month - location form
 * @param addReportLocations
 * @return
 */
	public AddReportLocationModel saveReportLocations(AddReportLocationModel addReportLocations) {
		int month = addReportLocations.getMonth();
		int year = addReportLocations.getYear();
		List<LocationMaster> removelist =addReportLocations.getLocations();
		List<LocationMaster> addList = addReportLocations.getAddingLocations();
		
		if(removelist!=null)
			addReportLocationsDao.delete(month, year, removelist);
		if(addList!=null)
			for(LocationMaster loc:addList) {
				System.out.println(loc);
				addReportLocationsDao.save(new AddReportLocations(month,year,loc), null);
		
			}
		return addReportLocations;
	}

}
