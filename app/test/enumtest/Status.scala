package test.enumtest

object Status extends Enumeration {
  type Status = Value

  def delete = Value("deleted")

}
