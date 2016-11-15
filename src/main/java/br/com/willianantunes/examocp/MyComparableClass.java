package br.com.willianantunes.examocp;

import java.util.Comparator;

public class MyComparableClass implements Comparable {

	static <T> boolean predicate(T t) { return false; }	
	static <T> void consumer(T t) { }
	static <T> T supplier() { return null; }
	
	
	
	@Override
	public int compareTo(Object o) {
		
		return 0;
	}

}
