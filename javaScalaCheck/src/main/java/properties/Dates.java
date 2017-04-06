package properties;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;

public class Dates {
	static String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	static DateFormat dateFormat = new SimpleDateFormat(pattern);

	public static String nextDay(String s) throws ParseException {
		Calendar c = Calendar.getInstance();
		c.setTime(fromString(s));
		c.add(Calendar.DAY_OF_YEAR, 1);
		return toString(c.getTime());
	}

	public static String nextDayJava8(String s) throws ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDateTime localDate = LocalDateTime.parse(s, formatter);
		LocalDateTime date2 = localDate.plus(1, ChronoUnit.DAYS);
		return date2.format(formatter);
	}

	public static String toString(Date d) {
		return dateFormat.format(d);
	}

	public static Date fromString(String s) throws ParseException {
		return dateFormat.parse(s);
	}

}
