package Modele;

public class EtatTransaction {

	private int numEtat;
	private String labelEtat;
	
	public EtatTransaction() {
		
	}

	public EtatTransaction(int _numEtat, String _labelEtat){
		this.numEtat = _numEtat;
		this.labelEtat = _labelEtat;
	}
	
	
	// Getters & Setters
	
	public int getNumEtat() {
		return numEtat;
	}

	public void setNumEtat(int numEtat) {
		this.numEtat = numEtat;
	}

	public String getLabelEtat() {
		return labelEtat;
	}

	public void setLabelEtat(String labelEtat) {
		this.labelEtat = labelEtat;
	}
	

}
