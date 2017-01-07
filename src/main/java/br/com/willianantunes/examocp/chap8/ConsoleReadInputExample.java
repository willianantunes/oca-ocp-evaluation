package br.com.willianantunes.examocp.chap8;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;

public class ConsoleReadInputExample {
	public static void main(String args[]) throws IOException {
		Console console = System.console();
		if (console != null) {
			console.writer().print("How excited are you about your trip today? ");
			console.flush(); // Read page 439 and 440 please
			String excitementAnswer = console.readLine();
			String name = console.readLine("Please enter your name: ");
			
			Integer age = null;
			console.writer().print("What is your age? ");
			console.flush(); // Read page 439 and 440 please
			BufferedReader reader = new BufferedReader(console.reader());
			String value = reader.readLine(); // Must implement or throw checked (IOException) exception
			age = Integer.valueOf(value);
			
			console.writer().println();
			
			console.format("Your name is " + name);
			console.writer().println();
			console.format("Your age is " + age);
			console.writer().println();
			console.format("Your excitement level is " + excitementAnswer);
		} else {
			throw new RuntimeException("Console is not supported!");
		}
	}
}
