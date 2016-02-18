package Modele;

import java.util.ResourceBundle;

public class EtatStart extends EtatTransaction{

	public EtatStart() {
		super(0, ResourceBundle.getBundle("stringEtat").getString("0"), "EtatStart");
	}
	
	public EtatTransaction init(){
		return new EtatInit();
	}

}
