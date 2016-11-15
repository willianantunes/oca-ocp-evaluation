package br.com.willianantunes.exam;

import java.io.IOException;

public abstract class Bird {
	private void fly(){
		System.out.println("abbbaa");
	}
	
	public static void main(String...strings){
//		Bird bird = new Pelican();
//		bird.fly();
//		Pelican pelican = (Pelican) bird;
//		System.out.println(bird.toString());
//		//pelican.receveIt(new Object());
//		throw new ExceptionInInitializerError();
		try{
			System.out.println(5/0);	
		}catch (RuntimeException e) {
			System.out.println("RuntimeException");
		}
		
		try {
			System.out.println("");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public Exception myTest(int a) throws IOException {
		return null;
	}
	
	public String toString() {
		return "toString()";
	}
}

class Pelican extends Bird {
	protected void fly() {
		System.out.println("aaa");
	}
	
	public void receveIt(Bird bird) {
		System.out.println(bird.toString());
	}
}