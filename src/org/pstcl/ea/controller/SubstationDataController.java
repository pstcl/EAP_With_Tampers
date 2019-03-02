package org.pstcl.ea.controller;

import java.io.File;
import java.io.IOException;

import org.pstcl.ea.model.EAFilter;
import org.pstcl.ea.model.FileModel;
import org.pstcl.ea.service.impl.SubstationDataServiceImpl;
import org.pstcl.ea.service.impl.UploadingService;
import org.pstcl.ea.service.impl.parallel.DataService;
import org.pstcl.ea.util.DateUtil;
import org.pstcl.ea.util.EAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class SubstationDataController {

	@Autowired
	SubstationDataServiceImpl substationDataService;

	@Autowired
	private DataService dateService;

	@Autowired
	private UploadingService uploadingService;
	
	@PreAuthorize("hasRole('ROLE_SS_JE') or hasRole('ROLE_SS_AE') or hasRole('ROLE_SR_XEN') or hasRole('ROLE_SE')")
	@RequestMapping(value = "/getPendingLossReportLocationPM", method = RequestMethod.GET)
	public String getPendingLossReportLocation(@RequestParam(value = "month") Integer month,
			@RequestParam(value = "year") Integer year,ModelMap modelMap) {
		modelMap.addAttribute("pendingLocList", substationDataService.getPendingLocations(null,month,year));
		modelMap.addAttribute("monthOfReport",DateUtil.convertMonthYearToDate(month, year) );
		return "pendingLossMetersDetail";
	}

	@RequestMapping(value = { "/substationMaster" }, method = { RequestMethod.GET })
	public String substationMaster(final ModelMap model) {
		model.addAttribute("currentUser", substationDataService.getLoggedInUser());
		model.addAttribute("substationList", substationDataService.getSubstationList(null));
		return "substationList";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/viewSubstationDetails{id}" }, method = RequestMethod.GET)
	public String viewSubstationDetails(@PathVariable Integer id, ModelMap model) {
		EAFilter filter = new EAFilter();
		model.addAttribute("currentUser", substationDataService.getLoggedInUser());
		model.addAttribute("energyMeters", substationDataService.listMeters(filter));
		return "metersList";
	}

	@RequestMapping(value = "/viewRepoFileData-{id}", method = RequestMethod.GET)
	public String viewRepoFileData(@PathVariable Integer id, ModelMap modelMap) {
		modelMap.addAttribute("cmriModel", dateService.viewRepoFileData(id));
		return "fileImportExport";
	}

	@RequestMapping(value = { "/substationHome" }, method = RequestMethod.GET)
	public String substationHome(@RequestParam(value = "month",required=false) Integer month,
			@RequestParam(value = "year",required=false) Integer year,ModelMap model) {
		EAFilter filter = new EAFilter();
		model.addAttribute("currentUser", substationDataService.getLoggedInUser());
//		model.addAttribute("energyMeters", substationDataService.listMeters(filter));
		model.addAttribute("reportMonthYearDate",DateUtil.convertMonthYearToDate(month, year) );
		model.addAttribute("ssMeterLocations", substationDataService.getSubstationMeterFilesList(filter,month,year));
		
		return "substationHome";
	}
	
	
	
	// @PreAuthorize("hasRole('ROLE_SLDC_USER') or hasRole('ROLE_SLDC_ADMIN')")
	// @RequestMapping(value = "/uploadZipPM", method = RequestMethod.GET)
	// public String uploadMultiZip(ModelMap modelMap) {
	// FileModel fileModel=new FileModel();
	// modelMap.addAttribute("fileModel", fileModel);
	// return "addCMRIMultiZip";
	// }
	
	@PreAuthorize("hasRole('ROLE_SS_JE') or hasRole('ROLE_SS_AE') or hasRole('ROLE_SR_XEN') or hasRole('ROLE_SE')")
	@RequestMapping(value = { "/uploadZipPM" }, method = RequestMethod.GET)
	public String uploadZipPM(ModelMap model) {
		EAFilter filter = new EAFilter();
		model.addAttribute("currentUser", substationDataService.getLoggedInUser());
//		model.addAttribute("energyMeters", substationDataService.listMeters(filter));
//		model.addAttribute("ssMeterLocations", substationDataService.getSubstationMeterFilesList(filter));
		return "substationHome";
	}

	@PreAuthorize("hasRole('ROLE_SS_JE') or hasRole('ROLE_SS_AE') or hasRole('ROLE_SR_XEN') or hasRole('ROLE_SE')")
	@RequestMapping(value = "/uploadZipPM", method = RequestMethod.POST)
	public String uploadMultiZip(FileModel fileModel, BindingResult result, ModelMap modelMap) {
		modelMap.addAttribute("fileModel", uploadingService.processMultiZipUploadedFile(fileModel));

		modelMap.addAttribute("currentUser", substationDataService.getLoggedInUser());
	//	modelMap.addAttribute("energyMeters", substationDataService.listMeters(null));
	//	modelMap.addAttribute("ssMeterLocations", substationDataService.getSubstationMeterFilesList(null));
		return "substationHome";
		// return "uploadedFilesDetail";
	}

	@RequestMapping(value = "/approveRepoFile-{id}", method = RequestMethod.GET)
	public String approveRepoFile(@PathVariable Integer id, @RequestParam(value = "p") Integer page,
			ModelMap modelMap) {
		modelMap.addAttribute("cmriModel",
				uploadingService.changeFileActionStatus(id, EAUtil.FILE_ACTION__APPROVED_AE));
		if (page.equals(2)) {
			return "redirect:/substationHome";
		}
		return "fileImportExport";
	}

	// @RequestMapping(value = "/unLockRepoFile-{id}", method = RequestMethod.GET)
	// public String unLockRepoFile(@PathVariable Integer id,@RequestParam(value =
	// "p")Integer page,ModelMap modelMap) {
	// modelMap.addAttribute("cmriModel",
	// uploadingService.changeFileActionStatus(id,
	// EAUtil.FILE_ACTION__UNLOCKED_AE));
	// if(page.equals(2))
	// {
	// return "redirect:/substationHome";
	// }
	// return "fileImportExport";
	// }
	// @RequestMapping(value = "/lockRepoFile-{id}", method = RequestMethod.GET)
	// public String lockRepoFile(@PathVariable Integer id,@RequestParam(value =
	// "p")Integer page,ModelMap modelMap) {
	// modelMap.addAttribute("cmriModel",
	// uploadingService.changeFileActionStatus(id, EAUtil.FILE_ACTION__LOCKED_JE));
	// if(page.equals(2))
	// {
	// return "redirect:/substationHome";
	// }
	// return "fileImportExport";
	// }
	@RequestMapping(value = "/removeRepoFile-{id}", method = RequestMethod.GET)
	public String removeRepoFile(@PathVariable Integer id, @RequestParam(value = "p") Integer page, ModelMap modelMap) {
		modelMap.addAttribute("cmriModel", uploadingService.changeFileActionStatus(id, EAUtil.FILE_ACTION__DELETED));
		if (page.equals(2)) {
			return "redirect:/substationHome";
		}
		return "fileImportExport";
	}

	//	@RequestMapping(value = "/viewTxtFile-{id}", method = RequestMethod.GET)
	//	public String viewTxtFile(@PathVariable Integer id, @RequestParam(value = "p") Integer page, ModelMap modelMap) {
	//		modelMap.addAttribute("cmriModel", uploadingService.changeFileActionStatus(id, EAUtil.FILE_ACTION__DELETED));
	//		if (page.equals(2)) {
	//			return "redirect:/substationHome";
	//		}
	//		return "fileImportExport";
	//	}

	@GetMapping(value = "/viewTxtFile-{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody HttpEntity<byte[]> getFile(@PathVariable Integer id, ModelMap modelMap) throws IOException {
		String fileName=uploadingService.getTextFilePath(id);

		File file=new File(fileName);


		byte[] pdfFile = null;
		try {
			pdfFile = FileCopyUtils.copyToByteArray(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.parseMediaType(MediaType.TEXT_PLAIN_VALUE)); //or what ever type it is
		headers.setContentLength(pdfFile.length);
		headers.set("Content-Disposition", "inline; filename=" + file.getName());	

		return new HttpEntity<byte[]>(pdfFile, headers);
	}


}
