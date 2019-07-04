package org.pstcl.ea.model;

import java.util.Date;

import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.MeterMaster;


public class FileFilter extends EAModel
{
	private Date transactionDateFrom;
	private Date transactionDateTo;
	
	private Date createDateTimeFrom;
	private Date createDateTimeTo;
	
	private Integer fileActionStatus;

	private LocationMaster location;
	private MeterMaster meterMaster;
	
	private Integer processingStatus;
	
	private Date updateDateTimeFrom;

	private Date updateDateTimeTo;

	public Date getTransactionDateFrom() {
		return transactionDateFrom;
	}

	public void setTransactionDateFrom(Date transactionDateFrom) {
		this.transactionDateFrom = transactionDateFrom;
	}

	public Date getTransactionDateTo() {
		return transactionDateTo;
	}

	public void setTransactionDateTo(Date transactionDateTo) {
		this.transactionDateTo = transactionDateTo;
	}

	public Date getCreateDateTimeFrom() {
		return createDateTimeFrom;
	}

	public void setCreateDateTimeFrom(Date createDateTimeFrom) {
		this.createDateTimeFrom = createDateTimeFrom;
	}

	public Date getCreateDateTimeTo() {
		return createDateTimeTo;
	}

	public void setCreateDateTimeTo(Date createDateTimeTo) {
		this.createDateTimeTo = createDateTimeTo;
	}

	public Integer getFileActionStatus() {
		return fileActionStatus;
	}

	public void setFileActionStatus(Integer fileActionStatus) {
		this.fileActionStatus = fileActionStatus;
	}

	public LocationMaster getLocation() {
		return location;
	}

	public void setLocation(LocationMaster location) {
		this.location = location;
	}

	public MeterMaster getMeterMaster() {
		return meterMaster;
	}

	public void setMeterMaster(MeterMaster meterMaster) {
		this.meterMaster = meterMaster;
	}

	public Integer getProcessingStatus() {
		return processingStatus;
	}

	public void setProcessingStatus(Integer processingStatus) {
		this.processingStatus = processingStatus;
	}

	public Date getUpdateDateTimeFrom() {
		return updateDateTimeFrom;
	}

	public void setUpdateDateTimeFrom(Date updateDateTimeFrom) {
		this.updateDateTimeFrom = updateDateTimeFrom;
	}

	public Date getUpdateDateTimeTo() {
		return updateDateTimeTo;
	}

	public void setUpdateDateTimeTo(Date updateDateTimeTo) {
		this.updateDateTimeTo = updateDateTimeTo;
	}
	
	
	
	

}
