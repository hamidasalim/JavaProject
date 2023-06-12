package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.entities.Club;
import dao.entities.User;

public class GestionClubImpJpa implements IGestionClub {
	
	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction et;
	
	public GestionClubImpJpa() {
		emf=Persistence.createEntityManagerFactory("punit1");
		em=emf.createEntityManager();
		et=em.getTransaction();
	}

	@Override
	public void ajouterClub(Club c) {
		et.begin();
		em.persist(c);
		et.commit();
		
	}

	@Override
	public void supprimerClub(int id) {
		Club c=em.find(Club.class, id);
		if(c != null){
		et.begin();
		em.remove(c);
		et.commit();
		}
		
	}

	@Override
	public Club rechercherParId(int id) {
		return em.find(Club.class, id);
	}

	@Override
	public List<Club> getAllClubs() {
		Query q=em.createQuery("select c from Club c");

		return q.getResultList();
	}

	@Override
	public List<Club> getClubParNom(String nomClub) {
		Query q=em.createQuery("select c from Club c where c.nomClub like :a");
		q.setParameter("a", "%"+nomClub+"%");
		return q.getResultList();
	}

	@Override
	public void modifClub(Club c) {
	 	et.begin();
	    em.merge(c);
	    et.commit();
		
	}
	/*public void modifClub(int idc, String name, String des) {
		 String hql = "UPDATE Club set descriptionClub = :des , nomClub = :name " + "WHERE id = :idc";
		 Query q=em.createQuery(hql);
        q.setParameter("des", des);
        q.setParameter("name", name);
        q.setParameter("idc", idc);
        // commit transaction
        et.commit();
	}*/

	@Override
	public void affecterUserClub(int userId, int clubId) {
		
		 et.begin();
		    
		    User user = em.find(User.class, userId);
		    Club club = em.find(Club.class, clubId);
		    
		    if (user != null && club != null) {
		        user.getClubs().add(club);
		        club.getUsers().add(user);
		    }
		    
		    et.commit();
		
	}
		
	}


