package br.com.willianantunes.examocp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

import br.com.willianantunes.exam4.A;

public class Crate {
	public static <T, U> U ship(T t, U u) {
		return null;
	}
	
	public static <T> void shipVoid(T t) {
		return;
	}
	
	public static <B extends A> B method3(List<B> list) {
		return null;
		
	}
	
	public static <X> void method5(List<X> list) {
		
	}
	
	public static void addSound(List<?> list) {

		
	}
	
	public static void main(String args[]) {
//		List<? extends Exception> upperBound;
//		upperBound = new ArrayList<Exception>();
//		upperBound = new ArrayList<RuntimeException>();
//		upperBound = new ArrayList<ArithmeticException>();
		// COMPILER ERROR AS OBJECT DOESN'T EXTEND EXCEPTION
//		upperBound = new ArrayList<Object>(); 
//		
//		List<? super Exception> lowerBound;
//		lowerBound = new ArrayList<Exception>();
//		lowerBound = new ArrayList<Object>();
		// COMPILER ERROR AS RUNTIMEEXCEPTION ISN'T SUPER OF EXCEPTION
//		lowerBound = new ArrayList<RuntimeException>();
		
//		NavigableSet<Integer> set = new TreeSet<>();
//		for(int i=0;i<20;i++)
//			set.add(i);
//		
//		System.out.println(set.lower(10));
//		System.out.println(set.floor(10));
//		System.out.println(set.higher(18));
//		System.out.println(set.ceiling(18));
		
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		stack.push(10);
		stack.push(4);
		System.out.println(stack.peek());
		System.out.println(stack.poll());
		System.out.println(stack.poll());
		System.out.println(stack.poll());
		
		
		
	}
}
