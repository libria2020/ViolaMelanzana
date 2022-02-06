package application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Utente;
import application.persistenza.Database;
import application.utilities.SendEmail;


@Controller
public class SignUpController {
	
	@GetMapping("signUpPage")
	public String signUpPage(HttpServletRequest req) {
		if(req.getSession().getAttribute("utente") != null)
			return "redirect:/";
		return "signup";
	}
	

	@PostMapping("register")
	public String register(HttpServletRequest req, @RequestParam("mail") String mail,@RequestParam("nome") String nome, 
			@RequestParam("cognome") String cognome, @RequestParam("username") String username, @RequestParam("password") String password) {
		
		Utente utente = new Utente();
		utente.setMail(mail);
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setPassword(password);
		utente.setUsername(username);
		utente.setMaster(false);
		utente.setEnable(false);
		utente.setVerificationCode(SendEmail.getInstance().getRandom());
		
		if(Database.getInstance().getFactory().getUtenteDao().save(utente, false)) {
			
			boolean test = SendEmail.getInstance().sendEmailVerification(utente);
			
			if(test) {
				HttpSession session = req.getSession();
				session.setAttribute("usernameUser", utente.getUsername());
				session.setAttribute("verificationCode", utente.getVerificationCode());
			}
			return "verify";
			
		} else {
			return "signup";
		}
	}
	
	@PostMapping("verifyCode")
	public String verificaCodice(HttpServletRequest req, String codice) {
		HttpSession session = req.getSession();
		String usernameUser = (String)session.getAttribute("usernameUser");
		String verificationCode = (String) session.getAttribute("verificationCode");
		
		if(codice.equals(verificationCode)) {
			session.removeAttribute("usernameUser");
			session.removeAttribute("verificationCode");
			
			Database.getInstance().getFactory().getUtenteDao().enableUser(usernameUser);
			
			return "redirect:/loginPage";
		} else {
			return "verify";
		}
	}
	
	
	/*@PostMapping("/googleSignIn")
	public String addGoogleUser(String mailGoogle, String nomeGoogle, String cognomeGoogle, HttpServletRequest req) {
		try {
			Utente u = Database.getInstance().getFactory().getUtenteDao().findByPrimaryKey(mailGoogle);
			if(u == null) {
				u = new Utente(mailGoogle, nomeGoogle, cognomeGoogle, "gmailUser" + IdBroker.getIdGoogle(Database.getInstance().getConn()), false);
				if(!Database.getInstance().getFactory().getUtenteDao().save(u, true))
					return "login";
			}
			
			HttpSession session = req.getSession();
			session.setAttribute("utente", u);
			session.setAttribute("google", true);
			
			return "redirect:/";
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		return "login";
	}*/
}
