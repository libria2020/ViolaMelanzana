package application.persistenza.dao.jdbc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.model.Ricetta;
import application.model.RicettaProxy;
import application.model.Utente;
import application.persistenza.Database;
import application.persistenza.dao.RicettaDao;

public class RicettaDaoJDBC implements RicettaDao {

	private Connection conn;
	
	public RicettaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	

	@Override
	public List<RicettaProxy> findAll() {
		
		ArrayList<RicettaProxy> ricettaProxy = null;
		
		String query ="select id, titolo, likes, image_ricetta from ricetta order by likes desc;";
		
		try {
			ricettaProxy = new ArrayList<RicettaProxy>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				RicettaProxy r = new RicettaProxy();
				
				r.setId(rs.getInt("id"));
				r.setTitolo(rs.getString("titolo"));
				r.setLikes(rs.getInt("likes"));
				r.setImg(rs.getString("image_ricetta"));
				
				if(r.getImg() != null) {
					BufferedReader read;
					try {
						read = new BufferedReader(new FileReader(r.getImg()));
						if(read.ready()) {
							String base64 = read.readLine();
							r.setBase64Image(base64);
						}
						read.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				ricettaProxy.add(r);
				
			}
			
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return ricettaProxy;
	}

	
	@Override
	public List<RicettaProxy> findByPublisher(String key) {
		
		ArrayList<RicettaProxy> ricettaProxy = null;
		
		String query ="select id, titolo, likes, image_ricetta from ricetta where mail_utente_pubblicatore='" + key + "';";
		
		try {
			ricettaProxy = new ArrayList<RicettaProxy>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				RicettaProxy r = new RicettaProxy();
				
				r.setId(rs.getInt("id"));
				r.setTitolo(rs.getString("titolo"));
				r.setLikes(rs.getInt("likes"));
				r.setImg(rs.getString("image_ricetta"));
				
				if(r.getImg() != null) {
					BufferedReader read;
					try {
						read = new BufferedReader(new FileReader(r.getImg()));
						if(read.ready()) {
							String base64 = read.readLine();
							r.setBase64Image(base64);
						}
						read.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				ricettaProxy.add(r);
			}
			
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return ricettaProxy;
	}

	@Override
	public List<RicettaProxy> findByCategory(String key) {
		ArrayList<RicettaProxy> ricettaProxy = null;
				
		String query ="select id, titolo, descrizione, likes, image_ricetta, id_categoria from ricetta inner join ricetta_categoria on id = id_ricetta and id_categoria='" + key + "';";

		try {
			ricettaProxy = new ArrayList<RicettaProxy>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				RicettaProxy r = new RicettaProxy();
				System.err.println("hi");
				
				r.setId(rs.getInt("id"));
				r.setTitolo(rs.getString("titolo"));
				r.setDescrizione(rs.getString("descrizione"));
				r.setLikes(rs.getInt("likes"));
				r.setImg(rs.getString("image_ricetta"));
				
				if(r.getImg() != null) {
					BufferedReader read;
					try {
						read = new BufferedReader(new FileReader(r.getImg()));
						if(read.ready()) {
							String base64 = read.readLine();
							r.setBase64Image(base64);
						}
						read.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				ricettaProxy.add(r);
			}
			
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return ricettaProxy;
	}
	



	@Override
	public Ricetta findByPrimaryKey(int id) {
		Ricetta ricetta = null;
		String query ="SELECT * from ricetta WHERE id=?;";
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			if(rs.next()) {
				ricetta= new Ricetta();
				ricetta.setAccettata(1);
				ricetta.setCommenti(Database.getInstance().getFactory().getCommentoDao().findForRecipe(id));
				ricetta.setConsiglio(rs.getString("consiglio"));
				ricetta.setDescrizione(rs.getString("descrizione"));
				ricetta.setId(rs.getInt("id"));
				ricetta.setLikes(rs.getInt("likes"));
				ricetta.setPreparazione(rs.getString("preparazione"));
				ricetta.setSegnalazioni(rs.getInt("segnalazioni"));
				ricetta.setTitolo(rs.getString("titolo"));
				ricetta.setDifficolta(rs.getInt("difficolta"));
				ricetta.setTempoCottura(rs.getInt("tempo_cottura"));
				ricetta.setTempoPreparazione(rs.getInt("tempo_preparazione"));
				ricetta.setDosi(rs.getString("dosi"));
				ricetta.setImg(rs.getString("image_ricetta"));
				
				if(ricetta.getImg() != null) {
					BufferedReader read;
					try {
						read = new BufferedReader(new FileReader(ricetta.getImg()));
						if(read.ready()) {
							String base64 = read.readLine();
							ricetta.setBase64Image(base64);
						}
						read.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				Utente ut = Database.getInstance().getFactory().getUtenteDao().findByPrimaryKey(rs.getString("mail_utente_pubblicatore"));
				ricetta.setUtentePubblicatore(ut);
			}	
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return ricetta;
	}
	
	@Override
	public List<String> findIngredientByRecipeId(int id) {
		List<String> ingredienti=new ArrayList<String>();
		String query ="SELECT * from contiene WHERE id_ricetta=?;";
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			while(rs.next()) {
				String ingr= rs.getString("nome_ingrediente") + " " + String.valueOf(rs.getInt("quantita")) + " " + rs.getString("unita_di_misura");
				ingredienti.add(ingr);
			}
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return ingredienti;
		
	}
	
	@Override
	public boolean delete(Ricetta ricetta) {
		if (ricetta == null)
			return false;
		
		try {
			PreparedStatement p = conn.prepareStatement("DELETE FROM commento WHERE id_ricetta=?;");
			PreparedStatement p1 = conn.prepareStatement("DELETE FROM likes_ricetta WHERE id_ricetta=?;");
			PreparedStatement p2 = conn.prepareStatement("DELETE FROM segnalazioni_ricetta WHERE id_ricetta=?;");
			PreparedStatement p3 = conn.prepareStatement("DELETE FROM contiene WHERE id_ricetta=?;");
			PreparedStatement p4 = conn.prepareStatement("DELETE FROM raccolta WHERE id_ricetta=?;");
			PreparedStatement p5 = conn.prepareStatement("DELETE FROM ricetta_categoria WHERE id_ricetta=?;");
			PreparedStatement p6 = conn.prepareStatement("DELETE FROM ricetta WHERE id=?;");

			p.setInt(1, ricetta.getId());
			p.executeUpdate();
			p1.setInt(1, ricetta.getId());
			p1.executeUpdate();
			p2.setInt(1, ricetta.getId());
			p2.executeUpdate();
			p3.setInt(1, ricetta.getId());
			p3.executeUpdate();
			p4.setInt(1, ricetta.getId());
			p4.executeUpdate();
			p5.setInt(1, ricetta.getId());
			p5.executeUpdate();
			p6.setInt(1, ricetta.getId());
			p6.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public List<RicettaProxy> findOrderBy(String expression, int limit, int offset) {
		ArrayList<RicettaProxy> ricettaProxy = null;
		
		String query ="select id, titolo, likes, image_ricetta from ricetta order by " + expression + " desc limit ? offset ?;";
		PreparedStatement ps;
		try {
			
			ricettaProxy = new ArrayList<RicettaProxy>();
			
			ps = conn.prepareStatement(query);
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				RicettaProxy r = new RicettaProxy();
				
				r.setId(rs.getInt("id"));
				r.setTitolo(rs.getString("titolo"));
				r.setLikes(rs.getInt("likes"));
				r.setImg(rs.getString("image_ricetta"));
				
				if(r.getImg() != null) {
					BufferedReader read;
					try {
						read = new BufferedReader(new FileReader(r.getImg()));
						if(read.ready()) {
							String base64 = read.readLine();
							r.setBase64Image(base64);
						}
						read.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				ricettaProxy.add(r);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ricettaProxy;
	}
	
	@Override
	public List<RicettaProxy> findPublishedBy(String key) {
		ArrayList<RicettaProxy> ricettaProxy = null;
		
		String query ="select id, titolo, descrizione, likes, image_ricetta, difficolta, tempo_preparazione from ricetta where chef='" + key + "' order by data_pubblicazione;";
		
		try {
			ricettaProxy = new ArrayList<RicettaProxy>();
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				RicettaProxy r = new RicettaProxy();
				
				r.setId(rs.getInt("id"));
				r.setTitolo(rs.getString("titolo"));
				r.setDescrizione(rs.getString("descrizione"));
				r.setLikes(rs.getInt("likes"));
				r.setImg(rs.getString("image_ricetta"));
				r.setDifficolta(rs.getInt("difficolta"));
				r.setTempoPreparazione(rs.getInt("tempo_preparazione"));
				
				if(r.getImg() != null) {
					BufferedReader read;
					try {
						read = new BufferedReader(new FileReader(r.getImg()));
						if(read.ready()) {
							String base64 = read.readLine();
							r.setBase64Image(base64);
						}
						read.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				ricettaProxy.add(r);
			}
			
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return ricettaProxy;
	}
	
	@Override
	public List<RicettaProxy> search(String query, String filter) {
		
		ArrayList<RicettaProxy> ricettaProxy = null;
		
		String querySql ="select id, titolo, descrizione, likes, image_ricetta, difficolta, tempo_preparazione, master from ricetta inner join utente on mail = mail_utente_pubblicatore where titolo like '%" + query + "%' or descrizione like '%" + query + "%' order by master desc;";
		
		try {
			ricettaProxy = new ArrayList<RicettaProxy>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(querySql);
			
			while(rs.next()) {
				RicettaProxy r = new RicettaProxy();
				r.setId(rs.getInt("id"));
				r.setTitolo(rs.getString("titolo"));
				r.setDescrizione(rs.getString("descrizione"));
				r.setLikes(rs.getInt("likes"));
				r.setImg(rs.getString("image_ricetta"));
				r.setDifficolta(rs.getInt("difficolta"));
				r.setTempoPreparazione(rs.getInt("tempo_preparazione"));
				
				if(r.getImg() != null) {
					BufferedReader read;
					try {
						read = new BufferedReader(new FileReader(r.getImg()));
						if(read.ready()) {
							String base64 = read.readLine();
							r.setBase64Image(base64);
						}
						read.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				ricettaProxy.add(r);
			}
		} catch(SQLException e) {
			e.getStackTrace();
		}
			
		return ricettaProxy;
	}


	@Override
	public List<RicettaProxy> findByPublisher(String key, int limit, int offset) {
		
		ArrayList<RicettaProxy> ricettaProxy = null;
		
		String query ="select id, titolo, likes, image_ricetta from ricetta where mail_utente_pubblicatore=? order by data_pubblicazione limit ? offset ? ;";
		
		try {
			ricettaProxy = new ArrayList<RicettaProxy>();
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, key);
			st.setInt(2, limit);
			st.setInt(3, offset);
			
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				RicettaProxy r = new RicettaProxy();
				
				r.setId(rs.getInt("id"));
				r.setTitolo(rs.getString("titolo"));
				r.setLikes(rs.getInt("likes"));
				r.setImg(rs.getString("image_ricetta"));
				
				if(r.getImg() != null) {
					BufferedReader read;
					try {
						read = new BufferedReader(new FileReader(r.getImg()));
						if(read.ready()) {
							String base64 = read.readLine();
							r.setBase64Image(base64);
						}
						read.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				ricettaProxy.add(r);
			}
			
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return ricettaProxy;
	}


	@Override
	public boolean save(Ricetta ricetta) {
		// TODO Auto-generated method stub
		if(ricetta == null)
			return false;
		try {
			String query = "INSERT INTO ricetta VALUES(?, ?, ?, ?, ?, ?, ?, 0, 0, null, null, ?, ?, ?, ?, null, ?, ?)";
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setInt(1, ricetta.getId()); 
			pr.setString(2, ricetta.getTitolo());
			pr.setString(3, ricetta.getDescrizione());
			pr.setString(4, ricetta.getPreparazione());
			pr.setString(5,  ricetta.getConsiglio());
			pr.setString(6, ricetta.getCuriosita());
			pr.setString(7, ricetta.getUtentePubblicatore().getMail());
			pr.setInt(8, ricetta.getDifficolta());
			pr.setInt(9, ricetta.getTempoPreparazione());
			pr.setInt(10, ricetta.getTempoCottura());
			pr.setString(11, ricetta.getDosi());
			pr.setString(12, ricetta.getImg());
			pr.setString(13, ricetta.getVideo());
			
			Database.getInstance().getFactory().getIngredienteDao().saveIngredientOfRecipe(ricetta.getId(), ricetta.getListaIngredientiConQuantita());
			pr.executeUpdate();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean update(Ricetta ricetta) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public ArrayList<Ricetta> findPendingRecipe() {
		ArrayList<Ricetta> ricette= new ArrayList<Ricetta>();
		
		String query = "select * from ricetta where amministratore_approvante is null";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				Ricetta ricetta = new Ricetta();
				ricetta.setId(rs.getInt("id"));
				ricetta.setTitolo(rs.getString("titolo"));
				ricetta.setDescrizione(rs.getString("descrizione"));
				ricetta.setPreparazione(rs.getString("preparazione"));
				ricetta.setConsiglio(rs.getString("consiglio"));
				ricetta.setCuriosita(rs.getString("curiosita"));
				ricetta.setUtentePubblicatore((Database.getInstance().getFactory().getUtenteDao().findByPrimaryKey(rs.getString("mail_utente_pubblicatore"))));
				ricetta.setLikes(0);
				ricetta.setSegnalazioni(0);
				ricetta.setDifficolta(rs.getInt("difficolta"));
				ricetta.setDosi(rs.getString("dosi"));
				ricetta.setTempoCottura(rs.getInt("tempo_cottura"));
				ricetta.setTempoPreparazione(rs.getInt("tempo_preparazione"));
				ricetta.setListaIngredientiConQuantita(Database.getInstance().getFactory().getIngredienteDao().findByRecipe(rs.getInt("id")));
				ricetta.setImg(rs.getString("image_ricetta"));
				ricetta.setVideo(rs.getString("video_preparazione"));
				
				if(ricetta.getImg() != null) {
					BufferedReader read = new BufferedReader(new FileReader(ricetta.getImg()));
					if(read.ready()) {
						String base64 = read.readLine();
						ricetta.setBase64Image(base64);
					}
					read.close();
				}
				
				ricette.add(ricetta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ricette;
	}



	@Override
	public boolean deleteRequest(Ricetta ricetta, Utente utente, String motivazione) {
		if (ricetta == null)
			return false;
		
		try {
			String query = "INSERT INTO richiesta_rimozione_ricetta VALUES(?,?,?)";
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setInt(1,ricetta.getId());
			pr.setString(2,utente.getMail());
			pr.setString(3, motivazione);
			
			pr.executeUpdate();
			return true;
		}
	  
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	

}
