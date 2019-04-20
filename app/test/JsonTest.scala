package test

import javax.inject.Singleton
import play.api.libs.json.{Json, OFormat}
import test.model.{CommonSegSnapshot, Demo}

/**
  * @author wusi
  * @since 2019-04-09 19:41
  */
@Singleton
class JsonTest {

  implicit val format: OFormat[CommonSegSnapshot] = Json.format[CommonSegSnapshot]
  implicit val demoFormat: OFormat[Demo] = Json.format[Demo]

  def test: Unit = {
    val snapshot = Json.parse("{\n    \"conditionExpr\": \"A\",\n    \"conditions\": [\n        {\n            \"type\": \"dimension\"}]}")
    val value: CommonSegSnapshot = snapshot.validate[CommonSegSnapshot].get
    println(value)
  }
}