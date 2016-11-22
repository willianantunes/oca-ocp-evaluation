package br.com.willianantunes.examocp.chap7;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaitingTasksToFinish {
	public static void main(String args[]) throws InterruptedException, ExecutionException {
		ExecutorService service = null;
		try {
			service = Executors.newSingleThreadExecutor();
			service.submit(() -> 30+11);
		} finally {
			if (service != null) service.shutdown();
			if (service != null) {
				service.awaitTermination(1,  TimeUnit.MINUTES);
				
				// Check whether all tasks are finished
				if (service.isTerminated())
					System.out.println("All tasks finished");
				else 
					System.out.println("At least one task in still running sadly...");
			}
		}
	}
}
