package br.com.willianantunes.examocp.chap7;

import java.util.*;
import java.util.concurrent.*;

public class CopyOnWriteSamples {
	public static void main(String[] args) {
		withCopyOnWrite();
		System.out.println();
		withRegular();
	}
	
	public static void withCopyOnWrite() {
		List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(4, 3, 52));
		
		for (Integer item: list) {
			System.out.println(item + " ");
			list.add(9);
			/**
			 * OUTPUT: 
			 * 4 
			 * 3 
			 * 52 
			 */
		}

		System.out.println();
		System.out.println("Size: " + list.size());
		System.out.println();
		System.out.println("And now...");
		
		for (Integer item: list) {
			System.out.println(item + " ");
			/*
			 * OUTPUT:
			 * 4 
			 * 3 
			 * 52 
			 * 9 
			 * 9 
			 * 9
			 */
		}
		/**
		 * Despite adding elements to the array while iterating over it, only 
		 * those elements in the collection at the time the for() loop was created 
		 * were accessed.
		 */
	}
	
	public static void withRegular() {
		List<Integer> list = new ArrayList<>(Arrays.asList(4, 3, 52));
		
		for (Integer item: list) {
			System.out.println(item + " ");
			list.add(9);
		}
		
		// ERROR on LINE 33 "for (Integer item: list) because of LIST.ADD(9)
/*
Exception in thread "main" java.util.ConcurrentModificationException
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901)
	at java.util.ArrayList$Itr.next(ArrayList.java:851)
	at br.com.willianantunes.examocp.chap7.CopyOnWriteSamples.withRegular(CopyOnWriteSamples.java:33)
	at br.com.willianantunes.examocp.chap7.CopyOnWriteSamples.main(CopyOnWriteSamples.java:9)		
 */

		System.out.println();
		System.out.println("Size: " + list.size());
		System.out.println();
		System.out.println("And now...");
		
		for (Integer item: list) {
			System.out.println(item + " ");
		}
	}
}
