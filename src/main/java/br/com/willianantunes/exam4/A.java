package br.com.willianantunes.exam4;

import java.util.function.Predicate;

interface interMyTest {
	public static int BREAD = 1;
}

class B {
	private boolean returnTrue() {
		return true;
	}
	
	public static void main(String args[]) {
		// System.out.println(myTest((Integer p) -> { return p == 5; }));
		// String[] grades[] = {{""}, {""}, {""}};
		Integer x = null;
		int b = 10;
		if(x == b)
			System.out.println();
		myTest((Integer p) -> p == 5);
	}
	
	private static boolean myTest(Predicate<Integer> p) {
		return p.test(5);
	}
	protected void finalize() {
		
	}
}

public class A extends B {
	
	public boolean returnTrue() {
		return false;
	}
	
}

class C {
	
}