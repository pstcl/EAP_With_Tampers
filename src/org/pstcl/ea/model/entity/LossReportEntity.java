package org.pstcl.ea.model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.pstcl.ea.util.EAUtil;


@Entity
@Table(name = "LOSS_REPORT_TABLE",uniqueConstraints={@UniqueConstraint(columnNames={"LOC_ID", "monthOfYear", "year"})})
public class LossReportEntity implements Comparable<LossReportEntity> {
	
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_BOUNDARY_PT_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_BOUNDARY_PT_IMPORT_EXPORT) private BigDecimal boundaryPtImportExportDifferenceMWH;
	
	@Column private Long daysInMonthCount;
	@Column(precision=EAUtil.DECIMAL_PRECESION_BOUNDARY_PT_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_BOUNDARY_PT_IMPORT_EXPORT) private BigDecimal exportBoundaryPtMWH;
	
	
	@Column private Long exportWHFCount;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_BOUNDARY_PT_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_BOUNDARY_PT_IMPORT_EXPORT) private BigDecimal exportWHFSum;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_BOUNDARY_PT_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_BOUNDARY_PT_IMPORT_EXPORT) private BigDecimal importBoundaryPtMWH;
	
	@Column private Long importWHFCount;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_BOUNDARY_PT_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_BOUNDARY_PT_IMPORT_EXPORT) private BigDecimal importWHFSum;
	@ManyToOne 
	@JoinColumn(name = "LOC_ID")
	private LocationMaster location;
	
	@Column private Integer lossReportOrder;
	
	@ManyToOne 
	@JoinColumn(name = "METER_ID")
	private MeterMaster meterMaster;
	@Column private Integer monthOfYear;
	@Column(precision=EAUtil.DECIMAL_PRECESION_BOUNDARY_PT_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_BOUNDARY_PT_IMPORT_EXPORT) private BigDecimal netMWH;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer txnId;
	@Column private Integer year;
	@Override
	public int compareTo(LossReportEntity o) {
		int result=0;
		
		if(null!=location&&null!=o.location)
		{
			result=location.getLossReportOrder()-o.location.getLossReportOrder();
		}
		else
		{
			result=1;
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
