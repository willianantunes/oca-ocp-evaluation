package br.com.willianantunes.exam5;

interface MyCustomInterface {
	int BETTER = 0;
}

public class Domino {
	public Domino() { }
	public Domino(int a) { super(); };
	
	public void print(boolean a) { System.out.println("boolean"); }
	//public void print(byte a) { System.out.println("byte"); }
	//public void print(short a) { System.out.println("short"); }
	public void print(char a) { System.out.println("char"); }	
	//public void print(int a) { System.out.println("int"); }	
	public void print(float a) { System.out.println("float"); }
	//public void print(long a) { System.out.println("long"); }
	//public void print(double a) { System.out.println("double"); }
	public void print(Object a) { System.out.println("object"); }
	
	public static void main(String[] args) {

	}
}
