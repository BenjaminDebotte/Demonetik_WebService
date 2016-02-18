package resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.Timer;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import Modele.*;
import WebSocket.DemonetikSessionHandler;

/**
 * Classe interface du WebService
 * @author emerikbedouin
 *
 */
@Path("/transaction")
public class TransactionResources {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	
	public TransactionResources() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Initialisation la transaction en cours
	 * @return 
	 */
	@GET
	@Path("/inittransaction")
	@Produces(MediaType.TEXT_PLAIN)
	public String initTransaction(){
		
		TransactionDao.getInstance().getWorkingTransaction().init();
		
		if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatInit){
			System.out.println("[ETAT] Initiation d'une nouvelle transaction");
			majClientWebSocket();
			return "Initiation pris en compte";
		}
		else{
			System.out.println("[ERROR] Erreur d'etat : Tentative d'initialisation de la transaction");
			return "Erreur etat";
		}
	}
	
	@POST
	@Path("/montant")
	//@Consumes("text/plain")
	@Produces(MediaType.TEXT_PLAIN)
	public String montantTransaction(@FormParam("montant") int montant){
				
		
		try{
				if(TransactionDao.getInstance().getWorkingTransaction() != null){
					TransactionDao.getInstance().getWorkingTransaction().montant(montant);
				}
				if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatMontant){
					
					System.out.println("[ETAT] Reception du montant de la transaction "+montant);
					TransactionDao.getInstance().getWorkingTransaction().setMontant(montant);
					
					majClientWebSocket();
					
					return "Montant pris en compte";
				}
				else{
					System.out.println("[ERROR] Erreur d'etat : tentative d'envoi d'un montant");
					return "Erreur etat";
				}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			return "Erreur";
		}
	}
	
	@POST
	@Path("/infoporteur")
	@Produces(MediaType.TEXT_PLAIN)
	public String infoPorteur(@FormParam("nom") String nom, @FormParam("prenom") String _prenom, @FormParam("numcarte") String _numCarte){
		System.out.println("nom: "+nom+" prenom : "+_prenom+" numcarte : "+_numCarte);
		if(nom != null && _prenom != null && _numCarte != null){
			Porteur p = new Porteur(nom, _prenom, 0, _numCarte);
			
			
			if(TransactionDao.getInstance().getWorkingTransaction() != null){
				TransactionDao.getInstance().getWorkingTransaction().porteurIdent(p);
			}
			
			// Changement d'etat correct 
			if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatPorteurIdent){
				System.out.println("[ETAT] Reception information porteur");
				TransactionDao.getInstance().getWorkingTransaction().setPorteurTransaction(p);
				
				majClientWebSocket();
				
				return "Porteur pris en compte";
			}
			else{
				System.out.println("[ERROR] Erreur d'etat : Envoi de données porteur");
				return "Erreur etat";
			}
		}
		else{
			System.out.println("[ERROR] Erreur d'etat : Envoi de données porteur incorrect");
			return "Erreur donnée porteur";
		}
	}
	
	@POST
	@Path("/demandeauto")
	@Produces(MediaType.TEXT_PLAIN)
	public String demandeAutorisation( @FormParam("pin") int pin ){
		
		
		TransactionDao.getInstance().getWorkingTransaction().demandeAuto(pin);
				
		if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatDemandeAutoAsk){
			System.out.println("Reception demande d'autorisation");
			majClientWebSocket();
					
			//Traitement de la demande d'auto par la banque
			TransactionDao.getInstance().getWorkingTransaction().processDemandeAuto();
			if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatDemandeAutoProcess){
				System.out.println("[ETAT] Processing de la demande d'autorisation");
				majClientWebSocket();
			}		
			return "Ok";
		}
		else{
			System.out.println("[ERROR] Erreur d'etat : Reception demande d'autorisation");
			return "Ko";
		}
		
	}
	
	@GET
	@Path("/demandeautoresultat")
	@Produces(MediaType.TEXT_PLAIN)
	public int demandeAutorisationResultat(){
		
		TransactionDao.getInstance().getWorkingTransaction().resultatDemandeAuto(1);
		
		if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatDemandeAutoRes){
			System.out.println("[ETAT] Demande d'autorisation traité");
			majClientWebSocket();
		}
		else{
			System.out.println("[ERROR] Erreur d'etat : resultat demande d'autorisation");
		}
		
		return TransactionDao.getInstance().getWorkingTransaction().getResultatTransaction();
		
	}
	
	@GET
	@Path("/endtransaction")
	@Produces(MediaType.TEXT_PLAIN)
	public String endTransaction(){
		
	
		
		if(TransactionDao.getInstance().getWorkingTransaction() != null){
			TransactionDao.getInstance().getWorkingTransaction().terminer();
			
			if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatFinTransaction){
				System.out.println("[ETAT] Transaction terminé");
				majClientWebSocket();
			}
			else{
				System.out.println("[ERROR] Erreur d'etat : Transaction terminé");
				return "Erreur etat";
			}
		}
		return "Transaction terminé";
	}
	
	/**
	 * Renvoi la transaction en cours
	 * @return
	 */
	@GET
	@Path("/gettransaction")
	@Produces(MediaType.APPLICATION_JSON)
	public Transaction getTransaction(){
		
		System.out.println("Recuperation de la transaction");
		
		TransactionDao transDao = TransactionDao.getInstance();
		
		System.out.println(transDao.getWorkingTransaction());
		
		
		return transDao.getWorkingTransaction();
	}
	
	/**
	 * Renvoi l'etat de la transacton en cours
	 * @return
	 */
	@GET
	@Path("/getetat")
	@Produces(MediaType.APPLICATION_JSON)
	public EtatTransaction getEtat(){
		
		System.out.println("Demande d'etat transaction");
		
		TransactionDao transDao = TransactionDao.getInstance();	
		
		return transDao.getWorkingTransaction().getEtat();
	}
	
	/*@POST
	@Path("/setEtat")
	@Produces(MediaType.TEXT_PLAIN)
	public String setEtat(@PathParam("etat") String etat){
		
		System.out.println("Modification d'etat transaction");
		
		TransactionDao transDao = TransactionDao.getInstance();
		
		transDao.getWorkingTransaction().setEtat(new EtatTransaction(1,etat));
		
		System.out.println(transDao.getWorkingTransaction().getEtat().getLabelEtat());
		
		return transDao.getWorkingTransaction().getEtat().getLabelEtat();
	}*/
	
	/**
	 * Renvoi le montant de la transaction actuel
	 * @return
	 */
	@GET
	@Path("/getmontant")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMontant(){
		
		System.out.println("Demande du montant de la transaction");
		
		TransactionDao transDao = TransactionDao.getInstance();
	
		
		
		return transDao.getWorkingTransaction().getMontant()+"";
	}
	
	/**
	 * Réinitianalise la transaction en cours
	 * @return
	 */
	@GET
	@Path("/resettransaction")
	@Produces(MediaType.TEXT_PLAIN)
	public String resetTransaction(){
		
		System.out.println("Transaction reset");
		
		TransactionDao transDao = TransactionDao.getInstance();
		
		transDao.setWorkingTransaction(new Transaction());
		
		majClientWebSocket();
		
	
		return "Transaction réinitialisée";
	}
	
	/**
	 * Envoi à chaque client connecté au websocket l'etat actuel de la transaction
	 */
	public void majClientWebSocket(){
		
		String message = "";
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			message = mapper.writeValueAsString(TransactionDao.getInstance().getWorkingTransaction().getEtat());
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(message);
		DemonetikSessionHandler.getInstance().sendEtatToSessions(message);
	}
	
	
	// Deprecated --------------------------------------------------------------------------------------
	
	/**
	 * Simule le traitement d'une demande d'autorisation
	 * @return
	 */
	public int authoRequestProcessing(){
		
		TransactionDao.getInstance().getWorkingTransaction().processDemandeAuto();
		
		if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatDemandeAutoProcess){
			System.out.println("[ETAT] Processing de la demande d'autorisation");
			majClientWebSocket();
			
			Timer timer = new Timer(3000, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TransactionDao.getInstance().getWorkingTransaction().resultatDemandeAuto(1);
					
					if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatDemandeAutoRes){
						TransactionDao.getInstance().getWorkingTransaction().setResultatTransaction(1);
						System.out.println("[ETAT] Demande d'autorisation traité");
						majClientWebSocket();
					}
					else{
						System.out.println("[ERROR] Erreur d'etat : resultat demande d'autorisation");
					}
					
				}
			});
			timer.setRepeats(false);
			timer.start();
			
			return TransactionDao.getInstance().getWorkingTransaction().getResultatTransaction();
		}
		else{
			System.out.println("[ERROR] Erreur d'etat : Processing de la demande d'autorisation");
			return 0;
		}
		
	}
	
	/**
	 * Classe implémentant l'interface runnable pour simuler une transaction en parallèle du thread principal
	 * @author emerikbedouin
	 *
	 */
	private class ThreadAuthoRequest implements Runnable{

		public void run() {
			
			try {
				Thread.sleep(4000);
				TransactionDao.getInstance().getWorkingTransaction().resultatDemandeAuto(1);
				
				if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatDemandeAutoRes){
					System.out.println("[ETAT] Demande d'autorisation traité");
					majClientWebSocket();
				}
				else{
					System.out.println("[ERROR] Erreur d'etat : resultat demande d'autorisation");
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Catch exception threadAuthoRequest : "+e.getMessage());
			}
			
		}
		
	}

	
}
