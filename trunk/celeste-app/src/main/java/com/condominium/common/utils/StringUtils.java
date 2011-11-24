package com.condominium.common.utils;

import java.io.UnsupportedEncodingException;

public class StringUtils {

	public static final String MAJOR_CHAR = ">";
	public static final String MINOR_CHAR = "<";
	public static final String EQUALS_CHAR = "=";
	public static final int ZERO_NUMBER = 0;
	
	public static final String JANUARY = "ENERO";
	public static final int JANUARY_NUMBER = 1;
	
	public static final String FEBRUARY = "FEBRERO";	
	public static final int FEBRUARY_NUMBER = 2;
	
	public static final String MARCH = "MARZO";
	public static final int MARCH_NUMBER = 3;
	
	public static final String APRIL = "ABRIL";
	public static final int APRIL_NUMBER = 4;
	
	public static final String MAY = "MAYO";
	public static final int MAY_NUMBER = 5;
	
	public static final String JUNE = "JUNIO";
	public static final int JUNE_NUMBER = 6;
	
	public static final String JULY = "JULIO";
	public static final int JULY_NUMBER = 7;
	
	public static final String AUGUST = "AGOSTO";
	public static final int AUGUST_NUMBER = 8;
	
	public static final String SEPTEMBER = "SEPTIEMBRE";
	public static final int SEPTEMBER_NUMBER = 9;
	
	public static final String OCTOBER = "OCTUBRE";
	public static final int OCTUBER_NUMBER = 10;
	
	public static final String NOVEMBER = "NOVIEMBRE";
	public static final int NOVEMBER_NUMBER = 11;
	
	public static final String DECEMBER = "DICIEMBRE";
	public static final int DECEMBER_NUMBER = 12;
	
	
	/** Empty character */
	private static final Object EMPTY_CHAR = "";
	
	/** Space character */
	public static final String SPACE_CHAR = " ";
	
	/** Left parenthesis */
	public static final String LEFT_PARENTHESIS = " (";
	
	/** Right parenthesis */
	public static final String RIGHT_PARENTHESIS = ") ";
	/** Comma */
	public static final String COMMA = ",";
	/** Vacio*/
	public static final String EMPTY_STRING = "";
	/** Guion*/
	public static final String SLASH_CHAR= "-";
	/** Porcentaje*/
	public static final String PERCENT_CHAR= "%";
	/** Puntos suspensivos*/
	private static final String SUSPENSIVOS_POINTS = "...";
	/**
	 * Validates if any string is empty
	 * @param valString
	 * @return If valString is empty
	 */
	public static boolean emptyString(String valString){
		boolean retVal = true;
		if( valString != null && !valString.trim().equals(EMPTY_CHAR)){
			retVal = false;
		}
		return retVal;
	}

	
	public static boolean numericString(String valString){
		boolean retVal = true;
		if( valString != null){
			try {
				Integer.parseInt(valString);
				retVal = true;
			} catch (NumberFormatException e) {
				retVal = false;
			}			
		}
		return retVal;
	}
	
	public static String likeStatement(String toConvertToLike){
		StringBuffer startToConvert = new StringBuffer(PERCENT_CHAR);
		try{
			if( toConvertToLike == null ){
				toConvertToLike = EMPTY_STRING;
			}
			startToConvert.append(toConvertToLike);
			startToConvert.append(PERCENT_CHAR);
		}catch (Exception exception) {
			startToConvert = new StringBuffer(toConvertToLike); 
		}
		return startToConvert.toString();
	}
	
	public static String likeRightStatement(String toConvertToLike){
		StringBuffer startToConvert = new StringBuffer(0);
		try{
			if( toConvertToLike == null ){
				toConvertToLike = EMPTY_STRING;
			}
			startToConvert.append(toConvertToLike);
			startToConvert.append(PERCENT_CHAR);
		}catch (Exception exception) {
			startToConvert = new StringBuffer(toConvertToLike); 
		}
		return startToConvert.toString();
	}
	
	public static String cutString(String strToCut, int length){
		StringBuffer strCuted;
		
		strCuted = new StringBuffer(0);
		if( strToCut != null ){
			if( strToCut.length() > length ){
				strCuted.append(strToCut.substring(0,length-3));
				strCuted.append(SUSPENSIVOS_POINTS);
			}else{
				strCuted.append(strToCut);
			}
		}
		
		return strCuted.toString();
	}
	/**
	 * Metodo que regresa el nombre del mes
	 * 
	 * @author Edgar Joao
	 * @param month
	 * @return Nombre del mes
	 */
	public static String getMonth(int month){
		String monthName = null;
		switch (month) {
		case JANUARY_NUMBER:
				monthName = JANUARY;
				break;
		case FEBRUARY_NUMBER:
				monthName = FEBRUARY;
				break;
		case MARCH_NUMBER:
				monthName = MARCH;
				break;
		case APRIL_NUMBER:
				monthName = APRIL;
				break;
		case MAY_NUMBER:	
				monthName = MAY;
				break;
		case JUNE_NUMBER:
				monthName = JUNE;
				break;
		case JULY_NUMBER:
				monthName = JULY;
				break;
		case AUGUST_NUMBER:
				monthName = AUGUST;
				break;
		case SEPTEMBER_NUMBER:
				monthName = SEPTEMBER;
				break;
		case OCTUBER_NUMBER:
				monthName = OCTOBER;
				break;
		case NOVEMBER_NUMBER:
				monthName = NOVEMBER;
				break;
		case DECEMBER_NUMBER:
				monthName = DECEMBER;
				break;
		case ZERO_NUMBER:
				monthName = EMPTY_STRING;
				break;
		default:			
			break;
		}
		return monthName;
	}
	/**
	 * Metodo que obtiene el nombre de un archivo
	 * eliminando la ruta
	 * 
	 * @author Edgar Joao
	 * @param name
	 * @param character
	 * @return
	 */
	public static String getFileName(String name){
		StringBuffer result = null;		
		result = new StringBuffer(0);		
		for (int i = name.length() -1; i >= 0; i--) {
			if(name.charAt(i) != '/'){
				result.append(name.charAt(i));
			}else{
				break;
			}
		}
		result.reverse();
		return result.toString();
	}
	
	public static String getUTF8Encoding(String value){
		byte[] utf8 = null; 
		String result = null;
		try {
			utf8 = value.getBytes("UTF-8");
			result = new String(utf8, "UTF-8"); 			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result; 
	}
}
