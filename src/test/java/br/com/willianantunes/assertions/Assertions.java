package br.com.willianantunes.assertions;

/**
 * You must run using the following commands (can be one or another):
 * # java -enableassertions Assertions<br />
 * # java -ea Assertions // specific class<br />
 * # java -ea br.com.willianantunes.assertions.Assertions // specific class<br /> 
 * # java -ea br.com.willianantunes... // enable for classes in willianantunes and any subpackages<br />
 * You can disable assertions using the <i>-disableassertions (or -da)</i> flag for a specific class 
 * or package that was previously enabled. Enabling for br.com package but disabling for Assertions class:
 * # java -ea:br.com... -da:br.com.willianantunes.assertions.Assertions my.program.Main
 */
public class Assertions {
	public static void main(String[] args) {
		withErrorMessage();
	}
	
	static void withoutErroMessage() {
		int numGuests = -5;
		assert numGuests > 0;
		System.out.println(numGuests);		
	}
	
	static void withErrorMessage() {
		int numGuests = -5;
		assert numGuests > 0 : "Check this out: " + numGuests;
	}
}
