package org.pstcl.ea.util;

import java.util.Calendar;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ibm.icu.math.BigDecimal;

public class EAUtil {
	//Raw MEter reading
	public static final int DECIMAL_SCALE_DAILY_SURVEY_IMPORT_EXPORT=3;
	//Raw MEter reading
	public static final int DECIMAL_PRECESION_DAILY_SURVEY_IMPORT_EXPORT=14;
	//Meter Reading multiplied by MF divided by 1000000
	public static final int DECIMAL_SCALE_BOUNDARY_PT_IMPORT_EXPORT=3;

	//Meter Reading multiplied by MF divided by 1000000
	public static final int DECIMAL_PRECESION_BOUNDARY_PT_IMPORT_EXPORT=16;

	public static final int DECIMAL_ROUNDING_MODE=BigDecimal.ROUND_HALF_UP;


	public static final int DECIMAL_SCALE_LOSS_PERCENTAGE=4;


	public static String CRMI_TXT_FILE_REPOSITORY="E:/SLDC_ENERGY_ACC/CMRI/CMRI_TXT_FILE_REPOSITORY/";
	public static String CRMI_ZIP_FILE_REPOSITORY="E:/SLDC_ENERGY_ACC/CMRI/CMRI_ZIP_FILE_REPOSITORY/";
	public static String TEMP_FILE_OPEN_DIR="E:/SLDC_ENERGY_ACC/CMRI/TEMP/";
	public static String CRMI_EXE_FILE_REPOSITORY="E:/SLDC_ENERGY_ACC/CMRI/CMRI_WS/";

	public static String LOSS_REPORT_CRITERIA_G_T="G_T";	
	public static String LOSS_REPORT_CRITERIA_I_T_= "I_T";	
	public static String LOSS_REPORT_CRITERIA_INDEPENDENT_="INDEPENDENT";	
	public static String LOSS_REPORT_CRITERIA_T_D_132_11_=	"T_D_132_11";	
	public static String LOSS_REPORT_CRITERIA_T_D_132_33_=	"T_D_132_33";	
	public static String LOSS_REPORT_CRITERIA_T_D_132_66=	"T_D_132_66";	
	public static String LOSS_REPORT_CRITERIA_T_D_220_66_=	"T_D_220_66";	
	public static String LOSS_REPORT_CRITERIA_X=	"X";	

	public static Integer LOSS_REPORT_LOC_INCLUDED_X=	100;	
	public static Integer LOSS_REPORT_LOC_NOT_INCLUDED_X=	-100;	


	public static Integer FILE_DAILY_RECORD_COUNT_HEALTHY=	45;	
	public static Integer FILE_LOAD_SURVEY_RECORD_COUNT_HEALTHY=	4320;	



	public static Integer TYPE_FILE_CONTAINS_TRANSACTION_DATE_AND_REGISTERS=	100;	
	public static Integer TYPE_FILE_CONTAINS_NO_TRNSACATION_DATE_NO_REGISTERS=	200;	

	public static String DAILY_TRANSACTION_ENTERED_MANUALLY="DAILY_TRANSACTION_ENTERED_MANUALLY";



	//case 1:
	//    //Response.Redirect("~/Inventory/storewelcome.aspx");
	//    Response.Redirect("~/Inventory/HomePageStoreJE.aspx");
	//    break;
	//case 2:
	//    Response.Redirect("~/Inventory/HomePageStoreAE.aspx");
	//    break;
	//case 10:
	//    Response.Redirect("~/Inventory/HomePageMaster.aspx");
	//
	//    break;
	//case 11:
	//    Response.Redirect("~/ReportManagement/HomePageMgt.aspx");
	//    break;
	//case 3:
	//    Response.Redirect("~/Inventory/HomePageSubDivison.aspx");
	//    break;
	//case 4:
	//   // Response.Redirect("~/Inventory/SubdivisonSRAE.aspx");
	//    Response.Redirect("~/Inventory/HomePageSubDivisonAE.aspx");
	//    break;
	//case 12:
	//    Response.Redirect("~/Inventory/SDInventory.aspx");
	//    break;
	//case 13:
	//    Response.Redirect("~/Inventory/dailystockposition.aspx");
	//    break;







	public static Integer FILE_ERROR_METER_NO_NOT_MENTIONED=-500;
	public static Integer FILE_ERROR_FILE_DELETED=-400;
	public static Integer FILE_ERROR_METER_NO_MISMATCH=-300;	
	public static Integer FILE_ERROR_METER_NOT_FOUND=-200;	
	public static Integer FILE_ERROR=-100;
	public static Integer FILE_ZIP_UPLOADED=25;
	public static Integer FILE_ZIP_EXTRACTED=100;
	public static Integer FILE_TXT_PROCESSED=200;




	public static Integer FILE_ACTION__JE_SAVED_APPROVAL_PENDING=25;
	//public static Integer FILE_ACTION__LOCKED_JE=50;
	//public static Integer FILE_ACTION__UNLOCKED_AE=75;
	public static Integer FILE_ACTION__APPROVED_AE=100;
	public static Integer FILE_ACTION__DELETED = -200;


	public static Integer DAILY_TRANSACTION_JE_SAVED_APPROVAL_PENDING=25;
	public static Integer DAILY_TRANSACTION_LOCKED_JE=50;
	public static Integer DAILY_TRANSACTION_UNLOCKED_AE=75;
	public static Integer DAILY_TRANSACTION_APPROVED_AE=100;
	public static Integer DAILY_TRANSACTION_NOT_ENTERED_YET=-100;

	public static Integer DAILY_TRANSACTION_ADDED_MANUALLY=500;



	public static Integer MONTHLY_TRANSACTION_JE_SAVED_APPROVAL_PENDING=125;
	public static Integer MONTHLY_TRANSACTION_LOCKED_JE=150;
	public static Integer MONTHLY_TRANSACTION_UNLOCKED_AE=175;
	public static Integer MONTHLY_TRANSACTION_APPROVED_AE=200;
	public static Integer MONTHLY_TRANSACTION_NOT_ENTERED_YET=-200;

	public static Date getDayBeforeDate(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		cal.set(Calendar.AM_PM, Calendar.AM);
		cal.add(Calendar.DAY_OF_MONTH, -1);



		return new Date(cal.getTime().getTime());
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public static Date getYesterday()
	{


		return getDayBeforeDate(getToday());
	}

	public static Date getToday()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		cal.set(Calendar.AM_PM, Calendar.AM);

		return new Date(cal.getTime().getTime());
	}
	public static Integer getCurrentMonth()
	{
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH);
	}

	public static Integer getCurrentYear()
	{
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	public static Integer getMonth(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}

	public static Integer getYear(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	public static int getPreviousMonth(Integer month, Integer year) {
		if(month==0)
		{
			month=11;
		}
		return month;
	}

	public static int getYearForPreviousMonth(Integer month, Integer year) {
		if(month==0)
		{
			year--;
		}
		return year;
	}

	public static Date convertMonthYearToDate(Integer month, Integer year) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,15);
		cal.set(Calendar.MONTH,month);
		cal.set(Calendar.YEAR,year);
		return cal.getTime();

	}


}
