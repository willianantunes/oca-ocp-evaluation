package br.com.willianantunes.examocp.chap10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class SamplesPlayground {
	public static void main(String args[]) {
		// executeUsage();
		// readingResultSet(); 
		// countSample();
		// gettingElsaDetails();
		// gettingObject();
		// scrollingResultSet();
		absoluteFun();
	}
	
	public static void executeUsage() {
		try {
			Connection con = DriverManager.getConnection("jdbc:derby:zoo");
			
			Statement stmt = con.createStatement();
			
			boolean isResultSet = stmt.execute("SELECT * FROM species");
			if (isResultSet) {
				ResultSet rs = stmt.getResultSet();
				System.out.println("Ran a query!");
			} else {
				int result = stmt.getUpdateCount();
				System.out.println("Ran an update, delete or insert!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void readingResultSet() {
		try {
			Connection con = DriverManager.getConnection("jdbc:derby:zoo");
			Statement stmt = con.createStatement();
			
			Map<Integer, String> idToNameMap = new HashMap<>();
			ResultSet rs = stmt.executeQuery("SELECT id, name FROM species");
			
			while (rs.next()) {
				int id = rs.getInt("id");
				// int id = rs.getInt(1);
				String name = rs.getString("name");
				// String name = rs.getString(2);
				idToNameMap.put(id, name);
			}
			
			System.out.println(idToNameMap); // {1=African Elephant, 2=Zebra}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void countSample() {
		try {
			Connection con = DriverManager.getConnection("jdbc:derby:zoo");
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT count(*) FROM animal");
			int result = 0;
			
			if (rs.next())
				result = rs.getInt(1);
			
			System.out.println(result); // 5
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void gettingElsaDetails() {
		try {
			Connection con = DriverManager.getConnection("jdbc:derby:zoo");
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT date_born FROM animal WHERE name='Elsa'");
			
			if (rs.next()) {
				java.sql.Date sqlDate = rs.getDate(1);
				LocalDate localDate = sqlDate.toLocalDate();
				System.out.println("Elsa born date: " + localDate); // Elsa born date: 2001-05-06
			}
			
			rs = stmt.executeQuery("SELECT date_born FROM animal WHERE name='Elsa'");
			if (rs.next()) {
				java.sql.Time sqlTime = rs.getTime(1);
				LocalTime localTime = sqlTime.toLocalTime();
				System.out.println("Elsa born time: " + localTime); // Elsa born time: 02:15
			}
			
			rs = stmt.executeQuery("SELECT date_born FROM animal WHERE name='Elsa'");
			if (rs.next()) {
				java.sql.Timestamp sqlTimestamp = rs.getTimestamp(1);
				LocalDateTime localDateTime = sqlTimestamp.toLocalDateTime();
				System.out.println("Elsa born date and time: " + localDateTime); // Elsa born date and time: 2001-05-06T02:15
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void gettingObject() {
		try {
			Connection con = DriverManager.getConnection("jdbc:derby:zoo");
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT id, name FROM species");
			
			while (rs.next()) {
				Object idField = rs.getObject(1);
				Object nameField = rs.getObject(2);
				if (idField instanceof Integer) {
					int id = (Integer) idField;
					System.out.println(id);					
				}
				if (nameField instanceof String) {
					String name = (String) nameField;
					System.out.println(name);
				}
			}
			
			/**
			 * OUTPUT:
1
African Elephant
2
Zebra
			 */
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void scrollingResultSet() {
		try {
			Connection con = DriverManager.getConnection("jdbc:derby:zoo");
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs = stmt.executeQuery("SELECT id FROM species ORDER BY id");
			rs.afterLast();
			System.out.println(rs.previous()); // true
			System.out.println(rs.getInt(1)); // 2
			System.out.println(rs.previous()); // true
			System.out.println(rs.getInt(1)); // 1
			System.out.println(rs.last()); // true
			System.out.println(rs.getInt(1)); // 2
			System.out.println(rs.first()); // true
			System.out.println(rs.getInt(1)); // 1
			rs.beforeFirst();
			System.out.println(rs.getInt(1)); // Throws SQLException
			
			/**
			 * OUTPUT:
true
2
true
1
true
2
true
1
java.sql.SQLException: Invalid cursor state - no current row.
	at org.apache.derby.impl.jdbc.SQLExceptionFactory.getSQLException(Unknown Source)
	at org.apache.derby.impl.jdbc.SQLExceptionFactory.getSQLException(Unknown Source)
	at org.apache.derby.impl.jdbc.Util.generateCsSQLException(Unknown Source)
	at org.apache.derby.impl.jdbc.Util.generateCsSQLException(Unknown Source)
	at org.apache.derby.impl.jdbc.EmbedConnection.newSQLException(Unknown Source)
	at org.apache.derby.impl.jdbc.ConnectionChild.newSQLException(Unknown Source)
	at org.apache.derby.impl.jdbc.EmbedResultSet.checkOnRow(Unknown Source)
	at org.apache.derby.impl.jdbc.EmbedResultSet.getColumn(Unknown Source)
	at org.apache.derby.impl.jdbc.EmbedResultSet.getInt(Unknown Source)
	at br.com.willianantunes.examocp.chap10.SamplesPlayground.scrollingResultSet(SamplesPlayground.java:162)
	at br.com.willianantunes.examocp.chap10.SamplesPlayground.main(SamplesPlayground.java:21)
Caused by: ERROR 24000: Invalid cursor state - no current row.
	at org.apache.derby.iapi.error.StandardException.newException(Unknown Source)
	at org.apache.derby.impl.jdbc.SQLExceptionFactory.wrapArgsForTransportAcrossDRDA(Unknown Source)
	... 11 more

			 */
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void absoluteFun() {
		try {
			Connection con = DriverManager.getConnection("jdbc:derby:zoo");
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs = stmt.executeQuery("SELECT id, name FROM species ORDER BY id");
			
			System.out.println(rs.absolute(3));
			// System.out.println(rs.getString(1)); // ERROR 24000: Invalid cursor state - no current row.
			System.out.println(rs.absolute(2));
			System.out.println(rs.getString(1)); // 2
			System.out.println(rs.getString(2)); // Zebra
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeTryWithResources() {
		String url = "jdbc:derby:zoo";
		try (Connection con = DriverManager.getConnection("jdbc:derby:zoo");
				Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT name FROM animal")) {
			while (rs.next())
				System.out.println(rs.getString(1));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
			System.out.println(e.getErrorCode());
		}	
	}
}
