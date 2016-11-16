package br.com.willianantunes.examocp.chap4;

import java.util.stream.*;
import java.util.function.*;
import java.util.*;

public class WorkingWithPrimitives {
	public static void main(String args[]) {
		quickStart();
	}
	
	public static void quickStart() {
		// Calculate the sum of numbers in a finite stream
		Stream<Integer> myStream = Stream.of(1, 2, 3);
		System.out.println(myStream.reduce(0, (s, v) -> s+v));
		
		// Another way of doing that
		Stream<Integer> myStream2 = Stream.of(1, 2, 3);
		System.out.println(myStream2.mapToInt(x -> x).sum());
		
		// Another example
		Stream<Integer> myStream3 = Stream.of(1, 2, 3);
		IntStream myIntStream = myStream3.mapToInt(x -> x);
		// System.out.println(myIntStream.count());
		// myIntStream.max().ifPresent(System.out::println);
		// myIntStream.min().ifPresent(System.out::println);
		myIntStream.average().ifPresent(System.out::println);
				
	}
}
