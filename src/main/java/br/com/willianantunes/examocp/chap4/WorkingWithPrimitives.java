package br.com.willianantunes.examocp.chap4;

import java.util.stream.*;
import java.util.function.*;
import java.util.*;

public class WorkingWithPrimitives {
	public static void main(String args[]) {
		/*
		quickStart();
		primitiveStreams();
		summarizingStatisticsFun();
		*/
		
		Optional<Integer> myInteger = Optional.of(1234235);
		myInteger.map(x -> x + "asdas").ifPresent(System.out::println);
	}
	
	public static void quickStart() {
		System.out.println("------- quickStart");
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
		System.out.println("");
				
	}
	
	public static void primitiveStreams() {
		System.out.println("------- primitiveStreams");
		Stream<Integer> myStreamInteger = Stream.of(1, 2, 3, 4);
		IntStream myIntStream = IntStream.of(1, 2, 3, 4);
		// LongStream myLongStream = myIntStream.asLongStream();
		// LongStream myLongStream = myIntStream.mapToLong(x -> x);
		// DoubleStream myDoubleStream = myIntStream.asDoubleStream();
		
		// DoubleStream random = DoubleStream.generate(Math::random);
		// DoubleStream fractions = DoubleStream.iterate(.5, d -> d / 2);
		// random.limit(5).forEach(System.out::print);
		
		// IntStream myRange = IntStream.range(1, 6); // 1 to 5
		// IntStream myRange = IntStream.rangeClosed(1, 6);
		// myRange.forEach(System.out::println);				
		System.out.println("");
	}
	
	/**
	 * min() and max() are terminal operations. If we need to use them on a single stream we 
	 * can extract a SummaryStatistics to work with them and other things.
	 */
	public static void summarizingStatisticsFun() {
		System.out.println("------- summarizingStatisticsFun");
		IntStream myIntStream = IntStream.iterate(1, x -> ++x);
		IntSummaryStatistics stats = myIntStream.limit(10).summaryStatistics();
		System.out.println(stats.getCount());
		System.out.println(stats.getMax());
		System.out.println(stats.getMin());
		System.out.println(stats.getAverage());
		// myIntStream.forEach(System.out::println); // stream has already been operated upon or closed		
		System.out.println("");
		
	}
}
