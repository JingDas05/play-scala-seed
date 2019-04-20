package test.model

import play.api.libs.json.Json

/**
  * @author wusi
  * @since 2019-04-09 20:13
  */
case class Demo(`type`: String,
                alias: String,
                flag: Boolean,
                id: String,
                range: String,
                op: Option[String],
                docId: Option[Int],
                values: Option[Seq[String]], dimFiltersOp: Option[String])

object Demo {
  implicit val demoFormat = Json.format[Demo]
}
