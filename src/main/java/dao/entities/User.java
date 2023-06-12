package dao.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;
	private String username;
	private String login;
	private String password;
	private String role;
	@ManyToMany
	@JoinTable(
		name = "user_club",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "club_id")
	)
	private Set<Club> clubs = new HashSet<>();
	
	public User() {
		super();
	}
	public User(String username, String login, String password, String role) {
		super();
		this.username = username;
		this.login = login;
		this.password = password;
		this.role = role;
	}
	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Set<Club> getClubs() {
		return clubs;
	}
	public void setClubs(Set<Club> clubs) {
		this.clubs = clubs;
	}
	public User(int id, String username, String login, String password, String role) {
		super();
		this.id = id;
		this.username = username;
		this.login = login;
		this.password = password;
		this.role = role;
	}
	
	

}
