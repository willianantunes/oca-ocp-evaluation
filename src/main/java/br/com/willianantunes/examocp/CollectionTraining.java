package br.com.willianantunes.examocp;

import java.util.*;

public class CollectionTraining {
	public static void main(String args[]) {
//		Set<Number> numbers = new HashSet<>();
//		numbers.add(new Integer(86));
//		numbers.add(75);
//		numbers.add(new Integer(86));
//		numbers.add(null);
//		numbers.add(309L);
//		
//		Iterator iter = numbers.iterator();
//		while(iter.hasNext())
//			System.out.println(iter.next());
		
		Queue<Integer> q = new LinkedList<>();
		q.add(10);
		q.add(12);
		q.remove();
		System.out.println(q);
	}
}
