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

  override def getResults(): String = {
    getAllPerson
    "RestRepository#getResults"
  }


  //  查询对象
  private class PersonTable(tag: Tag) extends Table[Person](tag, "people") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def age = column[Int]("age")

    override def * = (id, name, age) <> ((Person.apply _).tupled, Person.unapply)
  }

  private val people = TableQuery[PersonTable]

  def createPerson(name: String, age: Int): Future[Person] = db.run {
    // We create a projection of just the name and age columns, since we're not inserting a value for the id column
    (people.map(p => (p.name, p.age))
      // Now define it to return the id, because we want to know what id was generated for the person
      returning people.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((nameAge, id) => Person(id, nameAge._1, nameAge._2))
      // And finally, insert the person into the database
      ) += (name, age)
  }

  def getAllPerson: Future[Seq[Person]] = db.run {
    people.result
  }

}