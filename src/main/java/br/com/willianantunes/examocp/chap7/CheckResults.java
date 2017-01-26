package br.com.willianantunes.examocp.chap7;

public class CheckResults {
	private static int counter = 0;
	
	public static void main(String args[]) {
		// withoutThreadSleep();
		withThreadSleep();
	}
	
	public static void withoutThreadSleep() {
		new Thread(() -> {
			for (int i = 0; i < 50000; i++) {
				CheckResults.counter++;
			}
		}).start();
		
		while (CheckResults.counter < 30000) {
			System.out.println("Not reached yet");
		}
		System.out.println("Reached!");
	}
	
	public static void withThreadSleep() {
		new Thread(() -> {
			for (int i = 0; i < 5_000_000_000L; i++) {
				CheckResults.counter++;
			}
		}).start();
		
		while (CheckResults.counter < 100_000_000) {
			System.out.println("Not reached yet");
			try {
				// It does prevent CPU from being overwhelmed with a potentially infinite loop.
				Thread.sleep(100); // In order to implement pooling.
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		System.out.println("Reached!");
	}
}
