package it.cineca.iris.restclient.main;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.cineca.iris.ir.rest.command.model.BitstreamOptionsDTO;
import it.cineca.iris.ir.rest.model.AuthorityRestDTO;
import it.cineca.iris.ir.rest.model.CollectionRestDTO;
import it.cineca.iris.ir.rest.model.CollectionRestPageDTO;
import it.cineca.iris.ir.rest.model.ItemRestDTO;
import it.cineca.iris.ir.rest.model.ItemRestPageDTO;
import it.cineca.iris.ir.rest.model.ItemRestWriteDTO;
import it.cineca.iris.ir.rest.model.MetadataEntryRestDTO;
import it.cineca.iris.ir.rest.model.RmPersonRestDTO;
import it.cineca.iris.ir.rest.model.utils.AuthorityEnum;
import it.cineca.iris.ir.rest.model.utils.HeaderActAsBatchUserEnum;
import it.cineca.iris.ir.rest.model.utils.HeaderDisseminationOptionsEnum;
import it.cineca.iris.ir.rest.model.utils.HeaderScopeEnum;
import it.cineca.iris.ir.rest.model.utils.HeaderTargetStateEnum;
import it.cineca.iris.ir.rest.search.model.RestSearchCriteria;
import it.cineca.iris.ir.rest.search.model.SearchRestDTO;

/**
 * This is a test class that shows how to use RESTIRClient
 * to read and write items from the IRIS API
 * 
 * @author mdoni
*/
public class Main {

	private static RESTIRClient client;

