package Modele;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;

@XmlRootElement
public class StateMessage {

	private int numEtat;
	private String libelle;
	private String type;
	
	public StateMessage() {
		
	}

	public StateMessage(int numEtat, String libelle, String type) {
		this.numEtat = numEtat;
		this.libelle = libelle;
		this.type = type;
	}

	public int getNumEtat() {
		return numEtat;
	}

	public void setNumEtat(int numEtat) {
		this.numEtat = numEtat;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
