package fr.aoufi.ejbsample.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.aoufi.ejbsample.model.Personne;

@Stateless
public class PersonneDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Personne save(Personne personne) {
		if(personne.getId() == null) {
			entityManager.persist(personne);
		} else {
			personne = entityManager.merge(personne);
		}
		return personne;
	}
	
	public Personne findById(Long id) {
		return entityManager.find(Personne.class, id);
	}
	
	public void remove (Personne personne) {
		entityManager.remove(personne);
	}
		
	public List<Personne> list() {
		return entityManager
				.createQuery("SELECT p FROM Personne p", Personne.class)
				.getResultList();
	}

}
