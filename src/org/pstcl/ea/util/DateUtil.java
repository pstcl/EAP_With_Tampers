package org.pstcl.ea.util;

import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Integer daysInMonth(Integer month, Integer year) {
		YearMonth yearMonthObject = YearMonth.of(year,month);
		return yearMonthObject.lengthOfMonth();
	}
	public static Long daysBetweenDates(Date firstDate,Date secondDate) {
		return  ChronoUnit.DAYS.between(firstDate.toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate(), secondDate.toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate());
	}
	public static Integer daysInMonth(Date date) {
		YearMonth yearMonthObject =  YearMonth.from(date.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate());
		return yearMonthObject.lengthOfMonth();
	}
	public static Date getDayMinusMonth(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		cal.set(Calendar.AM_PM, Calendar.AM);
		cal.add(Calendar.DATE, -30);



		return new Date(cal.getTime().getTime());
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
		if(null==year)
		{
			year=2018;
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

		Date date=null;
		if(null!=month&&null!=year)
		{
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH,15);
			cal.set(Calendar.MONTH,month);
			cal.set(Calendar.YEAR,year);
			date= cal.getTime();
		}
		return date;
	}

	public static Date startDateTimeForDailySurveyRecs(Integer month, Integer year) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,1);
		cal.set(Calendar.MONTH,month);
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 1);
		return cal.getTime();
	}



	public static Date endDateTimeForDailySurveyRecs(Integer month, Integer year) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,1);
		cal.set(Calendar.MONTH,month+1);
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 1);
		return cal.getTime();
	}


	public static Date startDateTimeForLoadSurveyFromFileDate(Date fileDate) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(fileDate);


		cal.set(Calendar.DAY_OF_MONTH,1);
		cal.add(Calendar.MONTH,-1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 1);


		return cal.getTime();
	}

	public static Date endDateTimeForLoadSurveyFromFileDate(Date fileDate) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(fileDate);


		cal.set(Calendar.DAY_OF_MONTH,1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 1);


		return cal.getTime();
	}

	public static Date startDateTimeForDailyFromFileDate(Date fileDate) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(fileDate);


		cal.set(Calendar.DAY_OF_MONTH,1);
		cal.add(Calendar.MONTH,-1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 1);


		return cal.getTime();
	}

	public static Date endDateTimeForDailyFromFileDate(Date fileDate) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(fileDate);


		cal.set(Calendar.DAY_OF_MONTH,1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 1);


		return cal.getTime();
	}

	public static Date additionDailySurveyRecsStartDate(Integer month, Integer year) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,2);
		cal.set(Calendar.MONTH,month);
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	public static Date additionDailySurveyRecsEndDate(Integer month, Integer year) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,1);
		cal.set(Calendar.MONTH,month+1);
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}


	public static Date nextDay(Date current) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(current);
		calendar.add(Calendar.DATE, 1);
		current = calendar.getTime();
		return current;
	}


}
