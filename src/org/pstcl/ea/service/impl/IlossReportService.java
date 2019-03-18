package org.pstcl.ea.service.impl;

import java.util.Date;
import java.util.List;

import org.pstcl.ea.model.ConsolidatedLossReportModel;
import org.pstcl.ea.model.LossReportModel;
import org.pstcl.ea.model.entity.InstantRegisters;
import org.pstcl.ea.model.entity.LossReportEntity;
import org.pstcl.ea.model.entity.TamperDetailsProjectionEntity;

public interface IlossReportService {

	//tamper loss month year report 
	List<TamperDetailsProjectionEntity> getTamperDetailsProjectionReport(int month, int year);

	//tamper loss date range report
	List<TamperDetailsProjectionEntity> getTamperDetailsProjectionDateRangeReport(Date startDate, Date endDate);

	LossReportEntity saveLossReportEntity(LossReportEntity lossReportEntity);

	ConsolidatedLossReportModel getConsolidatedMonthlyLossReport(int month, int year);

	ConsolidatedLossReportModel getConsolidatedDateRangeLossReport(Date startDate, Date endDate);

	LossReportModel getReport(String reportType, int month, int year);

	LossReportModel getDateRangeReport(String reportType, Date startDate, Date endDate);

	InstantRegisters getIRDetails(String locationId, int month, int year);

}