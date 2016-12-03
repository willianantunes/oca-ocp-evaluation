package br.com.willianantunes.examocp.chap7.parallelStream;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.*;

public class QuickStart {
	public static void main(String args[]) {
		// samplesWithForEach();
		// performanceImprovements();
		// whyAvoidStatefulOperations();
		// orderBasedConsequences();
		// unorderedStreams();
		combiningResultsWithReduce();
	}
	
	public static void combiningResultsWithReduce() {
		// Standard way with stream
		System.out.println(Arrays.asList('w','o','l','f')
				.stream()
				.reduce("This is one ", (myIdentityString, c) -> myIdentityString + c, (s2, s3) -> s2 + s3)); // This is one wolf
		
		System.out.println();System.out.println();
		
		System.out.println(Arrays.asList('w','o','l','f')
				.parallelStream()
				.reduce("X", (myIdentityString, c) -> myIdentityString + c, (s2, s3) -> s2 + s3)); // MY CASE: XwXoXlXf
		
		System.out.println();System.out.println();
		
		Stream<String> ohMy = Stream.of("lions", "tigers", "bears").parallel();
		ConcurrentMap<Integer, String> map = ohMy
				.collect(Collectors
							.toConcurrentMap(String::length, k -> k, (s1, s2) -> s1+","+s2));
		System.out.println(map); // MY CASE: {5=bears,lions, 6=tigers} ... IT CAN BE {5=bears,lions, 6=tigers} for example.
		System.out.println(map.getClass()); // class java.util.concurrent.ConcurrentHashMap
		
		System.out.println();System.out.println();
		
		Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears").parallel();
		ConcurrentMap<Integer, List<String>> map2 = ohMy2
				.collect(Collectors.groupingByConcurrent(String::length));
		System.out.println(map2); // MY CASE: {5=[bears, lions], 6=[tigers]}
	}
	
	public static void unorderedStreams() {
		// It just tells the JVM that if an order-bases stream is applied, the order can be ignored.
		Arrays.asList(1,2,3,4,5,6,7,8,9,10).stream().unordered();
		
		// For example, calling skip(5) on an unordered stream will skip any 5 elements, not the first 5.
		Arrays.asList(1,2,3,4,5,6,7,8,9,10).stream().unordered().parallel().skip(5)
			.forEach((s) -> System.out.print(s + " "));; // See page 373
	}
	
	public static void orderBasedConsequences() {
		System.out.println(Arrays.asList(1,2,3,4,5,6,7,8,9).stream().findAny().get()); // Always output 1
		System.out.println(Arrays.asList(1,2,3,4,5,6,7,8,9).parallelStream().findAny().get()); // Can output any number
	}
	
	public static void samplesWithForEach() {
		Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
			.stream()
			.forEach(s -> System.out.print(s + " ")); // Output: 1 2 3 4 5 6 7 8 9 
	
		System.out.println();
	
		// It's equivalent to submitting multiple Runnable lambda expressions to a pooled thread executor.
		Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
			.parallelStream()
			.forEach(s -> System.out.print(s + " ")); // Output (when I ran it): 6 1 8 5 7 3 9 2 4 		
		
		System.out.println();
		
		Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
			.parallelStream()
			.forEachOrdered(s -> System.out.print(s + " ")); // Output: 1 2 3 4 5 6 7 8 9  	
	}
	
	public static void whyAvoidStatefulOperations() {
		List<Integer> data = Collections.synchronizedList(new ArrayList<>());
		
		Arrays.asList(1, 2, 3, 4, 5, 6)
			.parallelStream()
			.map(i -> { data.add(i); return i; }) // AVOID STATEFUL LAMBDA EXPRESSIONS!
			.forEachOrdered(i -> System.out.print(i+ " "));
		
		System.out.println();
		
		for (Integer e : data) {
			System.out.print(e + " ");
		}
	}
	
	public static void performanceImprovements() {
		class WhaleDataCalculator {
			public int processRecord(int input) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return ++input;
			}
			
			public void processAllDataWithoutParallel(List<Integer> data) {
				data.stream().map(a -> processRecord(a)).count();
			}
			
			public void processAllDataWithParallel(List<Integer> data) {
				data.parallelStream().map(a -> processRecord(a)).count();
			}
		}
		
		WhaleDataCalculator calculator = new WhaleDataCalculator();
		
		// TEST USING NORMAL STREAM (SINGLE THREAD)
		// Define the data
		List<Integer> data = new ArrayList<>();
		for (int i=0;i<4000;i++) data.add(i);
		
		// Process the data
		long start = System.currentTimeMillis();
		calculator.processAllDataWithoutParallel(data);
		double time = (System.currentTimeMillis()-start) / 1000.0;
		
		// Report results 
		System.out.println("\nTasks completed in: " +time+ " seconds"); // Tasks completed in: 40.054 seconds
		/**
		 * As the result was 40.054 seconds, in our example there are 40.000 records, which 
		 * each one took 0,09 seconds or almost 10 milliseconds to process.  
		 */	
		
		// TEST USING NORMAL PARALLELSTREAM (MULTIPLE THREADS)
		// Process the data
		start = System.currentTimeMillis();
		calculator.processAllDataWithParallel(data);
		time = (System.currentTimeMillis()-start) / 1000.0;
		
		// Report results
		System.out.println("\nTasks completed in: " +time+ " seconds");  // Tasks completed in: 5.003 seconds
	}
}
