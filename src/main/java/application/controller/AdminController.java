package application.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import application.model.Ricetta;
import application.persistenza.Database;

@Controller
public class AdminController {

	@GetMapping("/admin/recipe")
	public ModelAndView getPageRecipe(HttpServletRequest req) {
		ModelAndView mEv = null;
		
		if(req.getSession().getAttribute("admin") != null) {
			ArrayList<Ricetta> ricetteDaAccettare = Database.getInstance().getFactory().getRicettaDao().findPendingRecipe();
		
			mEv = new ModelAndView("adminReviewRecipe");
			mEv.addObject("pendingRecipes", ricetteDaAccettare);
			
			return mEv;
		}
		
		return mEv = new ModelAndView("redirect:/loginPage");
	}
}
