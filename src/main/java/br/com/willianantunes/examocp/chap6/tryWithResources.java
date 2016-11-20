package br.com.willianantunes.examocp.chap6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class tryWithResources {
	public static void main(String args[]) {
		
	}
	
	public static void oldApproach(Path path1, Path path2) throws IOException {
		System.out.println("------- oldApproach");
		
		BufferedReader in = null;
		BufferedWriter out = null;
		
		try {
			in = Files.newBufferedReader(path1);
			out = Files.newBufferedWriter(path2);
			out.write(in.readLine());
		} finally {
			if (in != null) in.close();
			if (out != null) out.close(); 
		}
		
		System.out.println();
	}
	
	public static void newApproach(Path path1, Path path2) throws IOException {
		System.out.println("------- newApproach");
		
		// Try-with-resources statement automatically closes all resouces opened in the try clause.
		// This feature is also known as AUTOMATIC RESOURCE MANAGEMENT.
		// Obs.: The finally clause exists implicitly.
		try (BufferedReader in = Files.newBufferedReader(path1);
				BufferedWriter out = Files.newBufferedWriter(path2)) {
			out.write(in.readLine());
		}
		
		/*
		 * It's still allowed to have catch and/or finnaly blocks.
		 * They run in addition to the implicit one. 
		 * The implicit finally block runs before any programmer-coded ones.
		 */		
		try (BufferedReader in = Files.newBufferedReader(path1);
				BufferedWriter out = Files.newBufferedWriter(path2)) {
			out.write(in.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Hã?!");
		}
		
		System.out.println();
	}
}
