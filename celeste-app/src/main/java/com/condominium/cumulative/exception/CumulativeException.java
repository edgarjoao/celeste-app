package com.condominium.cumulative.exception;

import com.condominium.common.exception.CondominiumException;
/**
 * Cumulative Exception
 * @author rioslore
 *
 */
public class CumulativeException  extends CondominiumException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3350951242030164204L;
	/** Specific customer error*/
	private String customError;
	/** Error code level */
	private String level = null;
	/** Error code action */
	private String action = null;
	/** Code Identifier Module */
	private static final String MODULE_EXCEPTION_CODE = "01";//$NON-NLS-1$
	
	/** Inicialitation Mail Service Exception with Level code and Action Code */
	public CumulativeException(Exception exception, String level, String action){
		super(exception);
		this.level = level;
		this.action = action;
	}
	
	/** Inicialitation Mail Service Exception with Level code and Action Code */
	public CumulativeException(String customError, String level, String action){
		super(customError);
		this.customError = customError;
		this.level = level;
		this.action = action;
	}
		
	public String getExceptionCode() {
		StringBuilder exceptionCode = new StringBuilder(0);
		exceptionCode.append(MODULE_EXCEPTION_CODE);
		exceptionCode.append(action);
		exceptionCode.append(level);
		return exceptionCode.toString();
	}
	
	public String getCustomError() {
		return customError;
	}
}
