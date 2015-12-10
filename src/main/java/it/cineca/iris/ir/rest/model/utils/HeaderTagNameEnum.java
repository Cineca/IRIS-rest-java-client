package it.cineca.iris.ir.rest.model.utils;

public enum HeaderTagNameEnum {
	
	SCOPE("scope"),
	ON_BEHALF_OF("on-behalf-of"),
	TARGET_STATE("target-state"),
	ON_BEHALF_OF_SCOPE("on-behalf-of-scope"),
	DISSEMINATION_OPTION("dissemination-option"),
	BITSTREAM_DISSEMINATION_OPTION("bitstream-validation-option"),
	ACT_AS_BATCH_USER("act-as-batch");
	
	private String headerTag;
	
	public String getHeaderTag() {
		return this.headerTag;
	}
	
	private HeaderTagNameEnum (String headerTag){
		this.headerTag = headerTag;
	}

}
