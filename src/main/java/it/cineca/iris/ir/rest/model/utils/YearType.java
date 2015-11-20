package it.cineca.iris.ir.rest.model.utils;

import it.cineca.iris.ir.rest.model.MetadataEntryRestDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YearType extends AbstractInputformType {

	@Override
	public Map<String, List<MetadataEntryRestDTO>> build(String schema, String element, String qualifier, String value, String authority) {
		Map<String, List<MetadataEntryRestDTO>> result = new HashMap<String, List<MetadataEntryRestDTO>>();
		
		MetadataEntryRestDTO entry = new MetadataEntryRestDTO();
		entry.setAuthority(authority);
		entry.setValue(value);
		
		result.put(KeyUtils.makeFieldKey(schema, element, qualifier), new ArrayList<MetadataEntryRestDTO>(Arrays.asList(entry)));
		
		return result;
	}

	

}
