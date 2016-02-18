package Modele;

public class Porteur {

	private String nom;
	private String prenom;
	private int plafond;
	private String pin;
	private String numCarte;
	
	
	public Porteur(){
		nom = "";
		prenom = "";
		plafond = 0;
		pin = "";
		numCarte = "0";
	}
	
	public Porteur(String _nom, String _prenom, int _plafond, String _numCarte) {
		this.nom = _nom;
		this.prenom = _prenom;
		this.plafond = _plafond;
		this.numCarte = _numCarte;
	}


// Getters & Setters
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getPlafond() {
		return plafond;
	}

	public void setPlafond(int plafond) {
		this.plafond = plafond;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getNumCarte() {
		return numCarte;
	}

	public void setNumCarte(String numCarte) {
		this.numCarte = numCarte;
	}
	

}
