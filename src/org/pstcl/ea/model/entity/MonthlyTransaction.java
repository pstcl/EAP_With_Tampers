package org.pstcl.ea.model.entity;

import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "MONTHLY_TRANSACTION",uniqueConstraints={@UniqueConstraint(columnNames={"LOC_ID", "monthOfYear","year"})})
public class MonthlyTransaction {

//	public class MyNamingStrategy extends DefaultNamingStrategy {
//		   ...
//		   @Override
//		   public  String tableName(String tableName) {
//		      return tableName+yearSuffixTable;
//		   }
//		   ...
//		}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int txnId;

	@ManyToOne 
	@JoinColumn(name = "LOC_ID")
	private LocationMaster location;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column Integer monthOfYear;
	@Column Integer year;
	@Column private String netWHSign;
	@Column private Double connectedCTRStart;




	@Column private Double importReadingStart;
	
	@Column private Double exportReadingStart;
	@Column private Double importReadingEnd;
	@Column private Double exportReadingEnd;
	@Column private Double netExport;
	@Column private Double netImport;
	@Column private Double netMWH;
	@Column private Double netEnergyMWH;
	@Column
	private Integer transactionStatus;
	@Column private String remarks;

	public Double getConnectedCTRStart() {
		return connectedCTRStart;
	}
	public Double getExportReadingEnd() {
		return exportReadingEnd;
	}
	public Double getExportReadingStart() {
		return exportReadingStart;
	}
	public Double getImportReadingEnd() {
		return importReadingEnd;
	}
	public Double getImportReadingStart() {
		return importReadingStart;
	}
	public LocationMaster getLocation() {
		return location;
	}
	public Integer getMonthOfYear() {
		return monthOfYear;
	}
	public Double getNetEnergyMWH() {

		return  netEnergyMWH; 
	}
	public Double getNetExport() {
		return netExport;
	}

	
	

	public Double getNetImport() {
		return netImport;
	}




	//Should be treated as NET WH not NET MWH
	public Double getNetMWH() {
		return netMWH;
	}




	public String getNetWHSign() {
		return netWHSign;
	}




	public String getRemarks() {
		return remarks;
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


	public void setConnectedCTRStart(Double connectedCTRStart) {
		this.connectedCTRStart = connectedCTRStart;
	}
	public void setExportReadingEnd(Double exportReadingEnd) {
		this.exportReadingEnd = exportReadingEnd;
	}
	
	public void setExportReadingStart(Double exportReadingStart) {
		this.exportReadingStart = exportReadingStart;
	}






	
	public void setImportReadingEnd(Double importReadingEnd) {
		this.importReadingEnd = importReadingEnd;
	}
	public void setImportReadingStart(Double importReadingStart) {
		this.importReadingStart = importReadingStart;
	}
	public void setLocation(LocationMaster location) {
		this.location = location;
	}
	
	
	public void setMonthOfYear(Integer monthOfYear) {
		this.monthOfYear = monthOfYear;
	}
	public void setNetEnergyMWH(Double netEnergyMWH) {
		DecimalFormat df = new DecimalFormat("###.##");
		this.netEnergyMWH = new Double(df.format(netEnergyMWH));
	}
	public void setNetExport(Double netExport) {
		this.netExport = netExport;
	}
	public void setNetImport(Double netImport) {
		this.netImport = netImport;
	}
	public void setNetMWH(Double netMWH) {
		DecimalFormat df = new DecimalFormat("###.##");
		this.netMWH = new Double(df.format(netMWH));
	}
	public void setNetWHSign(String netWHSign) {


		this.netWHSign = netWHSign;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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


	

	public void updateValues(MonthlyTransaction monthlyTransaction, MeterMaster meterMaster) {
		
		if(null!=monthlyTransaction.importReadingEnd)
		{
			this.importReadingEnd =monthlyTransaction.importReadingEnd;
		}
		
		if(null!=monthlyTransaction.importReadingStart)
		{
			this.importReadingStart =monthlyTransaction.importReadingStart;
		}
		if(null!=monthlyTransaction.exportReadingEnd)
		{
			this.exportReadingEnd =monthlyTransaction.exportReadingEnd;
		}
		if(null!=monthlyTransaction.exportReadingStart)
		{
			this.exportReadingStart =monthlyTransaction.exportReadingStart;
		}
		this.connectedCTRStart=monthlyTransaction.connectedCTRStart;
		
		this.transactionStatus =monthlyTransaction.transactionStatus;
		this.remarks =monthlyTransaction.remarks;
		
	}


	


}
