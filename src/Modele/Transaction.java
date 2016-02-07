package Modele;

public class Transaction {

	private int numTransaction;
	private EtatTransaction etat = new EtatStart();
	private int montant;
	private Porteur porteurTransaction;
	
	public Transaction() {
		numTransaction = 0;
		etat = new EtatStart();
		montant = 0;
		porteurTransaction = new Porteur();
	}
	
	public Transaction(int _numTransaction, EtatTransaction _etat, int montant, Porteur porteurTransaction) {
		this.numTransaction = _numTransaction;
		this.etat = etat;
		this.montant = montant;
		this.porteurTransaction = porteurTransaction;
	}

	
	//Etat
	
	public void init(){
		etat = etat.init();
	}
	public void montant(int montant){
		etat = etat.montant(montant);
	}
	public void porteurIdent(Porteur p){
		etat = etat.porteurIdent(p);
	}
	public void demandeAuto(int pin){
		etat = etat.demandeAuto(pin);
	}
	public void terminer(){
		etat = etat.terminer();
	}
	
	// Getters & Setters
	
	public EtatTransaction getEtat(){
		return etat;
	}
	
	public int getNumTransaction() {
		return numTransaction;
	}

	public void setNumTransaction(int numTransaction) {
		this.numTransaction = numTransaction;
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
