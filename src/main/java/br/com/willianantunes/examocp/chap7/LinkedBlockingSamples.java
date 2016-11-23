package br.com.willianantunes.examocp.chap7;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LinkedBlockingSamples {
	public static void main(String args[]) {
		queueSample();
		dequeSample();
	}
	
	public static void queueSample() {
		System.out.println("------- queueSample");
		try {
			BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
			
			blockingQueue.offer(30);
			blockingQueue.offer(3, 4, TimeUnit.SECONDS);
			
			System.out.println(blockingQueue.poll()); // 30
			System.out.println(blockingQueue.poll(1, TimeUnit.MILLISECONDS)); // 3			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	public static void dequeSample() {
	System.out.println("------- dequeSample");
		BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>();
		
		blockingDeque.offer(91);
		try {
			blockingDeque.offerFirst(5, 2, TimeUnit.MINUTES);
			blockingDeque.offerLast(47, 100, TimeUnit.MICROSECONDS);
			blockingDeque.offer(3, 4, TimeUnit.SECONDS);
			
			System.out.println(blockingDeque.poll()); // 5
			System.out.println(blockingDeque.poll(950, TimeUnit.MILLISECONDS)); // 91
			System.out.println(blockingDeque.pollFirst(200, TimeUnit.NANOSECONDS)); // 47
			System.out.println(blockingDeque.pollLast(1, TimeUnit.SECONDS)); // 3
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
}
