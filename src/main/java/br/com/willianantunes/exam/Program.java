package br.com.willianantunes.exam;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Collections.*;

abstract class ProgramExample implements Climb {

	ProgramExample(int age) {
		System.out.println(age);
	}
	public static final String meuAmor = "";
	protected static Integer chew() throws Exception {
		return null;
	}
	public static void asdsad() {
		
	}

	@Override
	public boolean isTooHigh(int height, int limit) {
		// TODO Auto-generated method stub
		return false;
	}
}

public class Program extends ProgramExample
{	
	public static Integer chew() throws RuntimeException {
		return null;
	}
	
	public Program() {
		super(1);
		System.err.println("");
	}
	public void program() throws RuntimeException{
		Climb object = new Program();
		System.out.println(this.test);
	}
	
	int age;
    public static void main( String[] args ) {
//    	Program program = new Program();
//    	program.age = 1;
//    	check(program, p -> p.age < 5);
    	
    	List<String> list = new ArrayList();
    	list.removeIf(s -> s.isEmpty());
    	list.removeIf(s -> {return s.isEmpty();});
    	list.removeIf((String s) -> s.isEmpty());
    }
    /*
    private static void check(Program program, Predicate<Program> pred) {
    	System.out.println(pred.test(program)? "matched" : "not matched");
    }*/
    private static void check(Climb climb, int height) throws Exception {
    	System.out.println(climb.isTooHigh(height, 10)? "too high" : "ok");
    }

	@Override
	public int testttt() {
		// TODO Auto-generated method stub
		return 0;
	}
}

class B{
    static void x() {
        System.out.println("x");
    }
    static void y() {
        System.out.println("y");
    }
}
class A extends B {
    public static void main(String[] args) {
        x();
        A.y();
    }
}