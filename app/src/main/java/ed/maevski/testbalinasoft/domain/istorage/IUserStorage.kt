package ed.maevski.testbalinasoft.domain.istorage

interface IUserStorage {
    fun save(userName: String)
    fun save(userId: Int)
    fun get(): String
}
