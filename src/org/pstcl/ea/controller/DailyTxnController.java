package org.pstcl.ea.controller;

import org.pstcl.ea.model.LocationSurveyDataModel;
import org.pstcl.ea.service.impl.DailyTxnService;
import org.pstcl.ea.service.impl.SubstationDataServiceImpl;
import org.pstcl.ea.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class DailyTxnController {

	@Autowired
	private DailyTxnService dailyTxnService;

	@Autowired
	SubstationDataServiceImpl substationDataService;

	
	@PreAuthorize("hasRole('ROLE_SLDC_USER') or hasRole('ROLE_SLDC_ADMIN')")
	@RequestMapping(value = "/addPendingLocData-{locationId}", method = RequestMethod.GET)
	public String addPendingLocData(@PathVariable String locationId,@RequestParam(value = "month") Integer month,
			@RequestParam(value = "year") Integer year,ModelMap modelMap) {
		modelMap.addAttribute("dailyTransactionModel", dailyTxnService.createReportTransactions(locationId,month,year));
		modelMap.addAttribute("monthOfReport",DateUtil.convertMonthYearToDate(month, year));
		return "addDailyExportImport";
	}
	
	@PreAuthorize("hasRole('ROLE_SLDC_USER') or hasRole('ROLE_SLDC_ADMIN')")
	@RequestMapping(value = "/editPendingLocData-{locationId}", method = RequestMethod.GET)
	public String editPendingLocData(@PathVariable String locationId,@RequestParam(value = "month") Integer month,
			@RequestParam(value = "year") Integer year,ModelMap modelMap) {
		modelMap.addAttribute("dailyTransactionModel", dailyTxnService.createReportTransactions(locationId,month,year));
		modelMap.addAttribute("monthOfReport",DateUtil.convertMonthYearToDate(month, year));
		return "addDailyExportImport";
	}

	
	//Save all meters data
		@RequestMapping(value = {"/saveDailyTxSS"}, method = {RequestMethod.POST})
		public String saveDailyTxSS(LocationSurveyDataModel dailyTransactionModel, BindingResult result, ModelMap model) {
			dailyTxnService.saveDailyTransactions(dailyTransactionModel);
			//return "addDailyExportImport";
			return "redirect:/home";
		}


}
