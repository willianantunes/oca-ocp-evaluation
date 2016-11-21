package br.com.willianantunes.examocp.chap7;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AddData {
	public static void main(String args[]) throws InterruptedException, ExecutionException {
		ExecutorService service = null;
		try {
			service = Executors.newSingleThreadExecutor();
			Future<Integer> result = service.submit(() -> 30+11);
			
			/**
			 * The results could have also been obtained using Runnable and some 
			 * shared, possibly static, object, although this solution that relies on 
			 * Callable is a lot simples and easier to follow.
			 */
			System.out.println(result.get());
		} finally {
			if (service != null) service.shutdown();
		}
	}
}
