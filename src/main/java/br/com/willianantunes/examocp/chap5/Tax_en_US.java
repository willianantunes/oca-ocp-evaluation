package br.com.willianantunes.examocp.chap5;

import java.util.ListResourceBundle;
import java.util.ResourceBundle;

public class Tax_en_US extends ListResourceBundle {

	static class UstaxCode {
		private int myTax = 10;
		
		public int getMyTax() {
			return myTax;
		}
		
		public String toString() {
			return String.valueOf(myTax);
		}
	}
	
	@Override
	protected Object[][] getContents() {		
		return new Object[][] {
			{ "tax", new UstaxCode() }
		};
	}
	
	public static void main(String args[]) {
		ResourceBundle rb = ResourceBundle.getBundle("br.com.willianantunes.examocp.chap5.Tax");
		System.out.println(rb.getObject("tax"));
	}
}