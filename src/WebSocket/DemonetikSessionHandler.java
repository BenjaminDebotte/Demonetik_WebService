package WebSocket;

import java.io.IOException;
import java.util.LinkedList;

import javax.websocket.Session;

import Modele.TransactionDao;

public class DemonetikSessionHandler {

	private static DemonetikSessionHandler instance;
	private LinkedList<Session> listSession;
	
	private DemonetikSessionHandler() {
		listSession = new LinkedList<Session>();
	}
	
	public int sendMessage(Session session, String message){
		try{   
	        session.getBasicRemote().sendText(message);
	        return 1;
	    } 
		catch (IOException ioe) {        
			System.out.println(ioe.getMessage());    
			return 0;
	    } 
	}
	
	public void sendEtatToSessions(String message){
		if( !instance.getListSession().isEmpty() ){
			for (int i = 0; i < instance.getListSession().size(); i++) {
				instance.sendMessage(instance.getListSession().get(i), message);
			}
		}
	}
	
	public static DemonetikSessionHandler getInstance(){
		if(instance == null){
			return instance = new DemonetikSessionHandler();
		}
		else{
			return instance;
		}
	}

	public LinkedList<Session> getListSession() {
		return listSession;
	}

	public void setListSession(LinkedList<Session> listSession) {
		this.listSession = listSession;
	}
	
	

}
