package br.com.willianantunes.examocp.chap7;

import java.time.ZonedDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorSample {
	public static void main(String args[]) throws InterruptedException, ExecutionException {
		//firstTest();
		// secondTest();
		System.out.println(Runtime.getRuntime().availableProcessors());
	}
	
	public static void firstTest() throws InterruptedException, ExecutionException {
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		
		Runnable myRunnable = () -> System.out.println("Hello RUNNABLE zoo at " + ZonedDateTime.now());
		Callable<String> myCallable = () -> "Hello CALLABLE zoo at " + ZonedDateTime.now();
		
		Future<?> resultRunnable = service.schedule(myRunnable, 10, TimeUnit.SECONDS);
		Future<?> resultCallable =service.schedule(myCallable, 15, TimeUnit.SECONDS);
		
		System.out.println(resultRunnable.get());
		System.out.println(resultCallable.get());
		
		// Output from the test:
		/*
Hello RUNNABLE zoo at 2016-11-22T07:37:35.459-02:00[America/Sao_Paulo]
null
Hello CALLABLE zoo at 2016-11-22T07:37:40.415-02:00[America/Sao_Paulo]
		 */
	}
	
	public static void secondTest() {
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		
		Runnable myRunnable = () -> System.out.println("Hello RUNNABLE zoo at " + ZonedDateTime.now());
		
		service.scheduleAtFixedRate(myRunnable, 5, 10, TimeUnit.SECONDS);
		
		// Output from the test:
		/*
Hello RUNNABLE zoo at 2016-11-22T07:40:25.486-02:00[America/Sao_Paulo]
Hello RUNNABLE zoo at 2016-11-22T07:40:35.442-02:00[America/Sao_Paulo]
Hello RUNNABLE zoo at 2016-11-22T07:40:45.442-02:00[America/Sao_Paulo]
Hello RUNNABLE zoo at 2016-11-22T07:40:55.442-02:00[America/Sao_Paulo]
// AND SO ON....
		 */
	}
}
