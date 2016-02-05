package resources;

import java.util.LinkedList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import Modele.EtatTransaction;
import Modele.Porteur;
import Modele.Transaction;
import Modele.TransactionDao;


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
	@Path("/initTransaction")
	@Produces(MediaType.TEXT_PLAIN)
	public String initTransaction(){
		
		System.out.println("Initiation d'une nouvelle transaction");
		
		TransactionDao.getInstance().getWorkingTransaction().setEtat(new EtatTransaction(1, "Transaction init"));
				
		return "Ok";
	}
	
	@GET
	@Path("/getTransaction")
	@Produces(MediaType.APPLICATION_JSON)
	public Transaction getTransaction(){
		
		System.out.println("Modification d'etat transaction");
		
		TransactionDao transDao = TransactionDao.getInstance();
		
		System.out.println(transDao.getWorkingTransaction());
		
		
		return transDao.getWorkingTransaction();
	}
	
	@POST
	@Path("/porteurTransaction")
	@Produces(MediaType.TEXT_PLAIN)
	public String porteurTransaction( String nom, int numCarte){
		
		System.out.println("Modification porteur");
		
		TransactionDao transDao = TransactionDao.getInstance();
		
		Porteur tempP = transDao.getWorkingTransaction().getPorteurTransaction();
	
		tempP.setNom(nom);
		tempP.setNumCarte(numCarte);
		
		return "Ok porteur";
	}
	
	@POST
	@Path("/DemandeAuto")
	@Produces(MediaType.TEXT_PLAIN)
	public String demandeAutorisation( int pin ){
		
		
		return "Ok";
	}
	
	@GET
	@Path("/getEtat")
	@Produces(MediaType.TEXT_PLAIN)
	public String getEtat(){
		
		System.out.println("Demande d'etat transaction");
		
		TransactionDao transDao = TransactionDao.getInstance();	
		
		return transDao.getWorkingTransaction().getEtat().getLabelEtat();
	}
	
	@POST
	@Path("/setEtat")
	@Produces(MediaType.TEXT_PLAIN)
	public String setEtat(String etat){
		
		System.out.println("Modification d'etat transaction");
		
		TransactionDao transDao = TransactionDao.getInstance();
		
		transDao.getWorkingTransaction().setEtat(new EtatTransaction(1,etat));
		
		System.out.println(transDao.getWorkingTransaction().getEtat().getLabelEtat());
		
		return transDao.getWorkingTransaction().getEtat().getLabelEtat();
	}
	
	@GET
	@Path("/getMontant")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMontant(){
		
		System.out.println("Demande du montant de la transaction");
		
		TransactionDao transDao = TransactionDao.getInstance();
	
		
		
		return transDao.getWorkingTransaction().getMontant()+"";
	}
	
	@GET
	@Path("/endTransaction")
	@Produces(MediaType.TEXT_PLAIN)
	public String endTransaction(){
		
		System.out.println("Transaction termin??");
		
		TransactionDao transDao = TransactionDao.getInstance();
		
		transDao.getWorkingTransaction().setEtat(new EtatTransaction(10, "Transaction termin??"));
	
		return "Transaction terminé";
	}
	
	@GET
	@Path("/resetTransaction")
	@Produces(MediaType.TEXT_PLAIN)
	public String resetTransaction(){
		
		System.out.println("Transaction termin??");
		
		TransactionDao transDao = TransactionDao.getInstance();
		
		transDao.setWorkingTransaction(new Transaction());
	
		return "Transaction réinitialisé";
	}
	
	
	
	
}
