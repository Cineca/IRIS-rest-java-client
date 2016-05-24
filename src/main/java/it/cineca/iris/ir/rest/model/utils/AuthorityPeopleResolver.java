package it.cineca.iris.ir.rest.model.utils;

import it.cineca.iris.ir.rest.model.RmPersonRestDTO;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;


public class AuthorityPeopleResolver extends AbstractAuthorityResolver {

	@Override
	public String resolve(String searchValue) throws Exception {
		String result = null;
	
		Response response = restIRClient.personByCF(searchValue);
		
		result = response.readEntity(String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		RmPersonRestDTO person = mapper
				.readValue(result, RmPersonRestDTO.class);
		System.out.println(person.lastName);
		
		result = person.getCrisId();
			
		return result;
	}



}
