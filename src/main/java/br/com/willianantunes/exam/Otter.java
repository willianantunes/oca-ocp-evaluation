package br.com.willianantunes.exam;

interface Animal {
	public default String getName() {
		return null;
	}
}

interface Mammal {
	public default String getName() {
		return null;
	}
}

abstract class Otter implements Mammal, Animal {

	// public String getName() { return null; }
	public abstract String getName();
	
	public static void main(String...strings) {
		Teste t = new Teste();
		// t.ge
	}

}

class Teste extends Otter {
	
}
