package br.com.willianantunes.examocp;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.*;

public class CollectingIntoMaps {
	public static void main(String args[]) {
		// exampleTwo();
		
		Stream<String> myStream = Stream.of("Willian", "Vitor", "Edinaura", "Wellington");
		List<Integer> myList = myStream.filter(x -> x.startsWith("W"))
			.map(String::length)
			.collect(Collectors.toList());
			// .forEach(System.out::println);;		
		myList.forEach(System.out::println);
		
		System.out.println(Stream.iterate(1, x -> ++x)
			.limit(5)
			.map(x -> x + "")
			.collect(Collectors.joining()));;
			
		List<Integer> l = IntStream.range(1, 6)
				.mapToObj(i -> i)
				.collect(Collectors.toList());
		l.forEach(System.out::println);
			
	}
	
	public static void exampleOne() {
		Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
		// Map<String, Integer> map = ohMy.collect(Collectors.toMap(s -> s, String::length));
		Map<String, Integer> map = ohMy.collect(Collectors.toMap(Function.identity(), String::length));
		System.out.println(map);
	}
	
	public static void exampleTwo() {
		Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
		TreeMap<Integer, String> map = ohMy.collect(Collectors
				.toMap(String::length, 
						k -> k,
						(s1, s2) -> s1 + "," + s2,
						TreeMap::new));
		System.out.println(map);
	}
}
