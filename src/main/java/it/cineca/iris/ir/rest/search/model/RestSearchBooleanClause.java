package it.cineca.iris.ir.rest.search.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("booleanClause")
public class RestSearchBooleanClause extends RestSearchElement {
	private List<? extends RestSearchElement> restSearchElementList;
	private BooleanOperatorEnum booleanOperator;

	public RestSearchBooleanClause() {
	}

	public RestSearchBooleanClause(List<? extends RestSearchElement> restSearchElementList,
			BooleanOperatorEnum booleanOperator) {
		super();
		this.restSearchElementList = restSearchElementList;
		this.booleanOperator = booleanOperator;
	}
	
	public static enum BooleanOperatorEnum {
		AND, OR, NOT
	}

	public List<? extends RestSearchElement> getRestSearchElementList() {
		return restSearchElementList;
	}

	public void setRestSearchElementList(List<? extends RestSearchElement> restSearchElementList) {
		this.restSearchElementList = restSearchElementList;
	}

	public BooleanOperatorEnum getBooleanOperator() {
		return booleanOperator;
	}

	public void setBooleanOperator(BooleanOperatorEnum booleanOperator) {
		this.booleanOperator = booleanOperator;
	}
}
