package br.com.willianantunes.examocp;

import java.util.Comparator;
import java.util.TreeSet;

public class Sorted implements Comparator<Sorted> {

	private int num;
	private String text;

	public Sorted(int num, String text) {
		super();
		this.num = num;
		this.text = text;
	}

	@Override
	public int compare(Sorted o1, Sorted o2) {
		return o1.num - o2.num;
	}
	
	public static void main(String args[]) {
		Sorted s1 = new Sorted(88, "a");
		Sorted s2 = new Sorted(55, "b");
		Sorted s3 = new Sorted(31, "b");
		TreeSet<Sorted> t1 = new TreeSet<>(s1);
		t1.add(s1); 
		t1.add(s2);
		t1.add(s3);
		System.out.println(t1);
	}

}
