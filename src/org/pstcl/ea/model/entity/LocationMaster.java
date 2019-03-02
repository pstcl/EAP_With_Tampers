package org.pstcl.ea.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the location_master database table.
 * 
 */
@Entity
@Table(name="location_master")
@NamedQuery(name="LocationMaster.findAll", query="SELECT l FROM LocationMaster l")
public class LocationMaster implements Serializable {


	
	
	
	@Column(precision=14,scale=2)
	private BigDecimal externalMF;
	private String externalPTRation;
	private String externalCTRation;
	
	@Column(length=45)
	public String getExternalCTRation() {
		return this.externalCTRation;
	}

	@Column(length=45)
	public String getExternalPTRation() {
		return this.externalPTRation;
	}
	

	public void setExternalCTRation(String externalCTRation) {
		this.externalCTRation = externalCTRation;
	}




	public void setExternalPTRation(String externalPTRation) {
		this.externalPTRation = externalPTRation;
	}
	
	//Location here is location of meter. i.e. Bay


	private static final long serialVersionUID = 1L;



	private BoundaryTypeMaster boundaryTypeMaster;


	private CircleMaster circleMaster;


	private DeviceTypeMaster deviceTypeMaster;
	private DivisionMaster divisionMaster;
	private FeederMaster feederMaster;
	private int id;
	//added in august list
	private String interfacePointId;
	private String location_status;
	private String locationId;
	private String lossReportCriteria;
	private Integer lossReportInclusion;

	private Integer netWHSign;
	private Integer lossReportOrder;


	@JsonIgnore
	private MeterMaster meterMaster;

	private SubstationMaster substationMaster;

	private String utiltiyName;

	private String voltageLevel;

	public LocationMaster() {
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocationMaster other = (LocationMaster) obj;
		if (locationId == null) {
			if (other.locationId != null)
				return false;
		} else if (!locationId.equals(other.locationId))
			return false;
		return true;
	}


	//bi-directional many-to-one association to BoundaryTypeMaster
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="BOUNDARY_CODE")
	public BoundaryTypeMaster getBoundaryTypeMaster() {
		return this.boundaryTypeMaster;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CR_code")
	public CircleMaster getCircleMaster() {
		return circleMaster;
	}

	//bi-directional many-to-one association to DeviceTypeMaster
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="DEVICE_TYPE_CODE")
	public DeviceTypeMaster getDeviceTypeMaster() {
		return this.deviceTypeMaster;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="div_code")
	public DivisionMaster getDivisionMaster() {
		return divisionMaster;
	}

	//bi-directional many-to-one association to FeederMaster
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="FEEDER_CODE")
	public FeederMaster getFeederMaster() {
		return this.feederMaster;
	}


	@Column(nullable=false)
	public int getId() {
		return this.id;
	}


	@Column
	public String getInterfacePointId() {
		return interfacePointId;
	}

	@Column(length=45)
	public String getLocation_status() {
		return this.location_status;
	}

	//Below are the fields in JULY list
	@Id
	@Column(unique=true, nullable=false, length=120)
	public String getLocationId() {
		return this.locationId;
	}





	@Column(length=45,name="LOSS_REPORT_CRITERIA")
	public String getLossReportCriteria() {
		return lossReportCriteria;
	}

	@Column(name = "LOSS_REPORT_INCLUSION")

	public Integer getLossReportInclusion() {
		return lossReportInclusion;
	}


	//bi-directional one-to-one association to MeterMaster
	@JsonIgnore
	@OneToOne(mappedBy="locationMaster", fetch=FetchType.EAGER)
	public MeterMaster getMeterMaster() {
		return this.meterMaster;
	}




	//bi-directional many-to-one association to SubstationMaster
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ss_code")
	public SubstationMaster getSubstationMaster() {
		return this.substationMaster;
	}

	@Column(length=45)
	public String getUtiltiyName() {
		return this.utiltiyName;
	}


	@Column(length=45)
	public String getVoltageLevel() {
		return this.voltageLevel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locationId == null) ? 0 : locationId.hashCode());
		return result;
	}


	public void setBoundaryTypeMaster(BoundaryTypeMaster boundaryTypeMaster) {
		this.boundaryTypeMaster = boundaryTypeMaster;
	}

	public void setCircleMaster(CircleMaster circleMaster) {
		this.circleMaster = circleMaster;
	}


	public void setDeviceTypeMaster(DeviceTypeMaster deviceTypeMaster) {
		this.deviceTypeMaster = deviceTypeMaster;
	}

	public void setDivisionMaster(DivisionMaster divisionMaster) {
		this.divisionMaster = divisionMaster;
	}


	public void setFeederMaster(FeederMaster feederMaster) {
		this.feederMaster = feederMaster;
	}

	public void setId(int id) {
		this.id = id;
	}


	public void setInterfacePointId(String interfacePointId) {
		this.interfacePointId = interfacePointId;
	}

	public void setLocation_status(String location_status) {
		this.location_status = location_status;
	}


	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public void setLossReportCriteria(String lossReportCriteria) {
		this.lossReportCriteria = lossReportCriteria;
	}


	public void setLossReportInclusion(Integer lossReportInclusion) {
		this.lossReportInclusion = lossReportInclusion;
	}

	public void setMeterMaster(MeterMaster meterMaster) {
		this.meterMaster = meterMaster;
	}





	public void setSubstationMaster(SubstationMaster substationMaster) {
		this.substationMaster = substationMaster;
	}


	public void setUtiltiyName(String utiltiyName) {
		this.utiltiyName = utiltiyName;
	}


	public void setVoltageLevel(String voltageLevel) {
		this.voltageLevel = voltageLevel;
	}

	@Column
	public Integer getNetWHSign() {
		return netWHSign;
	}

	public void setNetWHSign(Integer netWHSign) {
		this.netWHSign = netWHSign;
	}

	@Column
	public Integer getLossReportOrder() {
		return lossReportOrder;
	}

	public void setLossReportOrder(Integer lossReportOrder) {
		this.lossReportOrder = lossReportOrder;
	}

	
	public BigDecimal getExternalMF() {
		return externalMF;
	}

	public void setExternalMF(BigDecimal externalMF) {
		this.externalMF = externalMF;
	}

}