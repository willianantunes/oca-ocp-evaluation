package br.com.willianantunes.examocp.chap5;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

public class QuickStart {
	public static void main(String[]args) {

		// zoneIdFun();
		periodFun();
		durationFun();
		chronoUnitForDifferencesFun();
		instantsFun();
		dayLightSavingFun();
	}
	
	public static void zoneIdFun() {
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
	}
	
	public static void periodFun() {
		System.out.println("------- periodFun");
		
		// Period (intended for day or more) String output
		System.out.println(Period.of(1, 0, 3)); // P1Y3D
		System.out.println(Period.of(0, 2, 3)); // P2M3D
		System.out.println(Period.of(1, 2, 3)); // P1Y2M3D
		System.out.println(Period.ofMonths(2)); // P2M
		System.out.println(Period.ofDays(1)); // P1D
		
		System.out.println();
	}
	
	public static void durationFun() {
		System.out.println("------- durationFun");
		
		// Duration (intended for day or smaller) String output
		Duration daily = Duration.of(1, ChronoUnit.DAYS); System.out.println(daily); // PT24H
		daily = Duration.ofDays(1);
		Duration hourly = Duration.of(1, ChronoUnit.HOURS); System.out.println(hourly); // PT1H
		hourly = Duration.ofHours(1);
		Duration everyMinute = Duration.of(1, ChronoUnit.MINUTES); System.out.println(everyMinute); // PT1M
		everyMinute = Duration.ofMinutes(1);
		Duration everyTenSeconds = Duration.of(10, ChronoUnit.SECONDS); System.out.println(everyTenSeconds); // PT10S
		everyTenSeconds = Duration.ofSeconds(10);
		Duration everyMilli = Duration.of(10, ChronoUnit.MILLIS); System.out.println(everyMilli); // PT0.01S
		everyMilli = Duration.ofMillis(1);
		Duration everyNano = Duration.of(10, ChronoUnit.NANOS); System.out.println(everyNano); // PT0.00000001S
		everyNano = Duration.ofNanos(1);
		
		System.out.println();
	}
	
	/**
	 * ChronoUnit is a great way to determine how far apart two temporal values are.
	 * Temporal includes LocalDate, LocalTime and so on.
	 */
	public static void chronoUnitForDifferencesFun() {
		System.out.println("------- chronoUnitForDifferencesFun");
		
		LocalTime one = LocalTime.of(4, 30);
		LocalTime two = LocalTime.of(6, 30);
		LocalDate dateOne = LocalDate.of(2016, 1, 20);
		LocalDate dateTwo = LocalDate.of(2016, 1, 28);
		
		System.out.println(ChronoUnit.HOURS.between(one, two)); // 2
		two = LocalTime.of(6, 29);
		System.out.println(ChronoUnit.HOURS.between(one, two)); // 1
		System.out.println(ChronoUnit.MINUTES.between(one,  two)); // 119
		// System.out.println(ChronoUnit.MINUTES.between(one,  dateOne)); // java.time.DateTimeException: Unable to obtain LocalTime from TemporalAccessor: 2016-01-20 of type java.time.LocalDate
		System.out.println(ChronoUnit.DAYS.between(dateOne, dateTwo)); // 8
		
		LocalDate date = LocalDate.of(2015, 1, 20);
		LocalTime time = LocalTime.of(6, 15);
		LocalDateTime dateTime = LocalDateTime.of(date, time);
		System.out.println(dateTime); // 2015-01-20T06:15
		Duration duration = Duration.ofHours(6);
		System.out.println(dateTime.plus(duration)); // 2015-01-20T12:15
		System.out.println(time.plus(duration)); // 12:15
		// System.out.println(date.plus(duration)); // java.time.temporal.UnsupportedTemporalTypeException: Unsupported unit: Seconds
		
		System.out.println();
	}
	
	/**
	 * It represents a specific moment in time in the GTMT time zone.
	 */
	public static void instantsFun() {
		System.out.println("------- instantsFun");
		
		// You can do somenthing like that...
		Instant now = Instant.now(); System.out.println(now); // 2016-11-18T09:41:53.373Z
		// Do something time consuming.
		Instant later = Instant.now(); System.out.println(later); // 2016-11-18T09:41:53.382Z
		System.out.println("Duration between them: " + Duration.between(now, later)); // Duration between them: PT0.009S
		
		// Turm ZonedDateTime into an Instant
		LocalDate date = LocalDate.of(2015, 5, 25);
		LocalTime time = LocalTime.of(11,  55, 00);
		ZoneId zone = ZoneId.of("US/Eastern");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(date,  time, zone);
		System.out.println(zonedDateTime); // 2015-05-25T11:55-04:00[US/Eastern]
		Instant instant = zonedDateTime.toInstant();
		System.out.println(instant); // 2015-05-25T15:55:00Z
		
		System.out.println(date.toEpochDay()); // it's the number of days since January 1, 1970
		System.out.println(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()); // It's the number of seconds since January 1, 1970
		
		// If you have the number of seconds since 1970...
		System.out.println(Instant.ofEpochSecond(1479462111)); // 2016-11-18T09:41:51Z
		
		System.out.println(Instant.now().plus(1, ChronoUnit.DAYS)); // Add 1 day
		System.out.println(Instant.now().plus(1, ChronoUnit.HOURS)); // Add 1 hour
		// Though it displays year and month, you can't do math with those fields.
		// System.out.println(Instant.now().plus(1, ChronoUnit.WEEKS)); // UnsupportedTemporalTypeException: Unsupported unit: Weeks

		System.out.println();
	}
	
	public static void dayLightSavingFun() {
		System.out.println("------- dayLightSavingFun");
		
		// Move forward
		LocalDate date = LocalDate.of(2016, Month.MARCH, 13);
		LocalTime time = LocalTime.of(1, 30);
		ZoneId zone = ZoneId.of("US/Eastern");
		ZonedDateTime zonedDateTimeMarch = ZonedDateTime.of(date,  time, zone);
		
		System.out.println(zonedDateTimeMarch); // 2016-03-13T01:30-05:00[US/Eastern]
		// Notice daylight.. The time jumps from 1:30 to 3:30
		// We went from 6:30 GMT (1:30 minus -5:00) to 7:30 GMT (3:30 minus -4:00).
		System.out.println(zonedDateTimeMarch.plusHours(1)); // 2016-03-13T03:30-04:00[US/Eastern]
		
		// Fallback - 1:30 is also 1:30 because at 2:00 am we repeat the hour.
		date = LocalDate.of(2016, Month.NOVEMBER, 6);
		time = LocalTime.of(1, 30);
		ZonedDateTime zonedDateTimeNovember = ZonedDateTime.of(date,  time, zone);
		System.out.println(zonedDateTimeNovember); // 2016-11-06T01:30-04:00[US/Eastern]
		System.out.println(zonedDateTimeNovember.plus(1, ChronoUnit.HOURS)); // 2016-11-06T01:30-05:00[US/Eastern]
		
		System.out.println();
	}
}
