package application.controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.model.Commento;
import application.model.IngredienteQuantita;
import application.model.NomiSequenze;
import application.model.Prodotto;
import application.model.Raccolta;
import application.model.Ricetta;
import application.model.Utente;
import application.persistenza.Database;
import application.persistenza.dao.jdbc.IdBroker;

@Controller
public class RicettaController {
	
	@GetMapping("/recipePage")
	public String chooseRecipe(@RequestParam String ricetta_id, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Utente ut= (Utente) session.getAttribute("utente");
		Ricetta ricetta = Database.getInstance().getFactory().getRicettaDao().findByPrimaryKey(Integer.parseInt(ricetta_id));
		List<String> ingredienti=  Database.getInstance().getFactory().getRicettaDao().findIngredientByRecipeId(Integer.parseInt(ricetta_id));
		if(ut!= null) {
			boolean isLike= Database.getInstance().getFactory().getLikesRicettaDao().findForRecipeAndUser(ut.getMail(),Integer.parseInt(ricetta_id));
			session.setAttribute("like", isLike);
			boolean isBan= Database.getInstance().getFactory().getSegnalazioniRicettaDao().findForRecipeAndUser(ut.getMail(),Integer.parseInt(ricetta_id));
			session.setAttribute("ban", isBan);
			List <String> raccolteOk=new ArrayList<String>();
			raccolteOk=Database.getInstance().getFactory().getRaccoltaDao().getFolderWithNoRecipe(ut.getMail(), ricetta);
			if (raccolteOk.size()>0)
				session.setAttribute("folderList", raccolteOk);
			else
				session.setAttribute("folderList", null);
		}
		List <Commento> comm = new ArrayList<Commento>();
		comm = Database.getInstance().getFactory().getCommentoDao().findForRecipe(ricetta.getId());
		session.setAttribute("commentiRicetta", comm);
		session.setAttribute("ricetta", ricetta);
		session.setAttribute("ingredienti", ingredienti);
		
		return "recipePage";
	}
	
	
	@PostMapping("handleLike")
	public String handleLike(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Ricetta ricetta= (Ricetta) session.getAttribute("ricetta");
		Utente ut= (Utente)session.getAttribute("utente");
		Database.getInstance().getFactory().getLikesRicettaDao().handleLike(ricetta.getId() ,ut.getMail());
		session.removeAttribute("like");
		return "redirect:/recipePage?ricetta_id="+ ricetta.getId() ;
			
	}
	
	@PostMapping("addRecipe")
	public String addRecipe(HttpServletRequest req,@RequestParam("raccoltaSel") String raccoltaSel) {
		HttpSession session =req.getSession();
		Ricetta ricetta= (Ricetta) session.getAttribute("ricetta");
		Utente ut= (Utente)session.getAttribute("utente");
		Database.getInstance().getFactory().getRaccoltaDao().addRecipeToFolder(raccoltaSel,ut.getMail(), ricetta.getId());
		return "redirect:/recipePage?ricetta_id="+ ricetta.getId() ;
			
	}
	
	
	@PostMapping("saveComment")
	public String register(HttpServletRequest req, @RequestParam("contenuto") String contenuto) {
		HttpSession session =req.getSession();
		Ricetta ricetta= (Ricetta) session.getAttribute("ricetta");
		Utente ut=(Utente) session.getAttribute("utente");
		Utente publ= Database.getInstance().getFactory().getUtenteDao().findByUnique(ut.getUsername());
		Timestamp data= Timestamp.valueOf(LocalDateTime.now());
		Commento commento=new Commento(1,ricetta.getId(),publ,contenuto,data);
		Database.getInstance().getFactory().getCommentoDao().save(commento);

		return "redirect:/recipePage?ricetta_id="+ ricetta.getId() ;
	}
	
	@PostMapping("addCart")
	public String addCart(HttpServletRequest req) {
		HttpSession session =req.getSession();
		Ricetta ricetta= (Ricetta) session.getAttribute("ricetta");
		Utente ut=(Utente) session.getAttribute("utente");
		List<Prodotto> prodotti= new ArrayList<Prodotto>();
		prodotti= Database.getInstance().getFactory().getProdottoDao().findProductByRecipeId(ricetta.getId());
		if (Database.getInstance().getFactory().getOrdineDao().newOrder(ut.getMail(),prodotti))
			session.setAttribute("ordineSalvato", "ok");
		else
			session.setAttribute("ordineSalvato", "no");

		return "redirect:/recipePage?ricetta_id="+ ricetta.getId() ;

	}
	
	@PostMapping("ban")
	public String ban(HttpServletRequest req) {
		HttpSession session =req.getSession();
		Ricetta ricetta= (Ricetta) session.getAttribute("ricetta");
		Utente ut=(Utente) session.getAttribute("utente");
		Database.getInstance().getFactory().getSegnalazioniRicettaDao().add(ricetta.getId(), ut.getMail());
		return "redirect:/recipePage?ricetta_id="+ ricetta.getId() ;		
	}
	
