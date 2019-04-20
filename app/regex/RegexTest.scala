package regex

/**
  * @author wusi
  * @since 2019-04-15 19:47
  */
object RegexTest extends App {

  val nameRegex = """^[\\(\\)a-zA-Z0-9\u4e00-\u9fa5_\-/]{1,50}$""".r
  val nameRegex1 = """[`~!@#$%^&*+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]""".r
  val source = "触达tr测试(1)"

  test

  def test = {
    println(nameRegex1.findAllIn(source).isEmpty && source.length < 25)
  }

}
