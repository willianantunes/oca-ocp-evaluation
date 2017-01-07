package br.com.willianantunes.examocp.chap8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This is considered the old way.
 */
public class SystemInSample {
	public static void main(String args[]) throws IOException {
		/**
		 *  System.in return an InputStream and is used to retrieve text input from the user.
		 */		
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		
		String userInput = reader.readLine();
		System.out.println("You entered the following: " + userInput);
	}
}