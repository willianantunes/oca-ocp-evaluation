package br.com.willianantunes.examocp;

public class OuterMemberInnerClass {
	private int x = 5;
	protected class Inner {
		private static final int x = 10;
		private int b;
		public void go() {
			System.out.println();
		}
	}
}
