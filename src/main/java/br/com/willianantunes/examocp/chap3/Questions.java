package br.com.willianantunes.examocp.chap3;

import java.util.*;

public class Questions {
	public static void main(String args[]) {
		// q19();
		q25();
	}
	
	private static void q19() {
		List<Integer> q = new LinkedList<>();
		// Queue<Integer> q = new LinkedList<>();
		q.add(10);
		q.add(12);
		q.remove(1);
		// q.remove(10);
		System.out.println(q);
	}
	
	private static void q25() {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(1, 10);
		map.put(2, 20);
		map.put(3, null);
		
		System.out.println(map); // {1=10, 2=20, 3=null}
		map.merge(1, 3, (a,b) -> {
			System.out.println("Value of a: " + a);
			System.out.println("Value of b: " + b);
			return a + b;
		});
		System.out.println(map); // {1=10, 2=20, 3=null}
	}
}
