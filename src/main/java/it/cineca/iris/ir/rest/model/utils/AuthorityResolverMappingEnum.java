package it.cineca.iris.ir.rest.model.utils;

public enum AuthorityResolverMappingEnum {
	dc_authority_people(AuthorityPeopleResolver.class);
	
	private Class<? extends IAuthorityResolver> clazz;
	
	public IAuthorityResolver getResolver() throws InstantiationException, IllegalAccessException {
		return clazz.newInstance();
	}
	
	private AuthorityResolverMappingEnum(Class<? extends IAuthorityResolver> clazz) {
		this.clazz = clazz;
	}
}
