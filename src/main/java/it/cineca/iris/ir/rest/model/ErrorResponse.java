package it.cineca.iris.ir.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse extends Exception {
	
	public static final String GENERIC_EX_CODE = "GENERIC";
	
	@JsonProperty
	private String code;
	@JsonProperty
	private String message;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ErrorResponse() {
		super();
		this.code = GENERIC_EX_CODE;
		this.message = "Generic Error";
	}
	
	public ErrorResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public ErrorResponse(Exception ex) {
		super();
		this.code = ex.getClass().getName();
		this.message = ex.getMessage();
	}
	
	@Override
	public String toString() {
		return "RestException [code=" + code + ", message=" + message + "]";
	}
		
}
