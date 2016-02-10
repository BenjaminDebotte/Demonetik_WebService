package Modele;

public class EtatPorteurIdent extends EtatTransaction{

	private Porteur porteurTransaction; 
	
	public EtatPorteurIdent(Porteur _porteur) {
		super(3, "Information porteur reçu", "Carte");
		this.porteurTransaction = _porteur;
	}
	
	public EtatTransaction demandeAuto(int pin){
		return new EtatDemandeAuto(pin);
	}

	public Porteur getPorteurTransaction() {
		return porteurTransaction;
	}

	public void setPorteurTransaction(Porteur porteurTransaction) {
		this.porteurTransaction = porteurTransaction;
	}

	
	
}
