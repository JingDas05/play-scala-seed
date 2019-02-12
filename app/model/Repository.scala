package model

import com.google.inject.ImplementedBy

@ImplementedBy(classOf[RestRepository])
trait Repository {

  def getResults(): String
}
