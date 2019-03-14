package controllers


import batch.JobAction
import javax.inject.{Inject, Singleton}
import model.{Repository, RestfulRepository, User}
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import play.api.Play
import play.api.libs.json._
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class RestfulController @Inject()(cc: ControllerComponents,
                                  repository: Repository,
                                  jobAction: JobAction) extends AbstractController(cc) {


  implicit val personReads = Json.reads[model.User]
  implicit val ec = ExecutionContext.global

  def getUserBy(id: String) = Action.async { implicit request =>
    repository.getUserBy(id.toLong).map {
      users => Ok(users.head.toString)
    }
  }

  def getUsers() = Action.async { implicit request: Request[AnyContent] =>
    jobAction.start()
    repository.getUsers.map(users => Ok(users.toString))

  }

  def createUser() = Action.async { implicit request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson

    //println((jsonBody.get \ "name").get)
    //println(jsonBody.get("name"))

    val person = repository.createUser(User(0, jsonBody.get("name").as[String], jsonBody.get("age").as[Int]))

    val id = jsonBody.get("id")
    println(id)

    // 反序列化
    //val person: JsResult[Person] = Json.fromJson[Person](jsonBody.get)
    //println(person.get.name)
    person.flatMap(p => Future.successful(p))
    person.map { p =>
      Ok(p.name)
    }
  }

//  def createUser() = Action.async(parse.tolerantJson) { implicit request =>
//
//    request.body.validate[]
//
//    // 反序列化
//    //val person: JsResult[Person] = Json.fromJson[Person](jsonBody.get)
//    //println(person.get.name)
//    person.flatMap(p => Future.successful(p))
//    person.map { p =>
//      Ok(p.name)
//    }
//  }

  def updateUserBy() = Action { implicit request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson
    // 反序列化
    val person: JsResult[model.User] = Json.fromJson[model.User](jsonBody.get)
    println(person.get.id)
    repository.updateUserBy(person.get)
    Ok
  }

  def deleteUserBy(id: String) = Action { implicit request: Request[AnyContent] =>
    repository.deleteUserBy(id.asInstanceOf[Long])
    Ok("ok")
  }

}