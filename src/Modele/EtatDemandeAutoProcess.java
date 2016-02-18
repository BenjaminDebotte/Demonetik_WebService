package Modele;

import java.util.ResourceBundle;

public class EtatDemandeAutoProcess extends EtatTransaction{

	public EtatDemandeAutoProcess() {
		super(5, ResourceBundle.getBundle("stringEtat").getString("5"), "Banque");
	}

	
	public EtatTransaction resultatDemandeAuto(int resultat){
		return new EtatDemandeAutoRes(resultat);
	}
}
