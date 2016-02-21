package Modele;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe représenant une transaction
 * @author emerikbedouin
 *
 */
@XmlRootElement
public class Transaction {

	private int numTransaction;
	private EtatTransaction etat = new EtatStart();
	private int montant;
	private Porteur porteurTransaction;
	private int resultatTransaction;
	
	public Transaction() {
		numTransaction = 0;
		etat = new EtatStart();
		montant = -1;
		porteurTransaction = new Porteur();
	}
	
	public Transaction(int _numTransaction, EtatTransaction _etat, int montant, Porteur porteurTransaction) {
		this.numTransaction = _numTransaction;
		this.etat = etat;
		this.montant = montant;
		this.porteurTransaction = porteurTransaction;
	}

	
	// Fonction de passage d'un Etat à un autre
	
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
	public void processDemandeAuto(){
		etat = etat.processDemandeAuto();
	}
	public void resultatDemandeAuto(int resultat){
		etat = etat.resultatDemandeAuto(resultat);
		resultatTransaction = resultat;
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

	public int getResultatTransaction() {
		return resultatTransaction;
	}

	public void setResultatTransaction(int resultatTransaction) {
		this.resultatTransaction = resultatTransaction;
	}
	
	
	
}
