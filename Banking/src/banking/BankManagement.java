package banking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankManagement {
	static Connection con = connection.getConnection();
	public static void AccountOpen(String name,int balance) throws ClassNotFoundException, SQLException {
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery("SELECT MAX(Accno) FROM Bank");
			int max=0;
			while(result.next()) {
				max = result.getInt("MAX(Accno)");
			}
			max+=10;
			String query="Insert INTO Bank(Accno,Name,Balance) VALUES("+max +",'"+name+"',"+ balance +")";
			int count = stmt.executeUpdate(query);
			if(count>0) {
				System.out.println("Your Account created successfully! your Bank id is : "+ max);
			} else {
				System.out.println("There is some error while creating your account");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void checkBalance(int id) throws ClassNotFoundException, SQLException {
		try {
			Statement stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT Name,balance FROM Bank" + " where Accno= "+id+"");
			int amt=0;
			String name="";
			while (rset.next()) {
				name=rset.getString("Name");
			    amt=rset.getInt("balance");
			}
				System.out.println("Bank name : " +name+ " With Bank id : "+ id + " has a current balance : Rs."+amt);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void withdrawal(int id,int amount) throws ClassNotFoundException, SQLException {
		try {
			Statement stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT balance FROM Bank" + " where Accno= "+id+"");
			int amt=0;
			while (rset.next())
			    amt=rset.getInt(1);
			if(amt<amount) {
				System.out.println("You Don't have sufficient balance");
			}else {
					amt-=amount;
					@SuppressWarnings("unused")
					ResultSet rs = stmt.executeQuery("UPDATE Bank " + "SET balance="+amt+" where Accno="+id+"");
					ResultSet rsett = stmt.executeQuery("SELECT Name ,balance FROM Bank" + " where Accno= "+id+"");
					int amtt=0;
					String name="";
					while(rsett.next()) {
					name=rsett.getString("Name");
			        amtt = rsett.getInt("balance");
					}
					System.out.println("Bank name : " +name+ " With Bank id : "+ id + " withdrawal amount Rs."+amount+" has a current balance : Rs."+amtt);
				}
			} catch(Exception e) {
				e.printStackTrace();
		}
	}		
	public static void deposit(int id,int amount) throws SQLException, ClassNotFoundException {
		try {
			Statement stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT balance FROM Bank" + " where Accno= "+id+"");
			int amt=0;
			while (rset.next())
			    amt=rset.getInt(1);
			amt+=amount;
			@SuppressWarnings("unused")
			ResultSet rs = stmt.executeQuery("UPDATE Bank " + "SET balance="+amt+" where Accno="+id+"");
			ResultSet rsett = stmt.executeQuery("SELECT Name,balance FROM Bank" + " where Accno="+id+"");
			int amtt=0;
			String name="";
			while(rsett.next()) {
				name=rsett.getString("Name");
				amtt = rsett.getInt("balance");
			}
			System.out.println("Bank name : " +name+ " With Bank id : "+ id + " Deposited amount Rs."+amount+" has a current balance : Rs."+amtt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
