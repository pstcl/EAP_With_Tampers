package org.pstcl.ea.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pstcl.ea.dao.IInstantRegistersDao;
import org.pstcl.ea.dao.ILossReportDao;
import org.pstcl.ea.dao.ITamperLogDao;
import org.pstcl.ea.model.ConsolidatedLossReportModel;
import org.pstcl.ea.model.LossReportModel;
import org.pstcl.ea.model.entity.InstantRegisters;
import org.pstcl.ea.model.entity.LossReportEntity;
import org.pstcl.ea.model.entity.TamperDetailsProjectionEntity;
import org.pstcl.ea.util.DateUtil;
import org.pstcl.ea.util.EAUtil;
import org.springframework.beans.factory.annotation.Autowired;

//@Service("lossReportService")
public class LossReportService2 implements IlossReportService {

	@Autowired
	ILossReportDao lossReportDao;
	@Autowired
	ITamperLogDao tamperLogDao;

	//tamper loss month year report 
	/* (non-Javadoc)
	 * @see org.pstcl.ea.service.impl.IlossReport#getTamperDetailsProjectionReport(int, int)
	 */
	@Override
	public List<TamperDetailsProjectionEntity> getTamperDetailsProjectionReport(int month,int year){
		Date startDate=DateUtil.startDateTimeForDailySurveyRecs(month, year);
		Date endDate=DateUtil.endDateTimeForDailySurveyRecs(month, year);
		return getTamperDetailsProjectionDateRangeReport(startDate, endDate);	
	}

	//tamper loss date range report
	/* (non-Javadoc)
	 * @see org.pstcl.ea.service.impl.IlossReport#getTamperDetailsProjectionDateRangeReport(java.util.Date, java.util.Date)
	 */
	@Override
	public List<TamperDetailsProjectionEntity> getTamperDetailsProjectionDateRangeReport(Date startDate, Date endDate){
		List<TamperDetailsProjectionEntity> results =  tamperLogDao.getTamperLogTransactionsCountByDateRange(startDate, endDate);
		return results;
	}



	/* (non-Javadoc)
	 * @see org.pstcl.ea.service.impl.IlossReport#saveLossReportEntity(org.pstcl.ea.model.entity.LossReportEntity)
	 */
	@Override
	public LossReportEntity saveLossReportEntity(LossReportEntity lossReportEntity)

	{
		//calculateImportExport(lossReportEntity);
		return lossReportEntity;
	}

