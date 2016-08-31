package fr.aoufi.ejbsample.service;

import java.util.List;


import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.transaction.Transactional;

import fr.aoufi.ejbsample.dao.PersonneDAO;
import fr.aoufi.ejbsample.model.Personne;

@Stateful
public class PersonneService {
	 
	//la distination
	@Resource(mappedName="java:/jms/EcommerceQueue") // récupérer (la ressource) un nom dans le jndi
	private Queue queue;
	
	@Resource(mappedName="java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@EJB    //(@Stateful ou @Stateless)
	private PersonneDAO personneDAO;
	
	@Transactional
	public Personne save(Personne personne){
		personne =  personneDAO.save(personne);
		sendPersonne(personne);
		return personne;
	}
	
	@Transactional
	public void remove(Personne personne) {
		personne = personneDAO.findById(personne.getId());
		personneDAO.remove(personne);
	}
	
	public List<Personne> list() {
		return personneDAO.list();
	}
	
	public Personne findById(Long id) {
		return personneDAO.findById(id);
	}	
	
	public void sendPersonne(Personne personne) {
		Connection connection = null;
		try {
			connection = connectionFactory.createConnection();
			Session session = connection.createSession();
			MessageProducer producer = session.createProducer(queue);
			ObjectMessage objectMessage = session.createObjectMessage();
			objectMessage.setObject(personne);
			producer.send(objectMessage);		
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
