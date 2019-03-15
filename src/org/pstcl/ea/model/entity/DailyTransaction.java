package org.pstcl.ea.model.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.pstcl.ea.util.EAUtil;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "DAILY_TRANSACTION",uniqueConstraints={@UniqueConstraint(columnNames={"LOC_ID", "transactionDate"})})
public class DailyTransaction {

//	public class MyNamingStrategy extends DefaultNamingStrategy {
//		   ...
//		   @Override
//		   public  String tableName(String tableName) {
//		      return tableName+yearSuffixTable;
//		   }
//		   ...
//		}
	
	@Column(precision = EAUtil.DECIMAL_PRECESION_BOUNDARY_PT_IMPORT_EXPORT, scale = EAUtil.DECIMAL_SCALE_BOUNDARY_PT_IMPORT_EXPORT)
	private BigDecimal boundaryPtImportExportDifferenceMWH;

	@Column
	@CreationTimestamp
	private Date createDateTime;

	@Column private Double cumulativeNetWh;

	@Column Integer dayOfMonth;
	@Column(precision = EAUtil.DECIMAL_PRECESION_BOUNDARY_PT_IMPORT_EXPORT, scale = EAUtil.DECIMAL_SCALE_BOUNDARY_PT_IMPORT_EXPORT)
	private BigDecimal exportBoundaryPtMWH;
	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal exportWHF;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_MF,scale=EAUtil.DECIMAL_SCALE_MF)
	private BigDecimal externalMF;

	@JsonIgnore
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name = "MF_MAP_ID")
	private LocationEMF externalMFMap;

	@Column private String fileName;

	@Column private Integer netWHSign;

	



	@Column(precision = EAUtil.DECIMAL_PRECESION_BOUNDARY_PT_IMPORT_EXPORT, scale = EAUtil.DECIMAL_SCALE_BOUNDARY_PT_IMPORT_EXPORT)
	private BigDecimal importBoundaryPtMWH;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal importWHF;
	
	@JsonIgnore
	@ManyToOne 
	@JoinColumn(name = "LOC_ID")
	private LocationMaster location;

	@JsonIgnore
	@ManyToOne 
	@JoinColumn(name = "METER_ID")
	private MeterMaster meter;
	


	@JsonIgnore
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name = "METER_LOC_MAP_ID")
	private MeterLocationMap meterLocationMap;



	@Column Integer monthOfYear;



	@Column(precision = EAUtil.DECIMAL_PRECESION_BOUNDARY_PT_IMPORT_EXPORT, scale = EAUtil.DECIMAL_SCALE_BOUNDARY_PT_IMPORT_EXPORT)
	private BigDecimal netMWH;



	@Column private Integer recordNo;



	@Column private String remarks;



	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column private Date transactionDate;



	@Column
	private Integer transactionStatus;



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int txnId;



	@Column
	@UpdateTimestamp
	private Date updateDateTime;



	@Column Integer year;



	public BigDecimal getBoundaryPtImportExportDifferenceMWH() {
		return boundaryPtImportExportDifferenceMWH;
	}



	public Double getCumulativeNetWh() {
		return cumulativeNetWh;
	}



	public Integer getDayOfMonth() {
		return dayOfMonth;
	}



	public BigDecimal getExportBoundaryPtMWH() {
		return exportBoundaryPtMWH;
	}



	public BigDecimal getExportWHF() {
		return exportWHF;
	}



	public BigDecimal getExternalMF() {
		return externalMF;
	}


	public LocationEMF getExternalMFMap() {
		return externalMFMap;
	}
	
	public String getFileName() {
		return fileName;
	}


	public BigDecimal getImportBoundaryPtMWH() {
		return importBoundaryPtMWH;
	}

	public BigDecimal getImportWHF() {
		return importWHF;
	}
	
	public LocationMaster getLocation() {
		return location;
	}
	
	
	public MeterMaster getMeter() {
		return meter;
	}




	public MeterLocationMap getMeterLocationMap() {
		return meterLocationMap;
	}
	public Integer getMonthOfYear() {
		return monthOfYear;
	}
	public BigDecimal getNetMWH() {
		return netMWH;
	}
	
	public Integer getNetWHSign() {
		return netWHSign;
	}



	public void setNetWHSign(Integer netWHSign) {
		this.netWHSign = netWHSign;
	}
	public Integer getRecordNo() {
		return recordNo;
	}
	public String getRemarks() {
		return remarks;
	}
	
	
	
	//ADDED AFTER CMRI CHANGES
	
	public Date getTransactionDate() {
		return transactionDate;
	}	
	public Integer getTransactionStatus() {
		return transactionStatus;
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
	public void setCumulativeNetWh(Double cumulativeNetWh) {
		this.cumulativeNetWh = cumulativeNetWh;
	}
	public void setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}
	public void setExportBoundaryPtMWH(BigDecimal exportBoundaryPtMWH) {
		this.exportBoundaryPtMWH = exportBoundaryPtMWH;
	}
	public void setExportWHF(BigDecimal exportWHF) {
		this.exportWHF = exportWHF;
	}
	
	//ADDED AFTER CMRI CHANGES
	
	//Date/Time	
	//Import Wh (Fund) Total	
	//Export Wh (Fund) Total	
