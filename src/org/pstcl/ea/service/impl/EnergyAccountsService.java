package org.pstcl.ea.service.impl;

import java.util.List;

import org.pstcl.ea.dao.IDailyTransactionDao;
import org.pstcl.ea.dao.IFileMasterDao;
import org.pstcl.ea.dao.ILoadSurveyTransactionDao;
import org.pstcl.ea.dao.ILocationMasterDao;
import org.pstcl.ea.dao.ILossReportDao;
import org.pstcl.ea.dao.IMeterMasterDao;
import org.pstcl.ea.dao.IMonthlyTransactionDao;
import org.pstcl.ea.dao.ITamperLogDao;
import org.pstcl.ea.dao.SubstationUtilityDao;
import org.pstcl.ea.model.EAFilter;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class EnergyAccountsService {

	@Autowired
	protected SubstationUtilityDao substationUtilityDao;
	@Autowired
	protected IFileMasterDao fileMasterDao;
	
	@Autowired
	UserServiceImpl userService;
	@Autowired
	protected IDailyTransactionDao dailyTransactionDao;
	
	@Autowired
	protected ITamperLogDao tamperLogDao;
	
	@Autowired
	protected ILossReportDao lossReportDao;
	
	@Autowired
	protected IMonthlyTransactionDao monthlyTransactionDao;
	@Autowired
	protected IMeterMasterDao meterDao;
	

	@Autowired
	protected ILoadSurveyTransactionDao loadSurveyTransactionDao;
	
	
	@Autowired
	protected ILocationMasterDao locationMasterDao;

	public EnergyAccountsService() {
		super();
	}

	public String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
	
		return userName;
	}

	public EAUser getLoggedInUser() {
		EAUser loggedinUser= userService.findByUsername(getPrincipal());
		return loggedinUser;
	}
	
	public List<LocationMaster> listMeterLocationsForUser() {

		EAFilter filter=new EAFilter();
		filter.setSubstation(getLoggedInUser().getSubstationMaster());
		return locationMasterDao.findAllLocationMasters(filter);
	}

	public List<LocationMaster> listLocations(EAFilter filter) {
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
		return locationMasterDao.findAllLocationMasters(filter);
	}

}