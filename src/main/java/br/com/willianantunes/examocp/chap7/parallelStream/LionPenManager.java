package br.com.willianantunes.examocp.chap7.parallelStream;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LionPenManager {
	private void removeAnimals() {
		System.out.println("Removing animals");
	}
	private void cleanPen() {
		System.out.println("Cleaning the pen");
	}
	private void addAnimals() {
		System.out.println("Adding animals");
	}
	
	public void performTask() {
		removeAnimals();
		cleanPen();
		addAnimals();
	}
	
	public void performTaskWithCyclicBarrier(CyclicBarrier c1, CyclicBarrier c2) {
		try {
			removeAnimals();
			c1.await();
			cleanPen();
			c2.await();
			addAnimals();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String... args) {
		// chaoticWay();
		niceWay();
	}
	
	public static void niceWay() {
		ExecutorService service = null;
		
		try {
			service = Executors.newFixedThreadPool(4);
			
			LionPenManager manager = new LionPenManager();
			CyclicBarrier c1 = new CyclicBarrier(4);
			CyclicBarrier c2 = new CyclicBarrier(4, () -> System.out.println("*** Pen cleaned!")); // Will call Runnable upon completion
			
			for (int i = 0; i < 4; i++) {
				service.submit(() -> manager.performTaskWithCyclicBarrier(c1, c2));
			}
			/**
			 * Output:
					Removing animals
					Removing animals
					Removing animals
					Removing animals
					Cleaning the pen
					Cleaning the pen
					Cleaning the pen
					Cleaning the pen
					*** Pen cleaned!
					Adding animals
					Adding animals
					Adding animals
					Adding animals
			 * Some animals are sting being removed while the cage is being cleaned, 
			 * and other animals are added before the cleaning process is finished... Chaotic! 
			 * Some of our workers must have died during the process... >.<
			 */			
		} finally {
			if (service != null) service.shutdown();
		}
	}
	
	public static void chaoticWay() {
		ExecutorService service = null;
		
		try {
			service = Executors.newFixedThreadPool(4);
			LionPenManager manager = new LionPenManager();
			for (int i = 0; i < 4; i++) {
				service.submit(() -> manager.performTask());
			}
			/**
			 * Sample of output:
					Removing animals
					Cleaning the pen
					Adding animals
					Removing animals
					Cleaning the pen
					Adding animals
					Removing animals
					Cleaning the pen
					Adding animals
					Removing animals
					Cleaning the pen
					Adding animals
			 * Some animals are sting being removed while the cage is being cleaned, 
			 * and other animals are added before the cleaning process is finished... Chaotic! 
			 * Some of our workers must have died during the process... >.<
			 */			
		} finally {
			if (service != null) service.shutdown();
		}
	}
	
}
