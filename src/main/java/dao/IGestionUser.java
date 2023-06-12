package dao;

import java.util.List;

import dao.entities.User;

public interface IGestionUser {
	
	String verification(User u);
	void registration(User u);
	public String getUserName(String login, String password);
	public void supprimerUser(int id);
	public User rechercherParId(int id);
	public List<User> getAllUsers();
	public List<User> getUserParNom(String username);
	public void modifUser(User u);

}
