package application.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import application.model.Indirizzo;
import application.model.Ordine;
import application.model.Prodotto;
import application.model.Utente;
import application.persistenza.Database;
import application.utilities.Messaggi;

@Controller
public class AccountController {
	
	@GetMapping("account")
	public String getProfile(HttpServletRequest request) {
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		
		if ( utente == null ) {
			return "redirect:/";
		}
		
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
	
	
	
	
	@PostMapping("/data")
	@ResponseBody
	public Utente setData(@RequestParam("name") String name, @RequestParam("lastName") String lastName, HttpServletRequest request) {
		
		Utente utenteOld = (Utente) request.getSession().getAttribute("utente");
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
				
		utente.setNome(name);
		utente.setCognome(lastName);
		
		if ( Database.getInstance().getFactory().getUtenteDao().updateData(utente) ) {	
			HttpSession session = request.getSession(true);
			session.setAttribute("utente", utente);
			
			return utente;
		} 
		
		return utenteOld;
	}
	
	@PostMapping("/username")
	@ResponseBody
	public Utente setUsername(@RequestParam("newUsername") String newUsername, HttpServletRequest request) {
		
		Utente utenteOld = (Utente) request.getSession().getAttribute("utente");
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		
		utente.setUsername(newUsername);
		
		if ( Database.getInstance().getFactory().getUtenteDao().updateUsername(utente) ) {
			HttpSession session = request.getSession(true);
			session.setAttribute("utente", utente);
			
			return utente;
		}
		
		return utenteOld;
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@PostMapping("/password")
	public String verifyPassword(@RequestParam("newPassword") String newPassword, HttpServletRequest request) {
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		
		utente.setPassword(newPassword);
		
		if ( Database.getInstance().getFactory().getUtenteDao().updatePassword(utente) ) {
			HttpSession session = request.getSession(true);
			session.setAttribute("utente", utente);
		}
		
		return "redirect:/account";
	}
	
	@PostMapping("/verifyPassword")
	@ResponseBody
	public Messaggi setPassword(@RequestParam String password, HttpServletRequest request) {
		
		Messaggi msg = new Messaggi();
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		Utente checkedUtente = Database.getInstance().getFactory().getUtenteDao().checkByUniqueAndPassword(utente.getUsername(), password);
		
		if ( checkedUtente != null ) {
			msg.setStatus("Auth");
			msg.setMessaggio("Auth");
			return msg;
		}
		
		msg.setStatus("nonAuth");
		msg.setMessaggio("Controlla la tua password");
		
		return msg;
	}
	
	
	
	
	
	
	
	
	
	@GetMapping("/orderDetails")
	@ResponseBody
	public HashMap<Prodotto, Integer> getOrderDetail(@RequestParam int order, HttpServletRequest request) {
		System.out.println(order);
		
		HashMap<Prodotto, Integer> prod = Database.getInstance().getFactory().getOrdineDao().findProductsOfOrder(order);

		return prod;
	}
	
	@PostMapping("/deleteAddress")
	@ResponseBody
	public Messaggi deleteAddress(@RequestParam String orderId, HttpServletRequest request) {

		int id = Integer.parseInt(orderId);
		
		Messaggi msg = new Messaggi();
			
		if ( Database.getInstance().getFactory().getIndirizzoDao().deleteAddress(id) ) {
			msg.setStatus("Auth");
			msg.setMessaggio("Auth");
			return msg;
		}
		
		msg.setStatus("nonAuth");
		msg.setMessaggio("Si e' verificato un problema. L'indirizzo non e' stato rimosso");
		
		return msg;
	}
	
	@PostMapping("/saveAddres")
	public String saveAddress(@RequestParam("via") String via, @RequestParam("num") String num, 
							  @RequestParam("cap") String cap, @RequestParam("city") String city, 
							  @RequestParam("prov") String prov, @RequestParam("tel") String tel,
							  HttpServletRequest request) {
				
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		
		Indirizzo ind = new Indirizzo();
		
		ind.setIndirizzo(via);
		ind.setN_civico(num);
		ind.setCap(cap);
		ind.setCitta(city);
		ind.setProvincia(prov);
		ind.setTelefono(tel);
		ind.setMail(utente.getMail());
		ind.setAttivo(true);
		
		Database.getInstance().getFactory().getIndirizzoDao().saveOrUpdate(ind);
		
		return "redirect:/account";
	}
	
}
