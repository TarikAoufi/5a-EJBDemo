package fr.aoufi.ejbsample.managedbeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.ListDataModel;

import fr.aoufi.ejbsample.model.Personne;
import fr.aoufi.ejbsample.service.PersonneService;

@ManagedBean   // doit etre de javax.faces
@RequestScoped  // pareil du meme package
public class PersonneManagedBean {
	
	@EJB  // on peut mettre inject 
	public PersonneService personneService;
	
	private Personne personne = new Personne();
	
	private ListDataModel<Personne> personnes = new ListDataModel<>();
	
	public String create() {	
		personneService.save(personne);
		return "personne-list";
	}
	
	public String update() {
		personne = personnes.getRowData();  // la donnée sur la ligne
		return "personne";
	}
	
	public String remove() {
		personne = personnes.getRowData();
		personneService.remove(personne);
		return "personne-list";
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public ListDataModel<Personne> getPersonnes() {
		personnes.setWrappedData(personneService.list());
		return personnes;
	}

	public void setPersonnes(ListDataModel<Personne> personnes) {
		this.personnes = personnes;
	}
	
}
