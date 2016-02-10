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
		
		System.out.println("Initiation d'une nouvelle transaction");
		
		TransactionDao.getInstance().getWorkingTransaction().init();
				
		if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatInit){
			return "Initiation pris en compte";
		}
		else{
			return "Erreur etat";
		}
	}
	
	@POST
	@Path("/montant")
	//@Consumes("text/plain")
	@Produces(MediaType.TEXT_PLAIN)
	public String montantTransaction(@FormParam("montant") int montant){
				
		System.out.println("Reception du montant de la transaction "+montant);
		
		try{
				if(TransactionDao.getInstance().getWorkingTransaction() != null){
					TransactionDao.getInstance().getWorkingTransaction().montant(montant);
				}
				if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatMontant){
					TransactionDao.getInstance().getWorkingTransaction().setMontant(montant);
					return "Montant pris en compte";
				}
				else{
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
	public String infoPorteur(@FormParam("nom") String _nom, @FormParam("prenom") String _prenom, @FormParam("plafond") int _plafond, @FormParam("numcarte") double _numCarte){
		
		System.out.println("Reception information porteur");
		
		Porteur p = new Porteur(_nom, _prenom, _plafond, _numCarte);
		
		
		if(TransactionDao.getInstance().getWorkingTransaction() != null){
			TransactionDao.getInstance().getWorkingTransaction().porteurIdent(p);
		}
		
		// Changement d'etat correct 
		if(TransactionDao.getInstance().getWorkingTransaction().getEtat() instanceof EtatPorteurIdent){
			TransactionDao.getInstance().getWorkingTransaction().setPorteurTransaction(p);
			return "Porteur pris en compte";
		}
		else{
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
		
		return "Demande auto";
	}
	
	@GET
	@Path("/endtransaction")
	@Produces(MediaType.TEXT_PLAIN)
	public String endTransaction(){
		
		System.out.println("Transaction terminé");
		
		if(TransactionDao.getInstance().getWorkingTransaction() != null){
			TransactionDao.getInstance().getWorkingTransaction().terminer();
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
	@Produces(MediaType.TEXT_PLAIN)
	public String getEtat(){
		
		System.out.println("Demande d'etat transaction");
		
		TransactionDao transDao = TransactionDao.getInstance();	
		
		return transDao.getWorkingTransaction().getEtat().getLabelEtat();
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
		
		System.out.println("Transaction termin??");
		
		TransactionDao transDao = TransactionDao.getInstance();
		
		transDao.setWorkingTransaction(new Transaction());
	
		return "Transaction réinitialisée";
	}
	

	
}
