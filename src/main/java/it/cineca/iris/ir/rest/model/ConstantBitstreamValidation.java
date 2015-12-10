package it.cineca.iris.ir.rest.model;

public enum ConstantBitstreamValidation {

	NULL("null", -1),
    VALIDATE("validate", 1),
    NOVALIDATE("novalidate", 0);    
    
    private String description;
    
    private int value;
    
    public String getDescription() {
		return description;
	}

	public int getValue() {
		return value;
	}

	private ConstantBitstreamValidation(String desc, int value)
    {
        this.description = desc;
        this.value = value;
    }
}
