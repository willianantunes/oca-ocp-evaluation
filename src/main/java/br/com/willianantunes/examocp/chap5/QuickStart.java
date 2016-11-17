package br.com.willianantunes.examocp.chap5;

import java.time.Period;
import java.time.ZoneId;
import java.util.stream.Collectors;

public class QuickStart {
	public static void main(String[]args) {
		// Finding your time zone
		System.out.println(ZoneId.systemDefault());
		
		// Listing a part of them
		String result = ZoneId.getAvailableZoneIds().stream()
			// .peek(System.out::println) // Listing all of them
			.filter(z -> {  
				return z.contains("America/"); 
			})
			.sorted()
			.collect(Collectors.joining(", "));
		System.out.println(result);
		
		// Period (intended for day or more) String output
		System.out.println(Period.of(1, 0, 3)); // P1Y3D
		System.out.println(Period.of(0, 2, 3)); // P2M3D
		System.out.println(Period.of(1, 2, 3)); // P1Y2M3D
		System.out.println(Period.ofMonths(2)); // P2M
		System.out.println(Period.ofDays(1)); // P1D
		
		// Duration (intended for day or smaller) String output
	}
}
