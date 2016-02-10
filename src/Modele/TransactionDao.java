package Modele;



public class TransactionDao {

	private static TransactionDao instance;
	private Transaction workingTransaction;
	
	
	private TransactionDao() {
		
	}
	
	public TransactionDao(Transaction _wt){
		this.workingTransaction = _wt;
	}
	
	public static TransactionDao getInstance(){
		if(instance == null){
			return instance = new TransactionDao();
		}
		else{
			return instance;
		}
	}
	
	
	public Transaction getWorkingTransaction(){
			
		return workingTransaction;
	}
	
	public void setWorkingTransaction(Transaction _wt){
		
		workingTransaction = _wt;
	}
}
