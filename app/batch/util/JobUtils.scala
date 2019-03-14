package batch.util

import org.joda.time.DateTime
import org.springframework.batch.core.JobParameter

object JobUtils {

  // scala param -> java param
  def getJobParameter(parameter: Any): JobParameter = {
    parameter match {
      case long: Long =>
        new JobParameter(long.asInstanceOf[java.lang.Long])
      case string: String =>
        new JobParameter(string.asInstanceOf[java.lang.String])
      case double: Double =>
        new JobParameter(double.asInstanceOf[java.lang.Double])
      case date: DateTime =>
        new JobParameter(date.toDate)
    }
  }

}
