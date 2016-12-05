package br.com.willianantunes.examocp.chap8;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.charset.Charset;

public class QuickStart {
	public static void main(String[] args) {
		// atGlance();
		// readingFun();
		// markingStreamFun();
		// skippingOverDataFun();
		charactedEncodingFun();
	}
	
	public static void atGlance() {
		System.out.println(System.getProperty("file.separator"));
		
		System.out.println();
		
		File myFolder = new File("C:\\Users\\Willian\\Desktop\\ocp-tests");
		System.out.println(myFolder.exists());
		
		File myFile = new File("C:\\Users\\Willian\\Desktop\\ocp-tests\\zoo.txt");
		System.out.println(myFile.exists());
	}
	
	public static void readingFun() {
		/**
		 * FileReader is the low-level stream reader, whereas BufferedReader is the high-level 
		 * stream that takes a FileReader as input.
		 */
		try (BufferedReader bufferedReader = new BufferedReader(new 
				FileReader("C:\\Users\\Willian\\Development\\tools\\eclipse-neon-R\\eclipse.ini"))) {
			System.out.println(bufferedReader.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		/**
		 *  High-level streams can take other high-level streams as input like the code below shows.
		 *  FileInputStream is the low-level stream that interacts directly with the file, which is 
		 *  wrapped by a high-level BufferedInputStream to improve performance. Finally, the entire 
		 *  object is wrapped by a high-level ObjectInputStream, which allow us to filter the data 
		 *  as Java Objects.
		 */
		try (ObjectInputStream objectInputStream = new ObjectInputStream(
				new BufferedInputStream(
						new FileInputStream("C:\\Users\\Willian\\Development\\tools\\eclipse-neon-R\\eclipse.ini")))) {
			System.out.println(objectInputStream.readObject());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void markingStreamFun() {
		try(InputStream is = new FileInputStream("C:\\Users\\Willian\\Development\\tools\\eclipse-neon-R\\eclipse.ini")) {
			/**
			 * FILE CONTENT:

					-startup
					plugins/org.eclipse.equinox.launcher_1.3.200.v20160318-1642.jar
					--launcher.library
					plugins/org.eclipse.equinox.launcher.win32.win32.x86_64_1.1.400.v20160518-1444
					-product
					org.eclipse.epp.package.jee.product
					--launcher.defaultAction
					openFile
					-showsplash
					org.eclipse.platform
					--launcher.defaultAction
					openFile
					--launcher.appendVmargs
					-vmargs
					-Dosgi.requiredJavaVersion=1.8
					-XX:+UseG1GC
					-XX:+UseStringDeduplication
					-Dosgi.requiredJavaVersion=1.8
					-Xms256m
					-Xmx1024m

			 */
			System.out.println((char)is.read()); // -
			/**
			 * IN MY CASE MARK IS NOT SUPPORTED because I would need to try-with-resource using the following:
			 * try(BufferedInputStream is = new BufferedInputStream(new FileInputStream("C:\\Users\\Willian\\Development\\tools\\eclipse-neon-R\\eclipse.ini")))
			 * 
			 * If I use the example above, the output would be the following:
			 * -
			 * s
			 * t
			 * s
			 * t
			 * a  
			 */
			if (is.markSupported()) {
				/**
				 *  The readlimit arguments tells this input stream to allow that many bytes (my case 100) 
				 *  to be read before the mark position gets invalidated.
				 */
				is.mark(100);  
				System.out.println((char)is.read());
				System.out.println((char)is.read());
				is.reset();
			}
			System.out.println((char)is.read()); // s
			System.out.println((char)is.read()); // t
			System.out.println((char)is.read()); // a
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void skippingOverDataFun() {
		try(BufferedInputStream is = new BufferedInputStream(new FileInputStream("C:\\Users\\Willian\\Development\\tools\\eclipse-neon-R\\eclipse.ini"))) {			
			/**
			 * FILE CONTENT:

					-startup
					plugins/org.eclipse.equinox.launcher_1.3.200.v20160318-1642.jar
					--launcher.library
					plugins/org.eclipse.equinox.launcher.win32.win32.x86_64_1.1.400.v20160518-1444
					-product
					org.eclipse.epp.package.jee.product
					--launcher.defaultAction
					openFile
					-showsplash
					org.eclipse.platform
					--launcher.defaultAction
					openFile
					--launcher.appendVmargs
					-vmargs
					-Dosgi.requiredJavaVersion=1.8
					-XX:+UseG1GC
					-XX:+UseStringDeduplication
					-Dosgi.requiredJavaVersion=1.8
					-Xms256m
					-Xmx1024m

			 */			
			
			System.out.println((char)is.read()); // -
			is.skip(2); // It will skip 2 bytes, therefore: st
			System.out.println((char)is.read()); // a
			System.out.println((char)is.read()); // r
			System.out.println((char)is.read()); // t
			System.out.println((char)is.read()); // u
			System.out.println((char)is.read()); // p
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void charactedEncodingFun() {
		Charset usAsciiCharset = Charset.forName("US-ASCII");
		Charset utf8Charset = Charset.forName("UTF-8");
		Charset utf16Charset = Charset.forName("UTF-16");
		
		System.out.println("### Charsets availables:");
		Charset.availableCharsets().forEach((s, c) -> System.out.println(s));
	}
}
