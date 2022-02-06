package application.persistenza.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.model.Categoria;
import application.persistenza.dao.CategoriaDao;

public class CategoriaDaoJDBC implements CategoriaDao {
	
	private Connection conn;
	
	public CategoriaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Categoria> findAll() {
		ArrayList<Categoria> categorie = null;

		String query ="select * from categoria";
		
		try {
			categorie = new ArrayList<Categoria>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				Categoria c = new Categoria();
				
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				
				categorie.add(c);
				
			}
			
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return categorie;
	}
	
	@Override
	public ArrayList<Categoria> findByResearchKey(String researchKey) {
		String query = "select * from categoria where nome LIKE ?";
		ArrayList<Categoria> categorie = new ArrayList<Categoria>();
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, "%" + researchKey + "%");
			
			ResultSet rs = pr.executeQuery();
			
			while(rs.next()) {
				
				Categoria c = new Categoria();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				
				categorie.add(c);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return categorie;
	}

	@Override
	public Categoria findByPrimaryKey(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveOrUpdate(Categoria prodotto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Categoria prodotto) {
		// TODO Auto-generated method stub
		return false;
	}

}
