package test

import org.joda.time.DateTime

object CaseTest {

  val empty = Option.empty
  val value = Option.apply(new DateTime())


  def main(args: Array[String]): Unit = {
    println(empty)
    println(value)
    println("end")
  }
}
