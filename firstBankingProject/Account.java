package firstBankingProject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Account {
	
	private String name;
	private String id;
	private int balance = 0;
	private int depositMoney;
	private int withdrawMoney;
	private ArrayList<Character> options = new ArrayList<>();
	private char previous;
	private BigDecimal finalMoney = new BigDecimal(0);

	public Account(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	public void line() {
		System.out.println("====================");
	}

	public void showMenu() {
		System.out.printf("Welcome %s!\nYour ID is %s",name,id);
		char option;
		do {
			System.out.println("\n");
			System.out.println("What would you like to do?\n"
					+ "A. Check your balance\n"
					+ "B. Make a deposit\n"
					+ "C. Make a withdraw\n"
					+ "D. View previous transaction\n"
					+ "E. Calculate interest\n"
					+ "F. Exit\n");
			line();
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter your option: ");
			option = Character.toLowerCase(scanner.next().charAt(0));
			options.add(option);
			switch(option) {
			case 'a':
				line();
				System.out.printf("Balance = $%d",balance).println();
				line();
				break;
			case 'b':
				System.out.printf("Enter an amount to deposit: ");
				depositMoney = scanner.nextInt();
				balance += depositMoney;
				break;
			case 'c':
				System.out.printf("Enter an amount to withdraw: ");
				withdrawMoney = scanner.nextInt();
				balance -= withdrawMoney;
				break;
			case 'd':
				previous = options.get(options.size()-2);
				if (previous == 'b') {
					line();
					System.out.printf("Deposit : $%d",depositMoney).println();
					line();
				}
				else if (previous == 'c') {
					line();
					System.out.printf("Withdraw : $%d",withdrawMoney).println();
					line();
				}
				else if (previous == 'a') {
					int a = 0;
					for (int i = 3;options.size() > i; i++) {
						previous = options.get(options.size()-i);
						if (previous == 'b') {
							line();
							System.out.printf("Deposit : $%d",depositMoney).println();
							line();
							a++;
							break;
						}
						else if (previous == 'c') {
							line();
							System.out.printf("Withdraw : $%d",withdrawMoney).println();
							line();
							a++;
							break;
						}
					}
					if (a==0) {
						System.out.println("No Transaction occured");						
					}
				}
				else{
					System.out.println("No Transaction occured");
				}
				break;
			case 'e':
				System.out.printf("Enter how many years of accrued interest: ");
				int year = scanner.nextInt();
				BigDecimal balanceBD = new BigDecimal(balance);
				BigDecimal wholeInterest = new BigDecimal(year).multiply(new BigDecimal(0.015)).multiply(balanceBD);
				finalMoney = balanceBD.add(wholeInterest);
				line();
				System.out.printf("The current interest rate is 1.5\n"
						+ "After %dyears, your balance wll be $%.1f",year,finalMoney).println();
				line();
				break;
			case 'f':
				scanner.close();
				break;
			default:
				line();
				System.out.println("Invalid input");
				line();
			}
		}while(option != 'f');
		line();
		System.out.println("Thank you for Banking with us!");
	}
	
}
