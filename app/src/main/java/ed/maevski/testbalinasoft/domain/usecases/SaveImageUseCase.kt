package ed.maevski.testbalinasoft.domain.usecases

import ed.maevski.testbalinasoft.domain.irepository.IImageRepository
import ed.maevski.testbalinasoft.domain.models.Image

class SaveImageUseCase(
    private val repository: IImageRepository,
) {
    suspend operator fun invoke(image: Image): Boolean {
        repository.save(image)
        return true
    }
}