package br.com.willianantunes.examocp.chap5;

import java.util.Locale;
import java.util.ResourceBundle;

public class QuestionFiveTest {
	public static void main(String args[]) {
		Locale fr = new Locale("fr");
		Locale.setDefault(new Locale("en", "US"));
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("Dolphins", fr);
		System.out.println(resourceBundle.getString("name"));
		// "age" is not found in Dolphins_fr, so it has to look higher up in the hierarchy.
		// Therefore Dolphin.java and then Dolphin.properties 
		System.out.println(resourceBundle.getString("age"));
	}
}
