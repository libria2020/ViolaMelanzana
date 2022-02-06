package application.persistenza.dao;

import java.util.List;

import application.model.Utente;

public interface UtenteDao {

	public List<Utente> findAll();
	public Utente findByPrimaryKey(String mail);
	public boolean save(Utente utente, boolean googleLogin);
	public boolean update(Utente utente);
	public boolean delete(Utente utente);
	
	public Utente findByUnique(String username);
	public Utente checkByUniqueAndPassword(String username, String password);
	public boolean enableUser(String username);
	public Utente findByToken(String token);
	public boolean setToken(Utente user, String token);
	public boolean resetPassword(Utente u, String password);
}
