package controllers

import java.lang

import javax.inject.{Inject, Singleton}
import model.{Repository, RestfulRepository}
import play.api.libs.json.JsValue
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

@Singleton
class RestfulController @Inject()(cc: ControllerComponents, repository: Repository) extends AbstractController(cc) {


  def getResult(id: String) = Action { implicit request =>
    Ok(repository.getResults() + "[" + id + "]")
  }

  def getResultWith(id: String, name: String, age: Int) = Action { implicit request =>
    Ok(repository.getResults() + "| " + id + "| " + name + "| " + age)
  }

  def postResult() = Action { implicit request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson

    jsonBody.map {
      json => println((json\ "name").as[String])
    }
    Ok("post with body")
  }

}