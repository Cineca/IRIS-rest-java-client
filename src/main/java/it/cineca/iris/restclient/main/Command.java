/*
 *  Java Iris Rest Client, pratical example for use IRIS REST API
 * 
 *  Copyright (c) 2015, CINECA and third-party contributors as
 *  indicated by the @author tags or express copyright attribution
 *  statements applied by the authors.  All third-party contributions are
 *  distributed under license by CINECA.
 * 
 *  This copyrighted material is made available to anyone wishing to use, modify,
 *  copy, or redistribute it subject to the terms and conditions of the GNU
 *  Lesser General Public License v3 or any later version, as published 
 *  by the Free Software Foundation, Inc. <http://fsf.org/>.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 *  or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 *  for more details.
 * 
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this distribution; if not, write to:
 *  Free Software Foundation, Inc.
 *  51 Franklin Street, Fifth Floor
 *  Boston, MA  02110-1301  USA
 */
package it.cineca.iris.restclient.main;

import it.cineca.iris.ir.rest.model.CommunityRestDTO;
import it.cineca.iris.ir.rest.model.CommunityRestPageDTO;
import it.cineca.iris.ir.rest.model.DCInputSetRestDTO;
import it.cineca.iris.ir.rest.model.ItemIdRestPageDTO;
import it.cineca.iris.ir.rest.model.ItemRestDTO;
import it.cineca.iris.ir.rest.model.ItemRestPageDTO;
import it.cineca.iris.ir.rest.model.MetadataEntryRestDTO;
import it.cineca.iris.ir.rest.model.RecordANCERivistaRestDTO;
import it.cineca.iris.ir.rest.model.RmPersonRestDTO;
import it.cineca.iris.ir.rest.search.model.AnceSearchRestDTO;
import it.cineca.iris.ir.rest.search.model.RestSearchCriteria;
import it.cineca.iris.ir.rest.search.model.RestSortCriteria;
import it.cineca.iris.ir.rest.search.model.SearchIdsRestDTO;
import it.cineca.iris.ir.rest.search.model.SearchRestDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * 
 * @author pmeriggi
 * @author fmignogna
 * 
 */
public class Command {
	
