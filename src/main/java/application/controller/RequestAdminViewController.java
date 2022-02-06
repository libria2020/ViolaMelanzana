package application.controller;


import java.io.IOException;
import java.util.ArrayList;
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
public class RequestAdminViewController {
	
	@GetMapping("/requestAdminView")
	public String view(HttpServletRequest req)	{
		return "requestAdminView";
	}
	
	@GetMapping("/allBanRequest")
	@ResponseBody
	public List<Ricetta> getVanRequest(HttpServletRequest req){
		List<Ricetta> ricette= new ArrayList<Ricetta>();
		ricette= Database.getInstance().getFactory().getRicettaDao().getWithBan();
		
		return ricette;
	}
	
	@GetMapping("/allUsers")
	@ResponseBody
	public List<Utente> getAll(HttpServletRequest req){
		List<Utente> allUsers= new ArrayList<Utente>();
		allUsers= Database.getInstance().getFactory().getUtenteDao().findAllWithRequest();
		return allUsers;
	}
	
	@PostMapping("/getRequestForUser")
	@ResponseBody
	public List<List<String>> getRequestForUser(HttpServletRequest req) throws IOException{
		String mail1= req.getReader().readLine();
		String mail= mail1.substring(1,mail1.length()-1);
		List<List<String>> userRequest= new ArrayList<List<String>>();
		System.out.println(mail);
		userRequest= Database.getInstance().getFactory().getUtenteDao().getUserRequest(mail);
		for (List<String> l: userRequest) {
			System.out.println(l);
			for(String s : l)
				System.out.println(s);
		}
		return userRequest;
	}
	
	@PostMapping("/getRecipe")
	@ResponseBody
	public Ricetta getRecipe(HttpServletRequest req) throws IOException {
		HttpSession session = req.getSession();
		String id_ricetta1= req.getReader().readLine();
		String id_ricetta= id_ricetta1.substring(1,id_ricetta1.length()-1);
		Ricetta ricetta= Database.getInstance().getFactory().getRicettaDao().findByPrimaryKey(Integer.parseInt(id_ricetta));
		session.setAttribute("ricetta", ricetta);
		return ricetta;
	}
	
	@PostMapping("accept")
	public String setAccept(HttpServletRequest req,@RequestParam(value="accept") String id_o_mail,@RequestParam(value="requestType") String requestType) throws IOException{
		if(requestType.equals("aggiunta ricetta")) {
			Database.getInstance().getFactory().getRicettaDao().update(Integer.parseInt(id_o_mail),true);
			
		}
		if (requestType.equals("rimozione ricetta")) {
			Database.getInstance().getFactory().getRicettaDao().updateDeleteRequest(Integer.parseInt(id_o_mail),true);
			Database.getInstance().getFactory().getRicettaDao().update(Integer.parseInt(id_o_mail),false);
		
		}
		
		if (requestType.equals("promozione a master"))
			Database.getInstance().getFactory().getUtenteDao().isMaster(id_o_mail,true);
		else
			Database.getInstance().getFactory().getUtenteDao().isMaster(id_o_mail,false);
		
		return "redirect:/requestAdminView";
	}
	
	@PostMapping("refuse")
	public String setRefuse(HttpServletRequest req,@RequestParam(value="refuse") String id_o_mail,@RequestParam(value="requestType") String requestType) throws IOException{
		if(requestType.equals("aggiunta ricetta")) {
			Database.getInstance().getFactory().getRicettaDao().update(Integer.parseInt(id_o_mail),false);
			System.out.println("in aggiunta ricetta rifiutata");

		}
		if (requestType.equals("rimozione ricetta")) {
			Database.getInstance().getFactory().getRicettaDao().updateDeleteRequest(Integer.parseInt(id_o_mail),false);
			System.out.println("in rimozione ricetta rifiutata");

		}
		return "redirect:/requestAdminView";
	}
	
	@PostMapping("banRecipe")
	public String banRecipe(HttpServletRequest req,@RequestParam(value="banRecipe") String id_ricetta){
		Database.getInstance().getFactory().getRicettaDao().update(Integer.parseInt(id_ricetta),false);
		return "redirect:/requestAdminView";
	}
}
	
