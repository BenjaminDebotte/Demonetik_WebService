package Modele;

public class Transaction {

	private int numTransaction;
	private EtatTransaction etat;
	private int montant;
	private Porteur porteurTransaction;
	
	public Transaction() {
		numTransaction = 0;
		etat = new EtatTransaction();
		montant = 0;
		porteurTransaction = new Porteur();
	}
	
	public Transaction(int _numTransaction, EtatTransaction _etat, int montant, Porteur porteurTransaction) {
		this.numTransaction = _numTransaction;
		this.etat = etat;
		this.montant = montant;
		this.porteurTransaction = porteurTransaction;
	}

	
	// Getters & Setters
	
	public int getNumTransaction() {
		return numTransaction;
	}

	public void setNumTransaction(int numTransaction) {
		this.numTransaction = numTransaction;
	}

	public EtatTransaction getEtat() {
		return etat;
	}

	public void setEtat(EtatTransaction etat) {
		this.etat = etat;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public Porteur getPorteurTransaction() {
		return porteurTransaction;
	}

	public void setPorteurTransaction(Porteur porteurTransaction) {
		this.porteurTransaction = porteurTransaction;
	}
	
}