//	Net Wh	
//	Net Varh Hi	
//	Net Varh Lo	
//	PWR Off Count	
//	PWR Off Max Dur	
//	PWR Off Min Dur	
//	Import VAh Total	
//	Export VAh Total	
//	
//	Cum Net Varh Hi	
//	Cum Net Varh Lo	
//	PWR Off Sec	
//	Record Status
	
	
	

	public void setExternalMF(BigDecimal externalMF) {
		this.externalMF = externalMF;
	}

	

	public void setExternalMFMap(LocationEMF externalMFMap) {
		this.externalMFMap = externalMFMap;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	
	public void setImportBoundaryPtMWH(BigDecimal importBoundaryPtMWH) {
		this.importBoundaryPtMWH = importBoundaryPtMWH;
	}
	public void setImportWHF(BigDecimal importWHF) {
		this.importWHF = importWHF;
	}






	public void setLocation(LocationMaster location) {
		this.location = location;
	}
	public void setMeter(MeterMaster meter) {
		this.meter = meter;
	}
	public void setMeterLocationMap(MeterLocationMap meterLocationMap) {
		this.meterLocationMap = meterLocationMap;
	}
	public void setMonthOfYear(Integer monthOfYear) {
		this.monthOfYear = monthOfYear;
	}
	
	public void setNetMWH(BigDecimal netMWH) {
		this.netMWH = netMWH;
	}
	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
		if(null!=this.transactionDate)
		{
			Calendar cal = Calendar.getInstance();
			cal.setTime(transactionDate);
			this.year = cal.get(Calendar.YEAR);
			this.monthOfYear = cal.get(Calendar.MONTH);
			this.dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		}
	}
	public void setTransactionStatus(Integer transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public void setTxnId(int txnId) {
		this.txnId = txnId;
	}
	public void setYear(Integer year) {
		this.year = year;
	}

	
	
	
	@Override
	public String toString() {
		return "DailyTransaction [txnId=" + txnId + ", location=" + location + ", transactionDate=" + transactionDate
				+ ", dayOfMonth=" + dayOfMonth + ", monthOfYear=" + monthOfYear + ", year=" + year + ", netWHSign="
				+   ", importWHF=" + importWHF + ", exportWHF=" + exportWHF + ", netMWH=" 
				+ ", netEnergyMWH="  + ", transactionStatus=" + transactionStatus + ", remarks=" + remarks
				+ "]";
	}


	public void updateValues(DailyTransaction dailyTransaction) {
		if(null!=dailyTransaction.importWHF)
		{
			this.importWHF =dailyTransaction.importWHF;
		}
		if(null!=dailyTransaction.exportWHF)
		{
			this.exportWHF =dailyTransaction.exportWHF;
		}
		this.recordNo=dailyTransaction.recordNo;
		this.cumulativeNetWh=dailyTransaction.cumulativeNetWh;
		this.fileName=dailyTransaction.fileName;
		
		
		
	//	this.netEnergyMWH =dailyTransaction.netEnergyMWH;
		this.transactionStatus =dailyTransaction.transactionStatus;
		this.remarks =dailyTransaction.remarks;
	}


}
