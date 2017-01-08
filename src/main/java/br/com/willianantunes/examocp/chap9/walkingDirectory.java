package br.com.willianantunes.examocp.chap9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class walkingDirectory {
	public static void main(String args[]) {
		 Path path = Paths.get("..\\").toAbsolutePath().normalize();
		 System.out.println(path); // C:\Users\Willian\Development\git
		 
		 try {
			 // It will find all *.java files contained in the provided Path object
			 Files.walk(path)
			 	.filter(p -> p.toString().endsWith(".java"))
			 	.forEach(System.out::println);
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	}
}
