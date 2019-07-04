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

import org.pstcl.ea.util.EAUtil;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
@Table(name="INSTANT_REGISTERS", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "LOC_ID","transactionDate" }) })
public class InstantRegisters {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int txnId;	
	


	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "LOC_ID")
	private LocationMaster location;
	
	@Column(nullable=false)
	private Date transactionDate=new Date();
	
	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal phaseAVoltage;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal phaseBVoltage;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal phaseCVoltage;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
private BigDecimal phaseACurrent;
	
@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal phaseBCurrent;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal phaseCCurrent;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
private BigDecimal phaseAVoltAngle;
	
@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal phaseBVoltAngle;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal phaseCVoltAngle;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
private BigDecimal phaseACurrentAngle;
	
@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal phaseBCurrentAngle;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal phaseCCurrentAngle;
	
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal threePhasePF;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal frequency;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal activePowerA;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal activePowerB;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal activePowerC;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal activePowerTotal;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal activePowerFunA;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal activePowerFunB;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal activePowerFunC;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal activePowerFunTotal;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal apparentPowerA;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal apparentPowerB;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal apparentPowerC;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal apparentPowerTotal;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal reactivePowerA;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal reactivePowerB;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal reactivePowerC;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal reactivePowerTotal;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel1Unified;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel1Rate1;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel1Rate2;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel1Rate3;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel1Rate4;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel1Rate5;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
private BigDecimal touChannel2Unified;
	
@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel2Rate1;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel2Rate2;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel2Rate3;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel2Rate4;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal touChannel2Rate5;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
private BigDecimal touChannel3Unified;
	
@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel3Rate1;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel3Rate2;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel3Rate3;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel3Rate4;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel3Rate5;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
private BigDecimal touChannel4Unified;
	
@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel4Rate1;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel4Rate2;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel4Rate3;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel4Rate4;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel4Rate5;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
private BigDecimal touChannel5Unified;
	
@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel5Rate1;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel5Rate2;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel5Rate3;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel5Rate4;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel5Rate5;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
private BigDecimal touChannel6Unified;
	
@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel6Rate1;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel6Rate2;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel6Rate3;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel6Rate4;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel6Rate5;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
private BigDecimal touChannel7Unified;
	
@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel7Rate1;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel7Rate2;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel7Rate3;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel7Rate4;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel7Rate5;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
private BigDecimal touChannel8Unified;
	
@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel8Rate1;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel8Rate2;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel8Rate3;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel8Rate4;
	
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel8Rate5;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel9Unified;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel10Unified;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel11Unified;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS1)
	private BigDecimal touChannel12Unified;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandChannel1Unified;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandChannel2Unified;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandChannel3Unified;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandChannel4Unified;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandTouChannel1Rate1;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandTouChannel1Rate2;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandTouChannel1Rate3;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandTouChannel1Rate4;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandTouChannel1Rate5;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
private BigDecimal demandTouChannel2Rate1;
	
@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandTouChannel2Rate2;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandTouChannel2Rate3;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandTouChannel2Rate4;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandTouChannel2Rate5;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
    private BigDecimal demandTouChannel3Rate1;

