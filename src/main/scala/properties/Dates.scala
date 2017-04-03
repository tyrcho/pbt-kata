package properties

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Calendar

object Dates {
  val pattern = "yyyy-MM-dd HH:mm:SS"
  val dateFormat = new SimpleDateFormat(pattern)
  def toString(d: Date) = dateFormat.format(d)
  def fromString(s: String) = dateFormat.parse(s)

  def nextDay(s: String) = {
    val c = Calendar.getInstance
    c.setTime(fromString(s))
    c.add(Calendar.DAY_OF_YEAR, 1)
    toString(c.getTime)
  }

  import com.github.nscala_time.time.Imports._

  val formatJoda = DateTimeFormat.forPattern(pattern)

  def nextDayJoda(s: String) = {
    val d = formatJoda.parseDateTime(s)
    formatJoda.print(d + 1.day)
  }
}