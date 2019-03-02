package org.pstcl.ea.model.entity;

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
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "FILE_MASTER", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "LOC_ID", "transactionDate" }) })
public class FileMaster {


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "approvedBy")
	private EAUser approvedBy;

	@Column
	@CreationTimestamp
	private Date createDateTime;

	@Column
	private Integer dailyRecordCount;

	@Column
	private Integer dailyRecordNoEnd;

	@Column
	private Integer dailyRecordNoStart;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "deletedBy")
	private EAUser deletedBy;

	@Column
	private Integer fileActionStatus;



	@Column
	private Integer fileType;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "LOC_ID")
	private LocationMaster location;




	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "METER_ID")
	private MeterMaster meterMaster;
	// CMRI fields

	@Column
	private Integer processingStatus;

	
	public Integer getTamperLogCount() {
		return tamperLogCount;
	}

	public void setTamperLogCount(Integer tamperLogCount) {
		this.tamperLogCount = tamperLogCount;
	}

	public Integer getTamperLogNoEnd() {
		return tamperLogNoEnd;
	}

	public void setTamperLogNoEnd(Integer tamperLogNoEnd) {
		this.tamperLogNoEnd = tamperLogNoEnd;
	}

	public Integer getTamperLogNoStart() {
		return tamperLogNoStart;
	}

	public void setTamperLogNoStart(Integer tamperLogNoStart) {
		this.tamperLogNoStart = tamperLogNoStart;
	}

	@Column
	private Integer tamperLogCount;

	@Column
	private Integer tamperLogNoEnd;

	@Column
	private Integer tamperLogNoStart;
	
	@Column
	private Integer surveyRecordCount;

	@Column
	private Integer surveyRecordNoEnd;

	@Column
	private Integer surveyRecordNoStart;


	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column
	private Date transactionDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer txnId;

	@Column
	private String txtfileName;

	@Column
	@UpdateTimestamp
	private Date updateDateTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uploadedBy")
	private EAUser uploadedBy;

	@Column
	private String userfileName;

	@Column
	private String zipfileName;




	public EAUser getApprovedBy() {
		return approvedBy;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public Integer getDailyRecordCount() {
		return dailyRecordCount;
	}

	public Integer getDailyRecordNoEnd() {
		return dailyRecordNoEnd;
	}

	public Integer getDailyRecordNoStart() {
		return dailyRecordNoStart;
	}

	public EAUser getDeletedBy() {
		return deletedBy;
	}

	public Integer getFileActionStatus() {
		return fileActionStatus;
	}


	public Integer getFileType() {
		return fileType;
	}

	public LocationMaster getLocation() {
		return location;
	}

	public MeterMaster getMeterMaster() {
		return meterMaster;
	}

	public Integer getProcessingStatus() {
		return processingStatus;
	}

	public Integer getSurveyRecordCount() {
		return surveyRecordCount;
	}

	public Integer getSurveyRecordNoEnd() {
		return surveyRecordNoEnd;
	}




	public Integer getSurveyRecordNoStart() {
		return surveyRecordNoStart;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public Integer getTxnId() {
		return txnId;
	}

	public String getTxtfileName() {
		return txtfileName;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}


	public EAUser getUploadedBy() {
		return uploadedBy;
	}


	public String getUserfileName() {
		return userfileName;
	}

	public String getZipfileName() {
		return zipfileName;
	}


	public void setApprovedBy(EAUser approvedBy) {
		this.approvedBy = approvedBy;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public void setDailyRecordCount(Integer dailyRecordCount) {
		this.dailyRecordCount = dailyRecordCount;
	}



	public void setDailyRecordNoEnd(Integer dailyRecordNoEnd) {
		this.dailyRecordNoEnd = dailyRecordNoEnd;
	}

	public void setDailyRecordNoStart(Integer dailyRecordNoStart) {
		this.dailyRecordNoStart = dailyRecordNoStart;
	}

	public void setDeletedBy(EAUser deletedBy) {
		this.deletedBy = deletedBy;
	}

	public void setFileActionStatus(Integer fileActionStatus) {
		this.fileActionStatus = fileActionStatus;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public void setLocation(LocationMaster location) {
		this.location = location;
	}

	public void setMeterMaster(MeterMaster meterMaster) {
		this.meterMaster = meterMaster;
	}

	public void setProcessingStatus(Integer processingStatus) {
		this.processingStatus = processingStatus;
	}



	public void setSurveyRecordCount(Integer surveyRecordCount) {
		this.surveyRecordCount = surveyRecordCount;
	}

	public void setSurveyRecordNoEnd(Integer surveyRecordNoEnd) {
		this.surveyRecordNoEnd = surveyRecordNoEnd;
	}

	public void setSurveyRecordNoStart(Integer surveyRecordNoStart) {
		this.surveyRecordNoStart = surveyRecordNoStart;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}



	public void setTxnId(Integer txnId) {
		this.txnId = txnId;
	}

	public void setTxtfileName(String txtfileName) {
		this.txtfileName = txtfileName;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public void setUploadedBy(EAUser uploadedBy) {
		this.uploadedBy = uploadedBy;
	}






	public void setUserfileName(String userfileName) {
		this.userfileName = userfileName;
	}

	public void setZipfileName(String zipfileName) {
		this.zipfileName = zipfileName;
	}

	public void updateValues(FileMaster fileDetails) {
		this.dailyRecordCount =fileDetails.dailyRecordCount;
		this.dailyRecordNoEnd =fileDetails.dailyRecordNoEnd;
		this.dailyRecordNoStart =fileDetails.dailyRecordNoStart;
		this.location =fileDetails.location;
		this.meterMaster =fileDetails.meterMaster;
		this.processingStatus =fileDetails.processingStatus;
		this.fileActionStatus =fileDetails.fileActionStatus;
		this.surveyRecordCount =fileDetails.surveyRecordCount;
		this.surveyRecordNoEnd =fileDetails.surveyRecordNoEnd;
		this.surveyRecordNoStart =fileDetails.surveyRecordNoStart;
		this.transactionDate =fileDetails.transactionDate;
		this.txtfileName =fileDetails.txtfileName;
		this.zipfileName =fileDetails.zipfileName;
		this.userfileName =fileDetails.userfileName;
		this.approvedBy=fileDetails.approvedBy;
		this.deletedBy=fileDetails.deletedBy;

	}



}
