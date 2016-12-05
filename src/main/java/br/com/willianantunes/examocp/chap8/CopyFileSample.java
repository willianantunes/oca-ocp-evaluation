package br.com.willianantunes.examocp.chap8;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFileSample {
	public static void copy(File source, File destination) {
		try (InputStream is = new FileInputStream(source); 
				OutputStream out = new FileOutputStream(destination)) {
			int b;
			while ((b = is.read()) != -1) {
				out.write(b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void copyUsingByteArray(File source, File destination) {
		try (InputStream is = new BufferedInputStream(new FileInputStream(source)); 
				OutputStream out = new BufferedOutputStream(new FileOutputStream(destination))) {
			/**
			 * For example, if the buffer size is 1024 bytes and the file size is 1054 bytes, 
			 * then the last read will be only  30 bytes.
			 */
			byte[] buffer = new byte[1024];
			int lengthRead;
			while ((lengthRead = is.read(buffer)) > 0) {
				out.write(buffer, 0, lengthRead);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		File source = new File("C:\\Users\\Willian\\Development\\tools\\eclipse-neon-R\\eclipse.ini");
		File destination = new File("ZooCopy.class");
		
		/**
		 * The performance for this code, specially for large files, would not be 
		 * particularly good because the sample does not use any byte arrays.
		 */
		// copy(source, destination);
		
		copyUsingByteArray(source, destination);
		System.out.println(destination.getAbsolutePath()); // C:\Users\Willian\Development\git\oca-ocp-evaluation\ZooCopy.class
	}
}
