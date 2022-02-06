package application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.model.Ordine;
import application.persistenza.Database;
import application.utilities.StatoOrdine;
import application.utilities.Messaggi;

@RestController
public class CartControllerREST {
	
	@PostMapping("/changeStatus")
	public Messaggi saveOrdine(@RequestParam Float prezzo, HttpServletResponse resp, HttpServletRequest req) {
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		Messaggi messaggio = new Messaggi();
		Ordine ordine = (Ordine) req.getSession().getAttribute("ordine");
		ordine.setStato(StatoOrdine.IN_ATTESA_DELL_INDIRIZZO);
		ordine.setTotale(prezzo);
		/*for(var prodotto : prodotto_quantitaNuovi.keySet()) {
			ordine.getProdottiInOrder().put(Database.getInstance().getFactory().getProdottoDao().findByPrimaryKey(prodotto),
					prodotto_quantitaNuovi.get(prodotto));
		}*/
		if (!utenteLoggato.isNull(req) && Database.getInstance().getFactory().getOrdineDao().saveOrUpdate(ordine)) {
			messaggio.setStatus("OK");
			messaggio.setMessaggio("Ordine aggiornato sul DB con successo");
			req.getSession().setAttribute("ordine", ordine);
		}else {
			resp.setStatus(500);
			messaggio.setStatus("Fail");
			messaggio.setMessaggio("Ordine non aggiornato sul DB con successo");
		}
		return messaggio;
	}
	
	
	@PostMapping("/deleteProduct")
	public Messaggi deleteP(@RequestParam String nome_prodotto, HttpServletResponse resp, HttpServletRequest req){
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		Messaggi messaggio = new Messaggi();
		if (!utenteLoggato.isNull(req) && Database.getInstance().getFactory().getOrdineDao().deleteProduct(nome_prodotto, utenteLoggato.getUtente(req).getMail())) {
			messaggio.setStatus("OK");
			messaggio.setMessaggio("Prodotto eliminato dall'ordine con successo");
		}else {
			resp.setStatus(500);
			messaggio.setStatus("Fail");
			messaggio.setMessaggio("Prodotto non eliminato dall'ordine");
		}
		return messaggio;
	}
}
