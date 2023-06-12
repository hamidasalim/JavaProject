package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.entities.User;

public class GestionUserImpJpa implements IGestionUser {
	
	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction et;
	
	public GestionUserImpJpa() {
		emf=Persistence.createEntityManagerFactory("punit1");
		em=emf.createEntityManager();
		et=em.getTransaction();
	}

	@Override
	public String verification(User u) {
	
		Query q = em.createQuery("select u from User u where u.login = :l and u.password = :p");
		q.setParameter("l", u.getLogin());
		q.setParameter("p", u.getPassword());
		
		User usr = (User) q.getSingleResult();
		if (usr==null)
			return null;
		return usr.getRole();
	}
	@Override
	public void registration(User u) {
		et.begin();
		em.persist(u);
		et.commit();
		
	}

	@Override
	public void supprimerUser(int id) {

		User u=em.find(User.class, id);
		if(u != null){
		et.begin();
		em.remove(u);
		et.commit();
		}
		
	}

	@Override
	public User rechercherParId(int id) {

		return em.find(User.class, id);
	}

	@Override
	public List<User> getAllUsers() {

		Query q=em.createQuery("select u from User u");

		return q.getResultList();
	}

	@Override
	public List<User> getUserParNom(String username) {

		Query q=em.createQuery("select u from User u where u.username like :a");
		q.setParameter("a", "%"+username+"%");
		return q.getResultList();
	}

	@Override
	public void modifUser(User u) {

		et.begin();
	    em.merge(u);
	    et.commit();
		
	}

	@Override
	public String getUserName(String login, String password) {
		Query q = em.createQuery("Select e.username from dao.entities.User e where e.login = ?1 AND e.password = ?2");
		q.setParameter(1, login);
		q.setParameter(2, password);
		String username = (String) q.getSingleResult();
		return username;
	}

}
