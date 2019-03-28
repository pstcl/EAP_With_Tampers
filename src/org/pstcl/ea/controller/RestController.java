
package org.pstcl.ea.controller;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.pstcl.ea.dao.SubstationUtilityDao;
import org.pstcl.ea.model.ChangeMeterSnippet;
import org.pstcl.ea.model.EAFilter;
import org.pstcl.ea.model.entity.CircleMaster;
import org.pstcl.ea.model.entity.DivisionMaster;
import org.pstcl.ea.model.entity.MeterLocationMap;
import org.pstcl.ea.service.impl.RestService;
import org.pstcl.model.FilterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
			@RequestParam(value="locationSelected") String locationid,
			ModelMap model) {

		FilterModel locationModel= restService.getLocationModel(circleCode,divCode,substationCode,locationid);

		//FilterModel locationModel= restService.getLocationModel(Integer.parseInt(circleCode),Integer.parseInt(divCode),Integer.parseInt(substationCode));
		return locationModel;
	}




	@RequestMapping(value = {"/changeMeterDetails" }, method = RequestMethod.GET)
	public ModelAndView changeMeterDetails(
			@RequestParam(value="meterlocationId") int meterlocationId,ModelMap model) 
	{
		ChangeMeterSnippet chgMtr = new ChangeMeterSnippet();
		chgMtr.setOldValues(restService.getMeterDetails(meterlocationId));
		model.addAttribute("error", "");
		model.addAttribute("changeMeterSnippet",chgMtr);
		return new ModelAndView("testAjax", model) ;


	}



	@RequestMapping(value = {"/saveMeterDetails" }, method = RequestMethod.POST)
	public ModelAndView saveMeterDetails(ChangeMeterSnippet changeMeterSnippet,BindingResult bindingResult,ModelMap model) {
		System.out.println(changeMeterSnippet.getOldMeterLocationMap());
		System.out.println(changeMeterSnippet.getEndDate());
		System.out.println(changeMeterSnippet.getStartDate());
		System.out.println(changeMeterSnippet.getMeterMaster());
		Date endDate = changeMeterSnippet.getEndDate();
		Date startDate = changeMeterSnippet.getStartDate();
		String error="";
		if(changeMeterSnippet.getOldMeterLocationMap().getEndDate()!=null)
			error="End Date of old Location Already exists";
		else if(endDate==null || changeMeterSnippet.getLocation()==null || startDate==null)
			error="One of Value is Null";
		else if((startDate.before(endDate)))
				error="Start Date of Meter At New Location is Before End Date at Old Location";
		else if(endDate.before(changeMeterSnippet.getOldMeterLocationMap().getStartDate()))
			error="Start Date of Meter At Old Location is After End Date at Old Location";
		else if(restService.saveDetails(changeMeterSnippet))
		{
			List<MeterLocationMap> a = restService.findLocations(changeMeterSnippet.getMeterMaster());
			System.out.print(a.size());
			model.addAttribute("Locationlist", a);
			return new ModelAndView("success",model);
		}
		
		
		model.addAttribute("error", error);
		model.addAttribute("changeMeterSnippet",new ChangeMeterSnippet());
		return new ModelAndView("testAjax", model) ;
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

