package Modele;

public class EtatDemandeAuto extends EtatTransaction{

	private int pin;
	
	public EtatDemandeAuto(int _pin) {
		super(4, "Demande autorisation en cours de traitement", "TPE");
		this.pin = _pin;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	
	public EtatTransaction terminer(){
		return new EtatFinTransaction();
	}
}
