package br.com.willianantunes.examocp;

public class Outer9 {
	private int x;
	public int getX() {
		String message = "x is ";
		class Inner {
			private int x = Outer9.this.x;
			public void printX() {
				System.out.println(message + x);
			}
		}
		Inner inner = new Inner();
		inner.printX();
		return x;
	}
	
	public static void main(String[] args) {
		new Outer9().getX();
	}
	
	
}
