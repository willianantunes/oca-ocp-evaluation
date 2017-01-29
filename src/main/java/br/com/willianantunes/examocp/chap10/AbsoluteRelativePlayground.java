package br.com.willianantunes.examocp.chap10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AbsoluteRelativePlayground {
	public static void main(String args[]) {
		// absoluteFun();
		relativeFun();
	}
	
	public static void absoluteFun() {
		try {
			Connection con = DriverManager.getConnection("jdbc:derby:zoo");
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs = stmt.executeQuery("SELECT id, name FROM animal ORDER BY id");
			
			System.out.println(rs.absolute(-1)); // true
			System.out.println(rs.getString(2)); // Zoe
			
			System.out.println(rs.absolute(-5)); // true
			System.out.println(rs.getString(2)); // Elsa
			
			System.out.println(rs.absolute(2)); // true
			System.out.println(rs.getString(2)); // Zelda			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public static void relativeFun() {
		try {
			Connection con = DriverManager.getConnection("jdbc:derby:zoo");
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs = stmt.executeQuery("SELECT id, name FROM animal ORDER BY id");
			
			System.out.println(rs.relative(2)); // true
			System.out.println(rs.getString(2)); // Zelda
			System.out.println(rs.relative(1)); // true
			System.out.println(rs.getString(2)); // Ester
			System.out.println(rs.relative(2)); // true
			System.out.println(rs.getString(2)); // Zoe
			
			System.out.println(rs.relative(-4)); // true
			System.out.println(rs.getString(2)); // Elsa
			
			System.out.println(rs.relative(-1)); // false
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
