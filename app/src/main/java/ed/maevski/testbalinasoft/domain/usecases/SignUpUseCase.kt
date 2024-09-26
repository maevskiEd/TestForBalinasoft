package ed.maevski.testbalinasoft.domain.usecases

import ed.maevski.testbalinasoft.domain.irepository.IUserRepository
import ed.maevski.testbalinasoft.domain.models.User

class SignUpUseCase(
    private val repository: IUserRepository
) {
    suspend operator fun invoke(user: User): Boolean {
        val result = repository.signup(user)
        return result
    }
}