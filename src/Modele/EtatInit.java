package Modele;

import java.util.ResourceBundle;

public class EtatInit extends EtatTransaction{

	public EtatInit() {
		super(1, ResourceBundle.getBundle("stringEtat").getString("1"), "TPE");
	}
	
	public EtatTransaction montant(int montant){
		return new EtatMontant(montant);
	}

}
