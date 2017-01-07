package br.com.willianantunes.examocp.chap8;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterSample {
	public static void main(String[] args){
		File source = new File("zoo.log");
		
		try(PrintWriter out = new PrintWriter(
				new BufferedWriter(new FileWriter(source)))) {
			out.print("Today's weather is: ");
			out.println("Sunny");
			
			out.print("Today's temperature at the zoo is: ");
			out.print("1/3.0");
			out.println('C');
			
			out.format("It has rained 10.12 inches this year");
			out.println();
			out.printf("It may rain 21.2 inches this year");
			
			/**
			 * OUTPUT:
1:Today's weather is: Sunny
2:2Today's temperature at the zoo is: 1/3.0C
3:It has rained 10.12 inches this year
4:It may rain 21.2 inches this year
			 *
			 * If we have added out.println() at the end, it would have 5 lines
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
