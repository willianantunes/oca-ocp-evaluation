package br.com.willianantunes.examocp.chap7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ZooInfo {
	public static void main(String[] args) {
		// It's just a rewrite of PrintData and ReadInventoryThread classes.
		ExecutorService service = null;
		try {
			/**
			 * With single-thread executor, results are guaranteed to be executed in the order 
			 * in which they are added to the executor service.
			 * Notice that the "end" text is output while our thread executor tasks are still running =)
			 */
			service = Executors.newSingleThreadExecutor();
			
			System.out.println("begin");
			
			service.execute(() -> {
				System.out.println("Printing zoo inventory");
			});
			service.execute(() -> {
				for (int i = 0; i < 3; i++) {
					System.out.println("Printing record " + i);
				}
			});
			service.execute(() -> {
				System.out.println("Printing zoo inventory");
			});						
			
			System.out.println("end");
		} finally {
			/**
			 * A thread executor creates a non-daemon thread on the first task that is 
			 * executed, so failing to call shutdown() will result in your application 
			 * never terminating.
			 */
			if (service != null) service.shutdown();
		}
		
		/**
		 * Sample of output:
begin
Printing zoo inventory
Printing record 0
Printing record 1
Printing record 2
end
Printing zoo inventory
		 */
	}
}
