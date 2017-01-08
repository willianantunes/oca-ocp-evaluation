package br.com.willianantunes.examocp.chap9;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlaygroundPath {
	public static void main(String args[]) {
		// someSamplesPath();
		// someSamplesFileSystem();
		// workingWithLegacy();
		// subpathFun();
		// relativizeFun();
		relativizeAndNormalizeFun();
	}

	public static void someSamplesPath() {
		Path path1 = Paths.get("zoo.log");
		System.out.println("Is it absolute path? " + path1.isAbsolute());
		System.out.println(path1.toFile().length() + " bytes");
		
		System.out.println();		
		
		Path path2 = Paths.get("C:\\Users\\Willian\\Pictures\\Tmp\\103.jpg");
		System.out.println("Is it absolute path? " + path2.isAbsolute());
		System.out.println(path2.toFile().length() + " bytes");
		
		System.out.println();
		
		Path path3 = Paths.get("c:", "Users", "Willian");
		System.out.println("Is it a directory? " + path3.toFile().isDirectory());
		System.out.println(path3.toFile().length() + " bytes");
		
		System.out.println();
				
		/**
		 * Path path4 = Paths.get(new URI("http://willianantunes.blogspot.com.br/"));
		 * OUTPUT:
Exception in thread "main" java.nio.file.FileSystemNotFoundException: Provider "http" not installed
	at java.nio.file.Paths.get(Paths.java:147)
	at br.com.willianantunes.examocp.chap9.PlaygroundPath.main(PlaygroundPath.java:28)
		 */
		
		Path path5;
		try {
			path5 = Paths.get(new URI("file:///C:/Users/Willian/Pictures/Tmp/123.jpg"));
		System.out.println("Is it a directory? " + path5.toFile().isDirectory());
		System.out.println("Is it a file? " + path5.toFile().isFile());
		System.out.println(path5.toFile().length() + " bytes");
		System.out.println("Schema used by this URI: " + path5.toUri().getScheme()); // FILE
				
		/**
		 * Path path6 = Paths.get(new URI("ftp://localhost:21"));
		 * OUTPUT:
Exception in thread "main" java.nio.file.FileSystemNotFoundException: Provider "ftp" not installed
	at java.nio.file.Paths.get(Paths.java:147)
	at br.com.willianantunes.examocp.chap9.PlaygroundPath.main(PlaygroundPath.java:42)
		 */
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void someSamplesFileSystem() {
		Path path1 = FileSystems.getDefault().getPath("zoo.log");
		System.out.println("Is it absolute path? " + path1.isAbsolute());
		System.out.println("Is it a directory? " + path1.toFile().isDirectory());
		System.out.println("Is it a file? " + path1.toFile().isFile());		
		System.out.println(path1.toFile().length() + " bytes");
	}
	
	public static void workingWithLegacy() {
		File file = new File("C:\\Users\\Willian\\Pictures\\Tmp\\103.jpg");
		System.out.println(file.toPath()); // C:\Users\Willian\Pictures\Tmp\103.jpg
		System.out.println(file.toPath().getFileName()); // 103.jpg
		System.out.println(file.toPath().getNameCount()); // 5
		/**
		 * If the Path object represents the root element itself (for my OS is C:\), 
		 * then the number of names in the Path object returned by getName() will be 0;
		 */
		System.out.println(file.toPath().getName(0)); // Users
		System.out.println(file.toPath().getName(4)); // 103.jpg
		System.out.println(file.toPath().getParent()); // C:\Users\Willian\Pictures\Tmp
		System.out.println(file.toPath().getRoot()); // C:\
		
		System.out.println();
		
		File file2 = new File("zoo.log");
		System.out.println(file2.toPath()); // zoo.log
		System.out.println(file2.toPath().getNameCount()); // 1
		System.out.println(file2.toPath().getParent()); // null
		System.out.println(file2.toPath().getRoot()); // null
		System.out.println(file2.toPath().toAbsolutePath()); // C:\Users\Willian\Development\git\oca-ocp-evaluation\zoo.log
		System.out.println(file2.toPath().toAbsolutePath().getNameCount()); // 6
		System.out.println(file2.toPath().toAbsolutePath().getRoot()); // C:\
	}
	
	public static void subpathFun() {
		Path path = Paths.get("C:\\Users\\Willian\\Pictures\\Tmp\\103.jpg");
		System.out.println("Path is: " + path);
		System.out.println("Subpath from 0 to 3 is: " + path.subpath(0,3)); // Users\Willian\Pictures
		System.out.println("Subpath from 1 to 3 is: " + path.subpath(1,3)); // Willian\Pictures
		// System.out.println("Subpath from 3 to 3 is: " + path.subpath(3,3)); // It throws java.lang.IllegalArgumentException
	}
	
	public static void relativizeFun() {
		Path path1 = Paths.get("zoo.log");
		Path path2 = Paths.get("pom.xml");
		/**
		 * Since our path value POINT TO A FILE, we need 
		 * to move to the parent file which is the directory that 
		 * contains de file.
		 */
		System.out.println(path1.relativize(path2)); // ..\pom.xml
		System.out.println(path2.relativize(path1)); // ..\zoo.log
		
		System.out.println();
		
		Path path3 = Paths.get("C:\\Users\\Willian\\Pictures\\Tmp\\103.jpg");
		Path path4 = Paths.get("C:\\Dic\\WDIC");
		/**
		 * The same we used above applies here.
		 */
		System.out.println(path3.relativize(path4)); // ..\..\..\..\..\Dic\WDIC
		System.out.println(path4.relativize(path3)); // ..\..\Users\Willian\Pictures\Tmp\103.jpg
	}
	
	public static void relativizeAndNormalizeFun() {
		final Path path1 = Paths.get("C:\\Users\\Willian\\..\\Public"); // It means that there is a Public folder/file in Users folder
		final Path path2 = Paths.get("zoo.log");
		System.out.println(path1.resolve(path2)); // C:\Users\Willian\..\Public\zoo.log		
		System.out.println(path1.resolve(path2).normalize()); // C:\Users\Public\zoo.log
		System.out.println();
		System.out.println(path2.resolve(path1)); // C:\Users\Willian\..\Public
		System.out.println(path2.resolve(path1).normalize()); // C:\Users\Public
		
		System.out.println();
		System.out.println();		
	}
}
