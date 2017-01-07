package br.com.willianantunes.examocp.chap8;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PrintStreamAndWriter {
	public static void main(String args[]) {
		System.out.print("Teste 1." + System.getProperty("line.separator")); // For windows it means /r/n (carriege return and line feed).
		System.out.print("Teste 2.");
		System.out.print("Teste 3.");
		
		/**
		 * OUTPUT:
		 * Teste 1.
		 * Teste 2.Teste 3.
		 */
		
		System.out.println();
		System.out.println();
		
		usingPrint();
	}
	
	public static void usingPrint() {
		try {
			PrintWriter out = new PrintWriter("zoo.log");
			
			out.print(5); // PrintWriter method... Prints an integer
			out.write(5); // write(int c) -> Write a single character
			out.write(String.valueOf(5)); // Writes a String
			
			// VALUE OF CALLS THE OBJECT'S TOSTRING METHOD
			
			out.print(2.0); // PrintWriter method... Prints a double-precision floating-point number
			// out.write(2.0); // Does not compile
			out.print(String.valueOf(2.0)); // Writer method
			
			Animal animal = new Animal();
			out.print(animal); // PrintWriter method... Prints an object
			// out.write(animal); // Does not compile
			out.write(animal == null ? "null" : animal.toString());
			out.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
