package ed.maevski.testbalinasoft.domain.irepository

import ed.maevski.testbalinasoft.domain.models.Token
import ed.maevski.testbalinasoft.domain.models.User

interface IUserRepository {
    suspend fun signup(user: User): Result<Pair<User, Token>>
    suspend fun signin(user: User): Result<Pair<User, Token>>
}