/**
 * 
 */
package com.sebrown.cardealer.datamodel.util;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream; 

/**
 * @author Steve Brown
 *
 * Calculates the number of business days between 2 dates (inclusive).
 *
 *	** Will throw DateTimeException if an invalid date is entered.
 *	** Doesn't include public holidays public holidays.
 *	
 */
public class BusinessDaysCalculator {
	
	public static long calculateDaysBetweenDates(LocalDate startDate, LocalDate endDate) {
		long businessDays = 0;
		long numDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);				
		if(numDaysBetween > 0) {
			businessDays = IntStream.iterate(0, i -> i + 1).limit(numDaysBetween + 1).mapToObj(startDate::plusDays)
				.filter(d -> Stream.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY)
				.anyMatch(d.getDayOfWeek()::equals)).count();
		}
		return businessDays;
	}
}
