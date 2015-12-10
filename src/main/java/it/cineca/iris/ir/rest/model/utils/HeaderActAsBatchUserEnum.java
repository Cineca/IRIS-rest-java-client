package it.cineca.iris.ir.rest.model.utils;

public enum HeaderActAsBatchUserEnum  implements IHeaderTag {
	
    TRUE(),
    FALSE();
    
	private static final HeaderTagNameEnum TAG_NAME = HeaderTagNameEnum.ACT_AS_BATCH_USER;

	public static String getHeaderTag() {
		return TAG_NAME.getHeaderTag();
	}

	@Override
	public String getHeaderValue() {
		return this.name();
	}

}
