package org.pstcl.ea.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the boundary_type_master database table.
 * 
 */
@Entity
@Table(name="boundary_type_master")
@NamedQuery(name="BoundaryTypeMaster.findAll", query="SELECT b FROM BoundaryTypeMaster b")
public class BoundaryTypeMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String boundaryType;
	private Boolean invertExportImportOnNegativeSign;
	
	public BoundaryTypeMaster() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(length=45)
	public String getBoundaryType() {
		return this.boundaryType;
	}

	public void setBoundaryType(String boundaryType) {
		this.boundaryType = boundaryType;
	}


	@Column
	public Boolean getInvertExportImportOnNegativeSign() {
		return invertExportImportOnNegativeSign;
	}


	public void setInvertExportImportOnNegativeSign(Boolean invertExportImportOnNegativeSign) {
		this.invertExportImportOnNegativeSign = invertExportImportOnNegativeSign;
	}


	


//	//bi-directional many-to-one association to LocationMaster
//	@OneToMany(mappedBy="boundaryTypeMaster")
//	public Set<LocationMaster> getLocationMasters() {
//		return this.locationMasters;
//	}
//
//	public void setLocationMasters(Set<LocationMaster> locationMasters) {
//		this.locationMasters = locationMasters;
//	}

//	public LocationMaster addLocationMaster(LocationMaster locationMaster) {
//		getLocationMasters().add(locationMaster);
//		locationMaster.setBoundaryTypeMaster(this);
//
//		return locationMaster;
//	}
//
//	public LocationMaster removeLocationMaster(LocationMaster locationMaster) {
//		getLocationMasters().remove(locationMaster);
//		locationMaster.setBoundaryTypeMaster(null);
//
//		return locationMaster;
//	}

}