package application.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfirmedOrderController {
	
	@GetMapping("confirmedOrder")
	public String ConfirmedOrder(HttpServletRequest req) {
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		if(!utenteLoggato.isNull(req))
			return "confirmedOrder";
		else return "redirect:/";
	}
}
