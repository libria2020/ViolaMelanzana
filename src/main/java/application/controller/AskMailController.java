package application.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import application.model.Utente;
import application.persistenza.Database;
import application.utilities.Protocol;
import application.utilities.SendEmail;
import application.utilities.Utilities;
import net.bytebuddy.utility.RandomString;

@Controller
public class AskMailController {

	@GetMapping("askMailReset")
	public ModelAndView getAskMailPage() {
		return new ModelAndView("askMail");
	}
	
	@PostMapping("/sendMailRecoverPassword")
	public ModelAndView sendMailRecoverPassword(HttpServletRequest req, String email) {
		ModelAndView model = new ModelAndView("askMail");
		String token = RandomString.make(45);
		
		Utente user = Database.getInstance().getFactory().getUtenteDao().findByPrimaryKey(email) ;
		if(user == null) {
			model.addObject("message", Protocol.NO_ONE_WITH_EMAIL);
			
		} else if(user.getPassword() == null) {
			model.addObject("message", Protocol.GOOGLE_EMAIL);
		} else {
			model.addObject("message", Protocol.VALID_EMAIL);
			String link = Utilities.getSiteUrl(req) + "/resetPassword?token=" + token;
			if(SendEmail.getInstance().sendEmailChangePassword(user, link)) {
				Database.getInstance().getFactory().getUtenteDao().setToken(user, token);	
			} else {
				model.addObject("message", Protocol.IMPOSSIBLE_SEND_EMAIL);
			}
		}
		return model;
	}
}
