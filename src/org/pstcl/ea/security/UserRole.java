package org.pstcl.ea.security;

public enum UserRole {
	
	
	SS_JE(3, "ROLE_SS_JE"),
	SS_AE(4, "ROLE_SS_AE"),
	DIV_ASE(5, "ROLE_SR_XEN"),
	CIRCLE_SE(6, "ROLE_SE"),
	CHIEF_ENGG(7, "ROLE_CHIEF_ENGG"),
	
	SLDC_USER(15, "ROLE_SLDC_USER"),
	SLDC_ADMIN(20, "ROLE_SLDC_ADMIN");
	
	private Integer userRoleCode;
	private String userRoleName;
	private UserRole(Integer userProfileType, String userProfileLabel) {
		this.userRoleCode = userProfileType;
		this.userRoleName = userProfileLabel;
	}
	public Integer getUserRoleCode() {
		return userRoleCode;
	}
	public String getUserRoleName() {
		return userRoleName;
	}
	
	
//	public static String ROLE_JE="ROLE_SS_JE";
//	public static String ROLE_AE="ROLE_SS_AE";
//	public static String ROLE_ASE="ROLE_DIV_AE";
//	public static String ROLE_SLDC_USER="ROLE_SLDC_USER";
//	public static String ROLE_SLDC_ADMIN="ROLE_SLDC_ADMIN";
//	public static String ROLE_PSTCL_USER="ROLE_PSTCL_USER";
//	
//	public static Integer ROLE_CODE_JE=3;
//	public static Integer ROLE_CODE_AE=4;
//	public static Integer ROLE_CODE_ASE=5;
//	
//	public static Integer ROLE_CODE_SLDC_USER=6;
//	public static Integer ROLE_CODE_SLDC_ADMIN=7;
}
