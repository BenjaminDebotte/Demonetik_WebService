package Modele;

public class EtatMontant extends EtatTransaction{

	private int montant;
	
	public EtatMontant(int _montant){
		super(0, "Montant de la transaction saisie", "TPE");
		this.montant = _montant;
	}

	
	public EtatTransaction porteurIdent(Porteur p){
		return new EtatPorteurIdent(p);
	}
	
	// Getters & Setters
	
	public int getMontant() {
		return montant;
	}

	public void setMontant(int _montant) {
		this.montant = _montant;
	}
	


}
