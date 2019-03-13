package test

import test.HttpJsons.{Location, Place}

object ForTest {

  val person = "person"
  val ips = List("1", "2", "3")

  def main(args: Array[String]): Unit = {

    val c = for {
      a <- person; b <- ips
    } yield (a, b)
    println(c)
  }

}
