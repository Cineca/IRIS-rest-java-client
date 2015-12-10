package it.cineca.iris.ir.rest.model.utils;

public enum HeaderScopeEnum implements IHeaderTag {
	
	ROLE_USER,
	ROLE_ADMIN;
		
	private static final HeaderTagNameEnum TAG_NAME = HeaderTagNameEnum.SCOPE;

	public static String getHeaderTag() {
		return TAG_NAME.getHeaderTag();
	}

	@Override
	public String getHeaderValue() {
		return this.name();
	}

}
