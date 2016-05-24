package it.cineca.iris.ir.rest.search.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "type", defaultImpl = RestSearchCriteria.class)
@JsonSubTypes({ @JsonSubTypes.Type(value = RestSearchCriteria.class, name = "criteria"),
		@JsonSubTypes.Type(value = RestSearchBooleanClause.class, name = "booleanClause") })
public abstract class RestSearchElement {
	
}
