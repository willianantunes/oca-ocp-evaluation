package br.com.willianantunes.examocp.chap9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.stream.Stream;

public class walkingDirectory {
	public static void main(String args[]) {
		// showingAllJavaFiles();
		// searchingADirectory();
		// listingDirectoryContent();
		// printingFileContents();
		printingFileContentsTwo();
	}
	
	public static void showingAllJavaFiles() {
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
	
	public static void searchingADirectory() {
		Path path = Paths.get("..\\").toAbsolutePath().normalize();
		long dateFilter = 1420070400000l; // 2015-01-01T00:00:00Z
		// System.out.println(Instant.ofEpochMilli(dateFilter));
		
		try {
			Stream<Path> stream = Files.find(path, 10, 
					(p, a) -> p.toString().endsWith(".java") && a.lastModifiedTime().toMillis() > dateFilter);
			
			stream.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void listingDirectoryContent() {
		Path path = Paths.get("C:\\xampp\\htdocs\\resale-portal\\app\\views");
		
		try {
			/**
			 * It searches only one level deep and is 
			 * analogous to java.io.File.listFiles(), except that it relies 
			 * on streams.
			 */
			Files.list(path)
				.filter(p -> !Files.isDirectory(p)) // show only files
				.filter(p -> Files.isDirectory(p)) // show only directories
				.map(p -> p.toAbsolutePath())
				.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Files.readAllLines() always load the entire file in memory. 
	 * So using it to read a very large file could result in an OutOfMemoryError problem.
	 * Files.lines(path) does not suffer from this same issue. The contents of the file are 
	 * read and processed lazily, which means that only a small portion of the file 
	 * is stored in memory at any given time.
	 */
	public static void printingFileContents() {
		Path path = Paths.get("C:\\xampp\\catalina_service.bat");
		
		try {
			Files.lines(path).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void printingFileContentsTwo() {
		Path path = Paths.get("C:\\Users\\Willian\\Development\\git\\oca-ocp-evaluation\\catalina.2017-01-08.log");
		
		try {
			Files.lines(path)
				.filter(s -> s.startsWith("INFO: Starting"))
				.map(s -> s.substring(15))
				.forEach(System.out::println);
			/**
			 * OUTPUT:
service Catalina
Servlet Engine: Apache Tomcat/7.0.56
ProtocolHandler ["http-bio-8080"]
ProtocolHandler ["ajp-bio-8009"]
			 */
			
			Files.lines(path)
			.filter(s -> s.startsWith("INFO: Starting"))
			.forEach(System.out::println);
			
			/**
			 * OUTPUT:
INFO: Starting service Catalina
INFO: Starting Servlet Engine: Apache Tomcat/7.0.56
INFO: Starting ProtocolHandler ["http-bio-8080"]
INFO: Starting ProtocolHandler ["ajp-bio-8009"]
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
