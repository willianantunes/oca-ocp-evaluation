package br.com.willianantunes.examocp.chap4;

import java.util.stream.*;
import java.util.*;
import java.util.function.Function;

public class GroupingPartitioningCollectors {
	public static void main(String args[]) {
		// joiningFun();
		// countingFun();
		// averageFun();
		// filteringAndConvertingFun();
		// mapFun();
		// groupingFun();
		partitioningFun();
	}
	
	public static void joiningFun() {
		System.out.println("------- joiningFun");
		Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
		String result = ohMy.collect(Collectors.joining(", "));
		System.out.println(result); // lions, tigers, bears
		System.out.println("");
	}
	
	public static void countingFun() {
		System.out.println("------- countingFun");
		Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
		Long result = ohMy.collect(Collectors.counting());
		System.out.println(result); // 3
		System.out.println("");
	}
	
	public static void averageFun() {
		System.out.println("------- averageFun");
		Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
		Double result = ohMy.collect(Collectors.averagingInt(String::length));
		System.out.println(result); // 5.333333333333333
		System.out.println("");
	}
	
	public static void filteringAndConvertingFun() {
		System.out.println("------- filteringAndConvertingFun");
		Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
		TreeSet<String> result = ohMy.filter(x -> x.startsWith("b"))
				.collect(Collectors.toCollection(TreeSet::new));
		System.out.println(result); // [bears]
		
		Stream<String> ohMy2 = Stream.of("Fayol", "Edison", "Henry");
		Set<String> result2 = ohMy2.filter(x -> x.toLowerCase().startsWith("f"))
				.collect(Collectors.toSet());
		System.out.println(result2); // [Fayol]
		
		System.out.println("");
	}
	
	public static void mapFun() {
		System.out.println("------- mapFun");
		Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
		
		// About toMap method, the first parameter tells de collector how to create the key.
		// The second tells the collector how to create the value.		
		// Map<String, Integer> map = ohMy.collect(Collectors.toMap(k -> k, String::length));
		Map<String, Integer> map = ohMy.collect(Collectors.toMap(Function.identity(), String::length));
		System.out.println(map); // {lions=5, bears=5, tigers=6}
		
		// Reverse the map the length of the animal name to the name itself.
		Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears");
		// Page 219
		// Map<Integer, String> map2 = ohMy2.collect(Collectors.toMap(String::length, value -> value)); // BAD!
		Map<Integer, String> map2 = ohMy2.collect(Collectors
				.toMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2));
		System.out.println(map2); // {5=lions,bears, 6=tigers}
		
		// Page 220
		// The code above returns a HashMap and this behavior is not guaranteed.
		// If we want to mandate a certain type like TreeMap instead, it can be done as the following.
		Stream<String> ohMy3 = Stream.of("lions", "tigers", "bears");
		TreeMap<Integer, String> map3 = ohMy3.collect(Collectors
				.toMap(String::length, // How the key is mapped
						k -> k, // How the value is mapped 
						(s1, s2) -> s1 + ", " + s2, // If contains duplicate keys, how their values is merged
						TreeMap::new)); // The type which is used to return
		System.out.println("map3: " + map3); // map3: {5=lions, bears, 6=tigers}
				
		System.out.println("");
	}
	
	public static void groupingFun() {
		System.out.println("------- groupingFun");
		Stream<String> myStream = Stream.of("lions", "tigers", "bears");
		Map<Integer, List<String>> map = myStream.collect(Collectors.groupingBy(String::length));
		System.out.println(map); // {5=[lions, bears], 6=[tigers]}
		
		// If a LIST is not good as the value in the map, it's possible to set another one as SET
		Stream<String> myStream2 = Stream.of("lions", "tigers", "bears");
		Map<Integer, Set<String>> map2 = myStream2.collect(Collectors
				.groupingBy(String::length, Collectors.toSet()));
		System.out.println(map2); // {5=[lions, bears], 6=[tigers]}
		
		// We can even change the type of Map returned through yet another parameter
		Stream<String> myStream3 = Stream.of("lions", "tigers", "bears");
		TreeMap<Integer, Set<String>> map3 = myStream3.collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toSet()));
		System.out.println(map3); // {5=[lions, bears], 6=[tigers]}
		
		Stream<String> myStream4 = Stream.of("lions", "tigers", "bears");
		TreeMap<Integer, List<String>> map4 = myStream4.collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList()));
		System.out.println(map4); // {5=[lions, bears], 6=[tigers]}
		
		// Grouping the length  of the animal name to see how many of each length we have
		Stream<String> myStream5 = Stream.of("lions", "tigers", "bears");
		Map<Integer, Long> map5 = myStream5.collect(Collectors.groupingBy(String::length, Collectors.counting()));
		System.out.println("how many of each length we have: " + map5); // how many of each length we have: {5=2, 6=1}
		
		// Suppose that we wanted to get the first letter of the first animal alphabetically of each length.
		// It can be done as the following...
		Stream<String> myStream6 = Stream.of("lions", "tigers", "bears");
		Map<Integer, Optional<Character>> map6 = myStream6.collect(Collectors.groupingBy(String::length, 
				Collectors.mapping(s -> s.charAt(0), 
						Collectors.minBy(Comparator.naturalOrder()))
				));
		
		System.out.println("First letter of the first animal \r\n"
				+ "alphabetically of each length: " + map6); // alphabetically of each length: {5=Optional[b], 6=Optional[t]}
	}
	
	/**
	 * There are only two possible groups (true and false). 
	 * Partitioning is like splitting a list into two parts.
	 */
	public static void partitioningFun() {
		System.out.println("------- partitioningFun");
		Stream<String> myStream = Stream.of("lions", "tigers", "bears");
		Map<Boolean, List<String>> map = myStream.collect(Collectors.partitioningBy(s -> s.length() <= 5));
		System.out.println(map); // {false=[tigers], true=[lions, bears]}
		
		Stream<String> myStream2 = Stream.of("lions", "tigers", "bears");
		Map<Boolean, Set<String>> map2  = myStream2.collect(Collectors.partitioningBy(s -> s.length() <= 5, 
				Collectors.toSet()));
		System.out.println(map2); // {false=[tigers], true=[lions, bears]}
		
		System.out.println("");
	}
}
