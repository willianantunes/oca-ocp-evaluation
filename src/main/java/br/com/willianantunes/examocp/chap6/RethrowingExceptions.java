package br.com.willianantunes.examocp.chap6;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class RethrowingExceptions {
	public static void main(String []args) {
		
	}
	
	public static void multiCatch() throws SQLException, IOException /* this is duplication */  {
		class MyMultiCatch {
			private void parseDate(Optional<String> myValue) throws SQLException, IOException {
				if(myValue.isPresent())
					throw new SQLException();
				else
					throw new IOException("KDUzão");
			}
		}
		
		MyMultiCatch mmc = new MyMultiCatch();
		try {
			mmc.parseDate(Optional.of("hã"));
		} catch (SQLException | IOException e) {
			System.err.println(e);
			throw e;
		}
	}
	
	public static void rethrowing() throws SQLException, IOException  {
		class MyMultiCatch {
			private void parseDate(Optional<String> myValue) throws SQLException, IOException {
				if(myValue.isPresent())
					throw new SQLException();
				else
					throw new IOException("KDUzão");
			}
		}
		
		MyMultiCatch mmc = new MyMultiCatch();
		try {
			mmc.parseDate(Optional.of("hã"));
		} catch (Exception e) {
			System.err.println(e);
			throw e;
		}
	}
}
