package br.com.willianantunes.examocp.chap9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class viewPlayground {
	public static void main(String args[]) {
		// basicFileAttributesSample();
		basicFileAttributesViewSample();
	}	
	
	public static void basicFileAttributesSample() {
		final Path path = Paths.get("C:\\Users\\Willian\\Pictures\\Tmp\\103.jpg");
		
		try {
			BasicFileAttributes data = Files.readAttributes(path, BasicFileAttributes.class);
			System.out.println("Is path a directory? " + data.isDirectory());
			System.out.println("Is path a regular file? " + data.isRegularFile());
			System.out.println("Is path a symbolic link? " + data.isSymbolicLink());
			/**
			 * "Other" can be paths which refer to devices or resources in some file system.
			 */
			System.out.println("Path not a file, directory or symbolic link? " + data.isOther());
			System.out.println("Size (in bytes): " + data.size());
			System.out.println("Create date/time: " + data.creationTime());
			System.out.println("Last modified date/time: " + data.lastModifiedTime());
			System.out.println("Last accessed date/time: " + data.lastAccessTime());
			System.out.println("Unique file identifier (if available): " + data.fileKey());
			
			/**
			 * OUTPUT:
Is path a directory? false
Is path a regular file? true
Is path a symbolic link? false
Path not a file, directory or symbolic link? false
Size (in bytes): 23207
Create date/time: 2016-04-05T23:25:06.903671Z
Last modified date/time: 2017-01-08T20:12:03.084Z
Last accessed date/time: 2016-04-05T23:25:04.937559Z
Unique file identifier (if available): null
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void basicFileAttributesViewSample() {
		final Path path = Paths.get("C:\\Users\\Willian\\Pictures\\Tmp\\103 - Copy.jpg");
		
		BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);
		BasicFileAttributes data = null;
		
		try {
			data = view.readAttributes();
		} catch (IOException e) {
			System.err.println("It wasn't possible to get BasicFileAttributes.");
		}
		
		FileTime lastModifiedTime = FileTime.fromMillis(data.lastModifiedTime().toMillis() + 10_000);
		try {
			view.setTimes(lastModifiedTime, null, null);
		} catch (IOException e) {				
			System.err.println("It wasn't possible to set last modified time to " + path);
		}
	}

}
