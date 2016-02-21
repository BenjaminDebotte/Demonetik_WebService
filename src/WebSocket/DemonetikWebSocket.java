package WebSocket;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.Path;

@ServerEndpoint(value = "/websocketdemonetik")
public class DemonetikWebSocket{

	
    private DemonetikSessionHandler sessionHandler = DemonetikSessionHandler.getInstance();
	
	public DemonetikWebSocket() {
		super();
		
	}

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Connexion au websocket demonetik");
		sessionHandler.getListSession().add(session);
		
		
		/*try {
			session.getBasicRemote().sendText("Connexion réussi");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	@OnMessage
	public void onMessage(String message, Session session){
		System.out.println("Reception message sur websocket");
		
		try{   
	          session.getBasicRemote().sendText("Message bien reçu");
	    } 
		catch (IOException ioe) {        
	              System.out.println(ioe.getMessage());         
	     } 
	}
	
	@OnClose
	public void onClose(Session session){
		System.out.println("Fermeture de la session websocket");
		sessionHandler.getListSession().remove(session);
	}
	
	@OnError
	public void onError(Throwable error){
		System.out.println("Erreur websocket");
		Logger.getLogger(DemonetikWebSocket.class.getName()).log(Level.SEVERE, null, error);
	}

}