	/* (non-Javadoc)
	 * @see org.pstcl.ea.service.impl.IlossReport#getConsolidatedMonthlyLossReport(int, int)
	 */
	@Override
	public ConsolidatedLossReportModel getConsolidatedMonthlyLossReport (int month, int year) {

		Date startDate=DateUtil.startDateTimeForDailySurveyRecs(month, year);
		Date endDate=DateUtil.endDateTimeForDailySurveyRecs(month, year);
		return getConsolidatedDateRangeLossReport(startDate, endDate);

	}
	/* (non-Javadoc)
	 * @see org.pstcl.ea.service.impl.IlossReport#getConsolidatedDateRangeLossReport(java.util.Date, java.util.Date)
	 */
	@Override
	public ConsolidatedLossReportModel getConsolidatedDateRangeLossReport(Date startDate, Date endDate) {

		ConsolidatedLossReportModel consolidatedLossReportModel=new ConsolidatedLossReportModel();
		Map<String,LossReportModel> map=new HashMap<String,LossReportModel>();

		map.put(EAUtil.LOSS_REPORT_CRITERIA_G_T, getDateRangeReport(EAUtil.LOSS_REPORT_CRITERIA_G_T, startDate, endDate));
		map.put(EAUtil.LOSS_REPORT_CRITERIA_I_T_, getDateRangeReport(EAUtil.LOSS_REPORT_CRITERIA_I_T_, startDate, endDate));
		map.put(EAUtil.LOSS_REPORT_CRITERIA_INDEPENDENT_, getDateRangeReport(EAUtil.LOSS_REPORT_CRITERIA_INDEPENDENT_, startDate, endDate));
		map.put(EAUtil.LOSS_REPORT_CRITERIA_T_D_132_11_, getDateRangeReport(EAUtil.LOSS_REPORT_CRITERIA_T_D_132_11_, startDate, endDate));
		map.put(EAUtil.LOSS_REPORT_CRITERIA_T_D_132_33_, getDateRangeReport(EAUtil.LOSS_REPORT_CRITERIA_T_D_132_33_, startDate, endDate));
		map.put(EAUtil.LOSS_REPORT_CRITERIA_T_D_132_66, getDateRangeReport(EAUtil.LOSS_REPORT_CRITERIA_T_D_132_66, startDate, endDate));
		map.put(EAUtil.LOSS_REPORT_CRITERIA_T_D_220_66_, getDateRangeReport(EAUtil.LOSS_REPORT_CRITERIA_T_D_220_66_, startDate, endDate));

		consolidatedLossReportModel.setLossReportModelMap(map);

		LossReportEntity sumAllTD =getSumAllTD(consolidatedLossReportModel);
		LossReportEntity sumITGT =getSumITGT(consolidatedLossReportModel);

		consolidatedLossReportModel.setSumAllTD(sumAllTD);
		consolidatedLossReportModel.setSumITGT(sumITGT);
		consolidatedLossReportModel.setSumAll(getSumAll(consolidatedLossReportModel));
		calculateDifferenceImportExport(consolidatedLossReportModel);

		return consolidatedLossReportModel;

	}
	private void calculateDifferenceImportExport(ConsolidatedLossReportModel consolidatedLossReportModel) {
		if(null!=consolidatedLossReportModel.getSumAll()&&null!=consolidatedLossReportModel.getSumAll().getExportBoundaryPtMWH()&&null!=consolidatedLossReportModel.getSumAll().getImportBoundaryPtMWH())
		{
			consolidatedLossReportModel.setDifference(consolidatedLossReportModel.getSumAll().getImportBoundaryPtMWH().subtract(consolidatedLossReportModel.getSumAll().getExportBoundaryPtMWH()));
			if(consolidatedLossReportModel.getSumAll().getImportBoundaryPtMWH().intValue()!=0)
			{
				consolidatedLossReportModel.setPercentage((consolidatedLossReportModel.getDifference().multiply(new BigDecimal(100)).divide(consolidatedLossReportModel.getSumAll().getImportBoundaryPtMWH(),EAUtil.DECIMAL_SCALE_LOSS_PERCENTAGE, EAUtil.DECIMAL_ROUNDING_MODE)));
			}
		}
	}

	private LossReportEntity  getSumAll(ConsolidatedLossReportModel consolidatedLossReportModel) {

		LossReportEntity entity=new LossReportEntity();
		BigDecimal sumImportAll=new BigDecimal(0);
		BigDecimal sumExportAll=new BigDecimal(0);

		LossReportEntity sumEntityAllTD= consolidatedLossReportModel.getSumAllTD();
		LossReportEntity sumEntityITGT= consolidatedLossReportModel.getSumITGT();

		sumImportAll=sumImportAll.add(sumEntityAllTD.getImportBoundaryPtMWH());
		sumImportAll=sumImportAll.add(sumEntityITGT.getImportBoundaryPtMWH());

		sumExportAll=sumExportAll.add(sumEntityAllTD.getExportBoundaryPtMWH());
		sumExportAll=sumExportAll.add(sumEntityITGT.getExportBoundaryPtMWH());


		entity.setExportBoundaryPtMWH(sumExportAll);
		entity.setImportBoundaryPtMWH(sumImportAll);
		consolidatedLossReportModel.setCountAll(consolidatedLossReportModel.getCountAllTD()+consolidatedLossReportModel.getCountITGT());
		consolidatedLossReportModel.setCountAllDataAvailable(consolidatedLossReportModel.getCountAllTDDataAvailable()+consolidatedLossReportModel.getCountITGTDataAvailable());
		consolidatedLossReportModel.setCountAllDataManualEntry(consolidatedLossReportModel.getCountAllTDDataManualEntry()+consolidatedLossReportModel.getCountITGTDataManualEntry());

		return entity;

	}

