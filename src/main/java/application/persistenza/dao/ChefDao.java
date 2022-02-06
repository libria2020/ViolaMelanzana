package application.persistenza.dao;

import java.util.List;

import application.model.Chef;

public interface ChefDao {
	
	public List<Chef> findAll();
	public Chef findByPrimaryKey(String nome);
	public boolean saveOrUpdate(Chef chef);
	public boolean delete(Chef chef);
	public List<Chef> findOrderBy(String expression, int limit, int offset);
}
