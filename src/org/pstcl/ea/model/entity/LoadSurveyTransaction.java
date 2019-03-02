package org.pstcl.ea.model.entity;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "LOAD_SURVEY_TRANSACTION", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "LOC_ID", "transactionDate" }) })
public class LoadSurveyTransaction {

	@Column
	private BigDecimal avgFrequency;

	@Column
	@CreationTimestamp
	private Date createDateTime;
	@Column
	private Integer dayOfMonth;
	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal exportVAhTotal;

	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal exportWhFundTotal;

	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal exportWhTotal;

	@Column
	private String fileName;

	@Column
	private BigDecimal freqcode;

	@Column
	private Integer hourOfDay;

	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal importVAhTotal;

	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal importWhFundTotal;

	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal importWhTotal;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "LOC_ID")
	private LocationMaster location;

	@Column
	private Integer minuteOfHour;

	@Column
	private Integer monthOfYear;

	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal netWh;

	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal q1varhTotal;

	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal q2varhTotal;

	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal q3varhTotal;

	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal q4varhTotal;

	@Column
	private Integer recordNo;

	@Column
	private String recordStatus;

	@Column
	private Integer secondofMinute;

	@Column
	private BigDecimal statusIndication;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column
	private Date transactionDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int txnId;

	@Column
	@UpdateTimestamp
	private Date updateDateTime;

	@Column
	private Integer year;

	public LoadSurveyTransaction() {super();}

	public LoadSurveyTransaction(String fileName, LocationMaster location, Date transactionDate, Integer recordNo,
			BigDecimal importWhFundTotal, BigDecimal exportWhFundTotal, BigDecimal avgFrequency, BigDecimal q1varhTotal,
			BigDecimal q2varhTotal, BigDecimal q3varhTotal, BigDecimal q4varhTotal, BigDecimal netWh, BigDecimal freqcode,
			BigDecimal importVAhTotal, BigDecimal exportVAhTotal, BigDecimal importWhTotal, BigDecimal exportWhTotal,
			BigDecimal statusIndication, String recordStatus) {
		super();
		this.setTransactionDate(transactionDate);
		this.location = location;

		this.fileName = fileName;
		this.recordNo = recordNo;
		this.importWhFundTotal = importWhFundTotal;
		this.exportWhFundTotal = exportWhFundTotal;
		this.avgFrequency = avgFrequency;
		this.q1varhTotal = q1varhTotal;
		this.q2varhTotal = q2varhTotal;
		this.q3varhTotal = q3varhTotal;
		this.q4varhTotal = q4varhTotal;
		this.netWh = netWh;
		this.freqcode = freqcode;
		this.importVAhTotal = importVAhTotal;
		this.exportVAhTotal = exportVAhTotal;
		this.importWhTotal = importWhTotal;
		this.exportWhTotal = exportWhTotal;
		this.statusIndication = statusIndication;
		this.recordStatus = recordStatus;
	}

	public BigDecimal getAvgFrequency() {
		return avgFrequency;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public Integer getDayOfMonth() {
		return dayOfMonth;
	}

	public BigDecimal getExportVAhTotal() {
		return exportVAhTotal;
	}

	public BigDecimal getExportWhFundTotal() {
		return exportWhFundTotal;
	}

	public BigDecimal getExportWhTotal() {
		return exportWhTotal;
	}

	public String getFileName() {
		return fileName;
	}

	public BigDecimal getFreqcode() {
		return freqcode;
	}

	public Integer getHourOfDay() {
		return hourOfDay;
	}

	public BigDecimal getImportVAhTotal() {
		return importVAhTotal;
	}

	public BigDecimal getImportWhFundTotal() {
		return importWhFundTotal;
	}

	public BigDecimal getImportWhTotal() {
		return importWhTotal;
	}

	public LocationMaster getLocation() {
		return location;
	}

	public Integer getMinuteOfHour() {
		return minuteOfHour;
	}

	public Integer getMonthOfYear() {
		return monthOfYear;
	}

	public BigDecimal getNetWh() {
		return netWh;
	}

	public BigDecimal getQ1varhTotal() {
		return q1varhTotal;
	}

	public BigDecimal getQ2varhTotal() {
		return q2varhTotal;
	}

	

	public BigDecimal getQ3varhTotal() {
		return q3varhTotal;
	}

	public BigDecimal getQ4varhTotal() {
		return q4varhTotal;
	}

	public Integer getRecordNo() {
		return recordNo;
	}

	

	public String getRecordStatus() {
		return recordStatus;
	}

	public Integer getSecondofMinute() {
		return secondofMinute;
	}

	public BigDecimal getStatusIndication() {
		return statusIndication;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public int getTxnId() {
		return txnId;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public Integer getYear() {
		return year;
	}

	public void setAvgFrequency(BigDecimal avgFrequency) {
		this.avgFrequency = avgFrequency;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public void setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public void setExportVAhTotal(BigDecimal exportVAhTotal) {
		this.exportVAhTotal = exportVAhTotal;
	}

	public void setExportWhFundTotal(BigDecimal exportWhFundTotal) {
		this.exportWhFundTotal = exportWhFundTotal;
	}

	public void setExportWhTotal(BigDecimal exportWhTotal) {
		this.exportWhTotal = exportWhTotal;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setFreqcode(BigDecimal freqcode) {
		this.freqcode = freqcode;
	}

	public void setHourOfDay(Integer hourOfDay) {
		this.hourOfDay = hourOfDay;
	}

	public void setImportVAhTotal(BigDecimal importVAhTotal) {
		this.importVAhTotal = importVAhTotal;
	}

	

	public void setImportWhFundTotal(BigDecimal importWhFundTotal) {
		this.importWhFundTotal = importWhFundTotal;
	}

	public void setImportWhTotal(BigDecimal importWhTotal) {
		this.importWhTotal = importWhTotal;
	}

	// CMRI fields

	public void setLocation(LocationMaster location) {
		this.location = location;
	}

	
	public void setMinuteOfHour(Integer minuteOfHour) {
		this.minuteOfHour = minuteOfHour;
	}
	public void setMonthOfYear(Integer monthOfYear) {
		this.monthOfYear = monthOfYear;
	}
	public void setNetWh(BigDecimal netWh) {
		this.netWh = netWh;
	}
	public void setQ1varhTotal(BigDecimal q1varhTotal) {
		this.q1varhTotal = q1varhTotal;
	}
	public void setQ2varhTotal(BigDecimal q2varhTotal) {
		this.q2varhTotal = q2varhTotal;
	}
	public void setQ3varhTotal(BigDecimal q3varhTotal) {
		this.q3varhTotal = q3varhTotal;
	}
	public void setQ4varhTotal(BigDecimal q4varhTotal) {
		this.q4varhTotal = q4varhTotal;
	}
	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public void setSecondofMinute(Integer secondofMinute) {
		this.secondofMinute = secondofMinute;
	}
	public void setStatusIndication(BigDecimal statusIndication) {
		this.statusIndication = statusIndication;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
		if (null != this.transactionDate) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(transactionDate);
			this.year = cal.get(Calendar.YEAR);
			this.monthOfYear = cal.get(Calendar.MONTH);
			this.dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

		}
	}
	public void setTxnId(int txnId) {
		this.txnId = txnId;
	}

	// CMRI Fields
	/*
	 * Record No. Date/Time Import Wh (Fund) Total Export Wh (Fund) Total Avg
	 * Frequency Q1 varh Total Q2 varh Total Q3 varh Total Q4 varh Total Net Wh
	 * Freqcode Import VAh Total Export VAh Total Import Wh Total Export Wh Total
	 * Status Indication Record Status
	 */

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "LoadSurveyTransaction [txnId=" + txnId + ", dayOfMonth=" + dayOfMonth + ", monthOfYear=" + monthOfYear
				+ ", year=" + year + ", hourOfDay=" + hourOfDay + ", minuteOfHour=" + minuteOfHour + ", secondofMinute="
				+ secondofMinute + ", fileName=" + fileName + ", location=" + location + ", transactionDate="
				+ transactionDate + ", recordNo=" + recordNo + ", importWhFundTotal=" + importWhFundTotal
				+ ", exportWhFundTotal=" + exportWhFundTotal + ", avgFrequency=" + avgFrequency + ", q1varhTotal="
				+ q1varhTotal + ", q2varhTotal=" + q2varhTotal + ", q3varhTotal=" + q3varhTotal + ", q4varhTotal="
				+ q4varhTotal + ", netWh=" + netWh + ", freqcode=" + freqcode + ", importVAhTotal=" + importVAhTotal
				+ ", exportVAhTotal=" + exportVAhTotal + ", importWhTotal=" + importWhTotal + ", exportWhTotal="
				+ exportWhTotal + ", statusIndication=" + statusIndication + ", recordStatus=" + recordStatus + "]";
	}

	public void updateValues(LoadSurveyTransaction loadSurveyTransaction) {
		// TODO Auto-generated method stub
		this.fileName =loadSurveyTransaction.fileName;
		this.recordNo =loadSurveyTransaction.recordNo;
		this.importWhFundTotal =loadSurveyTransaction.importWhFundTotal;
		this.exportWhFundTotal =loadSurveyTransaction.exportWhFundTotal;
		this.avgFrequency =loadSurveyTransaction.avgFrequency;
		this.q1varhTotal =loadSurveyTransaction.q1varhTotal;
		this.q2varhTotal =loadSurveyTransaction.q2varhTotal;
		this.q3varhTotal =loadSurveyTransaction.q3varhTotal;
		this.q4varhTotal =loadSurveyTransaction.q4varhTotal;
		this.netWh =loadSurveyTransaction.netWh;
		this.freqcode =loadSurveyTransaction.freqcode;
		this.importVAhTotal =loadSurveyTransaction.importVAhTotal;
		this.exportVAhTotal =loadSurveyTransaction.exportVAhTotal;
		this.importWhTotal =loadSurveyTransaction.importWhTotal;
		this.exportWhTotal =loadSurveyTransaction.exportWhTotal;
		this.statusIndication =loadSurveyTransaction.statusIndication;
		this.recordStatus =loadSurveyTransaction.recordStatus;

	}



}
