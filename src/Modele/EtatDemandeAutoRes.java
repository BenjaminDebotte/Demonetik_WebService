package Modele;

public class EtatDemandeAutoRes extends EtatTransaction{

	private int resultat;
	
	public EtatDemandeAutoRes(int resultat) {
		super(6, "Demande autorisation resultat", "Banque");
		this.resultat = resultat;
	}

		
	public EtatTransaction terminer(){
		return new EtatFinTransaction();
	}


	public int getResultat() {
		return resultat;
	}


	public void setResultat(int resultat) {
		this.resultat = resultat;
	}
	
	
	

}
