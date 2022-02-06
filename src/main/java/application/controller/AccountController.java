package application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import application.model.Indirizzo;
import application.model.Ordine;
import application.model.Utente;
import application.persistenza.Database;

@Controller
public class AccountController {
	
	@GetMapping("account")
	public String getProfile(HttpServletRequest request) {
		return "account";
	}
	
	@GetMapping("account/address")
	@ResponseBody
	public List<Indirizzo> getAddress(HttpServletRequest request) {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		
		List<Indirizzo> indirizzi = Database.getInstance().getFactory().getIndirizzoDao().findAllFromUserEnable(utente.getMail());
		
		return indirizzi;
	}
	
	@GetMapping("account/orders")
	@ResponseBody
	public List<Ordine> getOrders(HttpServletRequest request) {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		
		List<Ordine> ordini = Database.getInstance().getFactory().getOrdineDao().findAllOrdersFromUser(utente.getMail());
		
		return ordini;
	}

}
