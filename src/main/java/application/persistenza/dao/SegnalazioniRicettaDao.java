package application.persistenza.dao;

public interface SegnalazioniRicettaDao {
	public boolean add (int idRicetta, String mail_utente);
	public boolean findForRecipeAndUser (String mail_utente, int id_ricetta);
}
