package org.pstcl.ea.controller;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.pstcl.ea.model.entity.UserEntityPasswordChange;
import org.pstcl.ea.service.impl.SubstationDataServiceImpl;
import org.pstcl.ea.service.impl.UserServiceImpl;
import org.pstcl.ea.util.EAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MDEController {




	@Autowired
	MessageSource messageSource;
	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	@Autowired
	SubstationDataServiceImpl substationService;

	@Autowired
	UserServiceImpl userService;




	@RequestMapping(value = {"/Access_Denied"}, method = {RequestMethod.GET})
	public String accessDeniedPage(final ModelMap model) {
		model.addAttribute("loggedinuser",  this.getPrincipal());
		return "accessDenied";
	}

	@RequestMapping(value = {"/login"}, method = {RequestMethod.GET})
	public String loginPage() {
		if (this.isCurrentAuthenticationAnonymous()) {
			return "login";
		}
		return "redirect:/home";
	}

	@RequestMapping(value = {"/logout"}, method = {RequestMethod.GET})
	public String logoutPage(final HttpServletRequest request, final HttpServletResponse response) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			this.persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication((Authentication) null);
		}
		return "redirect:/login?logout";
	}

	private String getPrincipal() {
		String userName = null;
		final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return this.authenticationTrustResolver.isAnonymous(authentication);
	}

	@RequestMapping(value = {"/", "/home"}, method = {RequestMethod.GET})
	public String home(final ModelMap model) {

//		if(substationService.getLoggedInUser().getFirstLogin())
//		{
//			model.addAttribute("currentUser", substationService.getLoggedInUser());
//			model.addAttribute("passwordEntity", userService.getUserForPwdChange(substationService.getLoggedInUser()) );
//			return "firstLoginPwdChange";
//		}
		


		prepareHomeModel(model);
		return "home";
	}


	@RequestMapping(value = {"/firstLoginPwdChange"}, method = {RequestMethod.GET})
	public String firstLoginPwdChange(final ModelMap model) {

		model.addAttribute("currentUser", substationService.getLoggedInUser());
		model.addAttribute("passwordEntity", userService.getUserForPwdChange(substationService.getLoggedInUser()) );
		return "firstLoginPwdChange";



	}

	
	@RequestMapping(value = {"/firstLoginPwdChange"}, method = {RequestMethod.POST})
	public String firstLoginPwdChange(@ModelAttribute("passwordEntity") @Valid UserEntityPasswordChange eaUserPasswordChange, BindingResult result, ModelMap model,final HttpServletRequest request, final HttpServletResponse response) {

		
		model.addAttribute("passwordEntity", eaUserPasswordChange );
		if(userService.isBothNewPasswordsEqual(eaUserPasswordChange))
		{
			if(!userService.isUserLoggedIn(eaUserPasswordChange))
			{
				FieldError equipIdError =new FieldError("passwordEntity","userPassword",messageSource.getMessage("sldc.user.login.error",new String[]{ eaUserPasswordChange.getUsername()}, Locale.getDefault()));
				result.addError(equipIdError);
				//model.addAttribute("passwordEntity", userService.getUserForPwdChange(userService.getLoggedInUser()) );
				
				return "firstLoginPwdChange";
			}
			if(userService.isNewPassSameAsOld(eaUserPasswordChange))
			//if(eaUserPasswordChange.getUserPassword().equals(eaUserPasswordChange.getNewPassword2()))
			{
				FieldError equipIdError =new FieldError("passwordEntity","newPassword1",messageSource.getMessage("sldc.password.same.old", new String[]{ eaUserPasswordChange.getUsername()}, Locale.getDefault()));
				result.addError(equipIdError);
				
				return "firstLoginPwdChange";
			
			}
			if(!userService.changePassword(eaUserPasswordChange))
			{
				
				FieldError equipIdError =new FieldError("passwordEntity","username",messageSource.getMessage("sldc.password.error", new String[]{ eaUserPasswordChange.getUsername()}, Locale.getDefault()));
				result.addError(equipIdError);
				equipIdError =new FieldError("passwordEntity","userPassword",messageSource.getMessage("sldc.password.error", new String[]{ eaUserPasswordChange.getUserPassword()}, Locale.getDefault()));
				result.addError(equipIdError);
				//model.addAttribute("passwordEntity", userService.getUserForPwdChange(userService.getLoggedInUser()) );
				return "firstLoginPwdChange";
			}
			else
			{
				
				model.addAttribute("passwordChanged", "Password Changed");
			}
		}
		
		else if(!userService.isBothNewPasswordsEqual(eaUserPasswordChange))
		{
			FieldError equipIdError =new FieldError("passwordEntity","newPassword1",messageSource.getMessage("sldc.password.error", new String[]{ eaUserPasswordChange.getNewPassword2()}, Locale.getDefault()));
			result.addError(equipIdError);
			return "firstLoginPwdChange";
		
		}
		 loguserout(request,response);
		 return "redirect:/login?passwordChanged";
	}
	
	private void loguserout(final HttpServletRequest request, final HttpServletResponse response) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			this.persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication((Authentication) null);
		}
	}

