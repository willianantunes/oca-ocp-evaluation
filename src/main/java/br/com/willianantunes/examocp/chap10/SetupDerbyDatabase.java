package br.com.willianantunes.examocp.chap10;

import java.sql.*;

/**
 * You must run it before trying the book samples.<br />
 * It will create derby.log and the folder zoo at the root directory of this project. 
 * @see <a href="https://www.selikoff.net/2015/09/12/ocpbookbonuscreatingderbydatabasejava/">OCP 8 Book Bonus: Creating a Derby Database in Java 8</a>
 */
public class SetupDerbyDatabase {

	public static void main(String[] args) throws Exception {
		String url = "jdbc:derby:zoo;create=true";
		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {

			// stmt.executeUpdate("DROP TABLE animal");
			// stmt.executeUpdate("DROP TABLE species");

			stmt.executeUpdate("CREATE TABLE species "
					+ "(id INTEGER PRIMARY KEY, "
					+ "name VARCHAR(255), "
					+ "num_acres DECIMAL)");

			stmt.executeUpdate("CREATE TABLE animal "
					+ "(id INTEGER PRIMARY KEY, "
					+ "species_id integer REFERENCES species (id), "
					+ "name VARCHAR(255), "
					+ "date_born TIMESTAMP)");

			stmt.executeUpdate("INSERT INTO species VALUES (1, 'African Elephant', 7.5)");
			stmt.executeUpdate("INSERT INTO species VALUES (2, 'Zebra', 1.2)");

			stmt.executeUpdate("INSERT INTO animal VALUES (1, 1, 'Elsa', '2001-05-06 02:15:00')");
			stmt.executeUpdate("INSERT INTO animal VALUES (2, 2, 'Zelda', '2002-08-15 09:12:00')");
			stmt.executeUpdate("INSERT INTO animal VALUES (3, 1, 'Ester', '2002-09-09 10:36:00')");
			stmt.executeUpdate("INSERT INTO animal VALUES (4, 1, 'Eddie', '2010-06-08 01:24:00')");
			stmt.executeUpdate("INSERT INTO animal VALUES (5, 2, 'Zoe', '2005-11-12 03:44:00')");

			ResultSet rs = stmt.executeQuery("select count(*) from animal");
			rs.next();
			System.out.println(rs.getInt(1));
			
			/**
			 * OUTPUT of derby.log:
----------------------------------------------------------------
Sun Jan 08 20:17:05 BRST 2017:
Booting Derby version The Apache Software Foundation - Apache Derby - 10.13.1.1 - (1765088): instance a816c00e-0159-8028-09a2-00001db942f0 
on database directory C:\Users\Willian\Development\git\oca-ocp-evaluation\zoo with class loader sun.misc.Launcher$AppClassLoader@4e0e2f2a 
Loaded from file:/C:/Users/Willian/.m2/repository/org/apache/derby/derby/10.13.1.1/derby-10.13.1.1.jar
java.vendor=Oracle Corporation
java.runtime.version=1.8.0_73-b02
user.dir=C:\Users\Willian\Development\git\oca-ocp-evaluation
os.name=Windows 7
os.arch=amd64
os.version=6.1
derby.system.home=null
Database Class Loader started - derby.database.classpath=''
			 */
		}
	}
}