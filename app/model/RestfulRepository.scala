package model

import scala.concurrent.Future

class RestfulRepository extends Repository {

  @Override
  def getResults(): String = {
    "RestfulRepository#getResults"
  }

  //  返回默认的结果
  override def createPerson(name: String, age: Int): Future[Person] = Future.successful(Person(0, "default", 0))
}
