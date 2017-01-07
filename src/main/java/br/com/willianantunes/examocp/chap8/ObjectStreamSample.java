package br.com.willianantunes.examocp.chap8;

import java.io.*;
import java.util.*;

class Animal implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	private char type;
	
	public Animal() {
		
	}

	public Animal(String name, int age, char type) {
		this.name = name;
		this.age = age;
		this.type = type;
	}

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public int getAge() { return age; }
	public void setAge(int age) { this.age = age; }

	public char getType() { return type; }
	public void setType(char type) { this.type = type; }

	@Override
	public String toString() {
		return "Animal [name=" + name + ", age=" + age + ", type=" + type + "]";
	}
}

public class ObjectStreamSample {
	public static List<Animal> getAnimals(File dataFile) {
		List<Animal> animals = new ArrayList<>();
		
		try (ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(
						new FileInputStream(dataFile)))) {
			while (true) {
				Object object = ois.readObject();
				if (object instanceof Animal) {
					animals.add((Animal)object);
				}
			}
		} catch (ClassNotFoundException | EOFException | FileNotFoundException e) {
			if (e instanceof EOFException)
				System.out.println("File end reached!");
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		
		return animals;
	}
	
	public static void createAnimalsFile(List<Animal> animals, File dataFile) {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(
						new FileOutputStream(dataFile)))) {
			for (Animal animal: animals) {
				out.writeObject(animal);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		List<Animal> animals = new ArrayList<>();
		animals.add(new Animal("Tommy Tiger", 5, 'T'));
		animals.add(new Animal("Peter Penguin", 8, 'P'));
		File dataFile = new File("animal.data");
		
		createAnimalsFile(animals, dataFile);
		System.out.println(getAnimals(dataFile)); // [Animal [name=Tommy Tiger, age=5, type=T], Animal [name=Peter Penguin, age=8, type=P]]
	}
}
