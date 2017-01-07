package br.com.willianantunes.examocp.chap8;

import java.io.BufferedReader;
import java.io.Console;
import java.util.Arrays;

public class PasswordCompareSample {
	public static void main(String args[]) {
		Console console = System.console();
		
		if (console != null) {
			// ONE WAY
			char[] password = console.readPassword("Enter your password: ");
			
			// ANOTHER WAY
			console.format("Enter your password again: ");
			console.flush(); // Read page 439 and 440 please
			char[] verify = console.readPassword();
			boolean match = Arrays.equals(password, verify);
			
			// Immediately clear passwords from memory
			/**
			 * For security reasons, it's important to immediately clear 
			 * the character arrays that store the password as soon as 
			 * they are no longer needed in the application.
			 */
			for (int i=0; i<password.length; i++) password[i] = 'x';
			// ANOTHER WAY (beautiful one): Arrays.fill(password, 'x');
			for (int i=0; i<verify.length; i++) verify[i] = 'x';
			
			console.format("Your password was " + (match? "correct" : "incorrect"));
			
			
		} else {
			throw new RuntimeException("Console is not supported!");
		}
	}
}
