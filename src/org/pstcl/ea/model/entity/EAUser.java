package org.pstcl.ea.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "user_master")
public class EAUser {
	
	@Transient
	public String getUserNameLabel()
	{
		return username;
	}

	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "userpassword")
	private String userpassword;


	@Column(name = "rolecode")
	private Integer rolecode;
	
	@Column(name = "FIRST_LOGIN")
	private Boolean firstLogin;

	@Column(name = "lastPasswordChangeDate")
	private Date lastPasswordChangeDate;

	
	public Date getLastPasswordChangeDate() {
		return lastPasswordChangeDate;
	}

	public void setLastPasswordChangeDate(Date lastPasswordChangeDate) {
		this.lastPasswordChangeDate = lastPasswordChangeDate;
	}

	public Boolean getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(Boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	@Column(name = "rolename")
	private String rolename;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ss_code")
	private SubstationMaster substationMaster;


	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CR_CODE")
	private CircleMaster circleMaster;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="DIV_CODE")
	private DivisionMaster divisionMaster;


	public CircleMaster getCircleMaster() {
		return circleMaster;
	}

	public void setCircleMaster(CircleMaster circleMaster) {
		this.circleMaster = circleMaster;
	}

	public DivisionMaster getDivisionMaster() {
		return divisionMaster;
	}

	public void setDivisionMaster(DivisionMaster divisionMaster) {
		this.divisionMaster = divisionMaster;
	}

	public void setRolecode(Integer rolecode) {
		this.rolecode = rolecode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public Integer getRolecode() {
		return rolecode;
	}

	public void setRolecode(int rolecode) {
		this.rolecode = rolecode;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	//bi-directional many-to-one association to SubstationMaster
	public SubstationMaster getSubstationMaster() {
		return this.substationMaster;
	}

	public void setSubstationMaster(SubstationMaster substationMaster) {
		this.substationMaster = substationMaster;
	}
}