	@PostMapping ("removeRecipeMaster")
	public String removeRecipeMaster(HttpServletRequest req) {
		HttpSession session =req.getSession();
		Ricetta ricetta= (Ricetta) session.getAttribute("ricetta");
		Database.getInstance().getFactory().getRicettaDao().delete(ricetta);
		return "redirect:/";
	}
	
	@PostMapping("removeRecipe")
	public String removeRecipe(HttpServletRequest req, @RequestParam("remove") String motivazione) {
		HttpSession session =req.getSession();
		Ricetta ricetta= (Ricetta) session.getAttribute("ricetta");
		Utente ut=(Utente) session.getAttribute("utente");
		Database.getInstance().getFactory().getRicettaDao().deleteRequest(ricetta,ut,motivazione);
		return "redirect:/recipePage?ricetta_id="+ ricetta.getId() ;		
	}
	
	@PostMapping("newFolderRec")
	public String newFolder(HttpServletRequest req,@RequestParam("nome") String nameFolder) {
	HttpSession session = req.getSession();
	Utente ut=(Utente) session.getAttribute("utente");
	Ricetta ricetta= (Ricetta) session.getAttribute("ricetta");
	List <Raccolta> raccolteUtente=new ArrayList<Raccolta>();
	raccolteUtente=Database.getInstance().getFactory().getRaccoltaDao().getFolderForUser(ut.getMail());
	for (Raccolta raccolta: raccolteUtente) {
		if (raccolta.getNome().equals(nameFolder)) {
			return "redirect:/recipePage?ricetta_id="+ ricetta.getId() ;

		}
	}
	Database.getInstance().getFactory().getRaccoltaDao().newFolder(nameFolder, ut.getMail(),ricetta);
	return "redirect:/recipePage?ricetta_id="+ ricetta.getId() ;

}
	
	
	
	@GetMapping("/insertRecipePage")
	public String ricettPage(HttpServletRequest req) {
		if(req.getSession().getAttribute("utente") == null)
			return "redirect:/loginPage";
		return "insertRecipe";
	}
	
	@PostMapping("/insertRecipe")
	@ResponseBody
	public String inserisciRicetta(@RequestParam("titolo") String titolo, @RequestParam("descrizione") String descrizione, 
		@RequestParam("preparazione")String preparazione, @RequestParam("consiglio") String consiglio, @RequestParam("curiosita") String curiosita,
		@RequestParam("ingredientiQuantita") String ingredientiQuantita, @RequestParam("immagineBase64") String immagineBase64, @RequestParam("nameFile") String nameFile,
		@RequestParam("video") String video, @RequestParam("difficolta") int difficolta, @RequestParam("tempoP") int tempoP, 
		@RequestParam("dosi") String dosi, @RequestParam("tempoC") int tempoC, HttpServletRequest req ) {
		
				
		IngredienteQuantita[] ingrQ = null;
			
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			ingrQ = mapper.readValue(ingredientiQuantita, IngredienteQuantita[].class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		Ricetta ricetta = new Ricetta();
		try {
			ricetta.setId(IdBroker.getId(Database.getInstance().getConn(), NomiSequenze.RICETTA));
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ricetta.setTitolo(titolo);
		ricetta.setDescrizione(descrizione);
		ricetta.setPreparazione(preparazione);
		ricetta.setConsiglio(consiglio);
		ricetta.setCuriosita(curiosita);
		Utente u = (Utente) req.getSession().getAttribute("utente");
		ricetta.setUtentePubblicatore(u);
		ricetta.setLikes(0);
		ricetta.setSegnalazioni(0);
		try {
			ricetta.setImg("src/main/resources/images/" + nameFile + IdBroker.getId(Database.getInstance().getConn(), NomiSequenze.IMAGES) + ".txt");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<IngredienteQuantita> listaIngredienti = new ArrayList<IngredienteQuantita>(Arrays.asList(ingrQ));
		ricetta.setListaIngredientiConQuantita(listaIngredienti);
		ricetta.setBase64Image(immagineBase64);
		
		String[] videoPart = video.split("=");
		ricetta.setVideo(videoPart[1]);
		
		ricetta.setDosi(dosi);
		ricetta.setDifficolta(difficolta);
		ricetta.setTempoCottura(tempoC);
		ricetta.setTempoPreparazione(tempoP);
		
		if(!Database.getInstance().getFactory().getRicettaDao().save(ricetta)) 
			return "NO";
			
		PrintWriter out;
		try {
			out = new PrintWriter(ricetta.getImg());
			out.println(immagineBase64);
			out.close();
			
			return "OK";
		} catch (FileNotFoundException e) {
			return "NO";
		}		
	}
	
/*	@PostMapping("/getCategorie")  //FRANCESCO
	@ResponseBody
	public ArrayList<Categoria> getCategorie(@RequestParam("chiave") String researchKey, HttpServletResponse res){
		ArrayList<Categoria> categorie = Database.getInstance().getFactory().getCategoriaDao().findByResearchKey(researchKey);
	
		return categorie;
	} */
	
	
}
