package br.com.willianantunes.exam5;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

interface MyGoodInterface {
 int example();	
}

abstract class Mammal {
	abstract Long fly();
}

public class Egret2 {
	private String color;
	public void Egret() {
		Egret("white");
	}
	public void Egret(String color) {
		color = color;
	}
	public static void main(String[] args) {
//		Egret2 e = new Egret2();
//		System.out.println(e.color);
//		doIt((Mammal)new Object());
		DateTimeFormatter asdss = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		List<Egret2> asd = new ArrayList();
		asd.add(null);
		asd.add((Egret2)new Object());
		int a = 10;
		switch (a) {
		case 1*1:
			
			break;

		default:
			break;
		}
		/*
		final StringBuilder s3 = new StringBuilder();
		s3.append("asdasd").deleteCharAt(5);
		System.out.println(s3);*/
	}
	
	private static void doIt(Mammal mamal) {
		
	}
}
