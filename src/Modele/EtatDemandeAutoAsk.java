package Modele;

public class EtatDemandeAutoAsk extends EtatTransaction{

	private int pin;
	
	public EtatDemandeAutoAsk(int _pin) {
		super(4, "Demande autorisation reçu", "TPE");
		this.pin = _pin;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	
	public EtatTransaction processDemandeAuto(){
		return new EtatDemandeAutoProcess();
	}
}
