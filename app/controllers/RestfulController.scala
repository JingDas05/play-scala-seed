package controllers

import javax.inject.{Inject, Singleton}
import model.RestfulRepository
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

@Singleton
class RestfulController @Inject()(cc: ControllerComponents, repository: RestfulRepository) extends AbstractController(cc) {


  def getResult() = Action { implicit request =>
    println(request.body)
    println(request.contentType)
    Ok(repository.getResults() + "[" + request + "]")
  }
}