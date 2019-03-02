package org.pstcl.ea.model.entity;

import org.hibernate.validator.constraints.NotEmpty;

public class UserEntityPasswordChange {

	@NotEmpty
	private String username;

	@NotEmpty
	private String userPassword;

	@NotEmpty
	private String newPassword1;

	@NotEmpty
	private String newPassword2;

	public String getNewPassword1() {
		return newPassword1;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public String getUsername() {
		return username;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
