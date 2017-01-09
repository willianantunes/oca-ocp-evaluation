package br.com.willianantunes.examocp.chap10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ExampleThreeUpdateTypes {
	public static void main(String args[]) {
		try {
			Connection con = DriverManager.getConnection("jdbc:derby:zoo");
			
			Statement stmt = con.createStatement();
			int result = stmt.executeUpdate("INSERT INTO species VALUES(10, 'DEER', 3)");
			System.out.println("Total of rows affected: " + result); // 1
			
			result = stmt.executeUpdate("UPDATE species SET NAME= '' WHERE name='NONE'");
			System.out.println("Total of rows updated: " + result); // 0
			
			result = stmt.executeUpdate("DELETE FROM species WHERE id=10");
			System.out.println("Total rows deleted: " + result); // 1
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
