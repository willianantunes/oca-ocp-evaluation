package br.com.willianantunes.examocp.chap7;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * See page 382.
 */
public class WeighAnimalAction extends RecursiveAction {

	private int start;
	private int end;
	private Double[] weights;
	
	public WeighAnimalAction(Double [] weights, int start, int end) {
		this.weights = weights;
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected void compute() {
		if (end-start <= 3) { // OUR BASE CASE
			for(int i=start; i<end; i++) {
				weights[i] = (double) new Random().nextInt(100);
				System.out.println("Animal weighed: " + i);
			}
		} else { // OUR RECURSIVE CASE
			int middle = start + ((end-start)/2);
			System.out.println("[start="+start+",middle="+middle+",end="+end+"]");
			invokeAll(new WeighAnimalAction(weights, start, middle), new WeighAnimalAction(weights, middle, end));
		}
	}

	public static void main(String args[]) {
		Double[] weights = new Double[10];
		ForkJoinTask<?> task = new WeighAnimalAction(weights, 0, weights.length);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(task);
		
		// Print results
		System.out.println(); System.out.println();
		System.out.print("Weights: ");
		Arrays.asList(weights).stream().forEach(d -> System.out.print(d.intValue() + " "));
		
		/**
		 * Sample of OUTPUT:

[start=0,middle=5,end=10]
[start=0,middle=2,end=5]
Animal weighed: 0
Animal weighed: 2
[start=5,middle=7,end=10]
Animal weighed: 1
Animal weighed: 7
Animal weighed: 3
Animal weighed: 5
Animal weighed: 6
Animal weighed: 8
Animal weighed: 4
Animal weighed: 9


Weights: 41 80 81 44 47 94 34 3 97 33 

		 */
		
	}
}
