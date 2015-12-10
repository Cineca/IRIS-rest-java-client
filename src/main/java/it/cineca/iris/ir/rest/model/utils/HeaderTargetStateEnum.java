package it.cineca.iris.ir.rest.model.utils;

public enum HeaderTargetStateEnum  implements IHeaderTag {
	
	WORKSPACE,
	STEPONE,
	STEPTWO,
	STEPTHREE,
	PUBLISH;
	
	private final static HeaderTagNameEnum TAG_NAME = HeaderTagNameEnum.TARGET_STATE;
	
	public static String getHeaderTag() {
		return TAG_NAME.getHeaderTag();
	}

	@Override
	public String getHeaderValue() {
		return this.name();
	}

}
