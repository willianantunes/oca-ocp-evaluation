package br.com.willianantunes.examocp.chap5;

import java.time.*;
import java.time.format.*;

public class FormattingDatesTimes {
	public static void main(String args[]) {
		quickStart();
		shortMediumFun();
		yourOrnFormatterFun();
		convertingLikeACharm();
	}
	
	public static void quickStart() {
		System.out.println("------- quickStart");
		LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
		LocalTime time = LocalTime.of(11, 12, 34);
		LocalDateTime dateTime = LocalDateTime.of(date, time);
		System.out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE)); // 2020-01-20
		System.out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_TIME)); // 11:12:34
		System.out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); // 2020-01-20T11:12:34
		
		System.out.println();
		
		// There are some predefined formats that are more useful...
		DateTimeFormatter shortDateTime = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
		shortDateTime = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);		
		shortDateTime = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);		
		System.out.println(dateTime.format(shortDateTime)); // 20/01/20
		// The format() method is declared on both 
		System.out.println(shortDateTime.format(date)); // 20/01/20
		// System.out.println(shortDateTime.format(time)); // java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: DayOfMonth
				
		System.out.println();
	}
	
	public static void shortMediumFun() {
		System.out.println("------- shortMediumFun");
		
		LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
		LocalTime time = LocalTime.of(11, 12, 34);
		LocalDateTime dateTime = LocalDateTime.of(date, time);
		
		DateTimeFormatter shortF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		DateTimeFormatter mediumF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		
		System.out.println(dateTime.format(shortF)); // 20/01/20 11:12
		System.out.println(mediumF.format(dateTime)); // 20/01/2020 11:12:34
		
		System.out.println();
	}
	
	public static void yourOrnFormatterFun() {
		System.out.println("------- yourOrnFormatterFun");
		
		LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
		LocalTime time = LocalTime.of(11, 12, 34);
		LocalDateTime dateTime = LocalDateTime.of(date, time);
		
		String myPattern = "MMMM dd, yyyy, hh:mm";
		DateTimeFormatter f = DateTimeFormatter.ofPattern(myPattern);
		
		// MMMM M represents the month. The more Ms you have, the more verbose the Java output.
		// M outputs 1, MM outputs 01, MMM outputs jan, MMMM outputs january
		System.out.println(dateTime.format(f)); // Janeiro 20, 2020, 11:12
		System.out.println(dateTime.format(
				DateTimeFormatter
				.ofPattern("MMM dd, yyyy, hh:mm"))); // jan 20, 2020, 11:12
		System.out.println(dateTime.format(
				DateTimeFormatter
				.ofPattern("MM dd, yyyy, hh:mm"))); // 01 20, 2020, 11:12
		System.out.println(dateTime.format(
				DateTimeFormatter
				.ofPattern("M dd, yyyyyyy, hh:mm:ss"))); // 1 20, 0002020, 11:12:34		
		
		System.out.println();
	}
	
	public static void convertingLikeACharm() {
		System.out.println("------- convertingLikeACharm");
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern("MM dd yyyy");
		LocalDate date = LocalDate.parse("06 23 1989", f);
		System.out.println(date); // 1989-06-23
		
		LocalTime time = LocalTime.parse("12:26");
		System.out.println(time); // 12:26
		
		System.out.println();
	}
}
