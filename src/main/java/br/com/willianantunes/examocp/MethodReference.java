package br.com.willianantunes.examocp;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MethodReference {
	public static void main(String[] args) {
//		Predicate<String> myPredicate = String::isEmpty;
//		String myString = "abc";
//		Predicate<String> myPredicate2 = myString::startsWith;
//		
//		List<String> cats = Arrays.asList("Annie", "Ripley");
//		Consumer<String> myConsumer = System.out::println;
//		cats.forEach(c -> System.out.println(c));
//		System.out.println();
//		cats.forEach(myConsumer);		
//		
//		Map<String, String> favorites = new HashMap<>();
//		favorites.put("Jenney", "Bus tour");
//		favorites.put("Jenney", "My test");
//		System.out.println(favorites);

		System.out.println("11111".compareTo("1111"));
		
//		Map<String, Integer> counts = new HashMap<>();
//		counts.put("Jenny", 1);
//		
//		System.out.println(counts);
//		counts.computeIfPresent("Jenny", (k, v) -> {v=v*10; return v; });
//		System.out.println(counts);
//		counts.computeIfAbsent("Sam", (x) -> { System.out.println("Value of x: " + x); return 1;});
//		System.out.println(counts);
//		
//		List<?> list = new Stack<String>();
//		MethodReference.showSize(list);
//		
//		
//		List<? extends Exception> listEx = new LinkedList<IOException>();
//		MethodReference.showSize(listEx);
		
		MethodReference m1 = new MethodReference();
		MethodReference m2 = new MethodReference();
		
		
	}
	
	public static void showSize(List<?> list) {
		System.out.println(list.size());
	}
}
