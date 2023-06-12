package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.entities.Event;

public class GestionEventImpJpa implements IGestionEvent {
	
	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction et;
	
	public GestionEventImpJpa() {
		emf=Persistence.createEntityManagerFactory("punit1");
		em=emf.createEntityManager();
		et=em.getTransaction();
	}

	@Override
	public void ajouterEvent(Event e) {
		et.begin();
		em.persist(e);
		et.commit();
		
	}

	@Override
	public void supprimerEvent(int id) {
		Event e=em.find(Event.class, id);
		if(e != null){
		et.begin();
		em.remove(e);
		et.commit();
		}
		
	}

	@Override
	public Event rechercherParId(int id) {
		return em.find(Event.class, id);
	}

	@Override
	public List<Event> getAllEvents() {
		Query q=em.createQuery("select e from Event e");

		return q.getResultList();
	}

	@Override
	public List<Event> getEventParNom(String nomEvent) {
		Query q=em.createQuery("select e from Event e where e.nomEvent like :a");
		q.setParameter("a", "%"+nomEvent+"%");
		return q.getResultList();
	}

	@Override
	public void modifEvent(Event e) {
	 	et.begin();
	    em.merge(e);
	    et.commit();
		
	}

}
