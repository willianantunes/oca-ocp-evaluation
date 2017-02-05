package br.com.willianantunes.examocp.chap4;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminateFunPlayGround {
	public static void main(String[] args) {
		// myCountFun();
		// myMinFun();
		// myMaxFun();
		// myFindAnyAndFirstFun();
		// myAllAnyNoneMatchFun();
		// myReduceFun();
		myCollectFun();
	}
	
	public static void myCountFun() {
		System.out.println("------- myCountFun");
		Stream<String> myStreamString = Stream.of("Willian", "Valeria", "Tozzi", "Lima", "Edinaura", "Leitão");
		// System.out.println(myStreamString.count()); // 6
		myStreamString.mapToInt(String::length).forEach(System.out::print); // 775486
		System.out.println("");
	}
	
	public static void myMinFun() {
		System.out.println("------- myMinFun");
		Stream<String> myStreamString = Stream.of("Monkey", "Ape", "Bonobo");
		// Finds the animal with the fewest letters in its name
		Optional<String> minValue = myStreamString.min((v1, v2) -> { // Comparator as parameter
			System.out.println("v1 / v2 = " + v1 + " / " + v2 + ". "
					+ "v1.length() - v2.length() = " + (v1.length() - v2.length()));
			return v1.length() - v2.length();	
		});
		/**
		 * OUTPUT:
v1 / v2 = Monkey / Ape. v1.length() - v2.length() = 3
v1 / v2 = Ape / Bonobo. v1.length() - v2.length() = -3
		 */
		minValue.ifPresent(System.out::println); // Ape
		System.out.println("");
	}
	
	public static void myMaxFun() {
		System.out.println("------- myMaxFun");
		Stream<String> myStreamString = Stream.of("Monkey", "Ape", "Bonobo");
		// Finds the animal with the highest letters in its name
		Optional<String> minValue = myStreamString.max((v1, v2) -> {
			System.out.println("v1 / v2 = " + v1 + " / " + v2 + ". "
					+ "v1.length() - v2.length() = " + (v1.length() - v2.length()));
			return v1.length() - v2.length();
		});
		/**
		 * OUTPUT:
v1 / v2 = Monkey / Ape. v1.length() - v2.length() = 3
v1 / v2 = Monkey / Bonobo. v1.length() - v2.length() = 0
		 */
		minValue.ifPresent(System.out::println); // Monkey
		System.out.println("");
	}	
	
	public static void myFindAnyAndFirstFun() {
		System.out.println("------- myFindAnyAndFirstFun");
		Stream<String> myStreamString = Stream.of("Monkey", "Gorilla", "Bonobo");
		Stream<String> infinite = Stream.generate(() -> "Chimp");
		
		myStreamString.findAny().ifPresent(System.out::println); // Monkey
		infinite.findFirst().ifPresent(System.out::println); // Chimp
	}
	
	public static void myAllAnyNoneMatchFun() {
		System.out.println("------- myAllAnyNoneMatchFun");
		List<String> list = Arrays.asList("monkey", "2", "chimp");
		Stream<String> infinite = Stream.generate(() -> "chimp");
		Predicate<String> pred = x -> Character.isLetter(x.charAt(0));
		
		// This shows that we can reuse the same predicate, but 
		// we need a DIFFERENT STREAM EACH TIME.
		System.out.println(list.stream().anyMatch(pred)); // true
		System.out.println(list.stream().allMatch(pred)); // false
		System.out.println(list.stream().noneMatch(pred)); // false
		System.out.println(infinite.anyMatch(pred)); // true		
	}
	
	/**
	 * It combines a stream into a single object.
	 */
	public static void myReduceFun() {
		System.out.println("------- myReduceFun");
		System.out.println("# TYPE 1 - Concatenating");
		Stream<String> myStream = Stream.of("w", "o", "l", "f");
		// String word = myStream.reduce("", (s, v) -> s + v);
		String word = myStream.reduce("", String::concat);
		System.out.println(word); // wolf
		myStream = Stream.of("w", "o", "l", "f");
		System.out.println(myStream.reduce("myIdentity", String::concat)); // myIdentitywolf
		
		System.out.println("# TYPE 2 - Multiplying all of the Integers objects in a stream");
		Stream<Integer> myIntStream = Stream.of(3, 5, 6);		
		// System.out.println(myIntStream.reduce(1, (s, v) -> s*v));
		// Most of the time the identity is not necessary, so we can omit it
		// But it will return an Optional instead
		myIntStream.reduce((s, v) -> s*v).ifPresent(System.out::println); // 90
		
		System.out.println("# TYPE 3 - When processing in parallel");
		BinaryOperator<Integer> myBinOpe = (x, p) -> {
			System.out.println("x / p = " + x + " / " + p);
			return x * p;
		};		
		Stream<Integer> myIntStream2 = Stream.of(3, 5, 6);
		// System.out.println(myIntStream2.reduce(1, myBinOpe, myBinOpe)); // 90
		// System.out.println(myIntStream2.reduce(4, myBinOpe, myBinOpe)); // 4 * 90 = 360
		System.out.println(myIntStream2.reduce(5, myBinOpe, myBinOpe));
		/**
		 * OUTPUT:
x / p = 5 / 3
x / p = 15 / 5
x / p = 75 / 6
450
		 */
	}
	
	/**
	 * It's a special type of reduction called a mutable reduction.
	 * It lets us get data out of streams and into another form. 
	 */
	public static void myCollectFun() {
		System.out.println("------- myCollectFun");
		System.out.println("# TYPE 1");
		Stream<String> myStream = Stream.of("w", "o", "l", "f");
		StringBuilder word = myStream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
		System.out.println(word); // wolf
		
		System.out.println("# TYPE 2");
		Stream<String> myStream2 = Stream.of("w", "o", "l", "f");
		TreeSet<String> myTreeSet = myStream2.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
		System.out.println(myTreeSet); // [f, l, o, w]
		
		System.out.println("# TYPE 3 - Rewriting type 2");
		Stream<String> myStream3 = Stream.of("w", "o", "l", "f");
		TreeSet<String> myTreeSet2 = myStream3.collect(Collectors.toCollection(TreeSet::new));
		System.out.println(myTreeSet2); // [f, l, o, w]
		
		System.out.println("# TYPE 4 - Without order using set");
		Stream<String> myStream4 = Stream.of("w", "o", "l", "f");
		Set<String> myTreeSet3 = myStream4.collect(Collectors.toSet());
		System.out.println(myTreeSet3);	// [f, w, l, o]
	}
}
