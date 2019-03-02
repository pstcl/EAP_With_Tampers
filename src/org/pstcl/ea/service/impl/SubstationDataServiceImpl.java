package org.pstcl.ea.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.pstcl.ea.model.EAFilter;
import org.pstcl.ea.model.EAModel;
import org.pstcl.ea.model.FileFilter;
import org.pstcl.ea.model.FileModel;
import org.pstcl.ea.model.LocationFileModel;
import org.pstcl.ea.model.entity.CircleMaster;
import org.pstcl.ea.model.entity.DivisionMaster;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.FileMaster;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.MeterMaster;
import org.pstcl.ea.model.entity.SubstationMaster;
import org.pstcl.ea.security.UserRole;
import org.pstcl.ea.util.DateUtil;
import org.pstcl.ea.util.EAUtil;
import org.springframework.stereotype.Service;

@Service("substationDataService")
public class SubstationDataServiceImpl extends EnergyAccountsService{


	public FileModel getFileInRepo(Integer month, Integer year2) {

		FileFilter filter=new FileFilter();
		if(userService.hasRole(UserRole.SS_JE.getUserRoleName())||userService.hasRole(UserRole.SS_AE.getUserRoleName()))
		{
			filter.setSubstation(getLoggedInUser().getSubstationMaster());
		}
		else 	if(userService.hasRole(UserRole.DIV_ASE.getUserRoleName()))
		{
			filter.setDivision(getLoggedInUser().getDivisionMaster());
		}
		else 	if(userService.hasRole(UserRole.CIRCLE_SE.getUserRoleName()))
		{
			filter.setCircle(getLoggedInUser().getCircleMaster());
		}
		filter.setTransactionDateFrom(DateUtil.startDateTimeForDailySurveyRecs(month, year2));
		filter.setTransactionDateTo(DateUtil.endDateTimeForDailySurveyRecs(month, year2));
		List<FileMaster> fileMasters=fileMasterDao.filterFiles(filter);
		FileModel fileModel=new FileModel();
		fileModel.setFilesUploadedDetail(fileMasters);
		return fileModel;
	}
	public List<MeterMaster> listMeters(EAFilter filter) {
		if(null==filter)
		{
			filter=new EAFilter();
		}
		EAUser eaUser=getLoggedInUser();
		if(eaUser.getRolecode().equals(UserRole.SS_JE.getUserRoleCode())||eaUser.getRolecode().equals(UserRole.SS_AE.getUserRoleCode()))
		{
			filter=new EAFilter();
			filter.setSubstation(getLoggedInUser().getSubstationMaster());
		}
		if(eaUser.getRolecode().equals(UserRole.DIV_ASE.getUserRoleCode()))
		{
			filter=new EAFilter();
			filter.setDivision(getLoggedInUser().getDivisionMaster());
		}
		if(eaUser.getRolecode().equals(UserRole.CIRCLE_SE.getUserRoleCode()))
		{
			filter=new EAFilter();
			filter.setCircle(getLoggedInUser().getCircleMaster());
		}
		return meterDao.findAllMeterMasters(filter);
	}


	private EAModel setUserLocation(EAModel filter)
	{
		if(null==filter)
		{
			filter=new EAFilter();
		}
		EAUser eaUser=getLoggedInUser();
		if(userService.hasRole(UserRole.SS_JE.getUserRoleName())||userService.hasRole(UserRole.SS_AE.getUserRoleName()))
		{
			filter=new EAFilter();
			filter.setSubstation(getLoggedInUser().getSubstationMaster());
		}
		else 	if(userService.hasRole(UserRole.DIV_ASE.getUserRoleName()))
		{
			filter=new EAFilter();
			filter.setDivision(getLoggedInUser().getDivisionMaster());
		}
		else 	if(userService.hasRole(UserRole.CIRCLE_SE.getUserRoleName()))
		{
			filter=new EAFilter();
			filter.setCircle(getLoggedInUser().getCircleMaster());
		}
		return filter;
	}



	public List<SubstationMaster> getSubstationList(EAModel filter)
	{
		filter=setUserLocation((EAFilter) filter);
		return substationUtilityDao.listSubstations(filter);
	}


	public List<DivisionMaster> getDivisionList(EAModel eaFilter) {

		if(null!=eaFilter)
		{
			if(null!=eaFilter.getDivision())
			{
				eaFilter.setDivision(substationUtilityDao.findDivisionByID(eaFilter.getDivision().getDivCode()));
				eaFilter.setCircle(eaFilter.getDivision().getCircleMaster());
			}
		}
		return substationUtilityDao.listDivisions(eaFilter);
	}

	public List<CircleMaster> getCircleList(EAModel filter) {
		return substationUtilityDao.listCircles(filter);
	}


	public List<LocationFileModel> getSubstationMeterFilesList(EAFilter filter,Integer month, Integer year) {
		filter=(EAFilter)setUserLocation(filter);

		List <LocationMaster> locations =locationMasterDao.findAllLocationMasters(filter);

		List<LocationFileModel> list =new ArrayList<LocationFileModel>();




		FileFilter fileFilter=new FileFilter();
		if(null!=month&&null!=year)
		{
			fileFilter.setTransactionDateFrom(DateUtil.startDateTimeForDailySurveyRecs(month, year));
			fileFilter.setTransactionDateTo(DateUtil.endDateTimeForDailySurveyRecs(month, year));
		}
		for (LocationMaster locationMaster : locations) {
			LocationFileModel locationFileModel=new LocationFileModel();
			locationFileModel.setLocationMaster(locationMaster);
			fileFilter.setLocation(locationMaster);
			List<FileMaster> fileMasters=fileMasterDao.filterFiles(fileFilter);
			locationFileModel.setFileMasters(fileMasters);
			list.add(locationFileModel);
		}


		return list;
	}


	public List<LocationFileModel> getPendingLocations(EAFilter eafilter,Integer month, Integer year) {

		eafilter=(EAFilter)setUserLocation(eafilter);



		List<LocationFileModel> list =new ArrayList<LocationFileModel>();
		List <LocationMaster> locations =lossReportDao.findPendingLocations(eafilter,month,year);


		FileFilter filter=new FileFilter();
		filter.setTransactionDateFrom(DateUtil.startDateTimeForDailySurveyRecs(month+1, year));
		filter.setTransactionDateTo(DateUtil.endDateTimeForDailySurveyRecs(month+1, year));

		for (LocationMaster locationMaster : locations) {
			LocationFileModel locationFileModel=new LocationFileModel();
			locationFileModel.setLocationMaster(locationMaster);
			filter.setLocation(locationMaster);
			filter.setFileActionStatus(EAUtil.FILE_ACTION__APPROVED_AE);
			List<FileMaster> fileMasters=fileMasterDao.filterFiles(filter);
			locationFileModel.setFileMasters(fileMasters);
			list.add(locationFileModel);
		}

		return list;
	}



}
