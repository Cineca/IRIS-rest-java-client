package it.cineca.iris.ir.rest.model.utils;

import it.cineca.iris.restclient.main.RESTIRClient;

public abstract class AbstractAuthorityResolver implements IAuthorityResolver {
	
	protected RESTIRClient restIRClient;

	public RESTIRClient getRestIRClient() {
		return restIRClient;
	}

	public void setRestIRClient(RESTIRClient restIRClient) {
		this.restIRClient = restIRClient;
	}

}
