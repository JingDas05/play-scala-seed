package test

object HttpJsons {

  case class Location(lat: Double, long: Double)

  case class Place(name: String, location: Location)

  object Place {

    var list: List[Place] = {
      List(
        Place(
          "Sandleford",
          Location(51.377797, -1.318965)
        ),
        Place(
          "Watership Down",
          Location(51.235685, -1.309197)
        )
      )
    }

    var list2: List[Place] = List(Place("name",Location(11,11)),Place("name",Location(11,11)))

    def save(place: Place) = {
      list = list ::: List(place)
    }
  }

  def main(args: Array[String]): Unit = {
    Place.save(Place("name", Location(11, 11)))
    println(Place.list)
  }

}
