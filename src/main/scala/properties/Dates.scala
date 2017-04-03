package properties

import java.text.SimpleDateFormat
import java.util.Date

object Dates {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS")
  def toString(d: Date) = dateFormat.format(d)
  def fromString(s: String) = dateFormat.parse(s)

}