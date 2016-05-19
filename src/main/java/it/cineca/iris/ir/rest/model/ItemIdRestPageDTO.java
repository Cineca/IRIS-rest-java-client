package it.cineca.iris.ir.rest.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemIdRestPageDTO {
	
	private List<Integer> idList;
	private Integer next;

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
	
	public Integer getNext() {
		return next;
	}

	public void setNext(Integer next) {
		this.next = next;
	}

}
