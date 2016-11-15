package br.com.willianantunes.exam;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

class Star {
    public void doStuff() {
        System.out.println("Twinkling Star");
    }
}
interface Universe {
    public void doStuff();
    abstract void getMyInt();
}
class Sun extends Star implements Universe {
    public void doStuff() {
        System.out.println("Shining Sun");
    }

	@Override
	public void getMyInt() {
		// TODO Auto-generated method stub
		
	}
}
public class Bob {
	private void freeze() { inTheHeadLights(); }
	public static void inTheHeadLights() {};
	
    public static void main(String[] args) {
        int i = 25;
        int j = i+++1;
        int a = ++i+1;
        if (j % 5 == 0) {
            System.out.println(j + " is divisible by 5");
        } else {
            System.out.println(j + " is not divisible by 5");
        }
        System.out.println("Done");
        
        List<Integer> list = new ArrayList<>();
        list.remove

    }
}