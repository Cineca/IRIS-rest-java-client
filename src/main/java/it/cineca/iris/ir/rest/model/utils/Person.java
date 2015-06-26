package it.cineca.iris.ir.rest.model.utils;

import it.cineca.iris.ir.rest.model.CareerItemDTO;
import it.cineca.iris.ir.rest.model.CareerItemsDTO;
import it.cineca.iris.ir.rest.model.PersonElementSetRestDTO;
import it.cineca.iris.ir.rest.model.RmPersonRestDTO;

public class Person {
	
	//FIXME: mappare oggetto stringMap in mappa di stringhe
	public static String getCF(RmPersonRestDTO person) {
		String result = null;
		boolean found = false;
		int i = 0;
		
		while (!found && i<person.personElementSet.length) {
			if ("codiceFiscale".equals(person.personElementSet[i].discriminator)) {
				PersonElementSetRestDTO element =  person.personElementSet[i];
				if (element.stringMap.length>0) {
					result = element.stringMap[0].value;
					found = true;
				}
			}
			i++;
		}
		
		return result;
	}
	
	public static String getMatricola(CareerItemsDTO career) {
		String result = null;
		boolean found = false;
		int i = 0;
		
		while (!found && i<career.items.length) {
			if ("research".equals(career.items[i].discriminator) 
					&& "Matricola".equals(career.items[i].organizationUnit.organizationUnitType)
					&& career.items[i].endDate == null) {
				CareerItemDTO element =  career.items[i];
				
				result = element.organizationUnit.description;
				found = true;
			}
			i++;
		}
		
		return result;
	}

}
