package application.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Ordine;
import application.persistenza.Database;

@Controller
public class VisualizzaOrdineInConsegnaController {

	@GetMapping("visualizzaOrdiniInConsegna")
	public String visualizzaOrdiniInConsegna( HttpServletRequest request ) {
		
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		
		if ( request.getSession().getAttribute("admin") != null ) {
			return "visualizzaOrdiniInConsegna";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/chageState")
	public String getRecipes(@RequestParam("state") String state, HttpServletRequest request) {
		
		Ordine o = (Ordine) request.getSession().getAttribute("ordine_visual");
		
		o.setStato(state);
		
		Database.getInstance().getFactory().getOrdineDao().saveOrUpdate(o);
		
		return "redirect:/ordiniInConsegna";
	}
}
