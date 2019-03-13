package test

object SaltTest {

  def main(args: Array[String]): Unit = {


    import org.hashids.Hashids

    val salt = "+WX2bv6ctTAh0G"

    val hashId = new Hashids(salt, 8)

     println(hashId.encode(1))

  }
}
