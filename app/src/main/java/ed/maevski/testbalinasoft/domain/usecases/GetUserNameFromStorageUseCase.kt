package ed.maevski.testbalinasoft.domain.usecases

import ed.maevski.testbalinasoft.domain.istorage.IUserStorage

class GetUserNameFromStorageUseCase(
    private val userSt: IUserStorage,
) {
    operator fun invoke(): String {
        return userSt.get()
    }
}