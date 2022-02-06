package application.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import application.model.Ordine;
import application.persistenza.Database;

@Controller
public class CartController {
	
	@GetMapping("cart")
	public String Cart(HttpServletRequest req) {
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		
		if ( !utenteLoggato.isNull(req)) {
			Ordine ordine = Database.getInstance().getFactory().getOrdineDao().findCurrentFromUser(utenteLoggato.getUtente(req).getMail());
			req.getSession().setAttribute("ordine", ordine);
			return "cart";
		}
		
		return "redirect:/";
	}
	
	@PostMapping("/shippingList")
	public String ShippingList(HttpServletRequest req) {
			return "shippingList";
	}

}
