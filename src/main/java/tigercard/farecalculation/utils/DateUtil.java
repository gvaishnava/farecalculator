package tigercard.farecalculation.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * Util class to have common function used across application for date function
 *
 */
public class DateUtil {

	/**
	 * Get days of week based on int value return from calendar instance
	 * 
	 * @param day
	 * @return return string representation of code
	 */
	public static String getDay(int day) {
		if (day == 0) {
			return "SUNDAY";
		} else if (day == 1) {
			return "MONDAY";
		} else if (day == 2) {
			return "TUESDAY";
		} else if (day == 3) {
			return "WEDNESDAY";
		} else if (day == 4) {
			return "THRUSDAY";
		} else if (day == 5) {
			return "FRIDAY";
		} else if (day == 6) {
			return "SATURDAY";
		} else {
			return "";
		}
	}

	/**
	 * To identify input journey date in current week or next week
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return boolean return true if journey date is in current week
	 */
	boolean isDateInCurrentWeek(int year, int month, int day) {
		LocalDate today = LocalDate.now(); // involves the system time zone and clock of system
		LocalDate startOfCurrentWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		LocalDate endOfNextWeek = startOfCurrentWeek.plusDays(13);

		LocalDate event = LocalDate.of(year, month, day); // input of example given by OP

		boolean matchingResult = !(event.isBefore(startOfCurrentWeek) || event.isAfter(endOfNextWeek));

		return matchingResult;
	}

}
