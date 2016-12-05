package br.com.willianantunes.examocp.chap8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CopyTextFileSample {
	public static List<String> readFile(File source) {
		List<String> data = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
			String s;
			while((s = reader.readLine()) != null) {
				data.add(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public static void writeFile(List<String> data, File destination) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {
			for (String s: data) {
				writer.write(s);
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		File source = new File("C:\\Users\\Willian\\Development\\tools\\eclipse-neon-R\\eclipse.ini");
		File destination = new File("ZooCopy.ini");
		
		List<String> data = readFile(source);
		for (String record: data) {
			System.out.println(record);
		}
		
		System.out.println("### Now I'm going to write the file in " + destination.getAbsolutePath());
		writeFile(data, destination);
	}
}
