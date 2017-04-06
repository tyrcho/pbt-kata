package properties

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

object Dates {
    internal val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS"
    internal val dateFormat: DateFormat = SimpleDateFormat(pattern)

    fun toString(d: Date) = dateFormat.format(d)!!
    fun fromString(s: String) = dateFormat.parse(s)!!

    fun nextDay(s: String): String {
        val c = Calendar.getInstance()
        c.time = fromString(s)
        c.add(Calendar.DAY_OF_YEAR, 1)
        return toString(c.time)
    }

    fun nextDayJava8(s: String): String {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        val localDate = LocalDateTime.parse(s, formatter)
        val date2 = localDate.plus(1, ChronoUnit.DAYS)
        return date2.format(formatter)
    }

}
