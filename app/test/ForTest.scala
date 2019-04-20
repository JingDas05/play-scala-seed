package test

import play.api.libs.json.{JsPath, JsValue, Json, JsonValidationError}
import play.api.mvc.Result
import play.api.mvc.Results.BadRequest
import test.HttpJsons.{Location, Place}

import scala.concurrent.Future

object ForTest {


  def main(args: Array[String]): Unit = {

    //    decareFor
    val subErrors1 = Seq(JsonValidationError("1"), JsonValidationError("2"), JsonValidationError("3"))
    val subErrors2 = Seq(JsonValidationError("4"), JsonValidationError("5"), JsonValidationError("6"))

    asyncJsonBadRequestV2()(Seq("a" -> subErrors1, "b" -> subErrors2))

  }

  def asyncJsonBadRequestV2()(errors: Seq[(String, Seq[JsonValidationError])]): Future[Result] = {
    val returnMsg: JsValue = Json.obj("errors" -> Json.toJson(
      for (
        seqError <- errors.map(_._2)
      ) yield Json.obj(
        "code" -> seqError.map(e => e.message).last,
        "message" -> seqError.map(e => e.message).last
      )
    ))
    Future.successful(BadRequest(returnMsg))
  }

  def decareFor(): Unit = {

    val person = "person"
    val ips = List("1", "2", "3")

    val c = for {
      a <- person; b <- ips
    } yield (a, b)
    println(c)
  }

}
