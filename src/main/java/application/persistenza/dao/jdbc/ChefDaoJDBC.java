package application.persistenza.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.model.Chef;
import application.persistenza.dao.ChefDao;

public class ChefDaoJDBC implements ChefDao {
	
	private Connection conn;
	
	public ChefDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Chef> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chef findByPrimaryKey(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveOrUpdate(Chef chef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Chef chef) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<Chef> findOrderBy(String expression, int limit, int offset) {
		ArrayList<Chef> chef = null;
		
		String query ="select * from chef order by ? desc limit ? offset ?;";
		PreparedStatement ps;
		try {
			
			chef = new ArrayList<Chef>();
			
			ps = conn.prepareStatement(query);
			ps.setString(1, expression);
			ps.setInt(2, limit);
			ps.setInt(3, offset);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Chef c = new Chef();
				
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setData(rs.getDate("data"));
				c.setDescrizione(rs.getString("descrizione"));
				c.setImg_link(rs.getString("img_link"));
				
				chef.add(c);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return chef;
	}

}
