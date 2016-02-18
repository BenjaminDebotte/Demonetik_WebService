package Modele;

import java.util.ResourceBundle;

public class EtatFinTransaction extends EtatTransaction{

	public EtatFinTransaction() {
		super(7, ResourceBundle.getBundle("stringEtat").getString("7"), "TPE");
	}

}
