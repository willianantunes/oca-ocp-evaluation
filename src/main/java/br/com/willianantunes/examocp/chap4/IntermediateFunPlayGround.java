package br.com.willianantunes.examocp.chap4;

import java.util.function.*;
import java.util.stream.*;

public class IntermediateFunPlayGround {
	public static void main(String args[]) {
		myFilterFun();
		myDistinctFun();
	}
	
	public static void myFilterFun() {
		Stream<String> myStream = Stream.of("monkey", "gorilla", "bonobo");
		myStream.filter(x -> x.startsWith("m")).forEach(System.out::println); // monkey
	}
	
	public static void myDistinctFun() {
		Stream<String> myStream = Stream.of("duck", "duck", "duck", "goose");
		myStream.distinct().forEach(System.out::println);
	}
}
