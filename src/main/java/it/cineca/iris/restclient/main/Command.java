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

import it.cineca.iris.ir.rest.command.model.OptionBitStreamDTO;
import it.cineca.iris.ir.rest.model.CareerItemsDTO;
import it.cineca.iris.ir.rest.model.CollectionRestDTO;
import it.cineca.iris.ir.rest.model.CollectionRestPageDTO;
import it.cineca.iris.ir.rest.model.CommunityRestDTO;
import it.cineca.iris.ir.rest.model.CommunityRestPageDTO;
import it.cineca.iris.ir.rest.model.DCInputSetRestDTO;
import it.cineca.iris.ir.rest.model.DCInputSetRowRestDTO;
import it.cineca.iris.ir.rest.model.ItemIdRestPageDTO;
import it.cineca.iris.ir.rest.model.ItemRestDTO;
import it.cineca.iris.ir.rest.model.ItemRestPageDTO;
import it.cineca.iris.ir.rest.model.ItemRestWriteDTO;
import it.cineca.iris.ir.rest.model.MetadataEntryRestDTO;
import it.cineca.iris.ir.rest.model.RecordANCERivistaRestDTO;
import it.cineca.iris.ir.rest.model.RmPersonRestDTO;
import it.cineca.iris.ir.rest.model.utils.AbstractAuthorityResolver;
import it.cineca.iris.ir.rest.model.utils.AbstractInputformType;
import it.cineca.iris.ir.rest.model.utils.ChoiceAuthorityManager;
import it.cineca.iris.ir.rest.model.utils.IInputformType;
import it.cineca.iris.ir.rest.search.model.AnceSearchRestDTO;
import it.cineca.iris.ir.rest.search.model.RestSearchCriteria;
import it.cineca.iris.ir.rest.search.model.RestSortCriteria;
import it.cineca.iris.ir.rest.search.model.SearchIdsRestDTO;
import it.cineca.iris.ir.rest.search.model.SearchRestDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.RandomStringUtils;
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

	private RESTIRClient cl;
	private List<ItemRestDTO> itemsDTO;
	private String authorAuthority;
	private String authorCF;
	private String username;

	public static void main(String[] argv) {
		Command command = new Command();
		try {
			command.createClient();
			
			//command.runReadTest();
		
			command.runWriteTest();
			
		} catch (SocketTimeoutException e) {
			System.out
			.println("\n-----------------------------------------------------------");
			System.out.print("Time out!!!");
			System.out
			.println("\n-----------------------------------------------------------");
		} catch (Exception e) {
			System.out
			.println("\n-----------------------------------------------------------");
			e.printStackTrace();
			System.out
			.println("\n-----------------------------------------------------------");
		} finally {
			if (command != null) {
				command.shutdownClient();
			}
		}
	}

	protected void createClient() throws IOException, KeyManagementException, NoSuchAlgorithmException {

		System.out
				.println("\n-----------------------------------------------------------");
		System.out
				.println("---------------------- START TESTING ----------------------");
		System.out
				.println("-----------------------------------------------------------\n");

		
		PropertiesReader reader = new PropertiesReader();
		Properties prop = reader.getProperties();

		String restBaseURI = prop.getProperty("BASE_URI"), pathIR = prop
				.getProperty("PATH_IR"), pathRM = prop
				.getProperty("PATH_RM"), username = prop
				.getProperty("USERNAME"), password = prop
				.getProperty("PASSWORD");

		System.out.println("Test on: " + restBaseURI);

		if (username == null || password == null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			if (username == null) {
				System.out.print("Enter Username:");
				username = br.readLine();
			}

			if (password == null) {
				System.out.print("Enter Password:");
				password = br.readLine();
			}
		}

		this.cl = new RESTIRClient(restBaseURI, pathIR, pathRM, username,
				password);
		
		this.cl.buildUnsecureInstance();
	}
	
	private void shutdownClient() {
		if (this.cl != null) {
			cl.close();
		}
	}

	/**
	 * Simple test for custom
	 * 
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	private void simpleTest() {}

	/**
	 * Long test...
	 * 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * 
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	private void runReadTest() throws JsonParseException, JsonMappingException, IOException {

		this.echo();

		this.testReadItems();

		this.testCollection();

		this.testCommunities();

		this.testSearchLastModified();

		this.testSearchAuthor();

		this.testInputForm();

		this.testAnce();

		this.testRestPerson();

		this.testDBDownloadLastModified();

		this.testDBDownloadPublishDate();

	}
	
	/**
	 * Write test...
	 * @throws Exception 
	 */
	private void runWriteTest() throws Exception {
		
		this.echo();
		
		this.testReadItems();
		
		this.testSearchAuthor();
		
		this.findPersonByCris(this.authorAuthority);
		
		this.createNewItem("11368","2842417");
		
		//this.testUpload(null);

	}

	/**
	 * REST API echo
	 * 
	 * @param cl
	 */
	private void echo() {
		System.out
				.println("-----------------------------------------------------------");
		System.out.println("Check IR...");
		Response response = cl.echoIR();
		String test = response.readEntity(String.class);
		System.out.println("Check: " + test);

		System.out.println("Check RM...");
		response = cl.echoRM();
		test = response.readEntity(String.class);
		System.out.println("Check: " + test);
		System.out
				.println("-----------------------------------------------------------");
	}

	/**
	 * Test for read community method
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private void testCommunities() throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		System.out
				.println("\n-----------------------------------------------------------");
		System.out.println("Read Communities");
		System.out
				.println("-----------------------------------------------------------");

		System.out.println("\nRead all Communities");
		Response response = cl.communities();
		String test = response.readEntity(String.class);
		System.out.println("Community JSON: " + test);
		CommunityRestPageDTO communities = mapper.readValue(test,
				CommunityRestPageDTO.class);
		System.out.println("Community handle: "
				+ communities.getRestResourseDTOList().get(0).getHandle());
		System.out.println("Community name: "
				+ communities.getRestResourseDTOList().get(0).getName());
		System.out.println("Community id: "
				+ communities.getRestResourseDTOList().get(0).getId());
		System.out.println("Community next: " + communities.getNext());

		System.out.println("\nRead First Community of list");
		System.out.println("Read Community: "
				+ communities.getRestResourseDTOList().get(0).getId());
		response = cl.community(String.valueOf(communities
				.getRestResourseDTOList().get(0).getId()));
		test = response.readEntity(String.class);
		System.out.println("Community JSON: " + test);
		CommunityRestDTO community = mapper.readValue(test,
				CommunityRestDTO.class);
		System.out.println("Community handle: " + community.getHandle());
		System.out.println("Community name: " + community.getName());
		System.out.println("Community id: " + community.getId());

	}

	/**
	 * Read collection from known item
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private void testCollection() throws JsonParseException,
			JsonMappingException, IOException {
		System.out
				.println("\n-----------------------------------------------------------");
		System.out.println("Read Collection from Item");
		System.out
				.println("-----------------------------------------------------------");

		if (!itemsDTO.isEmpty()) {
			ObjectMapper mapper = new ObjectMapper();

			int itemId = itemsDTO.get(0).getId();

			System.out.println("\nItem: " + String.valueOf(itemId));
			Response response = cl.itemAll(String.valueOf(itemId));
			String test = response.readEntity(String.class);
			System.out.println("Item JSON: " + test);
			ItemRestDTO item = mapper.readValue(test, ItemRestDTO.class);
			System.out.println("Item Handle: " + item.getHandle());
			System.out.println("Item last modify date: "
					+ item.getLastModified());
			System.out
					.println("Collection Id: " + item.getCollection().getId());

			System.out.println("\nRead Collection of Item read...");
			response = cl.collection(String.valueOf(item.getCollection()
					.getId()));
			test = response.readEntity(String.class);
			System.out.println("Community JSON: " + test);
			CollectionRestDTO collection = mapper.readValue(test,
					CollectionRestDTO.class);
			System.out.println("Collections handle: " + collection.getHandle());
			System.out.println("Collections name: " + collection.getName());
			System.out.println("Collections id: " + collection.getId());
			System.out.println("Collections inputform: "
					+ collection.getInputformActiveId());

		} else {
			System.out.println("No Item");
		}
	}

	/**
	 * Retrieve items
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private void testReadItems() throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		System.out
				.println("\n-----------------------------------------------------------");
		System.out.println("Retrieve items from REST API (first 4)");
		System.out
				.println("-----------------------------------------------------------");
		Response response = cl.items(4, 0);
		String test = response.readEntity(String.class);
		ItemRestPageDTO items = mapper.readValue(test, ItemRestPageDTO.class);

		itemsDTO = items.getRestResourseDTOList();

		if (!itemsDTO.isEmpty()) {
			int itemId = itemsDTO.get(0).getId();

			System.out
					.println("Read First Item ALL: " + String.valueOf(itemId));
			response = cl.itemAll(String.valueOf(itemId));
			test = response.readEntity(String.class);
			System.out.println("Item JSON: " + test);
			ItemRestDTO item = mapper.readValue(test, ItemRestDTO.class);
			System.out.println("Item Handle: " + item.getHandle());
			System.out.println("Item last modify date: "
					+ item.getLastModified());
			System.out
					.println("Collection Id: " + item.getCollection().getId());
			System.out.println("ISSN: " + item.getLookupValues().get("jissn"));

			System.out.println("\nRead First Item with METADATA: "
					+ String.valueOf(itemId));
			response = cl.itemWithMetadata(String.valueOf(itemId));
			test = response.readEntity(String.class);
			System.out.println("Item + metadata JSON: " + test);
			item = mapper.readValue(test, ItemRestDTO.class);
			System.out.println("Item handle: " + item.getHandle());
			System.out.println("Item title: "
					+ item.getMetadata().get("dc.title").get(0).getValue());

			System.out.println("\nRead First Item with no extra: "
					+ String.valueOf(itemId));
			response = cl.item(String.valueOf(itemId));
			test = response.readEntity(String.class);
			System.out.println("Item JSON: " + test);
			item = mapper.readValue(test, ItemRestDTO.class);
			System.out.println("Item Handle: " + item.getHandle());
		} else {
			System.out.println("\nNo Item retrieved");
		}

	}

	/**
	 * Test search method based on DTO:
	 * 
	 * lastModified > 21/04/2015 snapshot = 0
	 * 
	 * ORDER BY lastModified
	 * 
	 * @param cl
	 * @return
	 * @throws IOException
	 */
	private void testSearchLastModified() throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		System.out
				.println("\n-----------------------------------------------------------");
		System.out
				.println("Search the first 2 item with lastModified > 21/04/2015 and snapshot = 0");
		System.out
				.println("-----------------------------------------------------------");

		SearchRestDTO itemSearchDTO = new SearchRestDTO();
		itemSearchDTO.setOffset(0);
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

		if (items.getRestResourseDTOList().size() > 0) {
			System.out.println("prev:" + items.getPrev());
			System.out.println("next:" + items.getNext());

			for (ItemRestDTO itemRestDTO : items.getRestResourseDTOList()) {
				System.out.println("Item ids:" + itemRestDTO.getId());
			}
		} else {
			System.out.println("No Item retrieved");
		}

	}

	/**
	 * Test search method based on DTO:
	 * 
	 * lastModified > 21/04/2015 snapshot = 0
	 * 
	 * ORDER BY lastModified
	 * 
	 * @param cl
	 * @return
	 * @throws IOException
	 */
	private void testSearchAuthor() throws IOException {
		System.out
				.println("\n-----------------------------------------------------------");
		System.out.println("Search Item from author...");
		System.out
				.println("-----------------------------------------------------------");

		if (!itemsDTO.isEmpty()) {
			ObjectMapper mapper = new ObjectMapper();

			boolean found = false;
			int index = 0;

			System.out.println("\nRetrieve an rp...");

			while (!found && index < itemsDTO.size()) {
				int itemId = itemsDTO.get(index).getId();

				Response response = cl.itemWithMetadata(String.valueOf(itemId));
				String test = response.readEntity(String.class);
				System.out.println("Item + metadata JSON: " + test);

				ItemRestDTO item = mapper.readValue(test, ItemRestDTO.class);

				if (item != null
						&& item.getMetadata() != null
						&& item.getMetadata()
								.containsKey("dc.authority.people")) {
					List<MetadataEntryRestDTO> authors = item.getMetadata()
							.get("dc.authority.people");
					if (!authors.isEmpty()) {
						found = true;
						this.authorAuthority = authors.get(0).getAuthority();
					}
				}
			}

			if (found) {

				System.out.println("\nGet items from authors " + this.authorAuthority);
				System.out
						.println("-----------------------------------------------------------");

				SearchRestDTO itemSearchDTO = new SearchRestDTO();
				itemSearchDTO.setOffset(0);
				itemSearchDTO.setLimit(2);
				itemSearchDTO.setExpand("");

				RestSearchCriteria searchCriteriaLM = new RestSearchCriteria();
				searchCriteriaLM.setColumn("lookupValues_contextuser");
				searchCriteriaLM.setOperation("=");
				searchCriteriaLM.setValue(this.authorAuthority);

				RestSearchCriteria searchCriteriaSnap = new RestSearchCriteria();
				searchCriteriaSnap.setColumn("snapshot");
				searchCriteriaSnap.setOperation("=");
				searchCriteriaSnap.setValue("0");

				ArrayList<RestSearchCriteria> searchList = new ArrayList<>();
				searchList.add(searchCriteriaLM);
				searchList.add(searchCriteriaSnap);
				itemSearchDTO.setSearchColsCriteria(searchList);

				Response response = cl.items(itemSearchDTO);
				String test = response.readEntity(String.class);
				ItemRestPageDTO items = mapper.readValue(test,
						ItemRestPageDTO.class);

				if (items.getRestResourseDTOList().size() > 0) {
					System.out.println("id:"
							+ items.getRestResourseDTOList().get(0).getId());
					System.out.println("next:" + items.getNext());

					for (ItemRestDTO itemRestDTO : items
							.getRestResourseDTOList()) {
						System.out.println("id:" + itemRestDTO.getId());
					}
				} else {
					System.out.println("No Item retrieved");
				}
			} else {
				System.out.println("No rp retrieved");
			}
		}
	}

	/**
	 * Test input form read method
	 * 
	 * @param cl
	 * @param inputFormId
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private void testInputForm() throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		System.out
				.println("\n-----------------------------------------------------------");
		System.out.println("Read inputform from item...");
		System.out
				.println("-----------------------------------------------------------");

		if (!itemsDTO.isEmpty()) {
			int inputFormId = itemsDTO.get(0).getInputFormId();

			System.out.println("\nRead inputform: "
					+ String.valueOf(inputFormId));

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
		} else {
			System.out.println("No Item retrieved");
		}

	}

	/**
	 * Test read ance method
	 * 
	 * @param cl
	 * @throws IOException
	 */
	private void testAnce() {
		try {
			ObjectMapper mapper = new ObjectMapper();

			System.out
					.println("\n-----------------------------------------------------------");
			System.out.println("Ance search: journal76139");
			System.out
					.println("-----------------------------------------------------------");

			AnceSearchRestDTO anceSearchDTO = new AnceSearchRestDTO();
			anceSearchDTO.setCrisId("journal76139");
			Response response = cl.journals(anceSearchDTO);
			String test = response.readEntity(String.class);
			System.out.println("id:" + test);
			List<RecordANCERivistaRestDTO> journals = mapper.readValue(test,
					new TypeReference<List<RecordANCERivistaRestDTO>>() {
					});
			System.out.println("id:" + journals.get(0).getISSN());

			System.out
					.println("\n-----------------------------------------------------------");
			System.out.println("Ance by cris id: journal76139");
			System.out
					.println("-----------------------------------------------------------");

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
	 * Retrieve first 6 item using /items/ids API METHOD
	 * 
	 * @param cl
	 * @throws IOException
	 */
	private void testDBDownloadLastModified() throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		System.out
				.println("\n-----------------------------------------------------------");
		System.out.println("Start Sequencial random Items on Last Modified");
		System.out
				.println("-----------------------------------------------------------");

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

		itemSearchDTO.setSearchColsCriteria(searchColsCriteria);
		Integer startId = -1;

		// Real Use case: check ItemIdRestPageDTO.getNext() is null
		while (startId != null && i < 2) {
			itemSearchDTO.setStartId(startId);
			itemSearchDTO.setCount(3);

			System.out.println("StartId: " + itemSearchDTO.getStartId());
			System.out.println("Count: " + itemSearchDTO.getCount());

			Response response = cl.itemIds(itemSearchDTO);
			String test = response.readEntity(String.class);
			ItemIdRestPageDTO idsPage = mapper.readValue(test,
					ItemIdRestPageDTO.class);

			List<Integer> ids = idsPage.getIdList();

			for (Integer itemId : ids) {
				System.out.println("Read Item: " + itemId);
				response = cl.itemWithMetadata(String.valueOf(itemId));
				test = response.readEntity(String.class);
				System.out.println("Item + metadata JSON: " + test);

				ItemRestDTO item = mapper.readValue(test, ItemRestDTO.class);
				System.out.println("Item handle: " + item.getHandle());
				System.out.println("Item title: "
						+ item.getMetadata().get("dc.title").get(0).getValue());
			}
			startId = idsPage.getNext();

			i++;
		}
	}

	private void testDBDownloadPublishDate() throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		System.out
				.println("\n-----------------------------------------------------------");
		System.out.println("Start Sequencial random Items on Publish Date");
		System.out
				.println("-----------------------------------------------------------");

		int i = 0;
		SearchIdsRestDTO itemSearchDTO = new SearchIdsRestDTO();
		List<RestSearchCriteria> searchColsCriteria = new ArrayList<RestSearchCriteria>();

		RestSearchCriteria searchCriteriaBY = new RestSearchCriteria();
		searchCriteriaBY.setColumn("lookupValues_year");
		searchCriteriaBY.setOperation(">=");
		searchCriteriaBY.setValue("2014");

		searchColsCriteria.add(searchCriteriaBY);

		RestSearchCriteria searchCriteriaTY = new RestSearchCriteria();
		searchCriteriaTY.setColumn("lookupValues_year");
		searchCriteriaTY.setOperation("<=");
		searchCriteriaTY.setValue("2015");

		RestSearchCriteria searchCriteriaSnap = new RestSearchCriteria();
		searchCriteriaSnap.setColumn("snapshot");
		searchCriteriaSnap.setOperation("=");
		searchCriteriaSnap.setValue("0");

		searchColsCriteria.add(searchCriteriaSnap);

		itemSearchDTO.setSearchColsCriteria(searchColsCriteria);
		Integer startId = -1;

		// Real Use case: check ItemIdRestPageDTO.getNext() is null
		while (startId != null && i < 2) {
			itemSearchDTO.setStartId(startId);
			itemSearchDTO.setCount(3);

			System.out.println("StartId: " + itemSearchDTO.getStartId());
			System.out.println("Count: " + itemSearchDTO.getCount());

			Response response = cl.itemIds(itemSearchDTO);
			String test = response.readEntity(String.class);
			ItemIdRestPageDTO idsPage = mapper.readValue(test,
					ItemIdRestPageDTO.class);

			List<Integer> ids = idsPage.getIdList();

			for (Integer itemId : ids) {
				System.out.println("Read Item: " + itemId);
				response = cl.itemWithMetadata(String.valueOf(itemId));
				test = response.readEntity(String.class);
				System.out.println("Item + metadata JSON: " + test);

				ItemRestDTO item = mapper.readValue(test, ItemRestDTO.class);
				System.out.println("Item handle: " + item.getHandle());
				System.out.println("Item title: "
						+ item.getMetadata().get("dc.title").get(0).getValue());
			}
			startId = idsPage.getNext();

			i++;
		}
	}

	/**
	 * Test read person
	 * 
	 * @param cl
	 * @throws IOException
	 */
	private void testRestPerson() throws IOException {
		List<ItemRestDTO> items = new ArrayList<>();
		int factor = 0;

		// Create a list of 10 items.
		do {
			ItemRestPageDTO itemRest = this.getRandomItem(0, "metadata");

			if (itemRest != null && itemRest.getRestResourseDTOList() != null) {
				items.addAll(itemRest.getRestResourseDTOList());
			}

			factor++;
		} while (factor < 10 && items.size() <= 10);

		if (items.size() > 1) {

			// Foreach Items
			for (ItemRestDTO itemDto : items) {
				System.out
						.println("\n-----------------------------------------------------------");
				System.out.println("Show info Author of Item: "
						+ itemDto.getHandle());

				if (itemDto.getMetadata() != null
						&& itemDto.getMetadata().get("dc.authority.people") != null) {
					// Foreach Authors
					for (MetadataEntryRestDTO authors : itemDto.getMetadata()
							.get("dc.authority.people")) {
						String crisId = authors.getAuthority();
						this.findCurrentCareerByCris(crisId);
					}
				}

			}
		} else {
			System.out
					.println("\n-----------------------------------------------------------");
			System.out.println("Items NOT FOUND");
		}
	}
	
	private void findPersonByCris(String crisId) throws IOException {
		System.out
		.println("\n-----------------------------------------------------------");
		System.out.println("Author by cris id: " + crisId);

		ObjectMapper mapper = new ObjectMapper();
		Response response = cl.personByCris(crisId);
		String result = response.readEntity(String.class);
		System.out.println("Person JSON:" + result);
		RmPersonRestDTO person = mapper
				.readValue(result, RmPersonRestDTO.class);
		System.out.println(person.lastName);
		
		System.out.println("CF :" + person.getCF());
		
		this.authorCF = person.getCF();
		this.username = person.getUsername();

	}

	/**
	 * Show info of people
	 * 
	 * @param cl
	 * @param crisId
	 * @throws IOException
	 */
	private void findCurrentCareerByCris(String crisId) throws IOException {
		
		this.findPersonByCris(crisId);

		ObjectMapper mapper = new ObjectMapper();
		
		System.out
				.println("\n-----------------------------------------------------------");
		System.out.println("All Positions by cris id: " + crisId);
		Response response = cl.positionsByCris(crisId);
		String result = response.readEntity(String.class);
		System.out.println("Positions JSON:" + result);
		CareerItemsDTO career = mapper.readValue(result, CareerItemsDTO.class);
		System.out.println("Matricola:" + career.getMatricola());

		System.out
				.println("\n-----------------------------------------------------------");
		System.out.println("Position Current by cris id: " + crisId);

		try {
			response = cl.positioncurrentByCris(crisId);
			result = response.readEntity(String.class);
			System.out.println("Position Current JSON:" + result);
			career = mapper.readValue(result, CareerItemsDTO.class);
			System.out.println("Matricola:" + career.getMatricola());
		} catch (RuntimeException e) {
			System.out.println("Position Current JSON:" + e.getMessage());
			System.out.println("User has not current position");
		}
	}

	/**
	 * Search random item between 2 date
	 * 
	 * @param cl
	 * @param factor
	 *            : to expande range
	 * @return ItemRestPageDTO
	 * @throws IOException
	 */
	private ItemRestPageDTO getRandomItem(int factor, String expand)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		System.out
				.println("\n-----------------------------------------------------------");
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
	 * Test upload file
	 * 
	 * @param itemId
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private void testUpload(Integer itemId) throws JsonParseException,
			JsonMappingException, IOException {
		System.out
				.println("\n-----------------------------------------------------------");
		System.out.println("Upload Attachment from Item: ");
		System.out
				.println("-----------------------------------------------------------");

		if (itemId == null && !itemsDTO.isEmpty()) {
			itemId = itemsDTO.get(0).getId();
		}

		if (itemId != null) {
			System.out.println("\nItem: " + String.valueOf(itemId));
			System.out.println("\nUpload file...");
			
			createTestFile("test.txt");
			
			OptionBitStreamDTO optionBitStreamDTO = new OptionBitStreamDTO();
			optionBitStreamDTO.setDescription("Test file");
			optionBitStreamDTO.setOptionName("openAccess");
			
			Response response = cl.uploadStream(itemId, optionBitStreamDTO, "test.txt");
			String test = response.readEntity(String.class);
			System.out.println("Result JSON: " + test);
		} else {
			System.out.println("No Item");
		}
	}
	
	private void createNewItem(String authorityName, String localName) throws Exception { 
		ObjectMapper mapper = new ObjectMapper();
		CollectionRestDTO targetCollection = null;
		Integer targetInputFormId = null;
		Response response = null;  
		
		if (authorityName == null || localName == null) {

			response =  cl.collections();
	    	
	    	String result = response.readEntity(String.class);
	    	
	    	CollectionRestPageDTO collection = mapper.readValue(result, CollectionRestPageDTO.class);
	    	
	    	if (collection != null && !collection.getRestResourseDTOList().isEmpty()) {
	    		
	    		int index = randInt(0, collection.getRestResourseDTOList().size() - 1);
	    		
	    		targetCollection = collection.getRestResourseDTOList().get(index);
	    		targetInputFormId = collection.getRestResourseDTOList().get(index).getInputformActiveId();
	    		System.out.println("Collections handle: " + targetCollection.getHandle());
	    		System.out.println("Collections inputform: " + targetInputFormId);
	    		
	    	} else {
	    		System.out.println("\nNo collection found...");
	    		return;
	    	}
    	
		} else {
			
			response =  cl.collection(authorityName, localName);
			
			String result = response.readEntity(String.class);
	    	
	    	targetCollection = mapper.readValue(result, CollectionRestDTO.class);
	    	targetInputFormId = targetCollection.getInputformActiveId();
		}
    	
    	if (targetCollection != null && targetCollection.getHandle() != null && !targetCollection.getHandle().isEmpty() && targetInputFormId != null && targetInputFormId>-1) {
    		response = cl.inputFormAll(String.valueOf(targetInputFormId));
    		String test = response.readEntity(String.class);
    		System.out.println(test);
    		
    		DCInputSetRestDTO inputform = mapper.readValue(test,
    				DCInputSetRestDTO.class);
    		
    		List<DCInputSetRowRestDTO> inputformRow = inputform.getRows();
    		
    		ItemRestWriteDTO itemToCreate = new ItemRestWriteDTO();
    		itemToCreate.setCollection(targetCollection.getContainerDTO());
    		
    		String dcSchema = null, dcElement = null, dcQualifier = null, dcValue = null, dcAuthority = null;
    		
    		ChoiceAuthorityManager manager = ChoiceAuthorityManager.getManager();
    		
    		for (DCInputSetRowRestDTO dcInputSetRowRestDTO : inputformRow) {
    			//Only required!!!
    			if (dcInputSetRowRestDTO.isRequired()) {
    				
    				dcSchema = dcInputSetRowRestDTO.getDcSchema();
    				dcElement = dcInputSetRowRestDTO.getDcElement();
    				dcQualifier = dcInputSetRowRestDTO.getDcQualifier();
    				
    				if (manager.isAuthorityManaged(dcSchema, dcElement, dcQualifier, null, null)) {
    					AbstractAuthorityResolver resolver = (AbstractAuthorityResolver) manager.getAuthorityResolver(dcSchema, dcElement, dcQualifier);
    					resolver.setRestIRClient(this.cl);
    					dcAuthority = resolver.resolve(this.authorCF);
    				} else {
    					dcValue = RandomStringUtils.randomAlphabetic(randInt(1, 40));
    				}
    				
    				System.out.println("Type: " + dcInputSetRowRestDTO.getInputType());
    				
    				IInputformType typeEnum = AbstractInputformType.getInstance(dcInputSetRowRestDTO.getInputType());
    				itemToCreate.addMetadata(typeEnum.build(dcSchema, dcElement, dcQualifier, dcValue, dcAuthority));
    			}
			}
    		
    		MultivaluedMap<String, Object> headers = new MultivaluedHashMap<String, Object>();
    		headers.add("scope", "ROLE_ADMIN");
    		headers.add("on-behalf-of", this.username);
    		headers.add("target-state", "PUBLISH");
    		
			response = cl.createItem(itemToCreate, headers);
    	}
    }
	
	/*****************************************************************************************************************
	 * UTILITY METHODS
	 * ***************************************************************************************************************
	 */
	
    public static int randInt(int min, int max) {
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    
	/**
	 * Create file for test
	 * 
	 * @param fileName
	 */
	private void createTestFile(String fileName) {
		PrintWriter writer = null;
	    
		try{
			writer = new PrintWriter(fileName, "UTF-8");
			
			writer.println("This is a REST upload ...");
			writer.println("File text test....");
			    
	      }catch(Exception e){
				System.out
				.println("\n-----------------------------------------------------------");
				System.out.print(e.getMessage());
				System.out
				.println("\n-----------------------------------------------------------");
	      } finally { 
	    	  if (writer != null) {
	    		  writer.close();
	    	  }
	      }
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

	/**
	 * Utiliy method
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private static int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}
}
