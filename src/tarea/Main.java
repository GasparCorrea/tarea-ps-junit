package tarea;

import java.util.Scanner;

import tarea.Bank.ExceededWithdrawAmount;
import tarea.Bank.InvalidMaximumAmount;
import tarea.Bank.InvalidMinimumAmount;

public class Main {

	public static void main(String[] args) throws ExceededWithdrawAmount, InvalidMinimumAmount, InvalidMaximumAmount {
		Bank bank = new Bank();
		String choice = "";
		Scanner sc = new Scanner (System.in);
		int operations = 4;
		while(!choice.equals("4") && operations!=0) {
			System.out.println("Bienvenido al Banco Azul, selecciona la opcion a realizar\n"
					+"'1' Deposito\n"
					+"'2' Retiro\n"
					+"'3' Ver Transacciones\n"
					+ "'4' Cerrar sesion");
			 
		    choice = sc.nextLine();
		    if(choice.equals("1")) {
		    	operations--;
		    	System.out.println("Ingrese moneda y monto a depositar");
		    	String[] c = sc.nextLine().split("\\s+");
		    	String currency = c[0];
		    	int amount = Integer.parseInt(c[1]);
		    	if(currency.equals("USD")) {
		    		bank.depositUSD(amount);
		    	}else if(currency.equals("CLP")) {
		    		bank.depositCLP(amount);
		    	}
		    }else if(choice.equals("2")) {
		    	operations--;
		    	System.out.println("Ingrese moneda y monto a retirar");
		    	String[] c = sc.nextLine().split("\\s+");
		    	String currency = c[0];
		    	int amount = Integer.parseInt(c[1]);
		    	if(currency.equals("USD")) {
		    		bank.withdrawUSD(amount);
		    	}else if(currency.equals("CLP")) {
		    		bank.withdrawCLP(amount);
		    	}
		    }else if(choice.equals("3")){
		    	bank.printTransactions();
		    }
		}
		sc.close();
	}
}
