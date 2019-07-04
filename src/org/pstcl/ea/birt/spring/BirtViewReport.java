package org.pstcl.ea.birt.spring;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.birt.report.engine.api.EXCELRenderOption;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.eclipse.birt.report.engine.api.IPDFRenderOption;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.springframework.web.servlet.view.AbstractView;

public class BirtViewReport extends AbstractView {


	public static final String PARAM_ISNULL = "__isnull";
	public static final String UTF_8_ENCODE = "UTF-8"; 

	private IReportEngine birtEngine;
	private String reportName ; 
	private String reportFormat; 
	private Integer reportID; 
	
	private IRenderOption renderOptions ; 

	
	//http://localhost:8080/ODTL/reports?ReportName=FreshOil.rptdesign&ReportFormat=pdf&reportid=3
	
	
	public void setRenderOptions(IRenderOption ro) { 
		this.renderOptions = ro;
	} 

	

	protected void renderMergedOutputModel(
			Map map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		ServletContext sc = request.getSession().getServletContext();
		if( reportFormat == null ){
			reportFormat="html";
		}

		IReportRunnable runnable = null;
		runnable = birtEngine.openReportDesign( sc.getRealPath("/Reports")+"/"+reportName );
		IRunAndRenderTask runAndRenderTask = birtEngine.createRunAndRenderTask(runnable);
		
		HashMap<String, Object> parms = new HashMap<String, Object>(); 
		
		parms.put("reportid", reportID);
		
		runAndRenderTask.setParameterValues(parms);

		response.setContentType( birtEngine.getMIMEType( reportFormat ));
		IRenderOption options =  null == this.renderOptions ? new RenderOption() : this.renderOptions;		
		if( reportFormat.equalsIgnoreCase("html")){    
			HTMLRenderOption htmlOptions = new HTMLRenderOption( options);
			htmlOptions.setOutputFormat("html");
			htmlOptions.setOutputStream(response.getOutputStream());
			htmlOptions.setImageHandler(new HTMLServerImageHandler());
			htmlOptions.setBaseImageURL(request.getContextPath()+"/images");
			htmlOptions.setImageDirectory(sc.getRealPath("/images"));
			runAndRenderTask.setRenderOption(htmlOptions);

		}else if( reportFormat.equalsIgnoreCase("pdf") ){
			PDFRenderOption pdfOptions = new PDFRenderOption( options );
			pdfOptions.setOutputFormat("pdf");
			pdfOptions.setOption(IPDFRenderOption.PAGE_OVERFLOW, IPDFRenderOption.OUTPUT_TO_MULTIPLE_PAGES);
			pdfOptions.setOutputStream(response.getOutputStream());
			runAndRenderTask.setRenderOption(pdfOptions);
		}
		else if( reportFormat.equalsIgnoreCase("xlsx") ){
			EXCELRenderOption excelRenderOptions = new EXCELRenderOption();
			excelRenderOptions.setOutputFormat("xlsx");
			excelRenderOptions.setOutputStream(response.getOutputStream());
			excelRenderOptions.setEnableMultipleSheet(true);
			// TODO idueppe - should be configurable from cockpit
			excelRenderOptions.setHideGridlines(true);
			// TODO idueppe - should be configurable from cockpit
			// excelRenderOptions.setOfficeVersion();
			// TODO idueppe - should be configurable from cockpit
			excelRenderOptions.setImageHandler(new HTMLServerImageHandler());
			runAndRenderTask.setRenderOption(excelRenderOptions);
			//runAndRenderTask(runAndRenderTask,excelRenderOptions );
		}

		else{

			String att  ="download."+reportFormat;
			String uReportName = reportName.toUpperCase(); 
			if( uReportName.endsWith(".RPTDESIGN") ){ 
				att = uReportName.replace(".RPTDESIGN", "."+reportFormat);
			}	

			try{
				// Create file 
				FileWriter fstream = new FileWriter("c:/test/out.txt");
				BufferedWriter out = new BufferedWriter(fstream);
				out.write("Hello Java " + reportFormat + "--" + birtEngine.getMIMEType( reportFormat ));
				//Close the output stream
				out.close();
			}catch (Exception e){//Catch exception if any
				System.err.println("Error: " + e.getMessage());
			}

			response.setHeader(	"Content-Disposition", "attachment; filename=\"" + att + "\"" );
			options.setOutputStream(response.getOutputStream());
			options.setOutputFormat(reportFormat);
			runAndRenderTask.setRenderOption(options);
		}
		runAndRenderTask.getAppContext().put( EngineConstants.APPCONTEXT_BIRT_VIEWER_HTTPSERVET_REQUEST, request );
		runAndRenderTask.run();	
		runAndRenderTask.close();		

	}
	

	public void setBirtEngine(IReportEngine birtEngine) {
		this.birtEngine = birtEngine;
	}



	public String getReportName() {
		return reportName;
	}



	public void setReportName(String reportName) {
		this.reportName = reportName;
	}



	public String getReportFormat() {
		return reportFormat;
	}



	public void setReportFormat(String reportFormat) {
		this.reportFormat = reportFormat;
	}



	public Integer getReportID() {
		return reportID;
	}



	public void setReportID(Integer reportID) {
		this.reportID = reportID;
	}
}
