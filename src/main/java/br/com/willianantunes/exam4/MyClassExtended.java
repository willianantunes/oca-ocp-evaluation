package br.com.willianantunes.exam4;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.function.Predicate;

class ExtendedOne {
	
	public ExtendedOne() {
		System.out.println("Fui chamado!");
	}
	
	public void myMethod() {
		System.out.println("ExtendedOne");
	}
}

// public class MyClassExtended extends ExtendedOne implements MyInteface {
public class MyClassExtended extends MyAbstractClass {
	private int a;
	private int b = a;
/*	public void myMethod() {
		System.out.println("MyClassExtended");
	}
	
	@Override
	public boolean isBlind() {
		return false;
	}
	
	public MyClassExtended(int age) {
		System.out.println("age" + age);
	}*/

	public static void main(String...strings) {
		System.out.println(LocalDateTime
		.of(2015, 5, 10, 11, 22, 33)
		.minus(Period.of(1, 2, 3))
		.plusMonths(-1)
		.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
		System.out.println();
		
		// System.out.println(test((p) -> p.isEmpty(), ""));
		
		String letter = "abcde";
		// String test = letter.charAt(2);
	}

	@Override
	void eatFish(int count) {
		// TODO Auto-generated method stub
		
	}
	
	public static String test(Predicate<String> aaa, String a) {
		return aaa.test(a)?"true" : "false";
	}
}
