package ed.maevski.testbalinasoft.domain.usecases

import ed.maevski.testbalinasoft.domain.irepository.IUserRepository
import ed.maevski.testbalinasoft.domain.istorage.ITokenStorage
import ed.maevski.testbalinasoft.domain.istorage.IUserStorage
import ed.maevski.testbalinasoft.domain.models.User

class SignUpUseCase(
    private val repository: IUserRepository,
    private val userSt: IUserStorage,
    private val tokenSt: ITokenStorage,
) {
    suspend operator fun invoke(user: User): Boolean {
        val result = repository.signup(user)
        if (result.isSuccess) {
            val pair = result.getOrNull()
            pair?.let {
                pair.first.login?.let { userName -> userSt.save(userName) }
                pair.first.userId?.let { userId -> userSt.save(userId) }
                pair.second.token?.let { token -> tokenSt.save(token) }
            }
        }
        return result.isSuccess
    }
}