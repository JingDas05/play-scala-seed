package controllers

import javax.inject.{Inject, Singleton}
import model.{Repository, RestfulRepository}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

@Singleton
class RestfulController @Inject()(cc: ControllerComponents, repository: Repository) extends AbstractController(cc) {


  def getResult(id: String) = Action { implicit request =>
    Ok(repository.getResults() + "[" + id + "]")
  }

  def getResultWith(id: String, name: String, age: Int) = Action { implicit request =>
    Ok(repository.getResults() + "| " + id + "| " + name + "| " + age)
  }

}