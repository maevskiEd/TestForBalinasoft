package ed.maevski.testbalinasoft.domain.irepository

import ed.maevski.testbalinasoft.domain.models.User

interface IUserRepository {
    suspend fun signup(user: User): Boolean
    suspend fun signin(user: User): Boolean
}