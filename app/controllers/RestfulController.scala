package controllers


import javax.inject.{Inject, Singleton}
import model.{Repository, RestfulRepository}
import play.api.libs.json._
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

@Singleton
class RestfulController @Inject()(cc: ControllerComponents, repository: Repository) extends AbstractController(cc) {

  // 定义类和隐私转换参数
  case class Person(name: String, age: Int)

  implicit val personReads = Json.reads[Person]

  def getResult(id: String) = Action { implicit request =>
    Ok(repository.getResults() + "[" + id + "]")
  }

  def getResultWith(id: String, name: String, age: Int) = Action { implicit request =>
    Ok(repository.getResults() + "| " + id + "| " + name + "| " + age)
  }

  def postResult() = Action { implicit request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson

    //println((jsonBody.get \ "name").get)
    //println(jsonBody.get("name"))

    val person = repository.createPerson(jsonBody.get("name").as[String], jsonBody.get("age").as[Int])
    // 反序列化
    //val person: JsResult[Person] = Json.fromJson[Person](jsonBody.get)
    //println(person.get.name)
    Ok(person.isCompleted.toString)
  }
}