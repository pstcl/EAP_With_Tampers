
package org.pstcl.ea.controller;


import java.util.Set;

import org.pstcl.ea.dao.SubstationUtilityDao;
import org.pstcl.ea.model.ChangeMeterSnippet;
import org.pstcl.ea.model.EAFilter;
import org.pstcl.ea.model.entity.CircleMaster;
import org.pstcl.ea.model.entity.DivisionMaster;
import org.pstcl.ea.service.impl.RestService;
import org.pstcl.model.FilterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class RestController {
	
	@Autowired
	RestService  restService;
	
	@Autowired
	SubstationUtilityDao populate;
	
	@RequestMapping(value = {"/getMeterDetails" }, method = RequestMethod.GET)
	public ModelAndView getMeterDetails(
			@RequestParam(value="locationId") int locationId,@RequestParam(value="ssCode") int ssCode,
			ModelMap model) 
	{
		
		model.addAttribute("location", restService.getMeterDetails(locationId));
		model.addAttribute("substation", restService.getSubstationMeterDetails(ssCode));
	
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


	
	
	@RequestMapping(value = {"/changeMeterDetails" }, method = RequestMethod.GET)
	public ModelAndView changeMeterDetails(
			@RequestParam(value="meterlocationId") int meterlocationId,ModelMap model) 
	{
		ChangeMeterSnippet chgMtr = new ChangeMeterSnippet();
		chgMtr.setOldValues(restService.getMeterDetails(meterlocationId));
		model.addAttribute("changeMeterSnippet",chgMtr);
		model.addAttribute("circleMaster", populate.listCircles(new EAFilter()));
		model.addAttribute("divisionMaster", null);
		model.addAttribute("substation", null);
		model.addAttribute("locationMaster", null);
		return new ModelAndView("changeMeter", model) ;
	
		
	}
	
	@RequestMapping(value = {"/changeMeterDetails" }, method = RequestMethod.POST)
	public String updateMeterDetails(@ModelAttribute("changeMeterSnippet")ChangeMeterSnippet changeMeterSnippet,BindingResult result) {
		restService.saveDetails(changeMeterSnippet);
		return "redirect:substationMaster";
	}
	
	@RequestMapping(value = {"/getDivisions1" },  method = {RequestMethod.POST})
	public ModelAndView getDivisions1(
			@ModelAttribute ChangeMeterSnippet changeMeterSnippet,BindingResult result,ModelMap model) 
	{
	
		System.out.println(changeMeterSnippet.getCircle());
		int crCode = Integer.parseInt(changeMeterSnippet.getCrcode());//changeMeterSnippet.getCircle().getCrCode();
		model.addAttribute("changeMeterSnippet",changeMeterSnippet);	
		model.addAttribute("divisionMaster", populate.findCircleByID(crCode).getDivisionMasters());
		model.addAttribute("substation", null);
		model.addAttribute("locationMaster", null);
		return new ModelAndView("changeMeter", model) ;
	
		
	}
	
	@RequestMapping(value = "/getDivisions" ,  method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody Set<DivisionMaster> getDivisions(
			@RequestParam(value="circleSelected") String circleSelected,ModelMap model) 
	{
		int crCode = Integer.parseInt(circleSelected);//changeMeterSnippet.getCircle().getCrCode0();
		CircleMaster circleMaster= populate.findCircleByID(crCode);//.getDivisionMasters();
	
		return circleMaster.getDivisionMasters();
	}
	
	


}

