package fr.aoufi.ejbsample.webservice;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import fr.aoufi.ejbsample.model.Personne;
import fr.aoufi.ejbsample.service.PersonneService;

@WebService
public class PersonneWS {
	
	@EJB
	private PersonneService personneService;
	
	@WebMethod
	public void saveFromWebservice(Personne personne){
		personneService.save(personne);
	}

}
