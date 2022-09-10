package banking;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("-------Bank Transaction-------\n  1.Open account\n 2.Check Balance  \n  3.Withdraw Amount \n 4.Deposit  \n (Click 5 to close)\n Choose one option :");
		    int choice=sc.nextInt();
			switch(choice) {
				case 1:
					System.out.println("Enter your name :");
					String name=sc.next();
					System.out.println("Enter deposit amount:");
					int balance=sc.nextInt();	
					BankManagement.AccountOpen(name,balance);
					break;
				case 2:
					System.out.println("Enter your cust ID:");
					int id1=sc.nextInt();
					BankManagement.checkBalance(id1);
					break;
				case 3:
					System.out.println("Enter your cust ID:");
					int id2=sc.nextInt();
					System.out.println("Enter withdrawal amount:");
					int amount=sc.nextInt();
					BankManagement.withdrawal(id2,amount);
					break;
				case 4:
					System.out.println("Enter your cust ID:");
					int id3=sc.nextInt();
					System.out.println("Enter amount you want to deposit:");
					int amount1=sc.nextInt();
					BankManagement.deposit(id3,amount1);
					break;
				case 5:
					System.out.println("Exit successfully");
					System.exit(0);
				default:
					System.out.println("Wrong input enter again");
					break;
			}
		}
	}
}