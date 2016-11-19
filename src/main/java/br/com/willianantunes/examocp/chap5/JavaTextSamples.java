package br.com.willianantunes.examocp.chap5;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class JavaTextSamples {
	public static void main(String args[]) {
		quickStart();
		parsingFun();
		parsingMonetaryAmountFun();
	}
	
	public static void quickStart() {
		System.out.println("------- quickStart");
		int attendeesPerYear = 3_200_000;
		int attendeesPerMonth = attendeesPerYear/12;
		
		NumberFormat us = NumberFormat.getInstance(Locale.US);
		System.out.println(us.format(attendeesPerMonth));
		
		NumberFormat de = NumberFormat.getInstance(Locale.GERMANY);
		System.out.println(de.format(attendeesPerMonth));
		
		NumberFormat fr = NumberFormat.getInstance(Locale.CANADA_FRENCH);
		System.out.println(fr.format(attendeesPerMonth));		
		
		/* OUTPUT:
		266,666
		266.666
		266 666
		// US uses commas to separate parts of large numbers, Germans use a dot for this function and 
		// French Canandians use neither.
		*/		
		
		double price = 48; // DO NOT EVER USE DOUBLE  for money, use instead INT, LONG or BIGDECIMAL.
		us = NumberFormat.getCurrencyInstance(Locale.US);
		de = NumberFormat.getCurrencyInstance(Locale.GERMANY);
		fr = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);
		
		System.out.println(us.format(price)); // $48.00
		System.out.println(de.format(price)); // 48,00 €
		System.out.println(fr.format(price)); // 48,00 $	
		
		System.out.println();
	}
	
	public static void parsingFun() {
		System.out.println("------- parsingFun");
		
		NumberFormat en = NumberFormat.getInstance(Locale.US);
		NumberFormat fr = NumberFormat.getInstance(Locale.FRANCE);
		
		String s = "40.45";
		try {
			System.out.println(en.parse(s)); // 40.45
			System.out.println(fr.parse(s)); // 40
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		NumberFormat nf = NumberFormat.getInstance();
		String one = "456abc";
		String two = "-2.5165x10";
		String three = "x85.3";
		
		try {
			// The parse methods parses only the beginning of a String.
			// After it reaches a character that cannot be parsed, the parsing 
			// stops and the value is returned.
			System.out.println(nf.parse(one)); // 456
			System.out.println(nf.parse(two)); // -25165
			// System.out.println(nf.parse(three)); // java.text.ParseException: Unparseable number: "x85.3"
		} catch (ParseException e) {
			e.printStackTrace();
		}
				
		System.out.println();
	}
	
	public static void parsingMonetaryAmountFun() {
		System.out.println("------- parsingMonetaryAmountFun");
		
		String amt = "$92,807.99";
		System.out.println(Locale.getDefault());
		System.out.println(Locale.US);
		NumberFormat cf = NumberFormat.getCurrencyInstance(Locale.US);
		double value = 0;
		try {
			value = (double) cf.parse(amt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(value);
		
		System.out.println();
	}
}
