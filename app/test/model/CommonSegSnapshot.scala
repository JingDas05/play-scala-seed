package test.model

import play.api.libs.json.Json

/**
  * @author wusi
  * @since 2019-04-09 20:13
  */
case class CommonSegSnapshot(conditionExpr: String, conditions: Seq[Demo])

object CommonSegSnapshot {
  implicit val format = Json.format[CommonSegSnapshot]
}