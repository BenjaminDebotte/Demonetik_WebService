package Modele;

import java.util.ResourceBundle;

public class EtatDemandeAutoRes extends EtatTransaction{

	private int resultat;
	
	public EtatDemandeAutoRes(int resultat) {
		super(6, ResourceBundle.getBundle("stringEtat").getString("6"), "Banque");
		this.resultat = resultat;
	}

		
	public EtatTransaction terminer(){
		return new EtatFinTransaction();
	}


	public int getResultat() {
		return resultat;
	}


	public void setResultat(int resultat) {
		this.resultat = resultat;
	}
	
	
	

}