@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandTouChannel3Rate2;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandTouChannel3Rate3;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandTouChannel3Rate4;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandTouChannel3Rate5;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
    private BigDecimal demandTouChannel4Rate1;
	
    @Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandTouChannel4Rate2;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandTouChannel4Rate3;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandTouChannel4Rate4;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal demandTouChannel4Rate5;
	
	@Column
	private Date dateChannel1Unified;
	
	@Column
	private Date dateChannel2Unified;
	
	@Column
    private Date dateChannel1Rate1;
	
	@Column
	private Date dateChannel1Rate2;
	
	@Column
	private Date dateChannel1Rate3;
	
	@Column
	private Date dateChannel1Rate4;
	
	@Column
	private Date dateChannel1Rate5;
	
	@Column
    private Date dateChannel2Rate1;
	
	@Column
	private Date dateChannel2Rate2;
	
	@Column
	private Date dateChannel2Rate3;
	
	@Column
	private Date dateChannel2Rate4;
	
	@Column
	private Date dateChannel2Rate5;
	
	@Column
	private Date dateChannel3Unified;
	
	@Column
	private Date dateChannel4Unified;
	
	@Column
	private long MDResetCode;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal CTPrimary;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal CTSecondary;
	
	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
    private BigDecimal VTPrimary;
	
    
    public LocationMaster getLocation() {
		return location;
	}

	public void setLocation(LocationMaster location) {
		this.location = location;
	}



	public BigDecimal getPhaseAVoltage() {
		return phaseAVoltage;
	}

	public void setPhaseAVoltage(BigDecimal phaseAVoltage) {
		this.phaseAVoltage = phaseAVoltage;
	}

	public BigDecimal getPhaseBVoltage() {
		return phaseBVoltage;
	}

	public void setPhaseBVoltage(BigDecimal phaseBVoltage) {
		this.phaseBVoltage = phaseBVoltage;
	}

	public BigDecimal getPhaseCVoltage() {
		return phaseCVoltage;
	}

	public void setPhaseCVoltage(BigDecimal phaseCVoltage) {
		this.phaseCVoltage = phaseCVoltage;
	}

	public BigDecimal getPhaseACurrent() {
		return phaseACurrent;
	}

	public void setPhaseACurrent(BigDecimal phaseACurrent) {
		this.phaseACurrent = phaseACurrent;
	}

	public BigDecimal getPhaseBCurrent() {
		return phaseBCurrent;
	}

	public void setPhaseBCurrent(BigDecimal phaseBCurrent) {
		this.phaseBCurrent = phaseBCurrent;
	}

	public BigDecimal getPhaseCCurrent() {
		return phaseCCurrent;
	}

	public void setPhaseCCurrent(BigDecimal phaseCCurrent) {
		this.phaseCCurrent = phaseCCurrent;
	}

	public BigDecimal getPhaseAVoltAngle() {
		return phaseAVoltAngle;
	}

	public void setPhaseAVoltAngle(BigDecimal phaseAVoltAngle) {
		this.phaseAVoltAngle = phaseAVoltAngle;
	}

	public BigDecimal getPhaseBVoltAngle() {
		return phaseBVoltAngle;
	}

	public void setPhaseBVoltAngle(BigDecimal phaseBVoltAngle) {
		this.phaseBVoltAngle = phaseBVoltAngle;
	}

	public BigDecimal getPhaseCVoltAngle() {
		return phaseCVoltAngle;
	}

	public void setPhaseCVoltAngle(BigDecimal phaseCVoltAngle) {
		this.phaseCVoltAngle = phaseCVoltAngle;
	}

	public BigDecimal getPhaseACurrentAngle() {
		return phaseACurrentAngle;
	}

	public void setPhaseACurrentAngle(BigDecimal phaseACurrentAngle) {
		this.phaseACurrentAngle = phaseACurrentAngle;
	}

	public BigDecimal getPhaseBCurrentAngle() {
		return phaseBCurrentAngle;
	}

	public void setPhaseBCurrentAngle(BigDecimal phaseBCurrentAngle) {
		this.phaseBCurrentAngle = phaseBCurrentAngle;
	}

	public BigDecimal getPhaseCCurrentAngle() {
		return phaseCCurrentAngle;
	}

	public void setPhaseCCurrentAngle(BigDecimal phaseCCurrentAngle) {
		this.phaseCCurrentAngle = phaseCCurrentAngle;
	}

	public BigDecimal getThreePhasePF() {
		return threePhasePF;
	}

	public void setThreePhasePF(BigDecimal threePhasePF) {
		this.threePhasePF = threePhasePF;
	}

	public BigDecimal getFrequency() {
		return frequency;
	}

	public void setFrequency(BigDecimal frequency) {
		this.frequency = frequency;
	}

	public BigDecimal getActivePowerA() {
		return activePowerA;
	}

	public void setActivePowerA(BigDecimal activePowerA) {
		this.activePowerA = activePowerA;
	}

	public BigDecimal getActivePowerB() {
		return activePowerB;
	}

	public void setActivePowerB(BigDecimal activePowerB) {
		this.activePowerB = activePowerB;
	}

	public BigDecimal getActivePowerC() {
		return activePowerC;
	}

	public void setActivePowerC(BigDecimal activePowerC) {
		this.activePowerC = activePowerC;
	}

	public BigDecimal getActivePowerTotal() {
		return activePowerTotal;
	}

	public void setActivePowerTotal(BigDecimal activePowerTotal) {
		this.activePowerTotal = activePowerTotal;
	}

	public BigDecimal getActivePowerFunA() {
		return activePowerFunA;
	}

	public void setActivePowerFunA(BigDecimal activePowerFunA) {
		this.activePowerFunA = activePowerFunA;
	}

	public BigDecimal getActivePowerFunB() {
		return activePowerFunB;
	}

	public void setActivePowerFunB(BigDecimal activePowerFunB) {
		this.activePowerFunB = activePowerFunB;
	}

	public BigDecimal getActivePowerFunC() {
		return activePowerFunC;
	}

	public void setActivePowerFunC(BigDecimal activePowerFunC) {
		this.activePowerFunC = activePowerFunC;
	}

	public BigDecimal getActivePowerFunTotal() {
		return activePowerFunTotal;
	}

	public void setActivePowerFunTotal(BigDecimal activePowerFunTotal) {
		this.activePowerFunTotal = activePowerFunTotal;
	}

	public BigDecimal getApparentPowerA() {
		return apparentPowerA;
	}

	public void setApparentPowerA(BigDecimal apparentPowerA) {
		this.apparentPowerA = apparentPowerA;
	}

	public BigDecimal getApparentPowerB() {
		return apparentPowerB;
	}

	public void setApparentPowerB(BigDecimal apparentPowerB) {
		this.apparentPowerB = apparentPowerB;
	}

	public BigDecimal getApparentPowerC() {
		return apparentPowerC;
	}

	public void setApparentPowerC(BigDecimal apparentPowerC) {
		this.apparentPowerC = apparentPowerC;
	}

	public BigDecimal getApparentPowerTotal() {
		return apparentPowerTotal;
	}

	public void setApparentPowerTotal(BigDecimal apparentPowerTotal) {
		this.apparentPowerTotal = apparentPowerTotal;
	}

	public BigDecimal getReactivePowerA() {
		return reactivePowerA;
	}

	public void setReactivePowerA(BigDecimal reactivePowerA) {
		this.reactivePowerA = reactivePowerA;
	}

	public BigDecimal getReactivePowerB() {
		return reactivePowerB;
	}

	public void setReactivePowerB(BigDecimal reactivePowerB) {
		this.reactivePowerB = reactivePowerB;
	}

	public BigDecimal getReactivePowerC() {
		return reactivePowerC;
	}

	public void setReactivePowerC(BigDecimal reactivePowerC) {
		this.reactivePowerC = reactivePowerC;
	}

	public BigDecimal getReactivePowerTotal() {
		return reactivePowerTotal;
	}

	public void setReactivePowerTotal(BigDecimal reactivePowerTotal) {
		this.reactivePowerTotal = reactivePowerTotal;
	}

	public BigDecimal getTouChannel1Unified() {
		return touChannel1Unified;
	}

	public void setTouChannel1Unified(BigDecimal touChannel1Unified) {
		this.touChannel1Unified = touChannel1Unified;
	}

	public BigDecimal getTouChannel1Rate1() {
		return touChannel1Rate1;
	}

	public void setTouChannel1Rate1(BigDecimal touChannel1Rate1) {
		this.touChannel1Rate1 = touChannel1Rate1;
	}

	public BigDecimal getTouChannel1Rate2() {
		return touChannel1Rate2;
	}

	public void setTouChannel1Rate2(BigDecimal touChannel1Rate2) {
		this.touChannel1Rate2 = touChannel1Rate2;
	}

	public BigDecimal getTouChannel1Rate3() {
		return touChannel1Rate3;
	}

	public void setTouChannel1Rate3(BigDecimal touChannel1Rate3) {
		this.touChannel1Rate3 = touChannel1Rate3;
	}

	public BigDecimal getTouChannel1Rate4() {
		return touChannel1Rate4;
	}

	public void setTouChannel1Rate4(BigDecimal touChannel1Rate4) {
		this.touChannel1Rate4 = touChannel1Rate4;
	}

	public BigDecimal getTouChannel1Rate5() {
		return touChannel1Rate5;
	}

	public void setTouChannel1Rate5(BigDecimal touChannel1Rate5) {
		this.touChannel1Rate5 = touChannel1Rate5;
	}

	public BigDecimal getTouChannel2Unified() {
		return touChannel2Unified;
	}

	public void setTouChannel2Unified(BigDecimal touChannel2Unified) {
		this.touChannel2Unified = touChannel2Unified;
	}

	public BigDecimal getTouChannel2Rate1() {
		return touChannel2Rate1;
	}

	public void setTouChannel2Rate1(BigDecimal touChannel2Rate1) {
		this.touChannel2Rate1 = touChannel2Rate1;
	}

	public BigDecimal getTouChannel2Rate2() {
		return touChannel2Rate2;
	}

	public void setTouChannel2Rate2(BigDecimal touChannel2Rate2) {
		this.touChannel2Rate2 = touChannel2Rate2;
	}

	public BigDecimal getTouChannel2Rate3() {
		return touChannel2Rate3;
	}

	public void setTouChannel2Rate3(BigDecimal touChannel2Rate3) {
		this.touChannel2Rate3 = touChannel2Rate3;
	}

	public BigDecimal getTouChannel2Rate4() {
		return touChannel2Rate4;
	}

	public void setTouChannel2Rate4(BigDecimal touChannel2Rate4) {
		this.touChannel2Rate4 = touChannel2Rate4;
	}

	public BigDecimal getTouChannel2Rate5() {
		return touChannel2Rate5;
	}

	public void setTouChannel2Rate5(BigDecimal touChannel2Rate5) {
		this.touChannel2Rate5 = touChannel2Rate5;
	}

	public BigDecimal getTouChannel3Unified() {
		return touChannel3Unified;
	}

	public void setTouChannel3Unified(BigDecimal touChannel3Unified) {
		this.touChannel3Unified = touChannel3Unified;
	}

	public BigDecimal getTouChannel3Rate1() {
		return touChannel3Rate1;
	}

	public void setTouChannel3Rate1(BigDecimal touChannel3Rate1) {
		this.touChannel3Rate1 = touChannel3Rate1;
	}

	public BigDecimal getTouChannel3Rate2() {
		return touChannel3Rate2;
	}

	public void setTouChannel3Rate2(BigDecimal touChannel3Rate2) {
		this.touChannel3Rate2 = touChannel3Rate2;
	}

	public BigDecimal getTouChannel3Rate3() {
		return touChannel3Rate3;
	}

	public void setTouChannel3Rate3(BigDecimal touChannel3Rate3) {
		this.touChannel3Rate3 = touChannel3Rate3;
	}

	public BigDecimal getTouChannel3Rate4() {
		return touChannel3Rate4;
	}

	public void setTouChannel3Rate4(BigDecimal touChannel3Rate4) {
		this.touChannel3Rate4 = touChannel3Rate4;
	}

	public BigDecimal getTouChannel3Rate5() {
		return touChannel3Rate5;
	}

	public void setTouChannel3Rate5(BigDecimal touChannel3Rate5) {
		this.touChannel3Rate5 = touChannel3Rate5;
	}

	public BigDecimal getTouChannel4Unified() {
		return touChannel4Unified;
	}

	public void setTouChannel4Unified(BigDecimal touChannel4Unified) {
		this.touChannel4Unified = touChannel4Unified;
	}

	public BigDecimal getTouChannel4Rate1() {
		return touChannel4Rate1;
	}

	public void setTouChannel4Rate1(BigDecimal touChannel4Rate1) {
		this.touChannel4Rate1 = touChannel4Rate1;
	}

	public BigDecimal getTouChannel4Rate2() {
		return touChannel4Rate2;
	}

	public void setTouChannel4Rate2(BigDecimal touChannel4Rate2) {
		this.touChannel4Rate2 = touChannel4Rate2;
	}

	public BigDecimal getTouChannel4Rate3() {
		return touChannel4Rate3;
	}

	public void setTouChannel4Rate3(BigDecimal touChannel4Rate3) {
		this.touChannel4Rate3 = touChannel4Rate3;
	}

	public BigDecimal getTouChannel4Rate4() {
		return touChannel4Rate4;
	}

	public void setTouChannel4Rate4(BigDecimal touChannel4Rate4) {
		this.touChannel4Rate4 = touChannel4Rate4;
	}

	public BigDecimal getTouChannel4Rate5() {
		return touChannel4Rate5;
	}

	public void setTouChannel4Rate5(BigDecimal touChannel4Rate5) {
		this.touChannel4Rate5 = touChannel4Rate5;
	}

	public BigDecimal getTouChannel5Unified() {
		return touChannel5Unified;
	}

	public void setTouChannel5Unified(BigDecimal touChannel5Unified) {
		this.touChannel5Unified = touChannel5Unified;
	}

	public BigDecimal getTouChannel5Rate1() {
		return touChannel5Rate1;
	}

	public void setTouChannel5Rate1(BigDecimal touChannel5Rate1) {
		this.touChannel5Rate1 = touChannel5Rate1;
	}

	public BigDecimal getTouChannel5Rate2() {
		return touChannel5Rate2;
	}

	public void setTouChannel5Rate2(BigDecimal touChannel5Rate2) {
		this.touChannel5Rate2 = touChannel5Rate2;
	}

	public BigDecimal getTouChannel5Rate3() {
		return touChannel5Rate3;
	}

	public void setTouChannel5Rate3(BigDecimal touChannel5Rate3) {
		this.touChannel5Rate3 = touChannel5Rate3;
	}

	public BigDecimal getTouChannel5Rate4() {
		return touChannel5Rate4;
	}

	public void setTouChannel5Rate4(BigDecimal touChannel5Rate4) {
		this.touChannel5Rate4 = touChannel5Rate4;
	}

	public BigDecimal getTouChannel5Rate5() {
		return touChannel5Rate5;
	}

	public void setTouChannel5Rate5(BigDecimal touChannel5Rate5) {
		this.touChannel5Rate5 = touChannel5Rate5;
	}

	public BigDecimal getTouChannel6Unified() {
		return touChannel6Unified;
	}

	public void setTouChannel6Unified(BigDecimal touChannel6Unified) {
		this.touChannel6Unified = touChannel6Unified;
	}

	public BigDecimal getTouChannel6Rate1() {
		return touChannel6Rate1;
	}

	public void setTouChannel6Rate1(BigDecimal touChannel6Rate1) {
		this.touChannel6Rate1 = touChannel6Rate1;
	}

	public BigDecimal getTouChannel6Rate2() {
		return touChannel6Rate2;
	}

	public void setTouChannel6Rate2(BigDecimal touChannel6Rate2) {
		this.touChannel6Rate2 = touChannel6Rate2;
	}

	public BigDecimal getTouChannel6Rate3() {
		return touChannel6Rate3;
	}

	public void setTouChannel6Rate3(BigDecimal touChannel6Rate3) {
		this.touChannel6Rate3 = touChannel6Rate3;
	}

	public BigDecimal getTouChannel6Rate4() {
		return touChannel6Rate4;
	}

	public void setTouChannel6Rate4(BigDecimal touChannel6Rate4) {
		this.touChannel6Rate4 = touChannel6Rate4;
	}

	public BigDecimal getTouChannel6Rate5() {
		return touChannel6Rate5;
	}

	public void setTouChannel6Rate5(BigDecimal touChannel6Rate5) {
		this.touChannel6Rate5 = touChannel6Rate5;
	}

	public BigDecimal getTouChannel7Unified() {
		return touChannel7Unified;
	}

	public void setTouChannel7Unified(BigDecimal touChannel7Unified) {
		this.touChannel7Unified = touChannel7Unified;
	}

	public BigDecimal getTouChannel7Rate1() {
		return touChannel7Rate1;
	}

	public void setTouChannel7Rate1(BigDecimal touChannel7Rate1) {
		this.touChannel7Rate1 = touChannel7Rate1;
	}

	public BigDecimal getTouChannel7Rate2() {
		return touChannel7Rate2;
	}

	public void setTouChannel7Rate2(BigDecimal touChannel7Rate2) {
		this.touChannel7Rate2 = touChannel7Rate2;
	}

	public BigDecimal getTouChannel7Rate3() {
		return touChannel7Rate3;
	}

	public void setTouChannel7Rate3(BigDecimal touChannel7Rate3) {
		this.touChannel7Rate3 = touChannel7Rate3;
	}

	public BigDecimal getTouChannel7Rate4() {
		return touChannel7Rate4;
	}

	public void setTouChannel7Rate4(BigDecimal touChannel7Rate4) {
		this.touChannel7Rate4 = touChannel7Rate4;
	}

	public BigDecimal getTouChannel7Rate5() {
		return touChannel7Rate5;
	}

	public void setTouChannel7Rate5(BigDecimal touChannel7Rate5) {
		this.touChannel7Rate5 = touChannel7Rate5;
	}

	public BigDecimal getTouChannel8Unified() {
		return touChannel8Unified;
	}

	public void setTouChannel8Unified(BigDecimal touChannel8Unified) {
		this.touChannel8Unified = touChannel8Unified;
	}

	public BigDecimal getTouChannel8Rate1() {
		return touChannel8Rate1;
	}

	public void setTouChannel8Rate1(BigDecimal touChannel8Rate1) {
		this.touChannel8Rate1 = touChannel8Rate1;
	}

	public BigDecimal getTouChannel8Rate2() {
		return touChannel8Rate2;
	}

	public void setTouChannel8Rate2(BigDecimal touChannel8Rate2) {
		this.touChannel8Rate2 = touChannel8Rate2;
	}

	public BigDecimal getTouChannel8Rate3() {
		return touChannel8Rate3;
	}

	public void setTouChannel8Rate3(BigDecimal touChannel8Rate3) {
		this.touChannel8Rate3 = touChannel8Rate3;
	}

	public BigDecimal getTouChannel8Rate4() {
		return touChannel8Rate4;
	}

	public void setTouChannel8Rate4(BigDecimal touChannel8Rate4) {
		this.touChannel8Rate4 = touChannel8Rate4;
	}

	public BigDecimal getTouChannel8Rate5() {
		return touChannel8Rate5;
	}

	public void setTouChannel8Rate5(BigDecimal touChannel8Rate5) {
		this.touChannel8Rate5 = touChannel8Rate5;
	}

	public BigDecimal getTouChannel9Unified() {
		return touChannel9Unified;
	}

	public void setTouChannel9Unified(BigDecimal touChannel9Unified) {
		this.touChannel9Unified = touChannel9Unified;
	}

	public BigDecimal getTouChannel10Unified() {
		return touChannel10Unified;
	}

	public void setTouChannel10Unified(BigDecimal touChannel10Unified) {
		this.touChannel10Unified = touChannel10Unified;
	}

	public BigDecimal getTouChannel11Unified() {
		return touChannel11Unified;
	}

	public void setTouChannel11Unified(BigDecimal touChannel11Unified) {
		this.touChannel11Unified = touChannel11Unified;
	}

	public BigDecimal getTouChannel12Unified() {
		return touChannel12Unified;
	}

	public void setTouChannel12Unified(BigDecimal touChannel12Unified) {
		this.touChannel12Unified = touChannel12Unified;
	}

	public BigDecimal getDemandTouChannel1Rate1() {
		return demandTouChannel1Rate1;
	}

	public void setDemandTouChannel1Rate1(BigDecimal demandTouChannel1Rate1) {
		this.demandTouChannel1Rate1 = demandTouChannel1Rate1;
	}

	public BigDecimal getDemandTouChannel1Rate2() {
		return demandTouChannel1Rate2;
	}

	public void setDemandTouChannel1Rate2(BigDecimal demandTouChannel1Rate2) {
		this.demandTouChannel1Rate2 = demandTouChannel1Rate2;
	}

	public BigDecimal getDemandTouChannel1Rate3() {
		return demandTouChannel1Rate3;
	}

	public void setDemandTouChannel1Rate3(BigDecimal demandTouChannel1Rate3) {
		this.demandTouChannel1Rate3 = demandTouChannel1Rate3;
	}

	public BigDecimal getDemandTouChannel1Rate4() {
		return demandTouChannel1Rate4;
	}

	public void setDemandTouChannel1Rate4(BigDecimal demandTouChannel1Rate4) {
		this.demandTouChannel1Rate4 = demandTouChannel1Rate4;
	}

	public BigDecimal getDemandTouChannel1Rate5() {
		return demandTouChannel1Rate5;
	}

	public void setDemandTouChannel1Rate5(BigDecimal demandTouChannel1Rate5) {
		this.demandTouChannel1Rate5 = demandTouChannel1Rate5;
	}

	public BigDecimal getDemandTouChannel2Rate1() {
		return demandTouChannel2Rate1;
	}

	public void setDemandTouChannel2Rate1(BigDecimal demandTouChannel2Rate1) {
		this.demandTouChannel2Rate1 = demandTouChannel2Rate1;
	}

	public BigDecimal getDemandTouChannel2Rate2() {
		return demandTouChannel2Rate2;
	}

	public void setDemandTouChannel2Rate2(BigDecimal demandTouChannel2Rate2) {
		this.demandTouChannel2Rate2 = demandTouChannel2Rate2;
	}

	public BigDecimal getDemandTouChannel2Rate3() {
		return demandTouChannel2Rate3;
	}

	public void setDemandTouChannel2Rate3(BigDecimal demandTouChannel2Rate3) {
		this.demandTouChannel2Rate3 = demandTouChannel2Rate3;
	}

	public BigDecimal getDemandTouChannel2Rate4() {
		return demandTouChannel2Rate4;
	}

	public void setDemandTouChannel2Rate4(BigDecimal demandTouChannel2Rate4) {
		this.demandTouChannel2Rate4 = demandTouChannel2Rate4;
	}

	public BigDecimal getDemandTouChannel2Rate5() {
		return demandTouChannel2Rate5;
	}

	public void setDemandTouChannel2Rate5(BigDecimal demandTouChannel2Rate5) {
		this.demandTouChannel2Rate5 = demandTouChannel2Rate5;
	}

	public BigDecimal getDemandTouChannel3Rate1() {
		return demandTouChannel3Rate1;
	}

	public void setDemandTouChannel3Rate1(BigDecimal demandTouChannel3Rate1) {
		this.demandTouChannel3Rate1 = demandTouChannel3Rate1;
	}

	public BigDecimal getDemandTouChannel3Rate2() {
		return demandTouChannel3Rate2;
	}

	public void setDemandTouChannel3Rate2(BigDecimal demandTouChannel3Rate2) {
		this.demandTouChannel3Rate2 = demandTouChannel3Rate2;
	}

	public BigDecimal getDemandTouChannel3Rate3() {
		return demandTouChannel3Rate3;
	}

	public void setDemandTouChannel3Rate3(BigDecimal demandTouChannel3Rate3) {
		this.demandTouChannel3Rate3 = demandTouChannel3Rate3;
	}

	public BigDecimal getDemandTouChannel3Rate4() {
		return demandTouChannel3Rate4;
	}

	public void setDemandTouChannel3Rate4(BigDecimal demandTouChannel3Rate4) {
		this.demandTouChannel3Rate4 = demandTouChannel3Rate4;
	}

	public BigDecimal getDemandTouChannel3Rate5() {
		return demandTouChannel3Rate5;
	}

	public void setDemandTouChannel3Rate5(BigDecimal demandTouChannel3Rate5) {
		this.demandTouChannel3Rate5 = demandTouChannel3Rate5;
	}

	public BigDecimal getDemandTouChannel4Rate1() {
		return demandTouChannel4Rate1;
	}

	public void setDemandTouChannel4Rate1(BigDecimal demandTouChannel4Rate1) {
		this.demandTouChannel4Rate1 = demandTouChannel4Rate1;
	}

	public BigDecimal getDemandTouChannel4Rate2() {
		return demandTouChannel4Rate2;
	}

	public void setDemandTouChannel4Rate2(BigDecimal demandTouChannel4Rate2) {
		this.demandTouChannel4Rate2 = demandTouChannel4Rate2;
	}

	public BigDecimal getDemandTouChannel4Rate3() {
		return demandTouChannel4Rate3;
	}

	public void setDemandTouChannel4Rate3(BigDecimal demandTouChannel4Rate3) {
		this.demandTouChannel4Rate3 = demandTouChannel4Rate3;
	}

	public BigDecimal getDemandTouChannel4Rate4() {
		return demandTouChannel4Rate4;
	}

	public void setDemandTouChannel4Rate4(BigDecimal demandTouChannel4Rate4) {
		this.demandTouChannel4Rate4 = demandTouChannel4Rate4;
	}

	public BigDecimal getDemandTouChannel4Rate5() {
		return demandTouChannel4Rate5;
	}

	public void setDemandTouChannel4Rate5(BigDecimal demandTouChannel4Rate5) {
		this.demandTouChannel4Rate5 = demandTouChannel4Rate5;
	}

	public Date getDateChannel1Unified() {
		return dateChannel1Unified;
	}

	public void setDateChannel1Unified(Date dateChannel1Unified) {
		this.dateChannel1Unified = dateChannel1Unified;
	}

	public Date getDateChannel2Unified() {
		return dateChannel2Unified;
	}

	public void setDateChannel2Unified(Date dateChannel2Unified) {
		this.dateChannel2Unified = dateChannel2Unified;
	}

	public Date getDateChannel1Rate1() {
		return dateChannel1Rate1;
	}

	public void setDateChannel1Rate1(Date dateChannel1Rate1) {
		this.dateChannel1Rate1 = dateChannel1Rate1;
	}

	public Date getDateChannel1Rate2() {
		return dateChannel1Rate2;
	}

	public void setDateChannel1Rate2(Date dateChannel1Rate2) {
		this.dateChannel1Rate2 = dateChannel1Rate2;
	}

	public Date getDateChannel1Rate3() {
		return dateChannel1Rate3;
	}

	public void setDateChannel1Rate3(Date dateChannel1Rate3) {
		this.dateChannel1Rate3 = dateChannel1Rate3;
	}

	public Date getDateChannel1Rate4() {
		return dateChannel1Rate4;
	}

	public void setDateChannel1Rate4(Date dateChannel1Rate4) {
		this.dateChannel1Rate4 = dateChannel1Rate4;
	}

	public Date getDateChannel1Rate5() {
		return dateChannel1Rate5;
	}

	public void setDateChannel1Rate5(Date dateChannel1Rate5) {
		this.dateChannel1Rate5 = dateChannel1Rate5;
	}

	public Date getDateChannel2Rate1() {
		return dateChannel2Rate1;
	}

	public void setDateChannel2Rate1(Date dateChannel2Rate1) {
		this.dateChannel2Rate1 = dateChannel2Rate1;
	}

	public Date getDateChannel2Rate2() {
		return dateChannel2Rate2;
	}

	public void setDateChannel2Rate2(Date dateChannel2Rate2) {
		this.dateChannel2Rate2 = dateChannel2Rate2;
	}

	public Date getDateChannel2Rate3() {
		return dateChannel2Rate3;
	}

	public void setDateChannel2Rate3(Date dateChannel2Rate3) {
		this.dateChannel2Rate3 = dateChannel2Rate3;
	}

	public Date getDateChannel2Rate4() {
		return dateChannel2Rate4;
	}

	public void setDateChannel2Rate4(Date dateChannel2Rate4) {
		this.dateChannel2Rate4 = dateChannel2Rate4;
	}

	public Date getDateChannel2Rate5() {
		return dateChannel2Rate5;
	}

	public void setDateChannel2Rate5(Date dateChannel2Rate5) {
		this.dateChannel2Rate5 = dateChannel2Rate5;
	}

	public Date getDateChannel3Unified() {
		return dateChannel3Unified;
	}

	public void setDateChannel3Unified(Date dateChannel3Unified) {
		this.dateChannel3Unified = dateChannel3Unified;
	}

	public Date getDateChannel4Unified() {
		return dateChannel4Unified;
	}

	public void setDateChannel4Unified(Date dateChannel4Unified) {
		this.dateChannel4Unified = dateChannel4Unified;
	}

	public long getMDResetCode() {
		return MDResetCode;
	}

	public void setMDResetCode(long mDResetCode) {
		MDResetCode = mDResetCode;
	}

	public BigDecimal getCTPrimary() {
		return CTPrimary;
	}

	public void setCTPrimary(BigDecimal cTPrimary) {
		CTPrimary = cTPrimary;
	}

	public BigDecimal getCTSecondary() {
		return CTSecondary;
	}

	public void setCTSecondary(BigDecimal cTSecondary) {
		CTSecondary = cTSecondary;
	}

	public BigDecimal getVTPrimary() {
		return VTPrimary;
	}

	public void setVTPrimary(BigDecimal vTPrimary) {
		VTPrimary = vTPrimary;
	}

	@Column(precision=EAUtil.DECIMAL_PRECESION_INSTANT_REGISTERS,scale=EAUtil.DECIMAL_SCALE_INSTANT_REGISTERS)
	private BigDecimal VTSecondary;
	
    @Column
	private long powerOnHours;
	
    
    @Column
	private long powerOffHours;
	
    @Column
	private Integer element;
	@Column
	private String batteryStatus;
	@Column
	private String rtcStatus;
	@Column
	private String nonVolatileStatus;
	@Column
	private String displaySegmentStatus;
   @Column 
   private String filename;


