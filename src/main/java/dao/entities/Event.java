package dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Event {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nomEvent;
	private String 	descriptionEvent;
	@ManyToOne
	Club club;
	public Event(int id, String nomEvent, String descriptionEvent, Club club) {
		super();
		this.id = id;
		this.nomEvent = nomEvent;
		this.descriptionEvent = descriptionEvent;
		this.club = club;
	}
	public Event(String nomEvent, String descriptionEvent, Club club) {
		super();
		this.nomEvent = nomEvent;
		this.descriptionEvent = descriptionEvent;
		this.club = club;
	}
	public Event() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomEvent() {
		return nomEvent;
	}
	public void setNomEvent(String nomEvent) {
		this.nomEvent = nomEvent;
	}
	public String getDescriptionEvent() {
		return descriptionEvent;
	}
	public void setDescriptionEvent(String descriptionEvent) {
		this.descriptionEvent = descriptionEvent;
	}
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}
	
	

}