	private LossReportEntity  getSumITGT(ConsolidatedLossReportModel consolidatedLossReportModel) {

		LossReportEntity entity=new LossReportEntity();
		entity.setExportBoundaryPtMWH(new BigDecimal(0));
		entity.setImportBoundaryPtMWH(new BigDecimal(0));
		BigDecimal sumImportITGT=new BigDecimal(0);
		BigDecimal sumExportITGT=new BigDecimal(0);

		Map<String,LossReportModel> map=consolidatedLossReportModel.getLossReportModelMap();


		LossReportEntity sumEntityGT= map.get(EAUtil.LOSS_REPORT_CRITERIA_G_T).getSumEntity();
		LossReportEntity sumEntityIT= map.get(EAUtil.LOSS_REPORT_CRITERIA_I_T_).getSumEntity();

		sumImportITGT=sumImportITGT.add(sumEntityGT.getImportBoundaryPtMWH());
		sumImportITGT=sumImportITGT.add(sumEntityIT.getImportBoundaryPtMWH());

		sumExportITGT=sumExportITGT.add(sumEntityGT.getExportBoundaryPtMWH());
		sumExportITGT=sumExportITGT.add(sumEntityIT.getExportBoundaryPtMWH());


		entity.setExportBoundaryPtMWH(sumExportITGT);
		entity.setImportBoundaryPtMWH(sumImportITGT);


		Integer totalLocCountGTIT= map.get(EAUtil.LOSS_REPORT_CRITERIA_G_T).getTotalLocations().size();
		totalLocCountGTIT+= map.get(EAUtil.LOSS_REPORT_CRITERIA_I_T_).getTotalLocations().size();
		consolidatedLossReportModel.setCountITGT(totalLocCountGTIT);

		Integer manualEntryLocCountGTIT= map.get(EAUtil.LOSS_REPORT_CRITERIA_G_T).getManualEntryLocations().size();
		manualEntryLocCountGTIT+= map.get(EAUtil.LOSS_REPORT_CRITERIA_I_T_).getManualEntryLocations().size();
		consolidatedLossReportModel.setCountITGTDataManualEntry(manualEntryLocCountGTIT);


		Integer pointsDataAvailable= map.get(EAUtil.LOSS_REPORT_CRITERIA_G_T).getPointsCountDataAvailable();
		pointsDataAvailable+= map.get(EAUtil.LOSS_REPORT_CRITERIA_I_T_).getPointsCountDataAvailable();
		consolidatedLossReportModel.setCountITGTDataAvailable(pointsDataAvailable);


		return entity;


	}

