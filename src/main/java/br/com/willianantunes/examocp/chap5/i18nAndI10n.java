package br.com.willianantunes.examocp.chap5;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class i18nAndI10n {
	public static void main(String[]args) {
		pickingLocale();
		localeBuilderFun();
		resourceBundleTest();
		messageFormatExampleFun();
	}
	
	public static void pickingLocale() {
		System.out.println("------- pickingLocale");
		
		Locale locale = Locale.getDefault();
		System.out.println(locale); // My current locale: en_US
		System.out.println(Locale.CANADA_FRENCH); // fr_CA
		System.out.println(Locale.GERMAN); // de
		System.out.println(Locale.GERMANY); // de_DE
		
		// Jave let you create a Locale with an invalid language or country.
		// But it won't match the Locale you want to use and your program will not behave as expected.
		System.out.println(new Locale("fr")); // OK
		System.out.println(new Locale("fr", "KADUZAO")); // NOT OK
		
		System.out.println("");
	}
	
	public static void localeBuilderFun() {
		System.out.println("------- pickingLocale");
		
		Locale l1 = new Locale.Builder()
				.setLanguage("en")
				.setRegion("US") // If you insert lower case, it'll be transformed into upper case.
				.build();
		System.out.println(l1); // en_US
		
		Locale.setDefault(l1); // Change the default for this particular program
		
		System.out.println();
	}
	
	/**
	 * It contains the local specific objects to be used by a program. It's like a map 
	 * with keys and values where it can be located in a property file or Java class.
	 */
	public static void resourceBundleTest() {
		System.out.println("------- resourceBundleTest");
		
		Locale us = new Locale("en", "US");
		Locale france = new Locale("fr", "FR");
		france = new Locale("fr"); // The above one and this one are both valid
		
		class MyTest {
			public void printProperties(Locale locale) {
				System.out.println("# Start of printProperties for " + locale);
				ResourceBundle rb = ResourceBundle.getBundle("Zoo", locale);
				
				System.out.println(rb.getString("hello"));
				System.out.println(rb.getString("open"));
			}
			
			public void loppingThroughIt(Locale locale) {
				System.out.println("# Start of loppingThroughIt for " + locale);
				ResourceBundle rb = ResourceBundle.getBundle("Zoo", locale);
				
				// Old way
				Iterator<String> it = rb.keySet().iterator();
				while(it.hasNext()) {
					String key = it.next();
					System.out.println(String.format("%s => %s", key, rb.getString(key)));
				}
				
				// New way
				rb.keySet().stream()
					.map(k -> k + " => " + rb.getString(k))
					.forEach(System.out::println);
			}
			
			public void usingProperties(Locale locale) {
				System.out.println("# Start of usingProperties for " + locale);
				
				ResourceBundle rb = ResourceBundle.getBundle("Zoo", locale);
				Properties props = new Properties();
				// Filling the props up
				rb.keySet().stream()
					.forEach(k -> props.setProperty(k, rb.getString(k)));
				
				System.out.println(props.getProperty("Not reallyProperty")); // null
				System.out.println(props.getProperty("Not reallyProperty", "123")); // 123
			}
			
		}
		
		MyTest test = new MyTest();
		test.printProperties(us);
		test.printProperties(france);
		
		test.loppingThroughIt(us);
		test.usingProperties(us);

		System.out.println();
	}
	
	public static void messageFormatExampleFun() {
		System.out.println("------- messageFormatExampleFun");
		
		Locale us = new Locale("en", "US");
		ResourceBundle rb = ResourceBundle.getBundle("Zoo", us);
		
		String myValue = rb.getString("helloByName");
		String myValueFormatted = MessageFormat.format(myValue, "Antunes");
		System.out.println(String.format("myValue: %s\r\nmyValueFormatted: %s", 
				myValue,
				myValueFormatted));
		
		System.out.println();
	}
	
	
}