package properties

object DummyDao {
  private val personsDb = collection.mutable.Map.empty[String, Person]

  def insert(k: String, p: Person) = {
    personsDb += k -> p
  }

  def get(k: String): Option[Person] = {
    personsDb.get(k)
  }
}