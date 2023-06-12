package dao.entities;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Faculte {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nomFac;
	public Faculte(int id, String nomFac) {
		super();
		this.id = id;
		this.nomFac = nomFac;
	}
	public Faculte(String nomFac) {
		super();
		this.nomFac = nomFac;
	}
	public Faculte() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomFac() {
		return nomFac;
	}
	public void setNomFac(String nomFac) {
		this.nomFac = nomFac;
	}

	


}
