package it.cineca.iris.ir.rest.model;

public class AuthorityRestDTO {
	
	private String authority;
	private String label;
	private String value;
	
	public AuthorityRestDTO() {
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
