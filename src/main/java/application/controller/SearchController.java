package application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.RicettaProxy;
import application.persistenza.Database;


@Controller
public class SearchController {
	
	@GetMapping("/categoria")
	public String getRecipeCategory(@RequestParam String categoria, HttpServletRequest request) { 
		List<RicettaProxy> list = Database.getInstance().getFactory().getRicettaDao().findByCategory(categoria);
		request.setAttribute("list", list);
		return "search";
	}
	
	@GetMapping("/chef")
	public String getRecipeChef(@RequestParam String chef, HttpServletRequest request) { 
		List<RicettaProxy> list = Database.getInstance().getFactory().getRicettaDao().findPublishedBy(chef);
		request.setAttribute("list", list);
		return "search";
	}
	
	@GetMapping("/ricerca")
	public String getRecipes(@RequestParam("search") String search, @RequestParam("filter") String filter, HttpServletRequest request) {
		List<RicettaProxy> list = Database.getInstance().getFactory().getRicettaDao().search(search, filter);
		request.setAttribute("list", list);
		
		return "search";
	}
	
}


