package org.pstcl.ea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/"})
@SessionAttributes({"roles"})
public class BIRTController {

	
	@Autowired
	org.pstcl.ea.birt.spring.BirtViewReport birtViewReport;



	
	@RequestMapping(value = { "/pdfOilReport-{id}" }, method = RequestMethod.GET)
	public ModelAndView pdfOilReport(@PathVariable Integer id, ModelMap model) {
		prepareReport(id);
		birtViewReport.setReportFormat("pdf");
		return new ModelAndView(birtViewReport);
	}


	
	@RequestMapping(value = { "/xlsOilReport-{id}" }, method = RequestMethod.GET)
	public ModelAndView xlsOilReport(@PathVariable Integer id, ModelMap model) {
		prepareReport(id);
		birtViewReport.setReportFormat("xlsx");
		return new ModelAndView(birtViewReport);
	}




	private void prepareReport(Integer id)
	{
//		OilReport oilReport=oilReportService.findOilReportById(id);
//		birtViewOilReport.setReportID(oilReport.getId());
//
//
//		if(oilReport.getOilSample().getSampleType().contentEquals(StringUtil.OIL_REPORT_ROUTINE_OIL))
//		{
//			birtViewOilReport.setReportName("RoutineOil.rptdesign");
//		} 
//		else if(oilReport.getOilSample().getSampleType().contentEquals(StringUtil.OIL_REPORT_NEW_OIL))
//		{
//			birtViewOilReport.setReportName("NewOil.rptdesign");	
//		}
//		else if(oilReport.getOilSample().getSampleType().contentEquals(StringUtil.OIL_REPORT_FRESH_OIL))
//		{
//			birtViewOilReport.setReportName("RoutineOil.rptdesign");	
//		}

	}




	//	@RequestMapping(value = { "/printOilReport-{id}" }, method = RequestMethod.GET)
	//	public String printOilReport(@PathVariable Integer id, ModelMap model) {
	//		OilReport oilReport=oilReportService.findOilReportById(id);
	//		model.addAttribute("oilReport",oilReport);
	//
	//		if(oilReport.getOilSample().getSampleType().contentEquals(StringUtil.OIL_REPORT_NEW_OIL))
	//		{
	//			return "oilReportFresh";
	//		}
	//		else
	//		{
	//			return "oilReportRoutine";
	//		}
	//
	//	}

}
