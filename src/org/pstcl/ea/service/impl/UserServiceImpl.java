package org.pstcl.ea.service.impl;

import java.util.Collection;
import java.util.Date;

import org.pstcl.ea.dao.EAUserDao;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.UserEntityPasswordChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("eaUserService")
@Transactional(value="sldcTxnManager")
public class UserServiceImpl {

	@Autowired
	EAUserDao userDao;

	public Boolean hasRole(String role) {
		Boolean hasRole=false;
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equalsIgnoreCase(role)) 
			{
				hasRole=true;	
			}
		}
		return hasRole;
	}


	public EAUser findByUsername(String username) {

		return userDao.findById(username);
	}



	public Boolean changePassword(UserEntityPasswordChange eaUserPasswordChange) {


		EAUser eaUser= getLoggedInUser();

		if(eaUserPasswordChange.getUsername().equals(eaUser.getUsername()))
		{


			eaUser.setUserpassword(NoOpPasswordEncoder.getInstance().encode(eaUserPasswordChange.getNewPassword1()));
			eaUser.setLastPasswordChangeDate(new Date());
			// no need to update due to @transactional
			//	dao.update(eaUser);
			//System.out.println(eaUser.getUserpassword());
			userDao.save(eaUser);

		}
		else
		{
			return false;
		}
		return true;
	}



	public UserEntityPasswordChange getUserForPwdChange(EAUser eaUser) {
		UserEntityPasswordChange eaUserPasswordChange=new UserEntityPasswordChange();
		eaUserPasswordChange.setUsername(eaUser.getUsername());
		return eaUserPasswordChange;
	}


	public boolean isBothNewPasswordsEqual(UserEntityPasswordChange eaUserPasswordChange) {
		return eaUserPasswordChange.getNewPassword1().equals(eaUserPasswordChange.getNewPassword2());
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}

		return userName;
	}

	public EAUser getLoggedInUser() {
		EAUser loggedinUser= userDao.findById(getPrincipal());
		return loggedinUser;
	}
	
	public boolean isNewPassSameAsOld(UserEntityPasswordChange eaUserPasswordChange) {

		//return passwordEncoder.encode(eaUserPasswordChange.getNewPassword1()).equals(getLoggedInUser().getPassword());

		return NoOpPasswordEncoder.getInstance().matches(eaUserPasswordChange.getNewPassword1(),getLoggedInUser().getUserpassword());
		//return false;
	}

	public boolean isUserLoggedIn(UserEntityPasswordChange eaUserPasswordChange) {
		boolean loggedin=false;
		EAUser eaUser= getLoggedInUser();
		if(eaUserPasswordChange.getUsername().equals(eaUser.getUsername()))
		{
			if( NoOpPasswordEncoder.getInstance().matches(eaUserPasswordChange.getUserPassword(),eaUser.getUserpassword()))
			{
				loggedin=true;
			}
		}
		return loggedin;

	}


	public Boolean isPasswordChangeDue() {
		Boolean passwordChangeDue=false;
		
		EAUser eaUser=getLoggedInUser();
		if(null==eaUser.getLastPasswordChangeDate())
		{
			passwordChangeDue=true;
		}
		return passwordChangeDue;
	}
}
