package Modele;

public class Porteur {

	private String nom;
	private String prenom;
	private int plafond;
	private int pin;
	
	
	public Porteur(){
		
	}
	
	public Porteur(String _nom, String _prenom, int _plafond) {
		this.nom = _nom;
		this.prenom = _prenom;
		this.plafond = _plafond;
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
	
	

}