@Column
private int year;


@Column
private int monthOfYear;


@Column
private int dayOfMonth;
   public InstantRegisters() {
		
	}
   
  // LocationMaster, Date, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, Date, Date, Date, Date, Date, Date, Date, Date, Date, Date, Date, Date, Date, Date, long, BigDecimal, BigDecimal, BigDecimal, BigDecimal, long, long, Integer, String, String, String, String, String) 
  // LocationMaster, Date, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, Date, Date, Date, Date, Date, Date, Date, Date, Date, Date, Date, Date, Date, Date, long, BigDecimal, BigDecimal, BigDecimal, BigDecimal, long, long, Integer, String, String, String, String, String)
  // LocationMaster, Date, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, Date, Date, Date, Date, Date, Date, Date, Date, Date, Date, Date, Date, Date, Date, long, BigDecimal, BigDecimal, BigDecimal, BigDecimal, long, long, Integer, String, String, String, String, String
	    
   
    public InstantRegisters(LocationMaster location, Date transactionDate, BigDecimal phaseAVoltage, BigDecimal phaseBVoltage,
		BigDecimal phaseCVoltage, BigDecimal phaseACurrent, BigDecimal phaseBCurrent, BigDecimal phaseCCurrent,
		BigDecimal phaseAVoltAngle, BigDecimal phaseBVoltAngle, BigDecimal phaseCVoltAngle,
		BigDecimal phaseACurrentAngle, BigDecimal phaseBCurrentAngle, BigDecimal phaseCCurrentAngle,
		BigDecimal threePhasePF, BigDecimal frequency, 
		BigDecimal activePowerA, BigDecimal activePowerB,
		BigDecimal activePowerC, BigDecimal activePowerTotal,
		
		BigDecimal activePowerFunA, BigDecimal activePowerFunB,
		BigDecimal activePowerFunC, BigDecimal activePowerFunTotal,
		
		BigDecimal apparentPowerA,
		BigDecimal apparentPowerB, BigDecimal apparentPowerC, BigDecimal apparentPowerTotal,
		
		
		BigDecimal reactivePowerA,
		BigDecimal reactivePowerB, BigDecimal reactivePowerC, BigDecimal reactivePowerTotal,
		
		
		
		BigDecimal touChannel1Unified, BigDecimal touChannel1Rate1, BigDecimal touChannel1Rate2,
		BigDecimal touChannel1Rate3, BigDecimal touChannel1Rate4, BigDecimal touChannel1Rate5,
		
		
		BigDecimal touChannel2Unified, BigDecimal touChannel2Rate1, BigDecimal touChannel2Rate2,
		BigDecimal touChannel2Rate3, BigDecimal touChannel2Rate4, BigDecimal touChannel2Rate5,
		
		BigDecimal touChannel3Unified, BigDecimal touChannel3Rate1, BigDecimal touChannel3Rate2,
		BigDecimal touChannel3Rate3, BigDecimal touChannel3Rate4, BigDecimal touChannel3Rate5,
		BigDecimal touChannel4Unified, BigDecimal touChannel4Rate1, BigDecimal touChannel4Rate2,
		BigDecimal touChannel4Rate3, BigDecimal touChannel4Rate4, BigDecimal touChannel4Rate5,
		BigDecimal touChannel5Unified, BigDecimal touChannel5Rate1, BigDecimal touChannel5Rate2,
		BigDecimal touChannel5Rate3, BigDecimal touChannel5Rate4, BigDecimal touChannel5Rate5,
		BigDecimal touChannel6Unified, BigDecimal touChannel6Rate1, BigDecimal touChannel6Rate2,
		BigDecimal touChannel6Rate3, BigDecimal touChannel6Rate4, BigDecimal touChannel6Rate5,
		BigDecimal touChannel7Unified, BigDecimal touChannel7Rate1, BigDecimal touChannel7Rate2,
		BigDecimal touChannel7Rate3, BigDecimal touChannel7Rate4, BigDecimal touChannel7Rate5,
		BigDecimal touChannel8Unified, BigDecimal touChannel8Rate1, BigDecimal touChannel8Rate2,
		BigDecimal touChannel8Rate3, BigDecimal touChannel8Rate4, BigDecimal touChannel8Rate5,
		
		
		BigDecimal touChannel9Unified, BigDecimal touChannel10Unified, BigDecimal touChannel11Unified,
		BigDecimal touChannel12Unified, BigDecimal demandChannel1Unified, BigDecimal demandChannel2Unified,
		BigDecimal demandChannel3Unified, BigDecimal demandChannel4Unified, BigDecimal demandTouChannel1Rate1,
		BigDecimal demandTouChannel1Rate2, BigDecimal demandTouChannel1Rate3, BigDecimal demandTouChannel1Rate4,
		BigDecimal demandTouChannel1Rate5, BigDecimal demandTouChannel2Rate1, BigDecimal demandTouChannel2Rate2,
		BigDecimal demandTouChannel2Rate3, BigDecimal demandTouChannel2Rate4, BigDecimal demandTouChannel2Rate5,
		BigDecimal demandTouChannel3Rate1, BigDecimal demandTouChannel3Rate2, BigDecimal demandTouChannel3Rate3,
		BigDecimal demandTouChannel3Rate4, BigDecimal demandTouChannel3Rate5, BigDecimal demandTouChannel4Rate1,
		BigDecimal demandTouChannel4Rate2, BigDecimal demandTouChannel4Rate3, BigDecimal demandTouChannel4Rate4,
		BigDecimal demandTouChannel4Rate5, Date dateChannel1Unified, Date dateChannel2Unified, Date dateChannel1Rate1,
		Date dateChannel1Rate2, Date dateChannel1Rate3, Date dateChannel1Rate4, Date dateChannel1Rate5,
		Date dateChannel2Rate1, Date dateChannel2Rate2, Date dateChannel2Rate3, Date dateChannel2Rate4,
		Date dateChannel2Rate5, Date dateChannel3Unified, Date dateChannel4Unified, long mDResetCode,
		BigDecimal cTPrimary, BigDecimal cTSecondary, BigDecimal vTPrimary, BigDecimal vTSecondary, long powerOnHours,
		long powerOffHours, Integer element, String batteryStatus, String rtcStatus, String nonVolatileStatus,
		String displaySegmentStatus, String filename) {

	this.location = location;
	this.transactionDate = transactionDate;
	this.phaseAVoltage = phaseAVoltage;
	this.phaseBVoltage = phaseBVoltage;
	this.phaseCVoltage = phaseCVoltage;
	this.phaseACurrent = phaseACurrent;
	this.phaseBCurrent = phaseBCurrent;
	this.phaseCCurrent = phaseCCurrent;
	this.phaseAVoltAngle = phaseAVoltAngle;
	this.phaseBVoltAngle = phaseBVoltAngle;
	this.phaseCVoltAngle = phaseCVoltAngle;
	this.phaseACurrentAngle = phaseACurrentAngle;
	this.phaseBCurrentAngle = phaseBCurrentAngle;
	this.phaseCCurrentAngle = phaseCCurrentAngle;
	this.threePhasePF = threePhasePF;
	this.frequency = frequency;
	this.activePowerA = activePowerA;
	this.activePowerB = activePowerB;
	this.activePowerC = activePowerC;
	this.activePowerTotal = activePowerTotal;
	this.activePowerFunA = activePowerFunA;
	this.activePowerFunB = activePowerFunB;
	this.activePowerFunC = activePowerFunC;
	this.activePowerFunTotal = activePowerFunTotal;
	this.apparentPowerA = apparentPowerA;
	this.apparentPowerB = apparentPowerB;
	this.apparentPowerC = apparentPowerC;
	this.apparentPowerTotal = apparentPowerTotal;
	this.reactivePowerA = reactivePowerA;
	this.reactivePowerB = reactivePowerB;
	this.reactivePowerC = reactivePowerC;
	this.reactivePowerTotal = reactivePowerTotal;
	this.touChannel1Unified = touChannel1Unified;
	this.touChannel1Rate1 = touChannel1Rate1;
	this.touChannel1Rate2 = touChannel1Rate2;
	this.touChannel1Rate3 = touChannel1Rate3;
	this.touChannel1Rate4 = touChannel1Rate4;
	this.touChannel1Rate5 = touChannel1Rate5;
	this.touChannel2Unified = touChannel2Unified;
	this.touChannel2Rate1 = touChannel2Rate1;
	this.touChannel2Rate2 = touChannel2Rate2;
	this.touChannel2Rate3 = touChannel2Rate3;
	this.touChannel2Rate4 = touChannel2Rate4;
	this.touChannel2Rate5 = touChannel2Rate5;
	this.touChannel3Unified = touChannel3Unified;
	this.touChannel3Rate1 = touChannel3Rate1;
	this.touChannel3Rate2 = touChannel3Rate2;
	this.touChannel3Rate3 = touChannel3Rate3;
	this.touChannel3Rate4 = touChannel3Rate4;
	this.touChannel3Rate5 = touChannel3Rate5;
	this.touChannel4Unified = touChannel4Unified;
	this.touChannel4Rate1 = touChannel4Rate1;
	this.touChannel4Rate2 = touChannel4Rate2;
	this.touChannel4Rate3 = touChannel4Rate3;
	this.touChannel4Rate4 = touChannel4Rate4;
	this.touChannel4Rate5 = touChannel4Rate5;
	this.touChannel5Unified = touChannel5Unified;
	this.touChannel5Rate1 = touChannel5Rate1;
	this.touChannel5Rate2 = touChannel5Rate2;
	this.touChannel5Rate3 = touChannel5Rate3;
	this.touChannel5Rate4 = touChannel5Rate4;
	this.touChannel5Rate5 = touChannel5Rate5;
	this.touChannel6Unified = touChannel6Unified;
	this.touChannel6Rate1 = touChannel6Rate1;
	this.touChannel6Rate2 = touChannel6Rate2;
	this.touChannel6Rate3 = touChannel6Rate3;
	this.touChannel6Rate4 = touChannel6Rate4;
	this.touChannel6Rate5 = touChannel6Rate5;
	this.touChannel7Unified = touChannel7Unified;
	this.touChannel7Rate1 = touChannel7Rate1;
	this.touChannel7Rate2 = touChannel7Rate2;
	this.touChannel7Rate3 = touChannel7Rate3;
	this.touChannel7Rate4 = touChannel7Rate4;
	this.touChannel7Rate5 = touChannel7Rate5;
	this.touChannel8Unified = touChannel8Unified;
	this.touChannel8Rate1 = touChannel8Rate1;
	this.touChannel8Rate2 = touChannel8Rate2;
	this.touChannel8Rate3 = touChannel8Rate3;
	this.touChannel8Rate4 = touChannel8Rate4;
	this.touChannel8Rate5 = touChannel8Rate5;
	this.touChannel9Unified = touChannel9Unified;
	this.touChannel10Unified = touChannel10Unified;
	this.touChannel11Unified = touChannel11Unified;
	this.touChannel12Unified = touChannel12Unified;
	this.demandChannel1Unified = demandChannel1Unified;
	this.demandChannel2Unified = demandChannel2Unified;
	this.demandChannel3Unified = demandChannel3Unified;
	this.demandChannel4Unified = demandChannel4Unified;
	this.demandTouChannel1Rate1 = demandTouChannel1Rate1;
	this.demandTouChannel1Rate2 = demandTouChannel1Rate2;
	this.demandTouChannel1Rate3 = demandTouChannel1Rate3;
	this.demandTouChannel1Rate4 = demandTouChannel1Rate4;
	this.demandTouChannel1Rate5 = demandTouChannel1Rate5;
	this.demandTouChannel2Rate1 = demandTouChannel2Rate1;
	this.demandTouChannel2Rate2 = demandTouChannel2Rate2;
	this.demandTouChannel2Rate3 = demandTouChannel2Rate3;
	this.demandTouChannel2Rate4 = demandTouChannel2Rate4;
	this.demandTouChannel2Rate5 = demandTouChannel2Rate5;
	this.demandTouChannel3Rate1 = demandTouChannel3Rate1;
	this.demandTouChannel3Rate2 = demandTouChannel3Rate2;
	this.demandTouChannel3Rate3 = demandTouChannel3Rate3;
	this.demandTouChannel3Rate4 = demandTouChannel3Rate4;
	this.demandTouChannel3Rate5 = demandTouChannel3Rate5;
	this.demandTouChannel4Rate1 = demandTouChannel4Rate1;
	this.demandTouChannel4Rate2 = demandTouChannel4Rate2;
	this.demandTouChannel4Rate3 = demandTouChannel4Rate3;
	this.demandTouChannel4Rate4 = demandTouChannel4Rate4;
	this.demandTouChannel4Rate5 = demandTouChannel4Rate5;
	this.dateChannel1Unified = dateChannel1Unified;
	this.dateChannel2Unified = dateChannel2Unified;
	this.dateChannel1Rate1 = dateChannel1Rate1;
	this.dateChannel1Rate2 = dateChannel1Rate2;
	this.dateChannel1Rate3 = dateChannel1Rate3;
	this.dateChannel1Rate4 = dateChannel1Rate4;
	this.dateChannel1Rate5 = dateChannel1Rate5;
	this.dateChannel2Rate1 = dateChannel2Rate1;
	this.dateChannel2Rate2 = dateChannel2Rate2;
	this.dateChannel2Rate3 = dateChannel2Rate3;
	this.dateChannel2Rate4 = dateChannel2Rate4;
	this.dateChannel2Rate5 = dateChannel2Rate5;
	this.dateChannel3Unified = dateChannel3Unified;
	this.dateChannel4Unified = dateChannel4Unified;
	MDResetCode = mDResetCode;
	CTPrimary = cTPrimary;
	CTSecondary = cTSecondary;
	VTPrimary = vTPrimary;
	VTSecondary = vTSecondary;
	this.powerOnHours = powerOnHours;
	this.powerOffHours = powerOffHours;
	this.element = element;
	this.batteryStatus = batteryStatus;
	this.rtcStatus = rtcStatus;
	this.nonVolatileStatus = nonVolatileStatus;
	this.displaySegmentStatus = displaySegmentStatus;
	this.filename = filename;
	Calendar cal = Calendar.getInstance();
	cal.setTime(transactionDate);
	this.setYear(cal.get(Calendar.YEAR));
	this.setMonthOfYear(cal.get(Calendar.MONTH));
	this.setDayOfMonth(cal.get(Calendar.DAY_OF_MONTH));
}
    
    
    
	/**
 * @return the demandChannel1Unified
 */
