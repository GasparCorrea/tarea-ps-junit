package tarea;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import tarea.Bank.ExceededWithdrawAmount;
import tarea.Bank.InvalidMaximumAmount;
import tarea.Bank.InvalidMinimumAmount;

public class BankTest {

	@Test
	public void testFirstUSDDepositShouldReturn500USD() throws InvalidMinimumAmount {
		// Given
		Bank bank = new Bank();
		// When
		bank.depositUSD(500);
		//Then
		int usd_balance = bank.getUSDBalance();
		assertEquals(500,usd_balance);
	}
	
	@Test
	public void testFirstCLPDepositShouldReturn1200000() throws InvalidMinimumAmount {
		// Given
		Bank bank = new Bank();
		// When
		bank.depositCLP(200000);
		//Then
		int clp_balance = bank.getCLPBalance();
		assertEquals(1200000,clp_balance);
	}
	
	@Test
	public void testFirstCLPWithdrawShouldReturn500000() throws ExceededWithdrawAmount, InvalidMinimumAmount, InvalidMaximumAmount {
		// Given
		Bank bank = new Bank();
		// When
		bank.withdrawCLP(30000);
		//Then
		int clp_balance = bank.getCLPBalance();
		assertEquals(970000,clp_balance);
	}
	
	@Test
	public void testFirstUSDAfterFirstDepositWithdrawShouldReturnFirstDeposit() throws ExceededWithdrawAmount, InvalidMinimumAmount, InvalidMaximumAmount {
		// Given
		Bank bank = new Bank();
		bank.depositUSD(90);
		// When
		bank.withdrawUSD(90);
		//Then
		int usd_balance = bank.getUSDBalance();
		assertEquals(0,usd_balance);
	}
	
	@Test
	public void testWithdrawingUSDExceedingAmountThrowsException() {
		Exception exception = assertThrows(ExceededWithdrawAmount.class,()->{
			Bank bank = new Bank();
			bank.withdrawUSD(300);
		});
		String expectedMessage = "Has excedido el monto maximo de retiro en tu cuenta";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
		
	}
	
	@Test
	public void testWithdrawingCLPExceedingAmountThrowsException() {
		Exception exception = assertThrows(ExceededWithdrawAmount.class,()->{
			Bank bank = new Bank();
			bank.withdrawCLP(1000001);
		});
		String expectedMessage = "Has excedido el monto maximo de retiro en tu cuenta";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void testWithdrawingCLPExceedingMaximumAmountThrowsException() {
		Exception exception = assertThrows(InvalidMaximumAmount.class,()->{
			Bank bank = new Bank();
			bank.withdrawCLP(200001);
		});
		String expectedMessage = "Has superado el monto maximo de retiro";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void testWithdrawingUSDExceedingMaximumAmountThrowsException() {
		Exception exception = assertThrows(InvalidMaximumAmount.class,()->{
			Bank bank = new Bank();
			bank.depositUSD(200);
			bank.withdrawUSD(101);
		});
		String expectedMessage = "Has superado el monto maximo de retiro";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void testInvalidMinimumCLPAmountThrowsException() {
		Exception exception = assertThrows(InvalidMinimumAmount.class,()->{
			Bank bank = new Bank();
			bank.depositCLP(1999);
		});
		String expectedMessage = "No cumples con el monto minimo de operacion";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void testInvalidMinimumUSDAmountThrowsException() {
		Exception exception = assertThrows(InvalidMinimumAmount.class,()->{
			Bank bank = new Bank();
			bank.depositUSD(9);
		});
		String expectedMessage = "No cumples con el monto minimo de operacion";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}

}
