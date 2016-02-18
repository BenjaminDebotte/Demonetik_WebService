package Modele;

import java.util.ResourceBundle;

public class EtatPorteurIdent extends EtatTransaction{

	private Porteur porteurTransaction; 
	
	public EtatPorteurIdent(Porteur _porteur) {
		super(3, ResourceBundle.getBundle("stringEtat").getString("3"), "Carte");
		this.porteurTransaction = _porteur;
	}
	
	public EtatTransaction demandeAuto(int pin){
		return new EtatDemandeAutoAsk(pin);
	}

	// Getters & Setters
	
	public Porteur getPorteurTransaction() {
		return porteurTransaction;
	}

	public void setPorteurTransaction(Porteur porteurTransaction) {
		this.porteurTransaction = porteurTransaction;
	}

	
	
}
