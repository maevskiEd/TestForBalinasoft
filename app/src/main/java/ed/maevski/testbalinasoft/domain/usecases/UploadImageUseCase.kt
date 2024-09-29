package ed.maevski.testbalinasoft.domain.usecases

import ed.maevski.testbalinasoft.domain.irepository.IImageRepository
import ed.maevski.testbalinasoft.domain.models.Image

class UploadImageUseCase(
    private val repository: IImageRepository,
) {
    suspend operator fun invoke(image: Image): Pair<Boolean,Long> {
        val result = repository.upload(image)
        return result
    }
}