	public static void main(String[] argv) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		Command command = new Command(); 
		command.runTest();
	}
	
	/**
	 * 
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public void runTest()throws IOException, NoSuchAlgorithmException, KeyManagementException {
		System.out.println("-----------------------------------------------------------");
		System.out.println("---------------------- START TESTING ----------------------");
		System.out.println("-----------------------------------------------------------\n");
		
		PropertiesReader reader = new PropertiesReader();
		Properties prop = reader.getProperties();

		String restBaseURI = prop.getProperty("BASE_URI"), 
				pathIR = prop.getProperty("PATH_IR"), 
				pathRM = prop.getProperty("PATH_RM"), 
				username = prop.getProperty("USERNAME"), 
				password = prop.getProperty("PASSWORD");

		if (username == null || password == null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			if (username == null) {
				System.out.print("Enter Username:");
				username = br.readLine();
			}

			if (password == null) {
				System.out.print("Enter Password:");
				password = br.readLine();
			}
		}

		RESTIRClient cl = new RESTIRClient(restBaseURI, pathIR, pathRM, username, password);
		
		this.echo(cl);

		/*
		this.testCommunity(cl);

		this.testReadItems(cl);
		
		ItemRestPageDTO items = this.testSearch(cl);
		
		if (items.getRestResourseDTOList().size() >0) {
			int inputFormId = items.getRestResourseDTOList().get(0).getInputFormId();

			this.testInputForm(cl, inputFormId);
		}

		this.testAnce(cl);
		
		this.testRestPerson(cl);
		
		*/
		this.testDBDownload(cl);

		cl.close();
	}
	
	/**
	 * REST API echo
	 * 
	 * @param cl
	 */
	private void echo(RESTIRClient cl) {
		System.out.println("Check IR...");
		Response response = cl.echoIR();
		String test = response.readEntity(String.class);
		System.out.println("Check: " + test);
		
		System.out.println("Check RM...");
		response = cl.echoRM();
		test = response.readEntity(String.class);
		System.out.println("Check: " + test);
	}
	
	
	/**
	 * Test for read community method
	 * 
	 * @param cl
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private void testCommunity(RESTIRClient cl) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		System.out.println("-----------------------------------------------------------");
		System.out.println("Read Communities");
		Response response = cl.communities();
		String test = response.readEntity(String.class);
		System.out.println("Community JSON: " + test);

		CommunityRestPageDTO communities = mapper.readValue(test,
				CommunityRestPageDTO.class);

		System.out.println("Community handle: " + communities.getRestResourseDTOList().get(0).getHandle());
		System.out.println("Community name: " + communities.getRestResourseDTOList().get(0).getName());
		System.out.println("Community id: " + communities.getRestResourseDTOList().get(0).getId());
		System.out.println("Community next: " + communities.getNext());

		System.out.println("Read Community: " + communities.getRestResourseDTOList().get(0).getId());
		response = cl.community(String.valueOf(communities.getRestResourseDTOList().get(0).getId()));
		test = response.readEntity(String.class);
		System.out.println("Community JSON: " + test);

		CommunityRestDTO community = mapper.readValue(test,
				CommunityRestDTO.class);

		System.out.println("Community handle: " + community.getHandle());
		System.out.println("Community name: " + community.getName());
		System.out.println("Community id: " + community.getId());
	}
	
	/**
	 * Test read item
	 * 
	 * @param cl
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private void testReadItems(RESTIRClient cl) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		System.out.println("-----------------------------------------------------------");
		Response response = cl.items(2,0);
		String test = response.readEntity(String.class);
		ItemRestPageDTO items = mapper.readValue(test, ItemRestPageDTO.class);
		
		int itemId = items.getRestResourseDTOList().get(0).getId();
		System.out.println("Read Item: " + String.valueOf(itemId));
		response = cl.itemAll(String.valueOf(itemId));
		test = response.readEntity(String.class);
		System.out.println("Item JSON: " + test);

		ItemRestDTO item = mapper.readValue(test, ItemRestDTO.class);
		System.out.println("Item Handle: " + item.getHandle());
		System.out.println("Item last modify date: " + item.getLastModified());
		System.out.println("Collection Id: " + item.getCollection().getId());
		System.out.println("ISSN: " + item.getLookupValues().get("jissn"));

		response = cl.itemWithMetadata(String.valueOf(itemId));
		test = response.readEntity(String.class);
		System.out.println("Item + metadata JSON: " + test);

		item = mapper.readValue(test, ItemRestDTO.class);
		System.out.println("Item handle: " + item.getHandle());
		System.out.println("Item title: " + item.getMetadata().get("dc.title").get(0).getValue());

		response = cl.item(String.valueOf(itemId));
		test = response.readEntity(String.class);
		System.out.println("Item JSON: " + test);

		item = mapper.readValue(test, ItemRestDTO.class);
		System.out.println("Item Handle: " + item.getHandle());

		response = cl.itemWithMetadata(String.valueOf(itemId));
		test = response.readEntity(String.class);
		System.out.println("Item + metadata JSON: " + test);

		item = mapper.readValue(test, ItemRestDTO.class);
		System.out.println("Item handle: " + item.getHandle());

	}
	
	/**
	 * Test search method
	 * 
	 * @param cl
	 * @return
	 * @throws IOException
	 */
	private ItemRestPageDTO testSearch (RESTIRClient cl) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("Search Item");
		SearchRestDTO itemSearchDTO = new SearchRestDTO();
		itemSearchDTO.setOffset(4);
		itemSearchDTO.setLimit(2);
		itemSearchDTO.setExpand("");

		RestSearchCriteria searchCriteriaLM = new RestSearchCriteria();
		searchCriteriaLM.setColumn("lastModified");
		searchCriteriaLM.setOperation(">");
		searchCriteriaLM.setValue("21/04/2015");

		RestSearchCriteria searchCriteriaSnap = new RestSearchCriteria();
		searchCriteriaSnap.setColumn("snapshot");
		searchCriteriaSnap.setOperation("=");
		searchCriteriaSnap.setValue("0");

		ArrayList<RestSearchCriteria> searchList = new ArrayList<>();
		searchList.add(searchCriteriaLM);
		searchList.add(searchCriteriaSnap);
		itemSearchDTO.setSearchColsCriteria(searchList);

		RestSortCriteria sortCriteria = new RestSortCriteria();
		sortCriteria.setAsc(true);
		sortCriteria.setColumn("lastModified");

		ArrayList<RestSortCriteria> sortList = new ArrayList<>();
		sortList.add(sortCriteria);
		itemSearchDTO.setSortingColsCriteria(sortList);

		Response response = cl.items(itemSearchDTO);
		String test = response.readEntity(String.class);
		ItemRestPageDTO items = mapper.readValue(test, ItemRestPageDTO.class);
		
		if (items.getRestResourseDTOList().size()>0) {
			System.out.println("id:" + items.getRestResourseDTOList().get(0).getId());
			System.out.println("next:" + items.getNext());
		}
		
		return items;
	}
	
	/**
	 * Test input form read method
	 * @param cl
	 * @param inputFormId
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private void testInputForm(RESTIRClient cl, int inputFormId) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("Inputform: " + String.valueOf(inputFormId));
		Response response = cl.inputFormAll(String.valueOf(inputFormId));
		String test = response.readEntity(String.class);
		System.out.println(test);
		DCInputSetRestDTO inputform = mapper.readValue(test,
				DCInputSetRestDTO.class);
		System.out.println("Input form name:" + inputform.getFormName());
		System.out.println("Elemento con dropdown-> Pagina: "
				+ inputform.getRows().get(2).getPageNumber());
		System.out.println("Elemento con dropdown-> qualificatore: "
				+ inputform.getRows().get(2).getDcQualifier());
		System.out.println("Elemento con dropdown-> nome: "
				+ inputform.getRows().get(2).getDcElement());
		System.out.println("Elemento con dropdown-> tipo input: "
				+ inputform.getRows().get(2).getInputType());
		System.out
				.println("Elemento con dropdown-> nome elenco valori sulla value pair: "
						+ inputform.getRows().get(2).getValueListName());
		System.out
				.println("------------------------------------------------------------");
		System.out.println("Valuepair-> "
				+ inputform.getValuePairs().get(0).getName());
		System.out.println("Valuepair-> Valore su db 1: "
				+ inputform.getValuePairs().get(0).getValuePairs().get(0)
						.getKey());
		System.out.println("Valuepair-> Valore visualizzato all'utente 1: "
				+ inputform.getValuePairs().get(0).getValuePairs().get(0)
						.getValue());
		System.out.println("Valuepair-> Valore su db 2:  "
				+ inputform.getValuePairs().get(0).getValuePairs().get(1)
						.getKey());
		System.out.println("Valuepair-> Valore visualizzato all'utente 2: "
				+ inputform.getValuePairs().get(0).getValuePairs().get(1)
						.getValue());

	}

	/**
	 * Test read ance method
	 * 
	 * @param cl
	 * @throws IOException
	 */
	private void testAnce(RESTIRClient cl) {
		ObjectMapper mapper = new ObjectMapper();
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("Ance search: journal76139");
			
		try {
			AnceSearchRestDTO anceSearchDTO = new AnceSearchRestDTO();
			anceSearchDTO.setCrisId("journal76139");
			Response response;
			
			response = cl.journals(anceSearchDTO);
			
			String test = response.readEntity(String.class);
			System.out.println("id:" + test);
			List<RecordANCERivistaRestDTO> journals = mapper.readValue(test,
					new TypeReference<List<RecordANCERivistaRestDTO>>() {
					});
			System.out.println("id:" + journals.get(0).getISSN());
	
			System.out.println("-----------------------------------------------------------");
			System.out.println("Ance by cris id: journal76139");
			response = cl.journal("journal76139");
			test = response.readEntity(String.class);
			System.out.println("id:" + test);
			RecordANCERivistaRestDTO journal = mapper.readValue(test,
					RecordANCERivistaRestDTO.class);
			System.out.println("id:" + journal.getISSN());
		} catch (Exception e) {
			System.out.println("NOT FOUND...");
		}
	}
	

	/**
	 * Test read person
	 * 
	 * @param cl
	 * @throws IOException
	 */
	private void testRestPerson(RESTIRClient cl) throws IOException {
		List<ItemRestDTO> items = new ArrayList<>();
		int factor = 0;
		ObjectMapper mapper = new ObjectMapper();
		
		//Create a list of 10 items. 
		do{
			ItemRestPageDTO itemRest =this.getRandomItem(cl, 0, "metadata");
			if(itemRest != null && itemRest.getRestResourseDTOList() != null){
				items.addAll(itemRest.getRestResourseDTOList());
			}
			factor++;
		}while(factor < 10 && items.size() <= 10 );

		if(items.size() > 1){
			//Foreach Items
			for(ItemRestDTO itemDto : items){
				System.out.println("\n-----------------------------------------------------------");
				System.out.println("Show info Author of Item: " + itemDto.getHandle());
				
				if(itemDto.getMetadata() != null && itemDto.getMetadata() != null && itemDto.getMetadata().get("dc.authority.people") != null){
					//Foreach Authors
					for(MetadataEntryRestDTO authors : itemDto.getMetadata().get("dc.authority.people")){
						String crisId = authors.getAuthority();
						this.findPeopleByCris(cl, crisId);
					}
				}
				
			}
		}else{
			System.out.println("\n-----------------------------------------------------------");
			System.out.println("Items NOT FOUND");
		}
	}
	
	/**
	 * Show info of people
	 * 
	 * @param cl
	 * @param crisId
	 * @throws IOException
	 */
	private void findPeopleByCris(RESTIRClient cl, String crisId) throws IOException{
		System.out.println("\n-----------------------------------------------------------");
		System.out.println("Author by cris id: " + crisId);
		
		ObjectMapper mapper = new ObjectMapper();
		Response response = cl.personByCris(crisId);
		String result = response.readEntity(String.class);
		System.out.println("Person JSON:" + result);
		RmPersonRestDTO person = mapper.readValue(result, RmPersonRestDTO.class);
		System.out.println(person.lastName);
		
		System.out.println("\n-----------------------------------------------------------");
		System.out.println("All Positions by cris id: " + crisId);
		response = cl.positionsByCris(crisId);
		result = response.readEntity(String.class);
		System.out.println("Positions JSON:" + result);
		
		System.out.println("\n-----------------------------------------------------------");
		System.out.println("Position Current by cris id: " + crisId);
		try {
			response = cl.positioncurrentByCris(crisId);
			result = response.readEntity(String.class);
			System.out.println("Position Current JSON:" + result);
		} catch (RuntimeException e) {
			System.out.println("Position Current JSON:" + e.getMessage());
			System.out.println("User has not current position");
		}
	}

	/**
	 * Search random item between 2 date
	 * 
	 * @param cl
	 * @param factor: to expande range
	 * @return ItemRestPageDTO
	 * @throws IOException
	 */
	private ItemRestPageDTO getRandomItem(RESTIRClient cl, int factor, String expand)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		System.out.println("\n-----------------------------------------------------------");
		System.out.println("Start Search random Items");
		SearchRestDTO itemSearchDTO = new SearchRestDTO();
		itemSearchDTO.setOffset(4);
		itemSearchDTO.setLimit(10);
		itemSearchDTO.setExpand(expand);

		/* Create search criteria */
		// Set minus date for search items
		RestSearchCriteria lmAfter = new RestSearchCriteria();
		lmAfter.setColumn("lastModified");
		lmAfter.setOperation(">");
		lmAfter.setValue(this.getRandomDate(2010 - factor, 2013));
		// Set max date for search items
		RestSearchCriteria lmBefore = new RestSearchCriteria();
		lmBefore.setColumn("lastModified");
		lmBefore.setOperation("<");
		lmBefore.setValue(this.getRandomDate(2013, 2014 + factor));

		// Set search criteria
		ArrayList<RestSearchCriteria> searchList = new ArrayList<>();
		searchList.add(lmAfter);
		searchList.add(lmBefore);
		itemSearchDTO.setSearchColsCriteria(searchList);

		// Create sort criteria
		RestSortCriteria sortCriteria = new RestSortCriteria();
		sortCriteria.setAsc(true);
		sortCriteria.setColumn("lastModified");
		// Set sort criteria
		ArrayList<RestSortCriteria> sortList = new ArrayList<>();
		sortList.add(sortCriteria);
		itemSearchDTO.setSortingColsCriteria(sortList);

		Response response = cl.items(itemSearchDTO);
		String test = response.readEntity(String.class);
		ItemRestPageDTO items = mapper.readValue(test, ItemRestPageDTO.class);

		return items;
	}

	/**
	 * Utiliy method 
	 * 
	 * @param min
	 * @param max
	 * @return random date as string.
	 */
	private String getRandomDate(int min, int max) {
		GregorianCalendar gc = new GregorianCalendar();
		int year = randBetween(min, max);
		gc.set(gc.YEAR, year);
		int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
		gc.set(gc.DAY_OF_YEAR, dayOfYear);

		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		String dateFormatted = fmt.format(gc.getTime());
		System.out.println("Random Date: " + dateFormatted);

		return dateFormatted;
	}

	private static int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}
	
	/**
	 * Retrieve first 6 item using /items/ids API METHOD
	 * 
	 * @param cl
	 * @throws IOException
	 */
	private void testDBDownload(RESTIRClient cl) throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		System.out.println("\n-----------------------------------------------------------");
		System.out.println("Start Sequencial random Items");
		
		int i = 0;
		SearchIdsRestDTO itemSearchDTO = new SearchIdsRestDTO();
		List<RestSearchCriteria> searchColsCriteria = new ArrayList<RestSearchCriteria>();
		
		RestSearchCriteria searchCriteriaLM = new RestSearchCriteria();
		searchCriteriaLM.setColumn("lastModified");
		searchCriteriaLM.setOperation(">");
		searchCriteriaLM.setValue("21/04/2015");
	
		searchColsCriteria.add(searchCriteriaLM);

		RestSearchCriteria searchCriteriaSnap = new RestSearchCriteria();
		searchCriteriaSnap.setColumn("snapshot");
		searchCriteriaSnap.setOperation("=");
		searchCriteriaSnap.setValue("0");
		
		searchColsCriteria.add(searchCriteriaSnap);
		
		itemSearchDTO.setSearchColsCriteria(searchColsCriteria );
		Integer startId = -1;
		
		//Real Use case: check ItemIdRestPageDTO.getNext() is null
		while (startId != null && i<2) {
			itemSearchDTO.setStartId(startId);
			itemSearchDTO.setCount(3);
			
			System.out.println("StartId: " +itemSearchDTO.getStartId());
			System.out.println("Count: " +itemSearchDTO.getCount());

			Response response = cl.itemIds(itemSearchDTO);
			String test = response.readEntity(String.class);
			ItemIdRestPageDTO idsPage = mapper.readValue(test, ItemIdRestPageDTO.class);
			
			List<Integer> ids = idsPage.getIdList();
			
			for (Integer itemId : ids) {
				System.out.println("Read Item: " + itemId);
				response = cl.itemWithMetadata(String.valueOf(itemId));
				test = response.readEntity(String.class);
				System.out.println("Item + metadata JSON: " + test);

				ItemRestDTO item = mapper.readValue(test, ItemRestDTO.class);
				System.out.println("Item handle: " + item.getHandle());
				System.out.println("Item title: " + item.getMetadata().get("dc.title").get(0).getValue());
			}
			startId =  idsPage.getNext();
			
			i++;
		}
	}
}
