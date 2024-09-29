package ed.maevski.testbalinasoft.domain.usecases

import ed.maevski.testbalinasoft.domain.irepository.IImageRepository
import ed.maevski.testbalinasoft.domain.models.Image

class SaveImageToDbUseCase(
    private val repository: IImageRepository,
) {
    suspend operator fun invoke(image: Image): Boolean {
        val result = repository.save(image)
        return result.first
    }
}