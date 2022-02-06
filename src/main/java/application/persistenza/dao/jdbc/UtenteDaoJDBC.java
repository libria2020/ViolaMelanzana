package application.persistenza.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;

import application.model.Utente;
import application.persistenza.dao.UtenteDao;

public class UtenteDaoJDBC implements UtenteDao{

	private Connection conn;
	
	public UtenteDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public synchronized List<Utente> findAll() {
		ArrayList<Utente> utenti = null;
		String query ="SELECT * from utente;";
		try {
			utenti = new ArrayList<Utente>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				
				Utente utente = new Utente();
				utente.setMail(rs.getString("mail"));
				utente.setUsername(rs.getString("username"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setMaster(rs.getBoolean("master"));
				utente.setPassword(rs.getString("password"));

				utenti.add(utente);
			}
			
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return utenti;
	}

	@Override
	public synchronized Utente findByPrimaryKey(String mail) {
		Utente utente = null;
		String query ="SELECT * from utente WHERE mail=?;";
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, mail);
			ResultSet rs = pr.executeQuery();
			
			if(rs.next()) {
				
				utente = new Utente();
				utente.setMail(rs.getString("mail"));
				utente.setUsername(rs.getString("username"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setMaster(rs.getBoolean("master"));	
				utente.setPassword(rs.getString("password"));
			}	
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return utente;
	}

	@Override
	public boolean save(Utente utente, boolean googleLogin) {
		//Se l'utente è null ose esiste un utente già registrato con quella mail allora return falso, non si può inserire
		if(utente == null || findByPrimaryKey(utente.getMail()) != null)
			return false;
		
		if(!googleLogin) {
			String cryptPassword = BCrypt.hashpw(utente.getPassword(), BCrypt.gensalt(12));

			try {
				String insert = "INSERT INTO utente VALUES(?, ?, ?, ?, ? ,false,?, ?);";
				PreparedStatement prst = conn.prepareStatement(insert);
				
				prst.setString(1, utente.getMail());
				prst.setString(2, utente.getNome());
				prst.setString(3, utente.getCognome());
				prst.setString(4, utente.getUsername());
				prst.setString(5, cryptPassword);
				prst.setString(6, utente.getVerificationCode());
				prst.setBoolean(7, utente.isMaster());

				prst.executeUpdate();
				
				return true;
				
			} catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {

			try {
				String insert = "INSERT INTO utente VALUES(?, ?, ?, ?, null, true, null, false, null);";
				PreparedStatement prst = conn.prepareStatement(insert);
				
				prst.setString(1, utente.getMail());
				prst.setString(2, utente.getNome());
				prst.setString(3, utente.getCognome());
				prst.setString(4, utente.getUsername());
				prst.executeUpdate();
				
				return true;
				
			} catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		
	}

	@Override
	public synchronized boolean update(Utente utente) {
		if(utente == null)
			return false;
		
		String cryptPassword = BCrypt.hashpw(utente.getPassword(), BCrypt.gensalt(12));

		try {
			String update = "UPDATE utente SET mail=?, nome=?, cognome=?, master=?, username=?, password=? WHERE mail=?";
			PreparedStatement prst2 = conn.prepareStatement(update);
			
			prst2.setString(1, utente.getMail());
			prst2.setString(2, utente.getNome());
			prst2.setString(3, utente.getCognome());
			prst2.setBoolean(4, utente.isMaster());
			prst2.setString(5, utente.getUsername());
			prst2.setString(6, cryptPassword);
			
			prst2.setString(7, utente.getMail());
			
			prst2.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public synchronized boolean delete(Utente utente) {
		if(utente == null)
			return false;
		try {
			String query = "DELETE FROM utente where mail=?";
			PreparedStatement pr = conn.prepareStatement(query);
			
			pr.setString(1, utente.getMail());
			pr.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			return false;
		}
	}

	@Override
	public synchronized Utente findByUnique(String username) {
		Utente utente = null;
		String query ="SELECT * from utente WHERE username=?;";
		
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, username);
			ResultSet rs = pr.executeQuery();
			
			if(rs.next()) {
				utente = new Utente();
				utente.setMail(rs.getString("mail"));
				utente.setUsername(rs.getString("username"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setMaster(rs.getBoolean("master"));	
			}	
		} catch(SQLException e) {
			e.getStackTrace();
		}
		System.out.println("ut "+utente.getNome());
		return utente;
		
	}

	@Override
	public synchronized Utente checkByUniqueAndPassword(String username, String password) {
		Utente utente = null;
		String query ="SELECT * from utente WHERE username=?;";
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, username);
			ResultSet rs = pr.executeQuery();
			
			if(rs.next()) {
				String cryptPassword = rs.getString("password");
				if(BCrypt.checkpw(password, cryptPassword)) {
					utente = new Utente();
					utente.setMail(rs.getString("mail"));
					utente.setUsername(rs.getString("username"));
					utente.setPassword(rs.getString("password"));
					utente.setNome(rs.getString("nome"));
					utente.setCognome(rs.getString("cognome"));
					utente.setMaster(rs.getBoolean("master"));	
					utente.setEnable(rs.getBoolean("enable"));
				}
			}	
		} catch(SQLException e) {
			e.getStackTrace();
		}
		return utente;
	}

	@Override
	public boolean enableUser(String username) {
		String query = "update utente set enable=true where username=?";
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, username);
			
			pr.executeUpdate();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Utente findByToken(String token) {
		String query = "select * from utente where token=?";
		Utente utente = null;
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, token);
			
			ResultSet rs = pr.executeQuery();
			
			if(rs.next()) {
				utente = new Utente();
				utente.setMail(rs.getString("mail"));
				utente.setUsername(rs.getString("username"));
				utente.setPassword(rs.getString("password"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setMaster(rs.getBoolean("master"));	
				utente.setEnable(rs.getBoolean("enable"));
				utente.setTokenResetPassword(rs.getString("token"));
			}
		} catch(SQLException e) {
			
		}
		
		return utente;
	}

	@Override
	public boolean setToken(Utente user, String token) {
		String query = "update utente set token=? where mail=?";
		
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, token);
			pr.setString(2, user.getMail());
			
			pr.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean resetPassword(Utente u, String password) {
		String query = "update utente set password=?, token=null where mail=?";
		
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
			
			pr.setString(1, hashedPassword);
			pr.setString(2, u.getMail());
			
			pr.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	
	
}
