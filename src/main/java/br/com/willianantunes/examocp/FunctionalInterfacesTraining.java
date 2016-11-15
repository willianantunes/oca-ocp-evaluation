package br.com.willianantunes.examocp;

import java.util.List;
import java.util.Optional;
import java.util.function.*;

public class FunctionalInterfacesTraining {
	public static void main(String args[]) {
		BiPredicate<String, String> ex3 = (s1, s2) -> false;
		Function<List<String>, String> ex1 = x -> x.get(0);
		Predicate<String> ex4 = String::isEmpty;
		
		Optional<Integer> myOptional = Optional.empty(); // Optional.of(123);
		myOptional.ifPresent(System.out::println);
		System.out.println(myOptional.orElseGet(() -> 321));
	}
}
