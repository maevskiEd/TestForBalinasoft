package ed.maevski.testbalinasoft.domain.usecases

import ed.maevski.testbalinasoft.domain.irepository.IImageRepository
import ed.maevski.testbalinasoft.domain.irepository.IUserRepository
import ed.maevski.testbalinasoft.domain.istorage.ITokenStorage
import ed.maevski.testbalinasoft.domain.istorage.IUserStorage
import ed.maevski.testbalinasoft.domain.models.Image
import ed.maevski.testbalinasoft.domain.models.User

class UploadImageUseCase(
    private val repository: IImageRepository,
) {
    suspend operator fun invoke(image: Image): Boolean {
        val result = repository.upload(image)
        return result
    }
}