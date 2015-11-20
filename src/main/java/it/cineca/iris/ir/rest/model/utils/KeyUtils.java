package it.cineca.iris.ir.rest.model.utils;

public class KeyUtils {
	
    public static String makeFieldKey(String schema, String element, String qualifier) {
    	StringBuilder sb = new StringBuilder();
    	sb.append(schema==null?"dc":schema).append(".").append(element==null?"null":element).append(qualifier==null?"":".").append(qualifier==null?"":qualifier);
    	
    	return sb.toString();
    }

}
