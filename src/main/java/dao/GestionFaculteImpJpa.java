package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.entities.Faculte;

public class GestionFaculteImpJpa implements IGestionFaculte {
	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction et;
	
	public GestionFaculteImpJpa() {
		emf=Persistence.createEntityManagerFactory("punit1");
		em=emf.createEntityManager();
		et=em.getTransaction();
	}

	@Override
	public void ajouterFaculte(Faculte f) {
			et.begin();
			em.persist(f);
			et.commit();
		}
		
	@Override
	public void supprimerFaculte(int id) {
		Faculte f=em.find(Faculte.class, id);
		if(f != null){
		et.begin();
		em.remove(f);
		et.commit();
		}
		
	}

	@Override
	public Faculte rechercherParId(int id) {
		return em.find(Faculte.class, id);
	}

	@Override
	public List<Faculte> getAllFacultes() {
		Query q=em.createQuery("select f from Faculte f");

		return q.getResultList();
	}

	@Override
	public List<Faculte> getFaculteParNom(String nomFac) {
		Query q=em.createQuery("select f from Faculte f where f.nomFac like :a");
		q.setParameter("a", "%"+nomFac+"%");
		return q.getResultList();
	}

	@Override
	public void modifFaculte(Faculte f) {
		 	et.begin();
		    em.merge(f);
		    et.commit();
		
	}
	

}
