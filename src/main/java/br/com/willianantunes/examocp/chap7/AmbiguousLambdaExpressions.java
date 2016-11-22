package br.com.willianantunes.examocp.chap7;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class AmbiguousLambdaExpressions {
	public static void useCallable(Callable<Integer> expression) {}
	public static void useSupplier(Supplier<Integer> expression) {}
	
	public static void use(Supplier<Integer> expression) {}
	public static void use(Callable<Integer> expression) {}
	
	public static void main(String args[]) {
		useCallable(() -> { throw new IOException(); }); // COMPILES
		useSupplier(() -> { throw new IOException(); }); // DOES NOT COMPILE
		useSupplier(() -> { return 1; }); // COMPILES
		use(() -> { throw new IOException(); }); // DOES NOT COMPILE
		use((Callable<Integer>)() -> { throw new IOException(); }); // COMPILES

	}
}
