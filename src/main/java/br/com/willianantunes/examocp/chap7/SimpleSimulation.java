package br.com.willianantunes.examocp.chap7;

public class SimpleSimulation {
	
	public static void main(String args[]) {
		// It uses a total of four threads - the main() user thread and 
		// three additional threads created by the main() method. 
		
		System.out.println("begin");
		
		new ReadInventoryThread().start();
		new Thread(new PrintData()).start();
		new ReadInventoryThread().start();
		
		System.out.println("end");
		
		/**
		 * Output sample:

begin
Printing zoo inventory
end
Printing zoo inventory
Printing record: 0
Printing record: 1
Printing record: 2

		 */		
	}
}