	public static void main(String[] args) {
		try {
			PropertiesReader reader = new PropertiesReader();
			Properties prop = reader.getProperties();
			String restBaseURI = prop.getProperty("BASE_URI"), pathIR = prop.getProperty("PATH_IR"),
					pathRM = prop.getProperty("PATH_RM"), username = prop.getProperty("USERNAME"),
					password = prop.getProperty("PASSWORD");
			client = new RESTIRClient(restBaseURI, pathIR, pathRM, username, password);
			client.buildUnsecureInstance();
			client.setConnectTimeOut(RESTIRClient.CONNECT_TIMEOUT);
			client.setReadTimeOut(RESTIRClient.READ_TIMEOUT);

			// call test Echo IR and RM service
			ObjectMapper mapper = new ObjectMapper();
			Response response = client.echoIR();
			String responseString = response.readEntity(String.class);
			System.out.println("check IR: " + responseString);
			response = client.echoRM();
			responseString = response.readEntity(String.class);
			System.out.println("check RM: " + responseString);

			// get a random item
			response = client.items(1, 37);
			responseString = response.readEntity(String.class);
			ItemRestPageDTO items = mapper.readValue(responseString, ItemRestPageDTO.class);
			ItemRestDTO item = items.getRestResourseDTOList().get(0);
			System.out.println("item:" + item.getItemId() + "[" + item.getHandle() + "]");

			// get item submitter info
			String submitterId = String.valueOf(item.getSubmitterID());
			response = client.personById(submitterId);
			responseString = response.readEntity(String.class);
			RmPersonRestDTO submitter = mapper.readValue(responseString, RmPersonRestDTO.class);
			System.out.println("subitter:" + submitter.getCF() + "[" + submitter.getCrisId() + "]");

			// find items created by that author
			ArrayList<RestSearchCriteria> searchList = new ArrayList<>();
			SearchRestDTO itemSearchDTO = new SearchRestDTO();
			itemSearchDTO.setOffset(0);
			itemSearchDTO.setLimit(10);
			itemSearchDTO.setExpand(""); // "metadata", "bitstreams", "history", "all"
			RestSearchCriteria searchCriteria = new RestSearchCriteria();
			searchCriteria.setColumn("lookupValues_contextuser");
			searchCriteria.setOperation("=");
			searchCriteria.setValue(submitter.getCrisId()); // es: rp01234
			searchList.add(searchCriteria);
			searchCriteria = new RestSearchCriteria();
			searchCriteria.setColumn("snapshot");
			searchCriteria.setOperation("=");
			searchCriteria.setValue("0");
			searchList.add(searchCriteria);
			itemSearchDTO.setSearchColsCriteria(searchList);
			response = client.items(itemSearchDTO);
			responseString = response.readEntity(String.class);
			items = mapper.readValue(responseString, ItemRestPageDTO.class);
			for (ItemRestDTO itemRestDTO : items.getRestResourseDTOList()) {
				System.out.println("found :" + itemRestDTO.getId());
			}

			// create an item
			response = client.collections();
			responseString = response.readEntity(String.class);
			CollectionRestPageDTO collections = mapper.readValue(responseString, CollectionRestPageDTO.class);
			// get a random collection to create item in
			CollectionRestDTO collection = collections.getRestResourseDTOList().get(0);
			System.out.println("creating an item in collection " + collection.getHandle() + ":" + collection.getName());
			Integer targetInputFormId = collection.getInputformActiveId();
	        if (collection != null && collection.getHandle() != null && !collection.getHandle().isEmpty()
	                && targetInputFormId != null && targetInputFormId > -1) {
	        	// create item to write
	        	ItemRestWriteDTO itemToCreate = new ItemRestWriteDTO();
	            itemToCreate.setCollection(collection.getContainerDTO());
	            // add metadatas to item
	            Map<String, List<MetadataEntryRestDTO>> metadati = new HashMap<String, List<MetadataEntryRestDTO>>();
	            ArrayList<MetadataEntryRestDTO> dto = new ArrayList<MetadataEntryRestDTO>();
	            MetadataEntryRestDTO entry = new MetadataEntryRestDTO();
	            entry.setValue("rest test title");
	            dto.add(entry);
	            metadati.put("dc.title", dto);
	            dto = new ArrayList<MetadataEntryRestDTO>();
	            entry = new MetadataEntryRestDTO();
	            entry.setValue("2016");
	            dto.add(entry);
	            metadati.put("dc.date.issued", dto);
	            dto = new ArrayList<MetadataEntryRestDTO>();
	            entry = new MetadataEntryRestDTO();
	            entry.setValue("M. Rossi, G. Bianchi, L. Neri");
	            dto.add(entry);
	            metadati.put("dc.description.allpeople", dto);
	            dto = new ArrayList<MetadataEntryRestDTO>();
	            entry = new MetadataEntryRestDTO();
	            entry.setValue(submitter.getCF());
	            entry.setAuthority(submitter.getCrisId()); // use the same submitter 
	            dto.add(entry);
	            metadati.put("dc.authority.people", dto);
	            dto = new ArrayList<MetadataEntryRestDTO>();
	            entry = new MetadataEntryRestDTO();
	            entry.setValue("15");
	            dto.add(entry);
	            metadati.put("dc.relation.numberofpages", dto);
	            dto = new ArrayList<MetadataEntryRestDTO>();
	            entry = new MetadataEntryRestDTO();
	            entry.setValue("Mulino Editore");
	            dto.add(entry);
	            metadati.put("dc.publisher.name", dto);
	            // search on iris for the authority and create the authority matadata
	            List<AuthorityRestDTO> acFieldSearch = client.searchAuthority(AuthorityEnum.JOURNAL,"FASCICULI ARCHAEOLOGIAE HISTORICAE");
	            if (acFieldSearch.size()>0) {
	            	AuthorityRestDTO authority = acFieldSearch.get(0);
	            	dto = new ArrayList<MetadataEntryRestDTO>();
	                entry = new MetadataEntryRestDTO();
	                entry.setValue(authority.getValue());
	                entry.setAuthority(authority.getAuthority());
	                dto.add(entry);
	                metadati.put("dc.authority.ancejournal", dto);
	            }
	            itemToCreate.setMetadata(metadati);
	            MultivaluedMap<String, Object> headers = new MultivaluedHashMap<String, Object>();
	            headers.add(HeaderScopeEnum.getHeaderTag(), HeaderScopeEnum.ROLE_ADMIN.getHeaderValue());
	            // headers.add(HeaderTagNameEnum.ON_BEHALF_OF.getHeaderTag(), "anotherusername");
	            // workflow state: WORKSPACE, STEPONE, STEPTWO, STEPTHREE, PUBLISH
	            headers.add(HeaderTargetStateEnum.getHeaderTag(), HeaderTargetStateEnum.PUBLISH.getHeaderValue());
	            // public portal visibility
	            headers.add(HeaderDisseminationOptionsEnum.getHeaderTag(), HeaderDisseminationOptionsEnum.VISIBLE.getHeaderValue());
	            headers.add(HeaderActAsBatchUserEnum.getHeaderTag(), HeaderActAsBatchUserEnum.TRUE.getHeaderValue());
	            response = client.createItem(itemToCreate, headers);         
	            System.out.println("created item rest resource: " + response.getHeaderString("Location"));
	            String handle = getHandleFromResource(response.getHeaderString("Location"));
	            System.out.println("created item: " + handle);

				// upload a bitstream
	            if (handle!=null) {
	                String filename = "prova.txt"; // example file
	                InputStream targetStream = new FileInputStream(filename);
	                BitstreamOptionsDTO bitstreamOptionsDTO = new BitstreamOptionsDTO();
	                bitstreamOptionsDTO.setDescription("Test file");
	                bitstreamOptionsDTO.setPolicyKey("embargo"); // "embargo", "openAcess"
	                bitstreamOptionsDTO.setEmbargoDate(new java.util.Date()); // if under embargo
	                response = client.uploadStream(handle, bitstreamOptionsDTO, targetStream, filename);
	                System.out.println("uploaded bitstream location: " + response.getHeaderString("location"));
	            }
	        
	        }	
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (client != null) {
				client.close();
			}
		}
	}
	
	
	private static String getHandleFromResource(String itemResource) {
		String itemsPath = "items/";
        String handle = ""; 
        if (itemResource!=null) {
        	itemResource = itemResource.substring(itemResource.indexOf(itemsPath)+itemsPath.length());
            if (itemResource.indexOf("/")>0) {
                handle = itemResource;
            }
        }
        return handle;
	}

}