	private LossReportEntity  getSumAllTD(ConsolidatedLossReportModel consolidatedLossReportModel) {

		LossReportEntity entity=new LossReportEntity();

		Map<String,LossReportModel> map=consolidatedLossReportModel.getLossReportModelMap();
		BigDecimal sumImportTD=new BigDecimal(0);
		BigDecimal sumExportTD=new BigDecimal(0);

		LossReportModel modelEntityTD132_11= map.get(EAUtil.LOSS_REPORT_CRITERIA_T_D_132_11_);
		LossReportModel modelEntityTD132_33= map.get(EAUtil.LOSS_REPORT_CRITERIA_T_D_132_33_);
		LossReportModel modelEntityTD132_66= map.get(EAUtil.LOSS_REPORT_CRITERIA_T_D_132_66);
		LossReportModel modelEntityTD220_66= map.get(EAUtil.LOSS_REPORT_CRITERIA_T_D_220_66_);
		LossReportModel modelEntityTDInpen= map.get(EAUtil.LOSS_REPORT_CRITERIA_INDEPENDENT_);


		LossReportEntity sumEntityTD132_11= modelEntityTD132_11.getSumEntity();
		LossReportEntity sumEntityTD132_33= modelEntityTD132_33.getSumEntity();
		LossReportEntity sumEntityTD132_66= modelEntityTD132_66.getSumEntity();
		LossReportEntity sumEntityTD220_66= modelEntityTD220_66.getSumEntity();
		LossReportEntity sumEntityTDInpen= modelEntityTDInpen.getSumEntity();


		sumImportTD=sumImportTD.add(sumEntityTD132_11.getImportBoundaryPtMWH());
		sumImportTD=sumImportTD.add(sumEntityTD132_33.getImportBoundaryPtMWH());
		sumImportTD=sumImportTD.add(sumEntityTD132_66.getImportBoundaryPtMWH());
		sumImportTD=sumImportTD.add(sumEntityTD220_66.getImportBoundaryPtMWH());
		sumImportTD=sumImportTD.add(sumEntityTDInpen.getImportBoundaryPtMWH());




		sumExportTD=sumExportTD.add(sumEntityTD132_11.getExportBoundaryPtMWH());
		sumExportTD=sumExportTD.add(sumEntityTD132_33.getExportBoundaryPtMWH());
		sumExportTD=sumExportTD.add(sumEntityTD132_66.getExportBoundaryPtMWH());
		sumExportTD=sumExportTD.add(sumEntityTD220_66.getExportBoundaryPtMWH());
		sumExportTD=sumExportTD.add(sumEntityTDInpen.getExportBoundaryPtMWH());




		Integer totalLocCountTD= modelEntityTD132_11.getTotalLocations().size();
		totalLocCountTD+= modelEntityTD132_33.getTotalLocations().size();
		totalLocCountTD+= modelEntityTD132_66.getTotalLocations().size();
		totalLocCountTD+= modelEntityTD220_66.getTotalLocations().size();
		totalLocCountTD+= modelEntityTDInpen.getTotalLocations().size();
		consolidatedLossReportModel.setCountAllTD(totalLocCountTD);

		Integer manualEntryLocCountTD= modelEntityTD132_11.getManualEntryLocations().size();
		manualEntryLocCountTD+= modelEntityTD132_33.getManualEntryLocations().size();
		manualEntryLocCountTD+= modelEntityTD132_66.getManualEntryLocations().size();
		manualEntryLocCountTD+= modelEntityTD220_66.getManualEntryLocations().size();
		manualEntryLocCountTD+= modelEntityTDInpen.getManualEntryLocations().size();
		consolidatedLossReportModel.setCountAllTDDataManualEntry(manualEntryLocCountTD);


		Integer pointsDataAvailable= modelEntityTD132_11.getPointsCountDataAvailable();
		pointsDataAvailable+= modelEntityTD132_33.getPointsCountDataAvailable();
		pointsDataAvailable+= modelEntityTD132_66.getPointsCountDataAvailable();
		pointsDataAvailable+= modelEntityTD220_66.getPointsCountDataAvailable();
		pointsDataAvailable+= modelEntityTDInpen.getPointsCountDataAvailable();
		consolidatedLossReportModel.setCountAllTDDataAvailable(pointsDataAvailable);


		entity.setExportBoundaryPtMWH(sumExportTD);
		entity.setImportBoundaryPtMWH(sumImportTD);

		return entity;


	}