public BigDecimal getDemandChannel1Unified() {
	return demandChannel1Unified;
}
/**
 * @param demandChannel1Unified the demandChannel1Unified to set
 */
public void setDemandChannel1Unified(BigDecimal demandChannel1Unified) {
	this.demandChannel1Unified = demandChannel1Unified;
}
/**
 * @return the demandChannel2Unified
 */
public BigDecimal getDemandChannel2Unified() {
	return demandChannel2Unified;
}
/**
 * @param demandChannel2Unified the demandChannel2Unified to set
 */
public void setDemandChannel2Unified(BigDecimal demandChannel2Unified) {
	this.demandChannel2Unified = demandChannel2Unified;
}
/**
 * @return the demandChannel3Unified
 */
public BigDecimal getDemandChannel3Unified() {
	return demandChannel3Unified;
}
/**
 * @param demandChannel3Unified the demandChannel3Unified to set
 */
public void setDemandChannel3Unified(BigDecimal demandChannel3Unified) {
	this.demandChannel3Unified = demandChannel3Unified;
}
/**
 * @return the demandChannel4Unified
 */
public BigDecimal getDemandChannel4Unified() {
	return demandChannel4Unified;
}
/**
 * @param demandChannel4Unified the demandChannel4Unified to set
 */
public void setDemandChannel4Unified(BigDecimal demandChannel4Unified) {
	this.demandChannel4Unified = demandChannel4Unified;
}
	/**
 * @return the filename
 */
