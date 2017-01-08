package br.com.willianantunes.examocp.chap10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Like the authors well said, you wouldn't and shouldn't write code like this.
 * The only thing that matters is the OCP exam and what is asks you.
 */
public class MyFirstDatabaseConnection {
	public static void main(String args[]) {
		String url = "jdbc:derby:zoo";
		try (Connection con = DriverManager.getConnection(url);
				Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT name FROM animal")) {
			while (rs.next())
				// 
				/**
				 * Value from column index 1. It starts in 1 instead of 0.
				 */
				System.out.println(rs.getString(1)); 
			/**
			 * OUTPUT:
Elsa
Zelda
Ester
Eddie
Zoe
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
