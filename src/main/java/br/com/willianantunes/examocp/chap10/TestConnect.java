package br.com.willianantunes.examocp.chap10;

import java.sql.*;

public class TestConnect {
	public static void main(String args[]) {
		try {
			Connection con = DriverManager.getConnection("jdbc:derby:zoo");
			// Connection con = DriverManager.getConnection("jdbc:derby:zoo", "mySampleUser", "myPassword");
			System.out.println(con);
			/**
			 * OUTPUT:
org.apache.derby.impl.jdbc.EmbedConnection@1758386724 (XID = 210), (SESSIONID = 1), (DATABASE = zoo), (DRDAID = null) 
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
