package ed.maevski.testbalinasoft.data.repository

import ed.maevski.testbalinasoft.data.api.UserApi
import ed.maevski.testbalinasoft.data.dto.user.request.SignUserDtoIn
import ed.maevski.testbalinasoft.domain.irepository.IUserRepository
import ed.maevski.testbalinasoft.domain.models.User

class UserRepository(
    private val userApi: UserApi
) : IUserRepository {

    override suspend fun signup(user: User): Boolean {
        val x = userApi.signup(mapperUserToUserDto(user))
        println("Result: $x")
//        if (x.isSuccess) storage.save(mapperUserToUserSM(user))
        return x.isSuccess
    }

    override suspend fun signin(user: User): Boolean {
        val x = userApi.signin(mapperUserToUserDto(user))
        println("Result: $x")
        return x.isSuccess
    }

    fun mapperUserToUserDto(user: User): SignUserDtoIn {
        return SignUserDtoIn(
            login = user.login,
            password = user.password
        )
    }

    fun mapperUserDtoToUser(user: SignUserDtoIn): User {
        return User(
            login = user.login,
            password = user.password
        )
    }
}