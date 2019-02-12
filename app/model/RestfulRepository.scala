package model

class RestfulRepository extends Repository {

  @Override
  def getResults(): String = {
    "RestfulRepository#getResults"
  }
}
