package application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.model.Ordine;
import application.persistenza.Database;
import application.utilities.StatoOrdine;
import application.utilities.Messaggi;

@RestController
public class ShippingListControllerREST {
	@PostMapping("/changeStatusAddress")
	public Messaggi saveOrdine(@RequestBody int id, HttpServletResponse resp, HttpServletRequest req) {
		Messaggi messaggio = new Messaggi();
		Ordine ord = (Ordine) req.getSession().getAttribute("ordine");
		ord.setStato(StatoOrdine.IN_ATTESA_DEL_METODO_DI_PAGAMENTO);
		ord.setIndirizzo(Database.getInstance().getFactory().getIndirizzoDao().findByPrimaryKey(id));
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		if (!utenteLoggato.isNull(req) && Database.getInstance().getFactory().getOrdineDao().saveOrUpdate(ord)) {
			messaggio.setStatus("OK");
			messaggio.setMessaggio("Ordine aggiornato sul DB con successo");
			req.getSession().setAttribute("ordine", ord);
		}else {
			resp.setStatus(500);
			messaggio.setStatus("Fail");
			messaggio.setMessaggio("Ordine non aggiornato sul DB con successo");
		}
		return messaggio;
	}
}