package model

import com.google.inject.ImplementedBy

import scala.concurrent.Future

@ImplementedBy(classOf[RestRepository])
trait Repository {

  def createUser(person: User): Future[User]

  def deleteUserBy(id: Long)

  def updateUserBy(person: User): Unit

  def getUserBy(id: Long): Future[Seq[User]]

  def getUsers: Future[Seq[User]]
}
