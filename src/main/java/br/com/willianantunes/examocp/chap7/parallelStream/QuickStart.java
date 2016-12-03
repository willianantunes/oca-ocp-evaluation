package br.com.willianantunes.examocp.chap7.parallelStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuickStart {
	public static void main(String args[]) {
		// samplesWithForEach();
		// performanceImprovements();
		whyAvoidStatefulOperations();
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
	
	public static void performanceImprovements() {
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
	
	public static class WhaleDataCalculator {
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
}
