package br.com.willianantunes.exam5;

public class Egret {
	private String color;
	public Egret() {
		this("green");
	}
	
	public Egret(String color) {
		color = color;
	}
	
	public static void main(String...strings) {
		StringBuilder sb = new StringBuilder();
		sb.append("aaa").insert(1, "bb").insert(4, "ccc");
		System.out.println(sb);
	}
}
