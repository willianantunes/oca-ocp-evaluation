package br.com.willianantunes.examocp.chap9;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathFilePathTest {
	public static void printPathInformation(Path path) {
		System.out.println("Filename is: " +  path.getFileName());
		System.out.println("Root is: " + path.getRoot());
		
		Path currentPath = path;
		while((currentPath = currentPath.getParent()) != null) {
			System.out.println("Current parent is: " + currentPath);
		}
	}
	
	public static void main(String args[]) {
		printPathInformation(Paths.get("C:\\Users\\Willian\\Pictures\\Tmp\\103.jpg"));
		
		/**
		 * OUTPUT:
Filename is: 103.jpg
Root is: C:\
Current parent is: C:\Users\Willian\Pictures\Tmp
Current parent is: C:\Users\Willian\Pictures
Current parent is: C:\Users\Willian
Current parent is: C:\Users
Current parent is: C:\
		 */
		
		System.out.println();
		System.out.println();
		
		printPathInformation(Paths.get("zoo.log"));
		
		/**
		 * OUTPUT:
Filename is: zoo.log
Root is: null
		 */
	}
}
