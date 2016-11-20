package br.com.willianantunes.examocp.chap6;

/**
 * Rules with try-with-resources statemets:
 * 1 - Resources are closed after the try clause ends and before any catch/finally clauses.
 * 2 - Resources are closed in the reverse order from which they were created.
 */
public class AutoCloseableExample {
	
	public static void main(String []args) {
		// example3();
		// suppresedException();
		// suppresedExceptionIncorrect();
		figureItOUt();
	}
	
	/**
	 * You can't put any random class in a try-with-resources statement.
	 * In order to do it, Java requires it to implement an interface called AutoCloseable.
	 */
	public static class TurkeyCage implements AutoCloseable {
		@Override
		public void close() throws Exception {
			System.out.println("Close gate");
		}		
	}
	
	public static class TurkeyCage2 implements AutoCloseable {
		@Override
		public void close() throws IllegalArgumentException {
			throw new IllegalArgumentException("AutoCloseable !Hã!");
		}		
	}
	
	public static class Auto implements AutoCloseable {
		int num;
		Auto(int num) {
			this.num = num;
		}
		
		@Override
		public void close() throws Exception {
			System.out.println(this.num);
		}		
	}
	
	public static void example1() throws /* Do not forget this */ Exception {
		try(TurkeyCage t = new TurkeyCage()) {
			
		}
	}
	
	public static void example2() {
		try(TurkeyCage t = new TurkeyCage()) {
			
		} catch /* Do not forget this */ (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void example3() {
		try(TurkeyCage2 t = new TurkeyCage2()) {
			
		} catch /* Do not forget this */ (IllegalArgumentException e) {
			System.out.println("An IllegalArgumentException was caught! :)");
		}
	}
	
	public static void suppresedException() {
		try(TurkeyCage2 t = new TurkeyCage2()) {
			throw new IllegalStateException("turkeys ran offfffff! Ah! I'm tired of typing it.");
		} catch (IllegalStateException e) {
			System.out.println("caught " + e.getMessage());
			for (Throwable t : e.getSuppressed()) {
				System.out.println(e.getMessage());
			}
		}
		/* OUTPUT:
caught turkeys ran offfffff! Ah! I'm tired of typing it.
turkeys ran offfffff! Ah! I'm tired of typing it.
		 */
	}
	
	public static void suppresedExceptionIncorrect() {
		try(TurkeyCage2 t = new TurkeyCage2()) {
			throw new IllegalStateException("turkeys ran offfffff! Ah! I'm tired of typing it.");
		} catch (IllegalArgumentException e) {
			System.out.println("caught " + e.getMessage());
			for (Throwable t : e.getSuppressed()) {
				System.out.println(e.getMessage());
			}
		}
		/* Catch block look for matches on the primary exception, that is IllegalStateException!
		 * But we tried to catch IllegalArgumentException, which is wrong.
		 * OUTPUT:
Exception in thread "main" java.lang.IllegalStateException: turkeys ran offfffff! Ah! I'm tired of typing it.
	at br.com.willianantunes.examocp.chap6.AutoCloseableExample.suppresedExceptionIncorrect(AutoCloseableExample.java:68)
	at br.com.willianantunes.examocp.chap6.AutoCloseableExample.main(AutoCloseableExample.java:8)
	Suppressed: java.lang.IllegalArgumentException: AutoCloseable !Hã!
		at br.com.willianantunes.examocp.chap6.AutoCloseableExample$TurkeyCage2.close(AutoCloseableExample.java:25)
		at br.com.willianantunes.examocp.chap6.AutoCloseableExample.suppresedExceptionIncorrect(AutoCloseableExample.java:69)
		... 1 more		
		 */
	}	
	
	public static void figureItOUt() {
		try (Auto a1 = new Auto(1); Auto a2 = new Auto(2)) {
			throw new RuntimeException();
		} catch (Exception e) {
			System.out.println("ex");
		} finally {
			System.out.println("finally");
		}
		/**
		 * It outputs:
				2
				1
				ex
				finally
		 */
	}
}