public String getFilename() {
	return filename;
}

/**
 * @param filename the filename to set
 */




	/**
	 * @return the batteryStatus
	 */
	public String getBatteryStatus() {
		return batteryStatus;
	}

	/**
	 * @param batteryStatus the batteryStatus to set
	 */
	public void setBatteryStatus(String batteryStatus) {
		this.batteryStatus = batteryStatus;
	}

	/**
	 * @return the rtcStatus
	 */
	public String getRtcStatus() {
		return rtcStatus;
	}

	/**
	 * @param rtcStatus the rtcStatus to set
	 */
	public void setRtcStatus(String rtcStatus) {
		this.rtcStatus = rtcStatus;
	}

	/**
	 * @return the nonVolatileStatus
	 */
	public String getNonVolatileStatus() {
		return nonVolatileStatus;
	}

	/**
	 * @param nonVolatileStatus the nonVolatileStatus to set
	 */
	public void setNonVolatileStatus(String nonVolatileStatus) {
		this.nonVolatileStatus = nonVolatileStatus;
	}

	/**
	 * @return the displaySegmentStatus
	 */
	public String getDisplaySegmentStatus() {
		return displaySegmentStatus;
	}

	/**
	 * @param displaySegmentStatus the displaySegmentStatus to set
	 */
	public void setDisplaySegmentStatus(String displaySegmentStatus) {
		this.displaySegmentStatus = displaySegmentStatus;
	}

	/**
	 * @return the element
	 */
	public Integer getElement() {
		return element;
	}

	
	
	/**
	 * @param element the element to set
	 */
	public void setElement(Integer element) {
		this.element = element;
	}

	/**
	 * @return the powerOffHours
	 */
	public long getPowerOffHours() {
		return powerOffHours;
	}

	/**
	 * @param powerOffHours the powerOffHours to set
	 */
	public void setPowerOffHours(long powerOffHours) {
		this.powerOffHours = powerOffHours;
	}

	/**
	 * @return the powerOnHours
	 */
	public long getPowerOnHours() {
		return powerOnHours;
	}

	/**
	 * @param powerOnHours the powerOnHours to set
	 */
	public void setPowerOnHours(long powerOnHours) {
		this.powerOnHours = powerOnHours;
	}

	/**
	 * @return the vTSecondary
	 */
	public BigDecimal getVTSecondary() {
		return VTSecondary;
	}

	/**
	 * @param vTSecondary the vTSecondary to set
	 */
	public void setVTSecondary(BigDecimal vTSecondary) {
		VTSecondary = vTSecondary;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getTxnId() {
		return txnId;
	}

	public void setTxnId(int txnId) {
		this.txnId = txnId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonthOfYear() {
		return monthOfYear;
	}

	public void setMonthOfYear(int monthOfYear) {
		this.monthOfYear = monthOfYear;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	//add parameters to show
	public void updateValues(InstantRegisters ir) {
		this.location = ir.location;
		this.transactionDate = ir.transactionDate;
		this.phaseAVoltage = ir.phaseAVoltage;
		this.phaseBVoltage = ir.phaseBVoltage;
		this.phaseCVoltage = ir.phaseCVoltage;
		this.phaseACurrent = ir.phaseACurrent;
		this.phaseBCurrent = ir.phaseBCurrent;
		this.phaseCCurrent = ir.phaseCCurrent;
		this.phaseAVoltAngle =ir.phaseAVoltAngle;
		this.phaseBVoltAngle =ir.phaseBVoltAngle;
		this.phaseCVoltAngle =ir.phaseCVoltAngle;
		this.phaseACurrentAngle =ir.phaseACurrentAngle;
		this.phaseBCurrentAngle = ir.phaseBCurrentAngle;
		this.phaseCCurrentAngle = ir.phaseCCurrentAngle;
		this.threePhasePF = ir.threePhasePF;
		this.frequency = ir.frequency;
		this.activePowerA = ir.activePowerA;
		this.activePowerB = ir.activePowerB;
		this.activePowerC = ir.activePowerC;
		this.activePowerTotal = ir.activePowerTotal;
		this.activePowerFunA = ir.activePowerFunA;
		this.activePowerFunB = ir.activePowerFunB;
		this.activePowerFunC = ir.activePowerFunC;
		this.activePowerFunTotal = ir.activePowerFunTotal;
		this.apparentPowerA = ir.apparentPowerA;
		this.apparentPowerB = ir.apparentPowerB;
		this.apparentPowerC = ir.apparentPowerC;
		this.apparentPowerTotal = ir.apparentPowerTotal;
		this.reactivePowerA = ir.reactivePowerA;
		this.reactivePowerB = ir.reactivePowerB;
		this.reactivePowerC = ir.reactivePowerC;
		this.reactivePowerTotal = ir.reactivePowerTotal;
		this.touChannel1Unified = ir.touChannel1Unified;
		this.touChannel1Rate1 = ir.touChannel1Rate1;
		this.touChannel1Rate2 = ir.touChannel1Rate2;
		this.touChannel1Rate3 = ir.touChannel1Rate3;
		this.touChannel1Rate4 = ir.touChannel1Rate4;
		this.touChannel1Rate5 = ir.touChannel1Rate5;
		this.touChannel2Unified = ir.touChannel2Unified;
		this.touChannel2Rate1 = ir.touChannel2Rate1;
		this.touChannel2Rate2 = ir.touChannel2Rate2;
		this.touChannel2Rate3 = ir.touChannel2Rate3;
		this.touChannel2Rate4 = ir.touChannel2Rate4;
		this.touChannel2Rate5 = ir.touChannel2Rate5;
		this.touChannel3Unified = ir.touChannel3Unified;
		this.touChannel3Rate1 = ir.touChannel3Rate1;
		this.touChannel3Rate2 = ir.touChannel3Rate2;
		this.touChannel3Rate3 = ir.touChannel3Rate3;
		this.touChannel3Rate4 = ir.touChannel3Rate4;
		this.touChannel3Rate5 = ir.touChannel3Rate5;
		this.touChannel4Unified = ir.touChannel4Unified;
		this.touChannel4Rate1 = ir.touChannel4Rate1;
		this.touChannel4Rate2 = ir.touChannel4Rate2;
		this.touChannel4Rate3 = ir.touChannel4Rate3;
		this.touChannel4Rate4 = ir.touChannel4Rate4;
		this.touChannel4Rate5 = ir.touChannel4Rate5;
		this.touChannel5Unified = ir.touChannel5Unified;
		this.touChannel5Rate1 = ir.touChannel5Rate1;
		this.touChannel5Rate2 = ir.touChannel5Rate2;
		this.touChannel5Rate3 = ir.touChannel5Rate3;
		this.touChannel5Rate4 = ir.touChannel5Rate4;
		this.touChannel5Rate5 = ir.touChannel5Rate5;
		this.touChannel6Unified = ir.touChannel6Unified;
		this.touChannel6Rate1 = ir.touChannel6Rate1;
		this.touChannel6Rate2 = ir.touChannel6Rate2;
		this.touChannel6Rate3 = ir.touChannel6Rate3;
		this.touChannel6Rate4 = ir.touChannel6Rate4;
		this.touChannel6Rate5 = ir.touChannel6Rate5;
		this.touChannel7Unified = ir.touChannel7Unified;
		this.touChannel7Rate1 = ir.touChannel7Rate1;
		this.touChannel7Rate2 = ir.touChannel7Rate2;
		this.touChannel7Rate3 = ir.touChannel7Rate3;
		this.touChannel7Rate4 = ir.touChannel7Rate4;
		this.touChannel7Rate5 = ir.touChannel7Rate5;
		this.touChannel8Unified = ir.touChannel8Unified;
		this.touChannel8Rate1 = ir.touChannel8Rate1;
		this.touChannel8Rate2 = ir.touChannel8Rate2;
		this.touChannel8Rate3 = ir.touChannel8Rate3;
		this.touChannel8Rate4 = ir.touChannel8Rate4;
		this.touChannel8Rate5 = ir.touChannel8Rate5;
		this.touChannel9Unified = ir.touChannel9Unified;
		this.touChannel10Unified = ir.touChannel10Unified;
		this.touChannel11Unified = ir.touChannel11Unified;
		this.touChannel12Unified = ir.touChannel12Unified;
		this.demandChannel1Unified = ir.demandChannel1Unified;
		this.demandChannel2Unified = ir.demandChannel2Unified;
		this.demandChannel3Unified = ir.demandChannel3Unified;
		this.demandChannel4Unified = ir.demandChannel4Unified;
		this.demandTouChannel1Rate1 = ir.demandTouChannel1Rate1;
		this.demandTouChannel1Rate2 = ir.demandTouChannel1Rate2;
		this.demandTouChannel1Rate3 = ir.demandTouChannel1Rate3;
		this.demandTouChannel1Rate4 = ir.demandTouChannel1Rate4;
		this.demandTouChannel1Rate5 = ir.demandTouChannel1Rate5;
		this.demandTouChannel2Rate1 = ir.demandTouChannel2Rate1;
		this.demandTouChannel2Rate2 = ir.demandTouChannel2Rate2;
		this.demandTouChannel2Rate3 = ir.demandTouChannel2Rate3;
		this.demandTouChannel2Rate4 = ir.demandTouChannel2Rate4;
		this.demandTouChannel2Rate5 = ir.demandTouChannel2Rate5;
		this.demandTouChannel3Rate1 = ir.demandTouChannel3Rate1;
		this.demandTouChannel3Rate2 = ir.demandTouChannel3Rate2;
		this.demandTouChannel3Rate3 = ir.demandTouChannel3Rate3;
		this.demandTouChannel3Rate4 = ir.demandTouChannel3Rate4;
		this.demandTouChannel3Rate5 = ir.demandTouChannel3Rate5;
		this.demandTouChannel4Rate1 = ir.demandTouChannel4Rate1;
		this.demandTouChannel4Rate2 = ir.demandTouChannel4Rate2;
		this.demandTouChannel4Rate3 = ir.demandTouChannel4Rate3;
		this.demandTouChannel4Rate4 = ir.demandTouChannel4Rate4;
		this.demandTouChannel4Rate5 = ir.demandTouChannel4Rate5;
		this.dateChannel1Unified = ir.dateChannel1Unified;
		this.dateChannel2Unified = ir.dateChannel2Unified;
		this.dateChannel1Rate1 = ir.dateChannel1Rate1;
		this.dateChannel1Rate2 = ir.dateChannel1Rate2;
		this.dateChannel1Rate3 = ir.dateChannel1Rate3;
		this.dateChannel1Rate4 = ir.dateChannel1Rate4;
		this.dateChannel1Rate5 = ir.dateChannel1Rate5;
		this.dateChannel2Rate1 = ir.dateChannel2Rate1;
		this.dateChannel2Rate2 = ir.dateChannel2Rate2;
		this.dateChannel2Rate3 = ir.dateChannel2Rate3;
		this.dateChannel2Rate4 = ir.dateChannel2Rate4;
		this.dateChannel2Rate5 = ir.dateChannel2Rate5;
		this.dateChannel3Unified = ir.dateChannel3Unified;
		this.dateChannel4Unified = ir.dateChannel4Unified;
		MDResetCode = ir.MDResetCode;
		CTPrimary = ir.CTPrimary;
		CTSecondary = ir.CTSecondary;
		VTPrimary = ir.VTPrimary;
		VTSecondary = ir.VTSecondary;
		this.powerOnHours = ir.powerOnHours;
		this.powerOffHours = ir.powerOffHours;
		this.element = ir.element;
		this.batteryStatus = ir.batteryStatus;
		this.rtcStatus = ir.rtcStatus;
		this.nonVolatileStatus = ir.nonVolatileStatus;
		this.displaySegmentStatus = ir.displaySegmentStatus;
		this.filename = ir.filename;
		
		this.year=ir.year;
		this.monthOfYear=ir.monthOfYear;
		this.dayOfMonth=ir.dayOfMonth;

	
	}
}

