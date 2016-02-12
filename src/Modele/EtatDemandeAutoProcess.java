package Modele;

public class EtatDemandeAutoProcess extends EtatTransaction{

	public EtatDemandeAutoProcess() {
		super(5, "Demande autorisation en cours de traitement", "Banque");
	}

	
	public EtatTransaction resultatDemandeAuto(int resultat){
		return new EtatDemandeAutoRes(resultat);
	}
}
