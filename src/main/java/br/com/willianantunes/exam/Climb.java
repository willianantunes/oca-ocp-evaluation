package br.com.willianantunes.exam;

import java.io.IOException;

public interface Climb extends Climb2, Climb3 {
	
	int test = 1;
	
	boolean isTooHigh(int height, int limit) throws IOException;
	default boolean example() {
		return true;
	}
	/*
	int myHonestTest(int x);
	
	public void meuTestHonest();
	*/
	public static final String meuAmor = "";
	
	public default int testttt() {
		return 0;
	}
	
	public static void testeasd() {
	}
	
}
