package br.com.willianantunes.examocp;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
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
		// referencesFun();
		// myMapTests();
		myListTests();	
	}
	
	public static void myMapTests() {
		Map<String, Integer> counts = new HashMap<>();
		counts.put("Jenny", 1);		
		System.out.println(counts); // {Jenny=1}
		counts.computeIfPresent("Jenny", (k, v) -> {v=v*10; return v; });
		System.out.println(counts); // {Jenny=10}
		counts.computeIfAbsent("Sam", (x) -> { System.out.println("Value of x: " + x); return 1;}); // Value of x: Sam
		System.out.println(counts); // {Jenny=10, Sam=1}
		counts.computeIfAbsent("Sam", (x) -> { System.out.println("Value of x: " + x); return 5;});		
		System.out.println(counts); // {Jenny=10, Sam=1}
		
		System.out.println();
		System.out.println();
		
		counts.putIfAbsent("Jenny", 1);
		System.out.println(counts); // {Jenny=10, Sam=1}
		counts.putIfAbsent("Antunes", 1);
		System.out.println(counts); // {Jenny=10, Antunes=1, Sam=1}
		
		System.out.println();
		System.out.println();
		
		counts.merge("Leitão", 10, (k, v) -> { System.out.println("k and v: " + k + " / " + v); return 10;});
		System.out.println(counts); // {Leitão=10, Jenny=10, Antunes=1, Sam=1}
		counts.merge("Leitão", 10, (k, v) -> { System.out.println("k and v: " + k + " / " + v); return 5;});
		System.out.println(counts); // {Leitão=5, Jenny=10, Antunes=1, Sam=1}
	}
	
	public static void myListTests() {
		List<String> list = new ArrayList<>(Arrays.asList("Yu Yu Hakusho", "Urameshi", "Kuwabara"));
		System.out.println(list);		
		list.removeIf((s) -> s.equals("Urameshi"));
		System.out.println(list);
		list.replaceAll((s) -> s + " concat it!");
		System.out.println(list);
		
		System.out.println();
		list.forEach(System.out::println);
	}
	
	public static void referencesFun() {
  		Predicate<String> myPredicate = String::isEmpty;
  		String myString = "abc";
  		Predicate<String> myPredicate2 = myString::startsWith;
  		
  		List<String> cats = Arrays.asList("Annie", "Ripley");
  		Consumer<String> myConsumer = System.out::println;
  		cats.forEach(c -> System.out.println(c));
  		System.out.println();
  		cats.forEach(myConsumer);		
  		
  		Map<String, String> favorites = new HashMap<>();
  		favorites.put("Jenney", "Bus tour");
  		favorites.put("Jenney", "My test");
  		System.out.println(favorites);

  		System.out.println("11111".compareTo("1111"));
  		

		
  		List<?> list = new Stack<String>();
  		MethodReference.showSize(list);
  		
  		
  		List<? extends Exception> listEx = new LinkedList<IOException>();
  		MethodReference.showSize(listEx);
  		
  		MethodReference m1 = new MethodReference();
  		MethodReference m2 = new MethodReference();		
	}
	
	public static void showSize(List<?> list) {
		System.out.println(list.size());
	}
}
