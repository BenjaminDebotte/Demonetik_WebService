package resources;

import java.util.LinkedList;

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

import Modele.*;
import WebSocket.DemonetikSessionHandler;


@Path("/transaction")
public class TransactionResources {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	
	public TransactionResources() {
		// TODO Auto-generated constructor stub
	}
	
	@GET
	@Path("/inittransaction")
	@Produces(MediaType.TEXT_PLAIN)
	public String initTransaction(){
		
		TransactionDao.getInstance().getWorkingTransaction().init();
		
		if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatInit){
			System.out.println("[ETAT] Initiation d'une nouvelle transaction");
			majClientEtat();
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
					
					majClientEtat();
					
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
	public String infoPorteur(@FormParam("nom") String _nom, @FormParam("prenom") String _prenom, @FormParam("numcarte") String _numCarte){
		
		Porteur p = new Porteur(_nom, _prenom, 0, _numCarte);
		
		
		if(TransactionDao.getInstance().getWorkingTransaction() != null){
			TransactionDao.getInstance().getWorkingTransaction().porteurIdent(p);
		}
		
		// Changement d'etat correct 
		if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatPorteurIdent){
			System.out.println("[ETAT] Reception information porteur");
			TransactionDao.getInstance().getWorkingTransaction().setPorteurTransaction(p);
			
			majClientEtat();
			
			return "Porteur pris en compte";
		}
		else{
			System.out.println("[ERROR] Erreur d'etat : Envoi de données porteur");
			return "Erreur etat";
		}
	}
	
	@POST
	@Path("/demandeauto")
	@Produces(MediaType.TEXT_PLAIN)
	public String demandeAutorisation( @FormParam("pin") int pin ){
		
		
		/*if(TransactionDao.getInstance().getWorkingTransaction() != null){
			if(pin != 0){*/
				TransactionDao.getInstance().getWorkingTransaction().demandeAuto(pin);
				
				if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatDemandeAutoAsk){
					System.out.println("Reception demande d'autorisation");
					majClientEtat();
					
					//Traitement de la demande d'auto par la banque
					authoRequestProcessing();
					
					return "Ok";
				}
				else{
					System.out.println("Erreur d'etat : Reception demande d'autorisation");
					return "Ko";
				}
				
		/*		return "Ok";
			}
			else{
				TransactionDao.getInstance().getWorkingTransaction().demandeAuto(pin);
				return "Ko";
			}
		}
		else{
			return null;
		}*/
		
	}
	
	@GET
	@Path("/endtransaction")
	@Produces(MediaType.TEXT_PLAIN)
	public String endTransaction(){
		
	
		
		if(TransactionDao.getInstance().getWorkingTransaction() != null){
			TransactionDao.getInstance().getWorkingTransaction().terminer();
			
			if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatFinTransaction){
				System.out.println("[ETAT] Transaction terminé");
				majClientEtat();
			}
			else{
				System.out.println("[ERROR] Erreur d'etat : Transaction terminé");
				return "Erreur etat";
			}
		}
		return "Transaction terminé";
	}
	
	@GET
	@Path("/gettransaction")
	@Produces(MediaType.APPLICATION_JSON)
	public Transaction getTransaction(){
		
		System.out.println("Recuperation de la transaction");
		
		TransactionDao transDao = TransactionDao.getInstance();
		
		System.out.println(transDao.getWorkingTransaction());
		
		
		return transDao.getWorkingTransaction();
	}
	
	
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
	
	@GET
	@Path("/getmontant")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMontant(){
		
		System.out.println("Demande du montant de la transaction");
		
		TransactionDao transDao = TransactionDao.getInstance();
	
		
		
		return transDao.getWorkingTransaction().getMontant()+"";
	}
	
	
	@GET
	@Path("/resettransaction")
	@Produces(MediaType.TEXT_PLAIN)
	public String resetTransaction(){
		
		System.out.println("Transaction reset");
		
		TransactionDao transDao = TransactionDao.getInstance();
		
		transDao.setWorkingTransaction(new Transaction());
		
		majClientEtat();
		
	
		return "Transaction réinitialisée";
	}
	
	public void majClientEtat(){
		//Envoi aux clients d'écoute
		int numEtat = TransactionDao.getInstance().getWorkingTransaction().getEtat().getNumEtat();
		String libelleEtat = TransactionDao.getInstance().getWorkingTransaction().getEtat().getLabelEtat();
		String typeEtat = TransactionDao.getInstance().getWorkingTransaction().getEtat().getType();
		String message = "{\"numEtat\":\""+numEtat+"\",\"libelle\":\""+libelleEtat+"\", \"type\":\""+typeEtat+"\"}";
		DemonetikSessionHandler.getInstance().sendEtatToSessions(message);
	}
	
	public int authoRequestProcessing(){
		
		TransactionDao.getInstance().getWorkingTransaction().getEtat().processDemandeAuto();
		
		if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatDemandeAutoProcess){
			System.out.println("[ETAT] Processing de la demande d'autorisation");
			majClientEtat();
			ThreadAuthoRequest threadAutho = new ThreadAuthoRequest();
			threadAutho.run();
			
			if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatDemandeAutoRes){
				System.out.println("[ETAT] Demande d'autorisation traité");
				majClientEtat();
			}
			
			return 1;
		}
		else{
			return 0;
		}
		
	}
	
	private class ThreadAuthoRequest implements Runnable{

		
		public void run() {
			
			try {
				Thread.sleep(2000);
				TransactionDao.getInstance().getWorkingTransaction().getEtat().resultatDemandeAuto(1);
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	
}
