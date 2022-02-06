package application.model;

import java.util.ArrayList;
import java.util.List;

import application.persistenza.Database;

public class RicettaProxy extends Ricetta{

	@Override
	public String getDescrizione() {
		// TODO Auto-generated method stub
		return super.getDescrizione();
	}

	@Override
	public String getPreparazione() {
		// TODO Auto-generated method stub
		return super.getPreparazione();
	}
	public boolean getApprovazione() {
		return super.getApprovazione();
	}
	
	public ArrayList<IngredienteQuantita> getListaIngredientiConQuantita() {
		return super.getListaIngredientiConQuantita();
	}

	@Override
	public String getConsiglio() {
		// TODO Auto-generated method stub
		return super.getConsiglio();
	}

	@Override
	public String getCuriosita() {
		// TODO Auto-generated method stub
		return super.getCuriosita();
	}

	@Override
	public int getAccettata() {
		// TODO Auto-generated method stub
		return super.getAccettata();
	}

	@Override
	public int getSegnalazioni() {
		// TODO Auto-generated method stub
		return super.getSegnalazioni();
	}
	public int getDifficolta() {
		return super.getDifficolta();
	}

	@Override
	public ArrayList<Commento> getCommenti() {
		if (super.getCommenti() == null) {
			List<Commento> commenti = new ArrayList<Commento>();
			commenti=Database.getInstance().getFactory().getCommentoDao().findForRecipe(getId());
			
			setCommenti(commenti);
		}
		return super.getCommenti();
	}

}
