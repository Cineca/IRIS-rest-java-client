package it.cineca.iris.ir.rest.model.utils;

import java.util.HashMap;
import java.util.Map;

public class ChoiceAuthorityManager {
	
	private Map<String, IAuthorityResolver> resolver = new HashMap<String, IAuthorityResolver>();
	
	private ChoiceAuthorityManager() throws InstantiationException, IllegalAccessException {
		for (AuthorityResolverMappingEnum resolverMapper : AuthorityResolverMappingEnum.values()) {
			resolver.put(resolverMapper.toString().replace("_", "."), resolverMapper.getResolver());	
		}
	}
	
	public static ChoiceAuthorityManager getManager() throws InstantiationException, IllegalAccessException {
		return new ChoiceAuthorityManager();
	}
	
	public boolean isAuthorityManaged(String schema, String element, String qualifier, String authKey, String locale) {
		return resolver.containsKey(KeyUtils.makeFieldKey(schema, element, qualifier));
	}
	
    public IAuthorityResolver getAuthorityResolver(String schema, String element, String qualifier)
    {
    	return resolver.get(KeyUtils.makeFieldKey(schema, element, qualifier));
    }
    


}
