package br.com.willianantunes.examocp.chap8;

import java.io.Console;
import java.util.Locale;

/**
 * The <strong>System.in</strong> and <strong>System.out</strong> objects have been available since the 
 * earliest versions of Java. In Java 6, the java.io.Console class was introduced 
 * with far more features and abilities than the original techniques.
 */
public class ConsoleSample {
	public static void main(String args[]) {
		Console console = System.console();
		
		if (console != null) {
			String userInput = console.readLine();
			console.writer().println("You entered the following: " + userInput);
			
			console.writer().println();
			console.writer().println();
			
			console.format("It (format method) uses the default system locale to establish the formatter!");
			
			/**
			 * Note that the Console class defines only one format() and printf() methods, it does 
			 * not define them in other way which take a locale variable.
			 * In this manner, it uses the default system locale to establish the formatter. Of course, 
			 * you could always use a custom locale by retrieving the Writer object and passing your own 
			 * locale instance such as in the following example...
			 */
			console.writer().format(new Locale("fr", "CA"), "Hello world!");
		} else {
			System.out.println("If it's null then text interactions is not supported in your current environment!");
		}
	}
}
