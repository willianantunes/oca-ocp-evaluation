package br.com.willianantunes.examocp.chap7;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * See page 386.
 */
public class WeighAnimalTask extends RecursiveTask<Double> {

	private int start;
	private int end;
	private Double[] weights;
	
	public WeighAnimalTask(int start, int end, Double[] weights) {
		this.start = start;
		this.end = end;
		this.weights = weights;
	}

	@Override
	protected Double compute() {
		if (end-start <= 3) { // OUR BASE CASE
			double sum = 0;
			for (int i=start;i<end;i++) {
				weights[i] = (double) new Random().nextInt(100);
				System.out.println("Animal weighed: " + i);
				sum += weights[i];
			}
			return sum;
		} else { // OUR RECURSIVE CASE
			int middle = start + ((end-start)/2);
			System.out.println("[start="+start+",middle="+middle+",end="+end+"]");
			RecursiveTask<Double> otherTask = new WeighAnimalTask(start, middle, weights);
			
			/**
			 * The fork() method instructs the fork/join framework to complete the task in a separate 
			 * thread, while the join() method causes the current thread to wait for the results.
			 */
			otherTask.fork();
			return new WeighAnimalTask(middle, end, weights).compute() + otherTask.join();			
		}
	}
	
	public static void main(String args[]) {
		Double[] weights = new Double[10];
		ForkJoinTask<Double> task = new WeighAnimalTask(0, weights.length, weights);
		ForkJoinPool pool = new ForkJoinPool();
		Double sum = pool.invoke(task);
		System.out.println("Sum: " + sum);
		/**
		 * Sample of OUTPUT:

[start=0,middle=5,end=10]
[start=5,middle=7,end=10]
Animal weighed: 7
Animal weighed: 8
Animal weighed: 5
[start=0,middle=2,end=5]
Animal weighed: 6
Animal weighed: 9
Animal weighed: 0
Animal weighed: 2
Animal weighed: 1
Animal weighed: 3
Animal weighed: 4
Sum: 521.0

		 */
	}
}