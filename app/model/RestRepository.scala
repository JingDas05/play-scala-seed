package model

import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.ControllerComponents
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

//注入 DatabaseConfigProvider 以及 获取db
class RestRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
                               cc: ControllerComponents)
                              (implicit ec: ExecutionContext) extends Repository with HasDatabaseConfigProvider[JdbcProfile] {

  // 引入 slick DSL
  import profile.api._

  //  查询对象
  private class UserTable(tag: Tag) extends Table[User](tag, "people") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def age = column[Int]("age")

    override def * = (id, name, age) <> ((User.apply _).tupled, User.unapply)
  }

  private val user = TableQuery[UserTable]

  def createUser(person: User): Future[User] = db.run {
    // We create a projection of just the name and age columns, since we're not inserting a value for the id column
    (user.map(p => (p.name, p.age))
      // Now define it to return the id, because we want to know what id was generated for the person
      returning user.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((nameAge, id) => User(id, nameAge._1, nameAge._2))
      // And finally, insert the person into the database
      ) += (person.name, person.age)
  }

  def getUsers: Future[Seq[User]] = db.run {
    user.result
  }

  def updateUserBy(person: User): Unit = {
    user.filter(_.id === person.id)
      .map(p => (p.name, p.age))
      .update(("sas", 5432))

  }

  def deleteUserBy(id: Long) {
    user.filter(person => person.id === id).delete
  }

  def getUserBy(id: Long): Future[Seq[User]] = db.run {
    user.filter(user => user.id === id).result
  }
}