	/* (non-Javadoc)
	 * @see org.pstcl.ea.service.impl.IlossReport#getReport(java.lang.String, int, int)
	 */
	@Override
	public LossReportModel getReport(String reportType, int month, int year) {
		Date startDate=DateUtil.startDateTimeForDailySurveyRecs(month, year);
		Date endDate=DateUtil.endDateTimeForDailySurveyRecs(month, year);
		return getDateRangeReport(reportType, startDate, endDate);
	}
	/* (non-Javadoc)
	 * @see org.pstcl.ea.service.impl.IlossReport#getDateRangeReport(java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public LossReportModel getDateRangeReport(String reportType, Date startDate, Date endDate) {

		LossReportModel dailyProjectionModel = new LossReportModel();

		String criteria = EAUtil.LOSS_REPORT_CRITERIA_G_T;

		if (reportType.equalsIgnoreCase(EAUtil.LOSS_REPORT_CRITERIA_G_T)) {
			criteria = EAUtil.LOSS_REPORT_CRITERIA_G_T;
			dailyProjectionModel.setCriteria("G-T");
		} else if (reportType.equalsIgnoreCase(EAUtil.LOSS_REPORT_CRITERIA_I_T_)) {
			dailyProjectionModel.setCriteria("I-T");
			criteria = EAUtil.LOSS_REPORT_CRITERIA_I_T_;
		} else if (reportType.equalsIgnoreCase(EAUtil.LOSS_REPORT_CRITERIA_INDEPENDENT_)) {
			dailyProjectionModel.setCriteria("Independent Feeders");
			criteria = EAUtil.LOSS_REPORT_CRITERIA_INDEPENDENT_;
		} else if (reportType.equalsIgnoreCase(EAUtil.LOSS_REPORT_CRITERIA_T_D_132_11_)) {
			dailyProjectionModel.setCriteria("T-D 132/11");
			criteria = EAUtil.LOSS_REPORT_CRITERIA_T_D_132_11_;
		} else if (reportType.equalsIgnoreCase(EAUtil.LOSS_REPORT_CRITERIA_T_D_132_33_)) {
			dailyProjectionModel.setCriteria("T-D 132/33");
			criteria = EAUtil.LOSS_REPORT_CRITERIA_T_D_132_33_;
		} else if (reportType.equalsIgnoreCase(EAUtil.LOSS_REPORT_CRITERIA_T_D_132_66)) {
			dailyProjectionModel.setCriteria("T-D 132/66");
			criteria = EAUtil.LOSS_REPORT_CRITERIA_T_D_132_66;
		} else if (reportType.equalsIgnoreCase(EAUtil.LOSS_REPORT_CRITERIA_T_D_220_66_)) {
			dailyProjectionModel.setCriteria("T-D 220/66");
			criteria = EAUtil.LOSS_REPORT_CRITERIA_T_D_220_66_;
		}


		List<LossReportEntity> lossReportEntities = lossReportDao.getDailyTransactionsProjection(criteria, startDate, endDate);
		dailyProjectionModel.setManualEntryLocations(lossReportDao.manualDailyEntryLocations(criteria, startDate, endDate));
		dailyProjectionModel.setTotalLocations(lossReportDao.lossReportLocations(criteria, startDate, endDate));

		dailyProjectionModel.setLossReportEntities(lossReportEntities);
		dailyProjectionModel.setPointsCountDataAvailable(lossReportEntities.size()-dailyProjectionModel.getManualEntryLocations().size());
		Collections.sort(lossReportEntities);
		LossReportEntity sumEntity=lossReportDao.getDailyTransactionsProjectionSumEntity(reportType, startDate, endDate);
		//lossReportEntities.add(sumEntity);
		dailyProjectionModel.setSumEntity(sumEntity);

		return dailyProjectionModel;
	}

	

	@Autowired
	IInstantRegistersDao instantRegistersDao;


	/* (non-Javadoc)
	 * @see org.pstcl.ea.service.impl.IlossReport#getIRDetails(java.lang.String, int, int)
	 */
	@Override
	public InstantRegisters getIRDetails(String locationId,int month,int year){
		InstantRegisters ir = instantRegistersDao.findInstantRegistersByDayAndLocation(locationId, month, year);
		return ir;
	}

}
