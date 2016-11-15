package br.com.willianantunes.examocp;

interface CanWalk {
	default void walk() { System.out.println("walking..."); }
}

interface CanRun {
	default void walk() { System.out.println("walking..."); }
	void run();
}

public interface CanSprint extends CanRun, CanWalk {

	@Override
	default void walk() {
		// TODO Auto-generated method stub
		CanRun.super.walk();
	}

}
