package org.pstcl.ea.controller;

import org.pstcl.ea.service.impl.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
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

}
