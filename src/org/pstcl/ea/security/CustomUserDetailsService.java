package org.pstcl.ea.security;

import java.util.ArrayList;
import java.util.List;

import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	@Autowired
	private UserServiceImpl userService;

	@Transactional(value="sldcTxnManager",readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EAUser user = this.userService.findByUsername(username);
		logger.info("User : {}", user);
		if (user == null) {
			logger.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		} else {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getUserpassword(), true,
					true, true, true, this.getGrantedAuthorities(user));
		}
	}

	private List<GrantedAuthority> getGrantedAuthorities(EAUser user) {
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		logger.info("User RoleCode : {}", user.getRolecode()+":"+user.getRolename());
		//if(!user.getFirstLogin())
		{

			for (UserRole userRole : UserRole.values()) {
				if(userRole.getUserRoleCode().equals(user.getRolecode()))
				{
					authorities.add(new SimpleGrantedAuthority(userRole.getUserRoleName()));
				}
			}
		}

		logger.info("authorities : {}", authorities);
		return authorities;
	}
}