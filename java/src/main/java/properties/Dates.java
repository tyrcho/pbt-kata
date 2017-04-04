package properties;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates {
	static String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	static DateFormat dateFormat = new SimpleDateFormat(pattern);

	public static String toString(Date d) {
		return dateFormat.format(d);
	}

	public static Date fromString(String s) throws ParseException {
		return dateFormat.parse(s);
	}

	// def nextDay(s: String) = {
	// val c = Calendar.getInstance
	// c.setTime(fromString(s))
	// c.add(Calendar.DAY_OF_YEAR, 1)
	// toString(c.getTime)
	// }
}
