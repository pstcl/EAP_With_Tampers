package org.pstcl.ea.controller;

import org.pstcl.ea.service.impl.RestService;
import org.pstcl.model.FilterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestController {

	@Autowired
	RestService  restService;

	@RequestMapping(value = {"/getMeterDetails" }, method = RequestMethod.GET)
	public ModelAndView getMeterDetails(
			@RequestParam(value="locationid") String locationid,
			ModelMap model) 
	{
		model.addAttribute("location", restService.getMeterDeatils(locationid));
		return new ModelAndView("meterDetailsSnippet", model) ;

	}


	@RequestMapping(value = {"/testAjax" }, 
			method = RequestMethod.GET)
	public String testAjax(
			ModelMap model) 
	{	
		return "testAjax" ;
	}


	@RequestMapping(value = {"/getLocationsModel"}, 
			method = RequestMethod.POST,
			produces = "application/json")
	public @ResponseBody FilterModel getLocationsModel(
			@RequestParam(value="circleSelected") Integer circleCode,
			@RequestParam(value="divisionSelected") Integer divCode,
			@RequestParam(value="substationSelected") Integer substationCode,
			ModelMap model) {

		FilterModel locationModel= restService.getLocationModel(circleCode,divCode,substationCode);
		
		//FilterModel locationModel= restService.getLocationModel(Integer.parseInt(circleCode),Integer.parseInt(divCode),Integer.parseInt(substationCode));
		return locationModel;
	}


}
