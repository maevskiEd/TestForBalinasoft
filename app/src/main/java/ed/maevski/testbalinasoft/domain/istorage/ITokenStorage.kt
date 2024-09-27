package ed.maevski.testbalinasoft.domain.istorage

interface ITokenStorage {
    fun save(token: String)
    fun get(): String
}
