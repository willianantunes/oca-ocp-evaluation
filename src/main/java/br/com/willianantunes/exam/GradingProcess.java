package br.com.willianantunes.exam;

import java.util.function.Predicate;

class MarksOutOfBoundsException extends IndexOutOfBoundsException {}

public class GradingProcess {
	void verify(int marks) throws IndexOutOfBoundsException {
		if (marks > 100) {
			throw new MarksOutOfBoundsException();
		}
		if (marks > 50) {
			System.out.println("Pass");
		} else {
			System.out.println("Fail");
		}
	}
	
	public static void main(String args[]) {
		boolean aaa = myPredicate((Integer p) -> {return p == 5;});
		System.out.println(aaa);
	}
	
	private static boolean myPredicate(Predicate<Integer> pred) {
		return pred.test(5);
	}
}

final class Ww {}
