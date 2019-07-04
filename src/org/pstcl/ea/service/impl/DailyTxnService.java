package org.pstcl.ea.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.pstcl.ea.dao.ILocationMasterDao;
import org.pstcl.ea.dao.IMeterMasterDao;
import org.pstcl.ea.model.LocationSurveyDataModel;
import org.pstcl.ea.model.entity.DailyTransaction;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.MeterMaster;
import org.pstcl.ea.util.DateUtil;
import org.pstcl.ea.util.EAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dailyTxnService")
public class DailyTxnService extends EnergyAccountsService{


	@Autowired
	ILocationMasterDao locationMasterDao;

	@Autowired
	IMeterMasterDao meterDao;

	public LocationSurveyDataModel createReportTransactions(String locationId, int month, int year) {

		LocationSurveyDataModel locationSurveyDataModel=new LocationSurveyDataModel();
		LocationMaster locationMaster=locationMasterDao.findById(locationId);
		MeterMaster  meterMaster=meterDao.findMeterForMonth(locationId, month, year);
		locationSurveyDataModel.setLocationMaster(locationMaster);
		locationSurveyDataModel.setMeterMaster(meterMaster);
		
		Date startDate=DateUtil.additionDailySurveyRecsStartDate(month, year);
		Date endDate=DateUtil.additionDailySurveyRecsEndDate(month, year);
		List<DailyTransaction> dailyTransactions=new ArrayList<DailyTransaction>();
		for(Date current = startDate;!current.after(endDate);current =DateUtil.nextDay(current))
		{
			DailyTransaction dailyTransaction=dailyTransactionDao.findByLocationDateCombo(locationMaster, current);
			if(null==dailyTransaction)
			{	
				dailyTransaction=new DailyTransaction();
				dailyTransaction.setTransactionDate(current);
				dailyTransaction.setLocation(locationMaster);
				dailyTransaction.setExportWHF(new BigDecimal(0));
				dailyTransaction.setImportWHF(new BigDecimal(0));
			}
			dailyTransaction.setRemarks(EAUtil.DAILY_TRANSACTION_ENTERED_MANUALLY);
			dailyTransactions.add(dailyTransaction);
		}

		locationSurveyDataModel.setDailyTransactions(dailyTransactions);
		return locationSurveyDataModel;
	}


	public LocationSurveyDataModel saveDailyTransactions(LocationSurveyDataModel dailyTransactionModel) {
		List<DailyTransaction> dailyTransactions=dailyTransactionModel.getDailyTransactions();
		for (DailyTransaction dailyTransaction : dailyTransactions) {
			dailyTransaction.setTransactionStatus(EAUtil.DAILY_TRANSACTION_ADDED_MANUALLY);
		}
		dailyTransactionDao.save(dailyTransactions, getLoggedInUser());
		return dailyTransactionModel;

	}

}
