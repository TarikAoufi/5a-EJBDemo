package fr.aoufi.ejbsample.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import fr.aoufi.ejbsample.model.Personne;
import fr.aoufi.ejbsample.service.PersonneService;

@Path("personnes")
@Produces(value = "application/json")
@Consumes(value = "application/json")
@ApplicationPath("rest")
@Stateless
public class PersonneResource extends Application {
		
	@EJB
	private PersonneService personneService;
	
	@POST
	public void savePersonne(Personne personne) {
		personneService.save(personne);
	}
	
	@GET
	public List<Personne> findAllPersonnes(){
		return personneService.list();
	}
	
	@GET
	@Path(value = "/{id}")
	public Personne findById(@PathParam(value = "id") Long id) {
		return personneService.findById(id);
	}
	
	@DELETE
	@Path(value = "/{id}")
	public void remove(Personne personne, @PathParam(value = "id") Long id) {
		personne.setId(id);
		personneService.remove(personne);
	}
	
	// similaire à remove, sauf il faut spécifier l'objet json à  supprimer dans le body de RESTclient
	@DELETE
	public void remove(Personne personne) {
		personneService.remove(personne);
	}
	
	@PUT
	@Path(value = "/{id}")
	public void update(Personne personne, @PathParam(value = "id") Long id) {
		personne.setId(id);
		personneService.save(personne);		
	}
	
	@PUT
	public void update(Personne personne) {
		personneService.save(personne);		
	}

}
