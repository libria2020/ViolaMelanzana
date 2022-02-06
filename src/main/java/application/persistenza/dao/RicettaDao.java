package application.persistenza.dao;

import java.util.ArrayList;
import java.util.List;

import application.model.Ricetta;
import application.model.RicettaProxy;
import application.model.Utente;

public interface RicettaDao {

	public Ricetta findByPrimaryKey(int id);

	List<String> findIngredientByRecipeId(int id);
	public List<RicettaProxy> findAll();
	public boolean delete(Ricetta ricetta);
	public boolean deleteRequest(Ricetta ricetta,Utente utente,String motivazione);

	
	public List<RicettaProxy> findByPublisher(String key);
	public List<RicettaProxy> findByCategory(String key);
	public List<RicettaProxy> findOrderBy(String expression, int limit, int offset);
	public List<RicettaProxy> findByPublisher(String key, int limit, int offset);	
	public List<RicettaProxy> findPublishedBy(String key);
	public List<RicettaProxy> search(String query, String filter);
	public boolean save(Ricetta ricetta);
	public boolean update(Ricetta ricetta);
	
	public ArrayList<Ricetta> findPendingRecipe();
	
	
}