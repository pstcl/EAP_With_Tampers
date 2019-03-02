package org.pstcl.ea.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.pstcl.ea.model.entity.LossReportEntity;

public class ConsolidatedLossReportModel {
	

	private BigDecimal difference;
	private Date endDate;
	private Map<String, LossReportModel> lossReportModelMap;
	private Integer month;
	private BigDecimal percentage;
	private Date startDate;
	private LossReportEntity sumAll;
	private LossReportEntity sumAllTD;
	private LossReportEntity sumITGT;

	
	private Integer countAll;
	private Integer countAllTD;
	private Integer countITGT;


	public Integer getCountAllDataManualEntry() {
		return countAllDataManualEntry;
	}



	public void setCountAllDataManualEntry(Integer countAllDataManualEntry) {
		this.countAllDataManualEntry = countAllDataManualEntry;
	}



	public Integer getCountAllTDDataManualEntry() {
		return countAllTDDataManualEntry;
	}



	public void setCountAllTDDataManualEntry(Integer countAllTDDataManualEntry) {
		this.countAllTDDataManualEntry = countAllTDDataManualEntry;
	}



	public Integer getCountITGTDataManualEntry() {
		return countITGTDataManualEntry;
	}



	public void setCountITGTDataManualEntry(Integer countITGTDataManualEntry) {
		this.countITGTDataManualEntry = countITGTDataManualEntry;
	}

	private Integer countAllDataManualEntry;
	private Integer countAllTDDataManualEntry;
	private Integer countITGTDataManualEntry;

	private Integer countAllDataAvailable;
	private Integer countAllTDDataAvailable;
	private Integer countITGTDataAvailable;

	
	public Integer getCountAllDataAvailable() {
		return countAllDataAvailable;
	}



	public void setCountAllDataAvailable(Integer countAllDataAvailable) {
		this.countAllDataAvailable = countAllDataAvailable;
	}



	public Integer getCountAllTDDataAvailable() {
		return countAllTDDataAvailable;
	}



	public void setCountAllTDDataAvailable(Integer countAllTDDataAvailable) {
		this.countAllTDDataAvailable = countAllTDDataAvailable;
	}



	public Integer getCountITGTDataAvailable() {
		return countITGTDataAvailable;
	}



	public void setCountITGTDataAvailable(Integer countITGTDataAvailable) {
		this.countITGTDataAvailable = countITGTDataAvailable;
	}



	public Integer getCountAll() {
		return countAll;
	}



	public void setCountAll(Integer countAll) {
		this.countAll = countAll;
	}



	public Integer getCountAllTD() {
		return countAllTD;
	}



	public void setCountAllTD(Integer countAllTD) {
		this.countAllTD = countAllTD;
	}



	public Integer getCountITGT() {
		return countITGT;
	}



	public void setCountITGT(Integer countITGT) {
		this.countITGT = countITGT;
	}

	private Integer year;

	public BigDecimal getDifference() {
		return difference;
	}

	

	public Date getEndDate() {
		return endDate;
	}

	public Map<String, LossReportModel> getLossReportModelMap() {
		return lossReportModelMap;
	}

	public Integer getMonth() {
		return month;
	}
	public BigDecimal getPercentage() {
		return percentage;
	}

	public Date getStartDate() {
		return startDate;
	}

	public LossReportEntity getSumAll() {
		return sumAll;
	}

	public LossReportEntity getSumAllTD() {
		return sumAllTD;
	}

	public LossReportEntity getSumITGT() {
		return sumITGT;
	}

	public Integer getYear() {
		return year;
	}

	public void setDifference(BigDecimal difference) {
		this.difference = difference;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	
	public void setLossReportModelMap(Map<String, LossReportModel> lossReportModelMap) {
		this.lossReportModelMap = lossReportModelMap;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setSumAll(LossReportEntity sumAll) {
		this.sumAll = sumAll;
	}

	public void setSumAllTD(LossReportEntity sumAllTD) {
		this.sumAllTD = sumAllTD;
	}

	public void setSumITGT(LossReportEntity sumITGT) {
		this.sumITGT = sumITGT;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
