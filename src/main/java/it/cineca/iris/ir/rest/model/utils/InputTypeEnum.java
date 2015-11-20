package it.cineca.iris.ir.rest.model.utils;

public enum InputTypeEnum {
	
	/**
	 * the int constant for null InputTypeEnum
	 */
	textarea("textarea", TextType.class), 
	name("name", AbstractInputformType.class), 
	date("date", AbstractInputformType.class), 
	dropdown("dropdown", AbstractInputformType.class),
	qualdrop_value("qualdrop_value", AbstractInputformType.class),
	qualdrop_text("qualdrop_text", AbstractInputformType.class), 
	controlled_name("controlled_name", AbstractInputformType.class), 
	default_value("default_value", AbstractInputformType.class),
	sharepriority("sharepriority", TextType.class),
	onebox("onebox", AbstractInputformType.class),
	twobox("twobox", AbstractInputformType.class),
	list("list", AbstractInputformType.class),
	ance("ance", AbstractInputformType.class),
	fulldate("fulldate", AbstractInputformType.class),
	year("year",YearType.class),
	checkbox("checkbox", AbstractInputformType.class),
	annotation("annotation", AbstractInputformType.class),
	dropdown_json("dropdown_json", AbstractInputformType.class);
	
	private String keyEnum;
	
	private Class<? extends AbstractInputformType> clazz;
	
	public Class<? extends AbstractInputformType> getClazz() {
		return clazz;
	}
	
	private InputTypeEnum(String keyEnum, Class<? extends AbstractInputformType> clazz) {
		this.keyEnum = keyEnum;
		this.clazz = clazz;
	}
}
