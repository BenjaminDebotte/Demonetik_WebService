package Modele;

import java.util.ResourceBundle;
/**
 * Classe réprésentant l'état de reception des informatios porteurs
 * @author emerikbedouin
 *
 */
public class EtatPorteurIdent extends EtatTransaction{

	private Porteur porteurTransaction; 
	
	public EtatPorteurIdent(Porteur _porteur) {
		super(3, ResourceBundle.getBundle("stringEtat").getString("3"), "Carte");
		this.porteurTransaction = _porteur;
	}
	
	/**
	 * Passage à l'etat de demande d'autorisation
	 */
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
