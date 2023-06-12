package dao;

import java.util.List;

import dao.entities.Club;

public interface IGestionClub {
	
	public void ajouterClub(Club c);
	public void supprimerClub(int id);
	public Club rechercherParId(int id);
	public List<Club> getAllClubs();
	public List<Club> getClubParNom(String nomClub);
	public void modifClub(Club c);
	public void affecterUserClub(int userId, int clubId);

}
