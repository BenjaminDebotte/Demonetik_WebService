package Ressources;

import java.util.LinkedList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

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
		
		TransactionDao transDao = TransactionDao.getInstance();
		
		//transDao.getLastTransaction();
		
		System.out.println();
		
		
		return "Ok";
	}
	
	@GET
	@Path("/getTransaction")
	@Produces(MediaType.TEXT_PLAIN)
	public Transaction getTransaction(){
		
		System.out.println("Modification d'etat transaction");
		
		TransactionDao transDao = TransactionDao.getInstance();
		
		Transaction trans = transDao.getLastTransaction();
		
		System.out.println(trans);
		
		
		return trans;
	}
	
	@GET
	@Path("/DemandeAuto/{pin}")
	@Produces(MediaType.TEXT_PLAIN)
	public String demandeAutorisation( @PathParam("pin") int pin ){
		
		
		return "Ok";
	}
	
	@GET
	@Path("/getEtat")
	@Produces(MediaType.TEXT_PLAIN)
	public String getEtat(){
		
		System.out.println("Demande d'etat transaction");
		
		TransactionDao transDao = TransactionDao.getInstance();
		
		Transaction trans = transDao.getLastTransaction();
		
		System.out.println(trans);
		
		
		return trans.getEtat()+"";
	}
	
	@GET
	@Path("/setEtat")
	@Produces(MediaType.TEXT_PLAIN)
	public String setEtat(){
		
		System.out.println("Modification d'etat transaction");
		
		TransactionDao transDao = TransactionDao.getInstance();
		
		Transaction trans = transDao.getLastTransaction();
		
		System.out.println(trans);
		
		
		return trans.getEtat()+"";
	}
	
	@GET
	@Path("/getMontant")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMontant(){
		
		System.out.println("Demande du montant de la transaction");
		
		TransactionDao transDao = TransactionDao.getInstance();
		
		Transaction trans = transDao.getLastTransaction();
		
		System.out.println(trans);
		
		
		return trans.getMontant()+"";
	}
	
	
}
