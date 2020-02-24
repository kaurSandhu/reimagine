
public class Customer {

	private int transactionTime;
	private int arrivalTime;
	private static int customerNum;
	private int customerNumber;
	private int clockTime;
	public Customer(int clockTime,int transactionTime, int arrivalTime) {
		
		 	this.clockTime=clockTime;
			this.arrivalTime=arrivalTime;
			this.transactionTime=transactionTime;
			customerNumber=++customerNum;
		
	}
	
	public int getTransactionTime() {
		return transactionTime;
	}
	public int getArrivalTime(){
		return arrivalTime;
	}
	public int getCustomerNumber(){
		return customerNumber;
	}
	
	
}
