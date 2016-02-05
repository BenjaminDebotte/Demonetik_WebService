package init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import Modele.Transaction;
import Modele.TransactionDao;

public class InitWebService implements ServletContextListener{

	public InitWebService() {
		// TODO Auto-generated constructor stub
	}

	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("INIT du Web Service Demonetik");
		
		TransactionDao transDao = TransactionDao.getInstance();
		
		transDao.setWorkingTransaction(new Transaction());
		
		

		
	}

}
