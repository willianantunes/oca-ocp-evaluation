package br.com.willianantunes.examocp.chap4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.*;

public class IntermediateFunPlayGround {
	public static void main(String args[]) {
		// myFilterFun();
		// myDistinctFun();
		// myLimitAndSkipFun();
		// myMapFun();
		// myFlatMapFun();
		// mySortedFun();
		myPeekFun();
	}
	
	public static void myFilterFun() {
		System.out.println("------- myFilterFun");
		Stream<String> myStream = Stream.of("monkey", "gorilla", "bonobo");
		myStream.filter(x -> x.startsWith("m")).forEach(System.out::println); // monkey
		System.out.println("");
	}
	
	public static void myDistinctFun() {
		System.out.println("------- myDistinctFun");
		Stream<String> myStream = Stream.of("duck", "duck", "duck", "goose");
		myStream.distinct().forEach(System.out::println);
		/**
		 * OUTPUT:
duck
goose
		 */
		System.out.println("");
	}
	
	public static void myLimitAndSkipFun() {
		System.out.println("------- myLimitAndSkipFun");
		Stream<Integer> myStreamInt = Stream.iterate(1, n -> n + 1);
		myStreamInt.limit(10).forEach(System.out::println);
		/**
		 * OUTPUT:
1
2
3
4
5
6
7
8
9
10
		 */
		System.out.println("# Now skiping 5 and limiting to 2");
		Stream<Integer> myStreamInt2 = Stream.iterate(1, n -> n + 1);
		myStreamInt2.skip(5).limit(2).forEach(System.out::println);
		/**
		 * OUTPUT:
6
7
		 */
		System.out.println("");
	}
	
	/**
	 * It creates a one-to-one mapping from the elements in the stream to the 
	 * elements of the next step in the stream. It's for transforming data.
	 */
	public static void myMapFun() {
		System.out.println("------- myMapFun");
		// Converts a list of String objects to a list of Integer
		Stream<String> myStream = Stream.of("monkey", "gorilla", "bonobo");
		myStream.map(String::length).forEach(System.out::print); // 676
		
		System.out.println();
		
		Stream<String> myStream2 = Stream.of("monkey", "gorilla", "bonobo");
		Set<Integer> mySet = myStream2.map(String::length).collect(HashSet::new, HashSet::add, HashSet::addAll);
		System.out.println(mySet); // Only two because there are two objects with 6 in their length. Output: [6, 7]
		System.out.println("");
	}
	
	/**
	 * Take each element in the stream and make any elements it contains top-level elements in a single stream.
	 */
	public static void myFlatMapFun() {
		System.out.println("------- myFlatMapFun");
		
		List<String> zero =  Arrays.asList();
		List<String> one = Arrays.asList("Bonobo");
		List<String> two = Arrays.asList("Mama Gorilla", "Baby Gorilla");
		
		Stream<List<String>> animals = Stream.of(zero, one, two);
		animals.flatMap(l -> l.stream()).forEach(System.out::println);
		/**
		 * OUTPUT:
Bonobo
Mama Gorilla
Baby Gorilla
		 */
		System.out.println("");
	}
	
	/**
	 * It uses natural order (Comparable) unless we specify a comparator.
	 */
	public static void mySortedFun() {
		System.out.println("------- mySortedFun");
		Stream<String> myStream = Stream.of("brown bear-", "grizzly-");
		Stream<String> myStream2 = Stream.of("grizzly-", "brown bear-");
		myStream.sorted(Comparator.reverseOrder()).forEach(System.out::print); // grizzly-brown bear-
		System.out.println("");
		
		myStream2.sorted().forEach(System.out::print); // brown bear-grizzly-
		System.out.println("");
	}
	
	/**
	 * It's useful for debugging purpose.
	 * The most common use for it is to output the contents of the 
	 * stream as it goes by.
	 */
	public static void myPeekFun() {
		System.out.println("------- myPeekFun");
		Stream<String> myStream = Stream.of("black", "bear", "brown bear", "grizzly");
		long count = myStream.filter(s -> s.startsWith("g"))
				.peek(System.out::println).count(); // grizzly
		System.out.println(count); // 1
		System.out.println("");
	}
}
