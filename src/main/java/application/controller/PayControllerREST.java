package application.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import application.model.Ordine;
import application.persistenza.Database;
import application.utilities.StatoOrdine;
import application.utilities.Messaggi;

@RestController
public class PayControllerREST {
	@PostMapping("/confirmedOrderPayPal")
	public Messaggi ConfirmedOrderPayPal(HttpServletResponse resp, HttpServletRequest req) {
		Messaggi messaggio = new Messaggi();
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		Ordine ordine = Database.getInstance().getFactory().getOrdineDao().findCurrentFromUser(utenteLoggato.getUtente(req).getMail());
		ordine.setStato(StatoOrdine.IN_ATTESA_DELLA_CONSEGNA_PAYPAL);
		if (!utenteLoggato.isNull(req) && Database.getInstance().getFactory().getOrdineDao().saveOrUpdate(ordine)) {
			messaggio.setStatus("OK");
			messaggio.setMessaggio("Pagamento alla consegna aggiornato sul DB con successo");
		}else {
			resp.setStatus(500);
			messaggio.setStatus("Fail");
			messaggio.setMessaggio("Pagamento alla consegna non aggiornato sul DB con successo");
		}
		return messaggio;
	}
	
	@PostMapping("/confirmedOrderAllaConsegna")
	public Messaggi ConfirmedOrder_PagamentoAllaConsegna(HttpServletResponse resp, HttpServletRequest req) {
		Messaggi messaggio = new Messaggi();
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		Ordine ordine = Database.getInstance().getFactory().getOrdineDao().findCurrentFromUser(utenteLoggato.getUtente(req).getMail());
		ordine.setStato(StatoOrdine.IN_ATTESA_DELLA_CONSEGNA_ALLACONSEGNA);
		if (!utenteLoggato.isNull(req) && Database.getInstance().getFactory().getOrdineDao().saveOrUpdate(ordine)) {
			messaggio.setStatus("OK");
			messaggio.setMessaggio("Pagamento alla consegna aggiornato sul DB con successo");
		}else {
			resp.setStatus(500);
			messaggio.setStatus("Fail");
			messaggio.setMessaggio("Pagamento alla consegna non aggiornato sul DB con successo");
		}
		return messaggio;
	}
	
	@PostMapping("/control")
	public Messaggi controlloInMagazzino(HttpServletResponse resp, HttpServletRequest req) {
		Messaggi messaggio = new Messaggi();
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		if (!utenteLoggato.isNull(req)) {
			Ordine ordine = Database.getInstance().getFactory().getOrdineDao().findCurrentFromUser(utenteLoggato.getUtente(req).getMail());
			messaggio.setStatus("OK");
			messaggio.setMessaggio("true");
			for(var prodotto:ordine.getProdottiInOrder().keySet()) {
				if(ordine.getProdottiInOrder().get(prodotto) > prodotto.getQuantitaDisponibile()) {
					messaggio.setMessaggio("false");
					return messaggio;
				}
			}
		}else {
			resp.setStatus(500);
			messaggio.setStatus("Fail");
			messaggio.setMessaggio("Problemi interni, perfavore riprovi");
		}
		return messaggio;
	}
	
	
	@PostMapping("/date")
	public Messaggi controlloInMagazzinoeESetData(HttpServletResponse resp, HttpServletRequest req) {
		Messaggi messaggio = new Messaggi();
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		Ordine ordine = Database.getInstance().getFactory().getOrdineDao().findCurrentFromUser(utenteLoggato.getUtente(req).getMail());
        long now = System.currentTimeMillis();
        Date sqlDate = new Date(now);
		ordine.setData(sqlDate);
		System.out.println(ordine.getData());
		if (!utenteLoggato.isNull(req) && Database.getInstance().getFactory().getOrdineDao().saveOrUpdate(ordine)) {
			messaggio.setStatus("OK");
			messaggio.setMessaggio("Data aggiornata");
		}
		else {
			resp.setStatus(500);
			messaggio.setStatus("Fail");
			messaggio.setMessaggio("Problemi interni, perfavore riprovi");
		}
		return messaggio;
	}
	
	
	@PostMapping("/controlAndUpdate")
	public Messaggi UpdateDB(HttpServletResponse resp, HttpServletRequest req) {
		Messaggi messaggio = new Messaggi();
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		Ordine ordine = Database.getInstance().getFactory().getOrdineDao().findCurrentFromUser(utenteLoggato.getUtente(req).getMail());
		if (!utenteLoggato.isNull(req)) {
			Ordine o = Database.getInstance().getFactory().getOrdineDao().findCurrentFromUser(utenteLoggato.getUtente(req).getMail());
			messaggio.setStatus("OK");
			messaggio.setMessaggio("true");
			for(var prodotto:o.getProdottiInOrder().keySet()) {
				if(ordine.getProdottiInOrder().get(prodotto) > prodotto.getQuantitaDisponibile()) {
					messaggio.setMessaggio("false");
					return messaggio;
				}
			}
			for(var prodotto:ordine.getProdottiInOrder().keySet()) {
				prodotto.setQuantitaDisponibile(prodotto.getQuantitaDisponibile() - ordine.getProdottiInOrder().get(prodotto));
				Database.getInstance().getFactory().getProdottoDao().saveOrUpdate(prodotto);
				System.out.println(prodotto.getQuantitaDisponibile());
			}
			Database.getInstance().getFactory().getOrdineDao().saveOrUpdate(ordine);
		}else {
			resp.setStatus(500);
			messaggio.setStatus("Fail");
			messaggio.setMessaggio("Problemi interni, perfavore riprovi");
		}
		return messaggio;
	}
	
	
	@PostMapping("/restore")
	public Messaggi RipristinoProdottiInMagazzino(HttpServletResponse resp, HttpServletRequest req) {
		Messaggi messaggio = new Messaggi();
		Ordine ordine = (Ordine) req.getSession().getAttribute("ordine");
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		if (!utenteLoggato.isNull(req)) {
			messaggio.setStatus("OK");
			messaggio.setMessaggio("true");
			for(var prodotto:ordine.getProdottiInOrder().keySet()) {
				prodotto.setQuantitaDisponibile(prodotto.getQuantitaDisponibile() + ordine.getProdottiInOrder().get(prodotto));
				Database.getInstance().getFactory().getProdottoDao().saveOrUpdate(prodotto);
				System.out.println(prodotto.getQuantitaDisponibile());
			}
			Database.getInstance().getFactory().getOrdineDao().saveOrUpdate(ordine);
		}else {
			resp.setStatus(500);
			messaggio.setStatus("Fail");
			messaggio.setMessaggio("Problemi interni, perfavore riprovi");
		}
		return messaggio;
	}
}