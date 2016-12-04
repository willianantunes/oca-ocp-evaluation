package br.com.willianantunes.examocp.chap7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Food {}
class Water {}

class Fox {
	public void eatAndDrink(Food food, Water water) {
		synchronized (food) {
			System.out.println("Got food!");
			move();
			synchronized (water) {
				System.out.println("Got water!");
			}
		}
	}
	
	public void drinkAndEat(Food food, Water water) {
		synchronized (water) {
			System.out.println("Got water!");
			move();
			synchronized (food) {
				System.out.println("Got food!");	
			}
		}
	}
	
	public void move() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class DeadlockSample {
	public static void main(String[] args) {
		// Create participants and resources
		Fox foxy =  new Fox();
		Fox tails = new Fox();
		
		Food food = new Food();
		Water water = new Water();
		
		// Process data
		ExecutorService service = null;
		try {
			/**
			 * In this example, Foxy obtains the food and then moves 
			 * to the other side of the environment to obtain the water.
			 * Unfortunately, Tails already drank the water and is waiting for the food 
			 * to become available. The result is that our program outputs the following and it 
			 * hangs indefinitely:
			 * 		Got food!
			 * 		Got water!
			 * This example is considered a deadlock because both participants are permanently blocked, 
			 * waiting on resources that will never become available... NEVER.
			 */
			service = Executors.newScheduledThreadPool(10);
			service.submit(() -> foxy.eatAndDrink(food, water));
			service.submit(() -> tails.drinkAndEat(food, water));	
		} finally {
			if (service != null) service.shutdown();
		}
	}
}
