package br.com.willianantunes.examocp.chap9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class PlaygroundPath {
	public static void main(String args[]) {
		// someSamplesPath();
		// someSamplesFileSystem();
		// workingWithLegacy();
		// subpathFun();
		// relativizeFun();
		// relativizeAndNormalizeFun();
		// checkingFileExistence();
		// filesMethods();
		// movingFiles();
		// readingFun();
		// readingTwoFun();
		// writingFun();
		
		/**
		 *  Other java.nio.file.Files methods:
		 *  - For isDirectory(), isRegularFile() and isSymbolicLink() please see to page 478
		 *  - For isHidden(), isReadable(), isExecutable() and size() see pages 479 and 480
		 */
		
		// lastModifiedFun();
		
		/**
		 * Other java.nio.file.Files methods:
		 * For getOwner(), setOwner() see page 482
		 */
		// ownerFun();
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
	
	public static void resolveAndNormalizeFun() {
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
	
	public static void checkingFileExistence() {
		try {
			System.out.println(Paths.get(".")); // OUTPUT: .
			System.out.println(Paths.get(".").toRealPath()); // OUTPUT: C:\Users\Willian\Development\git\oca-ocp-evaluation
			System.out.println(Paths.get("..\\").toRealPath()); // OUTPUT: C:\Users\Willian\Development\git
			System.out.println(Paths.get("../").toRealPath()); // OUTPUT: C:\Users\Willian\Development\git
			System.out.println("Does \"../\" exist? " + Files.exists(Paths.get("../"))); // OUTPUT: Does "../" exist? false
			System.out.println(Paths.get("../it-does-not-exist.txt").toRealPath()); // OUTPUT: C:\Users\Willian\Development\git
		} catch (IOException e) {
			e.printStackTrace();
			/**
			 * OUTPUT FOR Paths.get("../it-does-not-exist.txt").toRealPath():
java.nio.file.NoSuchFileException: C:\Users\Willian\Development\git\it-does-not-exist.txt
	at sun.nio.fs.WindowsException.translateToIOException(WindowsException.java:79)
	at sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:90)
	at sun.nio.fs.WindowsLinkSupport.getRealPath(WindowsLinkSupport.java:259)
	at sun.nio.fs.WindowsPath.toRealPath(WindowsPath.java:836)
	at sun.nio.fs.WindowsPath.toRealPath(WindowsPath.java:44)
	at br.com.willianantunes.examocp.chap9.PlaygroundPath.checkingFileExistence(PlaygroundPath.java:153)
	at br.com.willianantunes.examocp.chap9.PlaygroundPath.main(PlaygroundPath.java:19)
			 */
		}
	}
	
	public static void filesMethods() {
		System.out.println("Does \"../\" exist? " + Files.exists(Paths.get("../"))); // OUTPUT: Does "../" exist? false
		Path path1 = Paths.get("C:\\Users\\Willian\\Pictures\\Tmp\\103.jpg");
		// C:\Users\Willian\Development\git\oca-ocp-evaluation
		// C:\Downloads\en_windows_server_2012_r2_x64_dvd_2707946.iso
		Path path2 = Paths.get("..\\..\\..\\..\\..\\Downloads\\en_windows_server_2012_r2_x64_dvd_2707946.iso");
		/**
		 * It does not normalize because it is a relative path
		 */
		System.out.println(path2.normalize()); // ..\..\..\..\..\Downloads\en_windows_server_2012_r2_x64_dvd_2707946.iso
		System.out.println(path2.toAbsolutePath()); // C:\Users\Willian\Development\git\oca-ocp-evaluation\..\..\..\..\..\Downloads\en_windows_server_2012_r2_x64_dvd_2707946.iso
		/**
		 * Now it's normalized! :)
		 */
		System.out.println(path2.toAbsolutePath().normalize()); // C:\Downloads\en_windows_server_2012_r2_x64_dvd_2707946.iso
		
		System.out.println();
		System.out.println();
				
		
		try {			
			System.out.println(Files.isSameFile(path1, path2)); // false		
			System.out.println();		
			LocalDate now = LocalDate.now();
			System.out.println(Files.createDirectory(Paths.get("C:\\Downloads\\TESTE-" + now)));		
			System.out.println();
			System.out.println(Files.createDirectories(Paths.get("C:\\Downloads\\A1\\A2\\A3")));
			System.out.println();
			System.out.println(Files
					.copy(Paths.get("C:\\Users\\Willian\\Pictures\\Tmp\\103.jpg"), 
							Paths.get("C:\\Downloads\\TESTE-" + now + "\\103-copied.jpg")));
			System.out.println();
			try(InputStream is = new FileInputStream("source-data.txt");
					OutputStream out = new FileOutputStream("output-data.txt")) {
				// Copy stream data to file
				/**
				 * It supports varargs as the targe is a Path object.
				 */
				Files.copy(is, Paths.get("c:\\Downloads\\wolf.txt"));
				
				// Copy file data to stream
				/**
				 * It does not support varargs as the target is a stream that 
				 * may not represent a file system resource.
				 */
				Files.copy(Paths.get("C:\\Install.log"), out);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void movingFiles() { 
		try {
			// Renaming case
			Files.move(Paths.get("C:\\Downloads\\test"), Paths.get("C:\\Downloads\\test-" + LocalDate.now()));
			
			// Move case
			Files.move(Paths.get("C:\\Downloads\\test-" + LocalDate.now()), Paths.get("C:\\Downloads\\myFolder\\test-" + LocalDate.now()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readingFun() {
		Path path = Paths.get("C:\\Install.log");
		
		// Files.newBufferedReader(path) = Files.newBufferedReader(path, StandardCharsets.UTF_8)
		try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("US-ASCII"))) {
			// Read from the stream
			String currentLine = null;
			while ((currentLine = reader.readLine()) != null) {
				System.out.println(currentLine);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readingTwoFun() {
		Path path = Paths.get("C:\\Install.log");
		
		try {
			/**
			 * All the content of the file is stored in the memory at once. 
			 * Therefore, if the file is significantly large, you may encounter an 
			 * OutOfMemoryError trying to load all of it at once.
			 */
			final List<String> lines = Files.readAllLines(path);
			for (String line: lines) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public static void writingFun() {
		Path path = Paths.get("my-written-file.txt");
		try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-16"))) {
			writer.write("Hello world!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void lastModifiedFun() {
		final Path path = Paths.get("C:\\Users\\Willian\\Pictures\\Tmp\\103.jpg");
		
		try {
			// Gettin it
			System.out.println(Files.getLastModifiedTime(path));
			FileTime myFileTime = Files.getLastModifiedTime(path);
			System.out.println(myFileTime.toMillis());
			
			// Setting it
			Files.setLastModifiedTime(path, FileTime.from(Instant.now()));
			Files.setLastModifiedTime(path, FileTime.fromMillis(Instant.now().toEpochMilli()));
			System.out.println(Files.getLastModifiedTime(path));			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void ownerFun() {
		try {
			UserPrincipal owner = FileSystems.getDefault()
					.getUserPrincipalLookupService()
					.lookupPrincipalByName("administrador");
			System.out.println(owner.getName()); // ANTUNES-SRV\Administrador			
			System.out.println();
			
			final Path path = Paths.get("C:\\Users\\Willian\\Pictures\\Tmp\\103 - Copy.jpg");
			System.out.println(Files.getOwner(path).getName()); // ANTUNES-SRV\Willian
			
			final Path pathAltered = Files.setOwner(path, owner);
			// It may throws java.nio.file.FileSystemException: This security ID may not be assigned as the owner of this object.
			System.out.println(Files.getOwner(pathAltered).getName()); // ANTUNES-SRV\Administrador
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
