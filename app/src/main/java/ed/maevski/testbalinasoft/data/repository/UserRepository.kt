package ed.maevski.testbalinasoft.data.repository

import ed.maevski.testbalinasoft.data.api.UserApi
import ed.maevski.testbalinasoft.data.dto.user.request.SignUserDtoIn
import ed.maevski.testbalinasoft.domain.irepository.IUserRepository
import ed.maevski.testbalinasoft.domain.models.Token
import ed.maevski.testbalinasoft.domain.models.User

class UserRepository(
    private val userApi: UserApi
) : IUserRepository {

    override suspend fun signup(user: User): Result<Pair<User, Token>> {
        val result = userApi.signup(mapperUserToUserDto(user))
        println("Result: $result")
        return result.map {
            Pair(User(userId = it.data.userId, login = it.data.login), Token(token = it.data.token))
        }
    }

    override suspend fun signin(user: User): Result<Pair<User, Token>> {
        val result = userApi.signin(mapperUserToUserDto(user))
        println("Result: $result")
        return result.map {
            Pair(User(userId = it.data.userId, login = it.data.login), Token(token = it.data.token))
        }
    }

    private fun mapperUserToUserDto(user: User): SignUserDtoIn {
        return SignUserDtoIn(
            login = user.login ?: "",
            password = user.password ?: ""
        )
    }
}