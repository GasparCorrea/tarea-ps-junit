package tarea;
import java.util.*;

public class Bank {
	private int usd_balance = 0;
	private int clp_balance = 1000000;
	private ArrayList<ArrayList<Integer>> transactions = new ArrayList<>();
	
	public int getUSDBalance() {
		return this.usd_balance;
	}
	
	public int getCLPBalance() {
		return this.clp_balance;
	}
	
	public int withdrawUSD(int usd) throws ExceededWithdrawAmount, InvalidMinimumAmount, InvalidMaximumAmount {
		if(usd>this.usd_balance) {
			throw new ExceededWithdrawAmount();
		}
		if(usd<10) {
			throw new InvalidMinimumAmount();
		}
		if(usd>100) {
			throw new InvalidMaximumAmount();
		}
		this.usd_balance = this.usd_balance - usd;
		this.updateBalance();
		return this.usd_balance;
	}
	
	public int withdrawCLP(int clp) throws ExceededWithdrawAmount, InvalidMinimumAmount, InvalidMaximumAmount {
		if(clp>this.clp_balance) {
			throw new ExceededWithdrawAmount();
		}
		if(clp<2000) {
			throw new InvalidMinimumAmount();
		}
		if(clp>200000) {
			throw new InvalidMaximumAmount();
		}
		this.clp_balance = this.clp_balance - clp;
		this.updateBalance();
		return this.clp_balance;
	}
	
	public int depositCLP(int clp) throws InvalidMinimumAmount {
		if(clp<2000) {
			throw new InvalidMinimumAmount();
		}
		this.clp_balance = this.clp_balance + clp;
		this.updateBalance();
		return this.clp_balance;
	}
	
	public int depositUSD(int usd) throws InvalidMinimumAmount {
		if(usd<10) {
			throw new InvalidMinimumAmount();
		}
		this.usd_balance = this.usd_balance + usd;
		this.updateBalance();
		return this.usd_balance;
	}
	
	private void updateBalance() {
		ArrayList <Integer> balance = new ArrayList<>(Arrays.asList(this.clp_balance,this.usd_balance));
		this.transactions.add(balance);
	}
	
	public void printTransactions(){
		this.transactions.forEach(balance -> System.out.println(balance.get(0)+"CLP,"+balance.get(1)+"USD"));
	}
	
	@SuppressWarnings("serial")
	public class ExceededWithdrawAmount extends Exception { 
	    public ExceededWithdrawAmount() {
	        super("Has excedido el monto maximo de retiro en tu cuenta");
	    }
	}
	
	@SuppressWarnings("serial")
	public class InvalidMinimumAmount extends Exception { 
	    public InvalidMinimumAmount() {
	        super("No cumples con el monto minimo de operacion");
	    }
	}
	
	@SuppressWarnings("serial")
	public class InvalidMaximumAmount extends Exception { 
	    public InvalidMaximumAmount() {
	        super("Has superado el monto maximo de retiro");
	    }
	}
}
