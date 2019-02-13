package model

import com.google.inject.ImplementedBy

import scala.concurrent.Future

@ImplementedBy(classOf[RestRepository])
trait Repository {

  def getResults(): String

  def createPerson(name: String, age: Int): Future[Person]
}
