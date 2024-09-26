package ed.maevski.testbalinasoft.domain.usecases

import ed.maevski.testbalinasoft.domain.irepository.IUserRepository
import ed.maevski.testbalinasoft.domain.models.User

class SignInUseCase(
    private val repository: IUserRepository
) {
    suspend operator fun invoke(user: User): Boolean {
        val result = repository.signin(user)
        return result
    }
}