package br.com.willianantunes.examocp;

import java.util.Arrays;
import java.util.Comparator;

public class MyComparator10 implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		// return o2.toLowerCase().compareTo(o1.toLowerCase());
		System.out.print(o1.toLowerCase().compareTo(o2.toLowerCase()) + " ");
		return o1.toLowerCase().compareTo(o2.toLowerCase());
	}
	
	public static void main(String[] args) {
		// TODO: fill and test with another values
		String[] values = {"123", "Abb", "aab"};
		
		// o2.toLowerCase().compareTo(o1.toLowerCase())
		// Abb, 123 = -48	/ 123 compareTo abb
		// aab, Abb = 1		/ abb compareTo aab
		// aab, 123 = -48	/ 123 compareTo aab
		// aab, Abb = 1		/ Abb compareTo aab
		
		// Abb, 123 = 48	/ Abb compareTo 123
		// aab, Abb = -1	/ aab compareTo abb 
		// aab, 123 = 48	/ aab compareTo 123
		// aab, Abb = -1	/ aab compareTo Abb 
		
		Arrays.sort(values, new MyComparator10());
		System.out.println();
		for (String string : values) {
			System.out.println(string);
		}
	}

}
