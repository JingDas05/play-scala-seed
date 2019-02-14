package model

abstract class RestfulRepository extends Repository {


  def getResults(): String = {
    "RestfulRepository#getResults"
  }
}