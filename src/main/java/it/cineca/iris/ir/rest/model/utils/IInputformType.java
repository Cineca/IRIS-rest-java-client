package it.cineca.iris.ir.rest.model.utils;

import it.cineca.iris.ir.rest.model.MetadataEntryRestDTO;

import java.util.List;
import java.util.Map;

public interface IInputformType {
	
	public Map<String, List<MetadataEntryRestDTO>> build(String schema, String element, String qualifier, String value, String authority);

}
