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
	
	public String toString() { return "" + num; }

	@Override
	public int compare(Sorted o1, Sorted o2) {
		return o1.num - o2.num;
		/**
		 * Evaluation process:
		 * 1 - When t1.add(s1), 88 is compared with itself.
		 * 2 - When t1.add(s2), the recently added (55) is compared with 88.
		 * 3 - When t1.add(s3), the recently added (31) is compared with the last one (88) available in the TreeSet.
		 * 4 - As 31-88 is less than zero, therefore it goes one slot before 88 
		 * and compare with the number behind 88, that is 55.
		 * 5 - As 31-55 is less than zero, therefore it goes one slot before  
		 * and as there are no more numbers to evaluate then the order stays [31, 55, 88].
		 */
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
