package application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import application.model.Categoria;
import application.persistenza.Database;


@Controller
public class NavbarController {
	
	@GetMapping("/category")
	public void getHomePage(HttpServletRequest request) {		
		
		List<Categoria> tutteLeCategorie = Database.getInstance().getFactory().getCategoriaDao().findAll();
		request.setAttribute("tutteLeCategorie", tutteLeCategorie);
	}
}
