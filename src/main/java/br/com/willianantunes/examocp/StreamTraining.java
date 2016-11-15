package br.com.willianantunes.examocp;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTraining {
	
	public static <T> T myTestMethod(T t, T t1, T t2) {
		return null;
	}
	
	public static void main(String[] args) {
		/*
		Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
		// System.out.println(s.count()); // If you leave it uncommented, the s.min won't work
		// It finds the animal with the fewest letter in its name
		Optional<String> min = s.min((s1, s2) -> s1.length() - s2.length());
		min.ifPresent(System.out::println);
		*/
		
		
		/*
		Stream<String> s2 = Stream.of("monkey", "gorilla", "bonobo");
		Stream<String> infinite = Stream.generate(() -> "chimp");
		s2.findAny().ifPresent(System.out::println);
		infinite.findAny().ifPresent(System.out::println);
		 */
		
		// reduceExample();
		mapExample();
		// flatMapExample();
		// peekingBegindScenes();
		// intStreamExample();
		
		
		/*
		Thread.sleep(1000*10); // 2 seconds
		
		Supplier<Double> myDoubleSupplier = () -> Math.random();
		// Stream<Double> randoms = Stream.generate(myDoubleSupplier);
		Stream<Double> randoms = Stream.generate(Math::random);
		
		Consumer<Double> myDoubleConsumer = d -> System.out.println(d);
		// randoms.forEach(myDoubleConsumer);
		randoms.forEach(System.out::println);
		
		UnaryOperator<Integer> myFunction = x -> x*2;
		Stream<Integer> oddNumbers = Stream.iterate(1, myFunction);
		*/
	}
	
	public static void reduceExample() {
		// "Traditional" way of doing things
		String[] array = new String[] { "w", "o", "l", "f" };
		String result = "";
		for (String s : array) {
			result += s;
		}
		System.out.println(result);
		
		System.out.println("----------");
		
		// With lambdas
		Stream<String> stream = Stream.of("w", "o", "l", "f");
		// String word = stream.reduce("", (s, c) -> s + c);
		// String word = stream.reduce("", String::concat);
		// System.out.println(word);
		Optional<String> wordOpt = stream.reduce(String::concat);
		wordOpt.ifPresent(System.out::println);
	}
	
	public static void collectExample() {
		Collectors.toSet();
		Collectors.toCollection(TreeSet::new);
	}
	
	public static void mapExample() {
		Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
		s.map(String::length).forEach(System.out::println);
	}
	
	public static void flatMapExample() {
		List<String> zero = Arrays.asList();
		List<String> one = Arrays.asList("Bobo");
		List<String> two = Arrays.asList("Mama Gorilla", "Baby Gorilla");
		Stream<List<String>> animals = Stream.of(zero, one, two); 
		
		animals.flatMap(l -> l.stream()).forEach(System.out::println);
	}
	
	public static void peekingBegindScenes() {
		Stream<Integer> infinite = Stream.iterate(1, x -> x+1);
		infinite.limit(5)
			// .peek(x -> System.out.println("Current is: " + x))
			.peek(System.out::println)
			.filter(x -> x % 2 == 1)
			.forEach(System.out::println);
		
		Double d = .5;
		System.out.println();
		System.out.println(d);
	}
	
	public static void intStreamExample() {
		IntStream count = IntStream.iterate(1, s -> s+1).limit(5);
		count.forEach(System.out::println);
	}
}
