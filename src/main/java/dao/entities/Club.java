package dao.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Club {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nomClub;
	private String 	descriptionClub;
	@ManyToOne
	Faculte faculte;
	@ManyToMany(mappedBy = "clubs")
	private Set<User> users = new HashSet<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomClub() {
		return nomClub;
	}
	public void setNomClub(String nomClub) {
		this.nomClub = nomClub;
	}
	public String getDescriptionClub() {
		return descriptionClub;
	}
	public void setDescriptionClub(String descriptionClub) {
		this.descriptionClub = descriptionClub;
	}
	public Faculte getFaculte() {
		return faculte;
	}
	public void setFaculte(Faculte faculte) {
		this.faculte = faculte;
	}
	public Club(int id, String nomClub, String descriptionClub, Faculte faculte) {
		super();
		this.id = id;
		this.nomClub = nomClub;
		this.descriptionClub = descriptionClub;
		this.faculte = faculte;
	}
	public Club(String nomClub, String descriptionClub, Faculte faculte) {
		super();
		this.nomClub = nomClub;
		this.descriptionClub = descriptionClub;
		this.faculte = faculte;
	}
	public Club() {
		super();
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	

}
