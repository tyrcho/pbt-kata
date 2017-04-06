package properties

object DummyDao {
    private val mockDb = mutableMapOf<String, Person>()

    fun insert(p: Person) = mockDb.put(p.name, p)

    operator fun get(name: String) = mockDb[name]
}
