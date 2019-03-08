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
@Table(name = "TAMPER_LOG", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "LOC_ID", "transactionDate","recordNo" }) })
public class TamperLogTransaction {

	
	@Override
	public String toString() {
		return "TamperLogTransaction [location=" + location + ", createDateTime=" + createDateTime + ", dayOfMonth="
				+ dayOfMonth + ", fileName=" + fileName + ", hourOfDay=" + hourOfDay + ", voltageRed=" + voltageRed
				+ ", voltageYellow=" + voltageYellow + ", voltageBlue=" + voltageBlue + ", currentRed=" + currentRed
				+ ", currentYellow=" + currentYellow + ", currentBlue=" + currentBlue + ", impWh=" + impWh + ", expWh="
				+ expWh + ", minuteOfHour=" + minuteOfHour + ", monthOfYear=" + monthOfYear + ", recordNo=" + recordNo
				+ ", recordStatus=" + recordStatus + ", secondofMinute=" + secondofMinute + ", tamperType=" + tamperType
				+ ", tamperDuration=" + tamperDuration + ", tamperCount=" + tamperCount + ", transactionDate="
				+ transactionDate + ", txnId=" + txnId + ", updateDateTime=" + updateDateTime + ", year=" + year + "]";
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "LOC_ID")
	private LocationMaster location;
	
	
	@Column
	@CreationTimestamp
	private Date createDateTime;
	
	@Column
	private Integer dayOfMonth;
	

	@Column
	private String fileName;


	@Column
	private Integer hourOfDay;

	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal voltageRed;

	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal voltageYellow;

	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal voltageBlue;

	
	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal currentRed;

	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal currentYellow;

	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal currentBlue;

	
	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal impWh;

	@Column(precision=EAUtil.DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT,scale=EAUtil.DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT)
	private BigDecimal expWh;

	

	@Column
	private Integer minuteOfHour;

	@Column
	private Integer monthOfYear;


//			Tamper Type	Tamper Duration	TamperCount	Voltage_R	Voltage_Y	Voltage_B	Current_R	Current_Y	Current_B	Imp Wh	Exp Wh	Power Factor	

	@Column
	private Integer recordNo;

	
	@Column
	private String recordStatus;

	@Column
	private Integer secondofMinute;

	@Column
	private String tamperType;
	
	@Column
	private String tamperDuration;
	
	@Column
	private Long tamperCount;

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
	
	
	

	public void updateValues(TamperLogTransaction tamperLog) {
		this.location = tamperLog.location;
		this.fileName = tamperLog.fileName;
		this.voltageRed = tamperLog.voltageRed;
		this.voltageYellow = tamperLog.voltageYellow;
		this.voltageBlue = tamperLog.voltageBlue;
		this.currentRed = tamperLog.currentRed;
		this.currentYellow = tamperLog.currentYellow;
		this.currentBlue = tamperLog.currentBlue;
		this.impWh = tamperLog.impWh;
		this.expWh = tamperLog.expWh;
		this.recordNo = tamperLog.recordNo;
		this.recordStatus = tamperLog.recordStatus;
		this.tamperType = tamperLog.tamperType;
		this.tamperDuration = tamperLog.tamperDuration;
		this.tamperCount = tamperLog.tamperCount;
		this.transactionDate = tamperLog.transactionDate;
		this.updateDateTime = tamperLog.updateDateTime;
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

	public LocationMaster getLocation() {
		return location;
	}

	
	
	public void setLocation(LocationMaster location) {
		this.location = location;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Integer getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getHourOfDay() {
		return hourOfDay;
	}

	public void setHourOfDay(Integer hourOfDay) {
		this.hourOfDay = hourOfDay;
	}

	public BigDecimal getVoltageRed() {
		return voltageRed;
	}

	public void setVoltageRed(BigDecimal voltageRed) {
		this.voltageRed = voltageRed;
	}

	public BigDecimal getVoltageYellow() {
		return voltageYellow;
	}

	public void setVoltageYellow(BigDecimal voltageYellow) {
		this.voltageYellow = voltageYellow;
	}

	public BigDecimal getVoltageBlue() {
		return voltageBlue;
	}

	public void setVoltageBlue(BigDecimal voltageBlue) {
		this.voltageBlue = voltageBlue;
	}

	public BigDecimal getCurrentRed() {
		return currentRed;
	}

	public void setCurrentRed(BigDecimal currentRed) {
		this.currentRed = currentRed;
	}

	public BigDecimal getCurrentYellow() {
		return currentYellow;
	}

	public void setCurrentYellow(BigDecimal currentYellow) {
		this.currentYellow = currentYellow;
	}

	public BigDecimal getCurrentBlue() {
		return currentBlue;
	}

	public void setCurrentBlue(BigDecimal currentBlue) {
		this.currentBlue = currentBlue;
	}

	public BigDecimal getImpWh() {
		return impWh;
	}

	public void setImpWh(BigDecimal impWh) {
		this.impWh = impWh;
	}

	public BigDecimal getExpWh() {
		return expWh;
	}

	public void setExpWh(BigDecimal expWh) {
		this.expWh = expWh;
	}

	public Integer getMinuteOfHour() {
		return minuteOfHour;
	}

	public void setMinuteOfHour(Integer minuteOfHour) {
		this.minuteOfHour = minuteOfHour;
	}

	public Integer getMonthOfYear() {
		return monthOfYear;
	}

	public void setMonthOfYear(Integer monthOfYear) {
		this.monthOfYear = monthOfYear;
	}

	public Integer getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public Integer getSecondofMinute() {
		return secondofMinute;
	}

	public void setSecondofMinute(Integer secondofMinute) {
		this.secondofMinute = secondofMinute;
	}

	public String getTamperType() {
		return tamperType;
	}

	public void setTamperType(String tamperType) {
		this.tamperType = tamperType;
	}

	public String getTamperDuration() {
		return tamperDuration;
	}

	public void setTamperDuration(String tamperDuration) {
		this.tamperDuration = tamperDuration;
	}

	public Long getTamperCount() {
		return tamperCount;
	}

	public void setTamperCount(Long tamperCount) {
		this.tamperCount = tamperCount;
	}

	public int getTxnId() {
		return txnId;
	}

	public void setTxnId(int txnId) {
		this.txnId = txnId;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}
	
	
	
	
	public TamperLogTransaction()
	{
		super();
	}

	public TamperLogTransaction(LocationMaster location, String fileName, BigDecimal voltageRed, BigDecimal voltageYellow,
			BigDecimal voltageBlue, BigDecimal currentRed, BigDecimal currentYellow, BigDecimal currentBlue,
			BigDecimal impWh, BigDecimal expWh, Integer recordNo, String recordStatus, String tamperType,
			String tamperDuration, Long tamperCount, Date transactionDate) {
		super();
		this.location = location;
		this.fileName = fileName;
		this.voltageRed = voltageRed;
		this.voltageYellow = voltageYellow;
		this.voltageBlue = voltageBlue;
		this.currentRed = currentRed;
		this.currentYellow = currentYellow;
		this.currentBlue = currentBlue;
		this.impWh = impWh;
		this.expWh = expWh;
		this.recordNo = recordNo;
		this.recordStatus = recordStatus;
		this.tamperType = tamperType;
		this.tamperDuration = tamperDuration;
		this.tamperCount = tamperCount;
		this.transactionDate = transactionDate;
	}
	
}
