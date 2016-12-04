package br.com.willianantunes.examocp.chap8;

import java.io.File;

public class ReadFileInformation {
	public static void main(String[] args) {
		// File file = new File("C:\\Users\\Willian\\Development\\tools\\eclipse-neon-R\\eclipse.exe");
		File file = new File("C:\\Users\\Willian\\Development\\tools\\eclipse-neon-R");
		System.out.println("File exists: " + file.exists());
		
		if (file.exists()) {
			System.out.println("Absolute path: " + file.getAbsolutePath());
			System.out.println("Is directory: " + file.isDirectory());
			System.out.println("Parent path: " + file.getParent());
			if (file.isFile()) {
				System.out.println("File size: " + file.length());
				System.out.println("File LastModified: " + file.lastModified());
			} else {
				for (File subfile: file.listFiles()) {
					System.out.println("\t" + subfile.getName());
				}
			}
		}
		
		/**
		 * Sample of output for a file:

File exists: true
Absolute path: C:\Users\Willian\Development\tools\eclipse-neon-R\eclipse.exe
Is directory: false
Parent path: C:\Users\Willian\Development\tools\eclipse-neon-R
File size: 319984
File LastModified: 1465848114000

		 * Sample of output for a folder:

File exists: true
Absolute path: C:\Users\Willian\Development\tools\eclipse-neon-R
Is directory: true
Parent path: C:\Users\Willian\Development\tools
	.eclipseproduct
	artifacts.xml
	configuration
	dropins
	eclipse.exe
	eclipse.ini
	eclipsec.exe
	features
	p2
	plugins
	readme


		 */
	}
}
