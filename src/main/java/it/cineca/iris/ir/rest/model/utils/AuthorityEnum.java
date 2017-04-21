package it.cineca.iris.ir.rest.model.utils;

public enum AuthorityEnum {

	PEOPLE("dc.authority.people"), 
	ORGUNIT("dc.authority.orgunit"), 
	JOURNAL("dc.authority.ancejournal"), 
	SERIE("dc.authority.anceserie"), 
	ACCADEMICFIELD("dc.authority.academicField2000"), 
	RESEARCHCENTER("dc.authority.researchcenter"), 
	PHDCOURSE("dc.authority.phdCourse"), 
	PHDSCHOOL("dc.authority.phdSchool");

	private String value;

	private AuthorityEnum(String value) {
		this.value=value;
	}

	public String getValue() {
		return value;
	}
	
}