//	//Save all meters data
//	@Deprecated
//	@RequestMapping(value = {"/firstLoginPwdChange_OLD"}, method = {RequestMethod.POST})
//	public String firstLoginPwdChange_OLD(@ModelAttribute("passwordEntity") @Valid UserEntityPasswordChange eaUserPasswordChange, BindingResult result, ModelMap model,final HttpServletRequest request, final HttpServletResponse response) {
//
//		model.addAttribute("passwordEntity", eaUserPasswordChange );
//		if(eaUserPasswordChange.getNewPassword1().equals(eaUserPasswordChange.getNewPassword2()))
//		{
//			if(!eaUserPasswordChange.getUsername().equals(substationService.getLoggedInUser().getUsername()))
//			{
//				FieldError equipIdError =new FieldError("passwordEntity","username",messageSource.getMessage("sldc.username.error",new String[]{ eaUserPasswordChange.getUsername()}, Locale.getDefault()));
//				result.addError(equipIdError);
//				return "firstLoginPwdChange";
//			}
//			if(eaUserPasswordChange.getUserPassword().equals(eaUserPasswordChange.getNewPassword2()))
//			{
//				FieldError equipIdError =new FieldError("passwordEntity","newPassword1",messageSource.getMessage("sldc.password.same.old", new String[]{ eaUserPasswordChange.getUsername()}, Locale.getDefault()));
//				result.addError(equipIdError);
//				return "firstLoginPwdChange";
//			
//			}
//			if(!userService.changePassword(eaUserPasswordChange))
//			{
//				
//				FieldError equipIdError =new FieldError("passwordEntity","username",messageSource.getMessage("sldc.password.error", new String[]{ eaUserPasswordChange.getUsername()}, Locale.getDefault()));
//				result.addError(equipIdError);
//				equipIdError =new FieldError("passwordEntity","userPassword",messageSource.getMessage("sldc.password.error", new String[]{ eaUserPasswordChange.getUserPassword()}, Locale.getDefault()));
//				result.addError(equipIdError);
//				model.addAttribute("passwordEntity", userService.getUserForPwdChange(substationService.getLoggedInUser()) );
//				return "firstLoginPwdChange";
//			}
//			else
//			{
//				
//				model.addAttribute("passwordChanged", "Password Changed");
//			}
//		}
//		else if(!eaUserPasswordChange.getNewPassword1().equals(eaUserPasswordChange.getNewPassword2()))
//		{
//			FieldError equipIdError =new FieldError("passwordEntity","newPassword1",messageSource.getMessage("sldc.password.error", new String[]{ eaUserPasswordChange.getNewPassword2()}, Locale.getDefault()));
//			result.addError(equipIdError);
//			return "firstLoginPwdChange";
//		
//		}
//		
//		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if (auth != null) {
//			this.persistentTokenBasedRememberMeServices.logout(request, response, auth);
//			SecurityContextHolder.getContext().setAuthentication((Authentication) null);
//		}
//		return "redirect:/login?logout";
//	}


	private void prepareHomeModel(ModelMap model)
	{
		model.addAttribute("currentUser", substationService.getLoggedInUser());
		model.addAttribute("passwordChangeDue",userService.isPasswordChangeDue());
		
		SimpleDateFormat formatter=new SimpleDateFormat("dd-mm-yyyy");

		model.addAttribute("yesterdayDate", EAUtil.getYesterday());

	}






}
