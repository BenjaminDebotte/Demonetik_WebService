package Modele;



public class TransactionDao {

	private static TransactionDao instance;
	
	public TransactionDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static TransactionDao getInstance(){
		if(instance == null){
			return instance = new TransactionDao();
		}
		else{
			return instance;
		}
	}
	
	
	public Transaction getLastTransaction(){
		
		
		return null;
	}
	
	public Transaction getTransactionById(){
		
		
		return null;
	}
}
