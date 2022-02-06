package application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import application.model.Categoria;
import application.model.Chef;
import application.model.Indirizzo;
import application.model.RicettaProxy;
import application.model.Utente;
import application.persistenza.Database;


@Controller
public class HomeController {
	
	@GetMapping("/")
	public String getHomePage(HttpServletRequest request) {		
		if ( request.getSession().getAttribute("utente") != null ) {			
			Utente utente = (Utente) request.getSession().getAttribute("utente");		
			List<RicettaProxy> ricettaUtente = Database.getInstance().getFactory().getRicettaDao().findByPublisher(utente.getMail(), 4, 0);
			List<Indirizzo> indirizzi = Database.getInstance().getFactory().getIndirizzoDao().findAllFromUserEnable(utente.getMail());
			request.setAttribute("ricettaUtente", ricettaUtente);
			request.setAttribute("indirizzi", indirizzi);
		}
		
		List<Categoria> tutteLeCategorie = Database.getInstance().getFactory().getCategoriaDao().findAll();
		request.setAttribute("tutteLeCategorie", tutteLeCategorie);
		
		return "index";
	}
	
	@GetMapping("/ricettePopolari")
	@ResponseBody
	public List<RicettaProxy> getRicettePopolari(@RequestParam int offset, HttpServletRequest request) {
		List<RicettaProxy> ricettaPopolari = Database.getInstance().getFactory().getRicettaDao().findOrderBy("likes", 8, offset);
		return ricettaPopolari;
	}
	
	@GetMapping("/ultimeRicette")
	@ResponseBody
	public List<RicettaProxy> getUltimeRicette(@RequestParam int offset, HttpServletRequest request) {
		List<RicettaProxy> ultimeRicette = Database.getInstance().getFactory().getRicettaDao().findOrderBy("data_pubblicazione", 4, offset);
		return ultimeRicette;
	}
	
	@GetMapping("/ricettePubblicateUtente")
	@ResponseBody
	public List<RicettaProxy> getRicettePubblicateUtente(@RequestParam int offset, HttpServletRequest request) {
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
			
		List<RicettaProxy> ricettaUtente = Database.getInstance().getFactory().getRicettaDao().findByPublisher(utente.getMail(), 4, offset);
		
		return ricettaUtente;
	}
	
	@GetMapping("/tuttiChef")
	@ResponseBody
	public List<Chef> getTuttiChef(@RequestParam int offset, HttpServletRequest request) {
		
		List<Chef> tuttiChef = Database.getInstance().getFactory().getChefDao().findOrderBy("data", 4, offset);
		return tuttiChef;
	}
}
