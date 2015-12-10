package it.cineca.iris.ir.rest.model.utils;

public enum HeaderBitstreamDisseminationOptionsEnum implements IHeaderTag{
	
    NULL(),
    VISIBLE(),
    NOTVISIBLE(),
    DISCLAIMER();
    
	private static final HeaderTagNameEnum TAG_NAME = HeaderTagNameEnum.BITSTREAM_DISSEMINATION_OPTION;	

	public static String getHeaderTag() {
		return TAG_NAME.getHeaderTag();
	}

	@Override
	public String getHeaderValue() {
		return this.name();
	}
	
}
