package it.cineca.iris.ir.rest.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemRestWriteDTO {
	
	private ContainerDTO collection;
	private Map<String, List<MetadataEntryRestDTO>> metadata;
	
    public ContainerDTO getCollection() {
        return collection;
    }

    public void setCollection(ContainerDTO collection) {
        this.collection = collection;
    }
    
    public Map<String, List<MetadataEntryRestDTO>> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, List<MetadataEntryRestDTO>> metadata) {
        this.metadata = metadata;
    }
    
    public void addMetadata(Map<String, List<MetadataEntryRestDTO>> metadata) {
    	if (this.metadata == null) {
    		this.metadata = new HashMap<String, List<MetadataEntryRestDTO>>();
    	}
        this.metadata.putAll(metadata);
    }

}
