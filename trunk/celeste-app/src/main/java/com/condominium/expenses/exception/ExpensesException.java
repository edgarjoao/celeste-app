package com.condominium.expenses.exception;

import com.condominium.common.exception.CondominiumException;
/**
 * 
 * @author rioslore
 *
 */
public class ExpensesException extends CondominiumException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4281256962951433246L;
	/** Specific customer error*/
	private String customError;
	/** Error code level */
	private String level = null;
	/** Error code action */
	private String action = null;
	/** Code Identifier Module */
	private static final String MODULE_EXCEPTION_CODE = "01";//$NON-NLS-1$
	
	/** Inicialitation Expenses Exception with Level code and Action Code */
	public ExpensesException(Exception exception, String level, String action){
		super(exception);
		this.level = level;
		this.action = action;
	}
	
	/** Inicialitation Expenses Exception with Level code and Action Code */
	public ExpensesException(String customError, String level, String action){
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
