package application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import application.model.Ricetta;
import application.model.Utente;
import application.persistenza.Database;

@Controller
public class SingleFolderController {
	@GetMapping("/singleFolder")
	public String getSingleFolder(@RequestParam String nome,HttpServletRequest req) { 
		HttpSession session = req.getSession();
		session.setAttribute("nome", nome);
		return "singleFolder";
	}
	
	@GetMapping("/folder")
	@ResponseBody
	public List<Ricetta> getFolder(HttpServletRequest req) { 
		HttpSession session = req.getSession();
		Utente ut=(Utente) session.getAttribute("utente");
		String nome = (String) session.getAttribute("nome");
		System.out.println("nome= " + nome);
		List<Ricetta> list = Database.getInstance().getFactory().getRaccoltaDao().getRecipeForFolder(ut.getMail(), nome);
		return list;
	}
	
	/*@GetMapping("/removeFolder")
	public String removeRecipe(@RequestParam String id_ricetta,@RequestParam String nome, HttpServletRequest req) { 
		HttpSession session = req.getSession();
		Utente ut=(Utente) session.getAttribute("utente");
		Database.getInstance().getFactory().getRaccoltaDao().deleteFolder(nome, ut.getMail());
		return "redirect:/folderLike";

	}*/
	
	@PostMapping("deleteFolder")
	public String deleteFolder(HttpServletRequest req) { 
		HttpSession session = req.getSession();
		Utente ut=(Utente) session.getAttribute("utente");
		String nome = (String) session.getAttribute("nome");
		Database.getInstance().getFactory().getRaccoltaDao().deleteFolder(nome, ut.getMail());
		return "redirect:/folderLike";

	}
	
	
	@PostMapping("removeFromFolder")
	public String removeRecipeFromFolder(@RequestParam(value="removeRecipeFromFolder") String id_ricetta,HttpServletRequest req) {
		HttpSession session = req.getSession();
		Utente ut=(Utente) session.getAttribute("utente");
		String nome = (String) session.getAttribute("nome");
		Database.getInstance().getFactory().getRaccoltaDao().deleteRecipeFromFolder(nome, ut.getMail(), Integer.parseInt(id_ricetta));
		return "redirect:/singleFolder?nome="+ nome;
	}
}
	

