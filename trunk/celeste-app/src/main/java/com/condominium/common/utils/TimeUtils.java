package com.condominium.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {
	
	public static final String DATE_PATTERN_YYYYMMDD = "yyyy-MM-dd";		
	/**
	 * Returns the current date in de format dd-MM-yyyy
	 * 
	 * @return The current date
	 */
	public static String actualDate() {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		String DATE_FORMAT = "yyyy/mm/dd";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		sdf.setTimeZone(TimeZone.getDefault());
		return sdf.format(cal.getTime());
	}

	public static java.sql.Date actualSQLDate() {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		java.sql.Date sqlDate = new java.sql.Date(cal.getTimeInMillis());
		return sqlDate;
	}

	public static java.sql.Timestamp actualSQLTimeStamp() {
		java.util.Date today = new java.util.Date();
		// Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		java.sql.Timestamp sqlDate = new java.sql.Timestamp(today.getTime());
		return sqlDate;
	}

	public static String convertJavaDateToStringDate(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);
	}
	
	public static String convertJavaDateToString(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}
	
	public static String convertJavaDateToStringDate(java.util.Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static java.util.Date convertStringToDate(String date, String pattern){
		if (date == null)
			return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		java.util.Date parsedDate = null;
		
		try {
			
			parsedDate = (Date) sdf.parse(date);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsedDate;
	}
	
	public static java.sql.Date convertStringToSQLDate(String mydate) {
		if (mydate == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date sqlDate = null;
		try {
			java.util.Date date = (Date) sdf.parse(mydate);
			sqlDate = new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sqlDate;
	}

	public static String convertSQLDateToString(java.sql.Date date) {
		if (date == null)
			return null;
		String DATE_FORMAT = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(date);
	}
	
	public static String convertSQLDateToString(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}
		
	/**
	 * Devuelve el ano actual 
	 * @return
	 */
	public static int getCurrentYear(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public static String convertDateToStringReportFormat(Date fecha){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return sdf.format(fecha);
	}	

}
