package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfirmedOrderController {
	
	@GetMapping("confirmedOrder")
	public String ConfirmedOrder() {
		return "confirmedOrder";
	}
}
