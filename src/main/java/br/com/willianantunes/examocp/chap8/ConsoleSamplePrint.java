package br.com.willianantunes.examocp.chap8;

import java.io.Console;

public class ConsoleSamplePrint {
	public static void main(String args[]) {
		Console console = System.console();
		
		if (console != null) {
			console.writer().println("Welcome to our Zoo!");
			console.format("Our zoo has 391 animals and employs 25 people.");
			console.writer().println();
			console.printf("The zoo spans 128.91 acres.");
		} else {
			System.out.println("If it's null then text interactions is not supported in your current environment!");
		}
	}
}
