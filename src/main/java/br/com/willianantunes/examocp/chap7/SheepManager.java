package br.com.willianantunes.examocp.chap7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SheepManager {
	public static class UnsafeSheepManager {
		private int sheepCount = 0;
		private void incrementAndReport() {
			System.out.print((++sheepCount)+ " ");
		}
	}
	
	public static class SafeButUnorderedSheepManager {
		private AtomicInteger sheepCount = new AtomicInteger(0);
		private void incrementAndReport() {
			System.out.print((sheepCount.incrementAndGet())+ " ");
		}
	}
	
	public static class SafeAndOrderedSheepManager {
		private AtomicInteger sheepCount = new AtomicInteger(0);
		private void incrementAndReport() {
			synchronized(this) { // We can put the synchronized reserved word in the method as well!
				System.out.print((sheepCount.incrementAndGet())+ " ");	
			}			
		}
	}		
	
	public static void main(String args[]) {
		// lackOfThreadSynchronization();
		// lackOfThreadSynchronizationYet();
		threadSynchronization();
		
	}
	
	public static void lackOfThreadSynchronization() {
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(20);
			UnsafeSheepManager sheepManager = new 
					UnsafeSheepManager();
			for (int i = 0; i < 10; i++) {
				service.submit(() -> sheepManager.incrementAndReport());
			}
			// Sample output: 1 2 3 3 4 5 6 7 8 9 
			/*
			 * A problem occurs when two thread both execute the right side of the 
			 * expression, reading the "old" value before either thread writes 
			 * the "new" value of the variable.
			 */
		} finally {
			if (service != null) service.shutdown();
		}
	}
	
	public static void lackOfThreadSynchronizationYet() {
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(20);
			SafeButUnorderedSheepManager sheepManager = new SafeButUnorderedSheepManager();
			for (int i = 0; i < 10; i++) {
				service.submit(() -> sheepManager.incrementAndReport());
			}
			// Sample output: 2 3 1 4 5 6 7 8 9 10 
			// Now there is no way to have copies of some value, but it's unordered yet.
		} finally {
			if (service != null) service.shutdown();
		}
	}	
	
	public static void threadSynchronization() {
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(20);
			SafeAndOrderedSheepManager sheepManager = new SafeAndOrderedSheepManager();
			for (int i = 0; i < 20; i++) {
				service.submit(() -> sheepManager.incrementAndReport());
			}
			// Sample output: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
		} finally {
			if (service != null) service.shutdown();
		}
	}	
}
