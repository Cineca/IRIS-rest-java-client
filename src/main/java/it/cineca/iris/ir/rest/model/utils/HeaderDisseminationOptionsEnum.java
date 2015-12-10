package it.cineca.iris.ir.rest.model.utils;

public enum HeaderDisseminationOptionsEnum  implements IHeaderTag {
	
    NULL(),
    VISIBLE(),
    NOTVISIBLE(),
    DISCLAIMER();
    
	private static final HeaderTagNameEnum TAG_NAME = HeaderTagNameEnum.DISSEMINATION_OPTION;

	public static String getHeaderTag() {
		return TAG_NAME.getHeaderTag();
	}

	@Override
	public String getHeaderValue() {
		return this.name();
	}

}
