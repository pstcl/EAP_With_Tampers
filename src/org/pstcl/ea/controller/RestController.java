
package org.pstcl.ea.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.pstcl.ea.dao.SubstationUtilityDao;
import org.pstcl.ea.model.AddReportLocationModel;
import org.pstcl.ea.model.ChangeLocationEmf;
import org.pstcl.ea.model.ChangeMeterSnippet;
import org.pstcl.ea.model.EAFilter;
import org.pstcl.ea.model.LocationMasterList;
import org.pstcl.ea.model.entity.AddReportLocations;
import org.pstcl.ea.model.entity.CircleMaster;
import org.pstcl.ea.model.entity.DivisionMaster;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.MeterLocationMap;
import org.pstcl.ea.model.entity.MeterMaster;
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
	RestService restService;

	@Autowired
	SubstationUtilityDao populate;

	@RequestMapping(value = { "/getMeterDetails" }, method = RequestMethod.GET)
	public ModelAndView getMeterDetails(@RequestParam(value = "locationId") int locationId,
			@RequestParam(value = "ssCode") int ssCode, ModelMap model) {

		model.addAttribute("location", restService.getMeterDetails(locationId));
		model.addAttribute("substation", restService.getSubstationMeterDetails(ssCode));

		return new ModelAndView("meterDetailsSnippet", model);

	}

	@RequestMapping(value = { "/testAjax" }, method = RequestMethod.GET)
	public String testAjax(ModelMap model) {
		return "testAjax";
	}

	@RequestMapping(value = { "/getLocationsModel" }, method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody FilterModel getLocationsModel(@RequestParam(value = "circleSelected") Integer circleCode,
			@RequestParam(value = "divisionSelected") Integer divCode,
			@RequestParam(value = "substationSelected") Integer substationCode,
			@RequestParam(value = "locationSelected") String locationid, ModelMap model) {

		FilterModel locationModel = restService.getLocationModel(circleCode, divCode, substationCode, locationid);

		// FilterModel locationModel=
		// restService.getLocationModel(Integer.parseInt(circleCode),Integer.parseInt(divCode),Integer.parseInt(substationCode));
		return locationModel;
	}

	@RequestMapping(value = { "/changeMeterDetails" }, method = RequestMethod.GET)
	public ModelAndView changeMeterDetails(@RequestParam(value = "meterlocationId") int meterlocationId,
			ModelMap model) {
		ChangeMeterSnippet chgMtr = new ChangeMeterSnippet();
		chgMtr.setOldValues(restService.getMeterDetails(meterlocationId));
		model.addAttribute("error", "");
		model.addAttribute("changeMeterSnippet", chgMtr);
		return new ModelAndView("testAjax", model);

	}

	@RequestMapping(value = { "/changeNewMeterDetails" }, method = RequestMethod.GET)
	public ModelAndView changeMeterDetails(@RequestParam(value = "meterId") String meterId, ModelMap model) {
		ChangeMeterSnippet chgMtr = new ChangeMeterSnippet();
		restService.setOnlyMeter(chgMtr, meterId);
		model.addAttribute("changeMeterSnippet", chgMtr);
		return new ModelAndView("testAjax", model);

	}

	@RequestMapping(value = { "/saveMeterDetails" }, method = RequestMethod.POST)
	public ModelAndView saveMeterDetails(ChangeMeterSnippet changeMeterSnippet, BindingResult bindingResult,
			ModelMap model) {
		System.out.println(changeMeterSnippet.getOldMeterLocationMap());
		System.out.println(changeMeterSnippet.getEndDate());
		System.out.println(changeMeterSnippet.getStartDate());
		System.out.println(changeMeterSnippet.getMeterMaster());
		Date endDate = changeMeterSnippet.getEndDate();
		Date startDate = changeMeterSnippet.getStartDate();
		Date current = new Date();
		String error = null;
		if (changeMeterSnippet.getSetNewMeter() == null)
			error = "Choose If u want or not to set next location Details";
		else {
			if (error == null && changeMeterSnippet.getSetNewMeter().equals("yes")
					&& (startDate == null || changeMeterSnippet.getLocation() == null)) {
				error = "One of value is null";
			}
			if (error == null && changeMeterSnippet.getOldMeterLocationMap() != null) {
				if (endDate == null)
					error = "One of Value is Null";
				else if (endDate.after(current))
					error = "Set End Date is greater than current Date";
				else if (changeMeterSnippet.getOldMeterLocationMap().getEndDate() != null)
					error = "End Date of old Location Already exists";
				else if (endDate.before(changeMeterSnippet.getOldMeterLocationMap().getStartDate()))
					error = "Start Date of Meter At Old Location is After End Date at Old Location";

				if (error == null && changeMeterSnippet.getSetNewMeter().equals("yes")) {
					if (startDate.after(current))
						error = "Set Start Date is greater than current Date";
					else if ((startDate.before(endDate)))
						error = "Start Date of Meter At New Location is Before End Date at Old Location";
				}
			}

			if (error == null && restService.saveDetails(changeMeterSnippet)) {
				List<MeterLocationMap> a = restService.findLocations(changeMeterSnippet.getMeterMaster());
				System.out.print(a.size());
				model.addAttribute("Locationlist", a);
				return new ModelAndView("success", model);
			}
		}

		model.addAttribute("error", error);
		model.addAttribute("changeMeterSnippet", changeMeterSnippet);
		return new ModelAndView("testAjax", model);
	}

	@RequestMapping(value = "/getDivisions", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Set<DivisionMaster> getDivisions(@RequestParam(value = "circleSelected") String circleSelected,
			ModelMap model) {
		int crCode = Integer.parseInt(circleSelected);// changeMeterSnippet.getCircle().getCrCode0();
		CircleMaster circleMaster = populate.findCircleByID(crCode);// .getDivisionMasters();

		return circleMaster.getDivisionMasters();
	}

	@RequestMapping(value = "/changeLocationEmf", method = RequestMethod.GET)
	public ModelAndView changeLocationEmf(@RequestParam(value = "locationId") String locationId, ModelMap model) {
		ChangeLocationEmf chgLocEmf = new ChangeLocationEmf();
		chgLocEmf.setLocationMaster(restService.findLocationBYId(locationId));
		chgLocEmf.setOldLocationEmf(restService.getLocationRecentEmfByLocid(locationId));
		System.out.print(chgLocEmf.getOldLocationEmf());
		model.addAttribute("changeLocationEmf", chgLocEmf);
		return new ModelAndView("locationEmfForm", model);
	}

	@RequestMapping(value = "/changeLocationEmf", method = RequestMethod.POST)
	public ModelAndView saveChangeLocationEmfDetails(ChangeLocationEmf changeLocationEmf, BindingResult bindingResult,
			ModelMap model) {
		System.out.println(changeLocationEmf.getExternalMF());
		System.out.println(changeLocationEmf.getSetNewEmf());
		System.out.println(changeLocationEmf.getEndDate());
		System.out.println(changeLocationEmf.getLocationMaster());
		String returnModel = "successEmf";
		String error = null;
		if (changeLocationEmf.getSetNewEmf() == null) {
			error = "Choose U want or not to set Location emf again";
			returnModel = "locationEmfForm";
		} else if (changeLocationEmf.getOldLocationEmf() != null && (changeLocationEmf.getEndDate() == null
				|| changeLocationEmf.getEndDate().after(new Date())
				|| changeLocationEmf.getEndDate().before(changeLocationEmf.getOldLocationEmf().getStartDate()))) {

			error = "End Date Of Existing External MF is either null  or greater than current date or start date of previous emf";
			returnModel = "locationEmfForm";

		} else if (changeLocationEmf.getSetNewEmf().equals("yes") && (changeLocationEmf.getExternalMF() == null
				|| changeLocationEmf.getStartDate() == null || changeLocationEmf.getNetWHSign() == null
				|| changeLocationEmf.getStartDate().after(new Date()) || (changeLocationEmf.getStartDate() != null
						&& changeLocationEmf.getEndDate().after(changeLocationEmf.getStartDate())))) {

			error = "One of value is not set";
			returnModel = "locationEmfForm";
		} else if (!restService.saveDetailsOfLocationEmf(changeLocationEmf)) {
			error = "Problem while Saving Details";
			returnModel = "locationEmfForm";
		} else {
			model.addAttribute("list",
					restService.getLocationEmfListByLocid(changeLocationEmf.getLocationMaster().getLocationId()));
		}
		if (error != null) {
			ChangeLocationEmf chg = new ChangeLocationEmf();
			if (changeLocationEmf.getOldLocationEmf() != null)
				chg.setOldLocationEmf(changeLocationEmf.getOldLocationEmf());
			chg.setLocationMaster(changeLocationEmf.getLocationMaster());
			model.addAttribute("changeLocationEmf", chg);
		}
		model.addAttribute("error", error);
		return new ModelAndView(returnModel, model);
	}

	@RequestMapping(value = "/addMeterDetails", method = RequestMethod.GET)
	public ModelAndView addMeterDetails(ModelMap model) {
		model.addAttribute("meter", new MeterMaster());
		return new ModelAndView("addMeterDetails", model);
	}

	@RequestMapping(value = "/addMeterDetails", method = RequestMethod.POST)
	public Object saveMeterDetails(MeterMaster meter, BindingResult bindingResult, ModelMap model) {

		if (meter.checkDetails() == false) {
			model.addAttribute("meter", meter);
			return new ModelAndView("addMeterDetails", model);
		}
		restService.saveMeterDetails(meter);
		return (String) "redirect:substationMaster";
	}
	
	
	
	@RequestMapping(value = { "/getLocationListModel" }, method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody LocationMasterList getLocationsMasterListModel( ModelMap model) {

		LocationMasterList listModel = restService.getLocationMasterListModel();
		return listModel;
	}

	@RequestMapping(value = { "/getMeterListModel" }, method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody LocationMasterList getMeterListModel( ModelMap model) {

		LocationMasterList listModel = restService.MeterListModel();
		return listModel;
	}
	
	@RequestMapping(value = "/addLocationDetails", method = RequestMethod.GET)
	public ModelAndView addLocationMasterDetails(ModelMap model) {
		String error=null;
		
		model.addAttribute("locationMaster", new LocationMaster());
		return new ModelAndView("addLocationDetails", model);
	}
	

	@RequestMapping(value = "/addLocationDetails", method = RequestMethod.POST)
	public Object saveLocationMeterDetails(LocationMaster locationMaster, BindingResult bindingResult, ModelMap model) {

		if (locationMaster.check() == false) {
			
			model.addAttribute("locationMaster", locationMaster);
			return new ModelAndView("addLocationDetails", model);
		}
		System.out.println(locationMaster.getDeviceTypeMaster());
		restService.saveLocationMasterDetails(locationMaster);
		return (String) "redirect:substationMaster";
	}
	
	@RequestMapping(value = "/selectMonthForReportLocations", method = RequestMethod.GET)
	public ModelAndView selectReportMonths(ModelMap model) {
		model.addAttribute("error",null);
		model.addAttribute("addReportLocations", new AddReportLocationModel());
		return new ModelAndView("selectMonthForReportLocations", model);
	}
	
	@RequestMapping(value = "/selectMonthForReportLocations", method = RequestMethod.POST)
	public Object selectReportLocations(AddReportLocationModel addReportLocations,ModelMap model) {
		//System.out.println(addReportLocations.getMonth());
		if( addReportLocations.getYear()<1000 || addReportLocations.getYear()>9999)
		{
			model.addAttribute("error","Year should be of 4 digits");
			model.addAttribute("addReportLocations", new AddReportLocationModel());
			return new ModelAndView("selectMonthForReportLocations", model);
		}
		
		
		Set <LocationMaster> pendingLocation= new HashSet<LocationMaster>(restService.selectReportLocations(addReportLocations));
		try {
		Set<LocationMaster> addedLocations = new HashSet<LocationMaster>(addReportLocations.getLocations());
		addReportLocations.setLocations(new ArrayList<LocationMaster>());
		if(addedLocations.size()>0)
		model.addAttribute("addedLocations",addedLocations);
		else
		model.addAttribute("addedLocations",null);	
		System.out.println(addedLocations.size());
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("addedLocations",null);
		}
		model.addAttribute("pendingLocation",pendingLocation);
		
		model.addAttribute("addReportLocations", addReportLocations);
		return new ModelAndView("addReportLocations", model);
	}
	
	@RequestMapping(value="/addReportLocation",method=RequestMethod.POST)
	public Object saveReportLocations(AddReportLocationModel addReportLocations,ModelMap model) {
		addReportLocations = restService.saveReportLocations(addReportLocations);
		model.addAttribute("addReportLocations", addReportLocations);
		return "redirect:selectMonthForReportLocations";
	}	

}