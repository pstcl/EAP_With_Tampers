package org.pstcl.ea.model.entity;

import java.math.BigDecimal;

public class LossReportEntity implements Comparable<LossReportEntity> {

	
	
	private Long daysInMonthCount;
	

	
	private Long exportWHFCount;



	
	private BigDecimal exportWHFSum;


	
	

	
	private Long importWHFCount;

	
	private BigDecimal importWHFSum;
	
	
	private LocationMaster location;

	
	private Integer lossReportOrder;

	
	private MeterMaster meterMaster;
	
	private Integer monthOfYear;

	
	
	
	private BigDecimal boundaryPtImportExportDifferenceMWH;

	
	private BigDecimal exportBoundaryPtMWH;

	
	private BigDecimal importBoundaryPtMWH;

	
	
	private BigDecimal netMWH;

	
	
	
	private Integer txnId;
	
	private Integer year;

	@Override
	public int compareTo(LossReportEntity o) {
		int result = 0;

		if (null != location && null != o.location) {
			result = location.getLossReportOrder() - o.location.getLossReportOrder();
		} else {
			result = 1;
		}
		return result;
	}

	public BigDecimal getBoundaryPtImportExportDifferenceMWH() {
		return boundaryPtImportExportDifferenceMWH;
	}

	public Long getDaysInMonthCount() {
		return daysInMonthCount;
	}

	public BigDecimal getExportBoundaryPtMWH() {
		return exportBoundaryPtMWH;
	}

	public Long getExportWHFCount() {
		return exportWHFCount;
	}

	public BigDecimal getExportWHFSum() {
		return exportWHFSum;
	}

	public BigDecimal getImportBoundaryPtMWH() {
		return importBoundaryPtMWH;
	}

	public Long getImportWHFCount() {
		return importWHFCount;
	}

	public BigDecimal getImportWHFSum() {
		return importWHFSum;
	}

	public LocationMaster getLocation() {
		return location;
	}

	public Integer getLossReportOrder() {
		return lossReportOrder;
	}

	public MeterMaster getMeterMaster() {
		return meterMaster;
	}

	public Integer getMonthOfYear() {
		return monthOfYear;
	}

	public BigDecimal getNetMWH() {
		return netMWH;
	}

	public int getTxnId() {
		return txnId;
	}

	public Integer getYear() {
		return year;
	}

	public void setBoundaryPtImportExportDifferenceMWH(BigDecimal boundaryPtImportExportDifferenceMWH) {
		this.boundaryPtImportExportDifferenceMWH = boundaryPtImportExportDifferenceMWH;
	}

	public void setDaysInMonthCount(Long daysInMonthCount) {
		this.daysInMonthCount = daysInMonthCount;
	}

	public void setExportBoundaryPtMWH(BigDecimal exportBoundaryPtMWH) {
		this.exportBoundaryPtMWH = exportBoundaryPtMWH;
	}

	public void setExportWHFCount(Long exportWHFCount) {
		this.exportWHFCount = exportWHFCount;
	}

	public void setExportWHFSum(BigDecimal exportWHFSum) {
		this.exportWHFSum = exportWHFSum;
	}

	public void setImportBoundaryPtMWH(BigDecimal importBoundaryPtMWH) {
		this.importBoundaryPtMWH = importBoundaryPtMWH;
	}

	public void setImportWHFCount(Long importWHFCount) {
		this.importWHFCount = importWHFCount;
	}

	public void setImportWHFSum(BigDecimal importWHFSum) {
		this.importWHFSum = importWHFSum;
	}

	public void setLocation(LocationMaster location) {
		this.location = location;
	}

	public void setLossReportOrder(Integer lossReportOrder) {
		this.lossReportOrder = lossReportOrder;
	}

	public void setMeterMaster(MeterMaster meterMaster) {
		this.meterMaster = meterMaster;
	}

	public void setMonthOfYear(Integer monthOfYear) {
		this.monthOfYear = monthOfYear;
	}

	public void setNetMWH(BigDecimal netMWH) {
		this.netMWH = netMWH;
	}

	public void setTxnId(int txnId) {
		this.txnId = txnId;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
