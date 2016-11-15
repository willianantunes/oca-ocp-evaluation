package br.com.willianantunes.examocp.chap4;

import java.util.Optional;

public class OptionalPlayGround {
	public static void main(String[] args) {
		Optional<Double> myOptional1 = OptionalPlayGround.average(1,3,56,1,2);
		myOptional1 = OptionalPlayGround.average();
		if(myOptional1.isPresent())
			System.out.println(myOptional1.get());
		myOptional1.ifPresent(System.out::println);
		System.out.println(myOptional1.orElse(Double.NaN));
		System.out.println(myOptional1.orElseGet(() -> Double.NaN));
		// System.out.println(myOptional1.orElseThrow(RuntimeException::new));
	}
	
	public static Optional<Double> average(int... scores) {
		
		if(scores.length == 0)
			return Optional.ofNullable(null); // return Optional.empty();
		int sum = 0;
		for(int score : scores) 
			sum += score;
		return Optional.of((double)sum/scores.length);
	}
}
