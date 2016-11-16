package it.cineca.iris.ir.rest.model.utils;

public enum InputTypeEnum {
	
	/**
	 * the int constant for null InputTypeEnum
	 */
	textarea("textarea", TextType.class), 
	name("name", TextType.class), 
	date("date", TextType.class), 
	dropdown("dropdown", TextType.class),
	qualdrop_value("qualdrop_value", TextType.class),
	qualdrop_text("qualdrop_text", TextType.class), 
	controlled_name("controlled_name", TextType.class), 
	default_value("default_value", TextType.class),
	sharepriority("sharepriority", TextType.class),
	onebox("onebox", TextType.class),
	twobox("twobox", TextType.class),
	list("list", TextType.class),
	ance("ance", TextType.class),
	fulldate("fulldate", TextType.class),
	year("year",YearType.class),
	checkbox("checkbox", TextType.class),
	annotation("annotation", TextType.class),
	dropdown_json("dropdown_json", TextType.class);
	
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
