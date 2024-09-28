package ed.maevski.testbalinasoft.domain.usecases

import ed.maevski.testbalinasoft.domain.irepository.IImageRepository
import ed.maevski.testbalinasoft.domain.models.Image

class GetImagesFromDbUseCase(
    private val repository: IImageRepository
) {

    suspend operator fun invoke(): List<Image> {
        val result = repository.getImages()
        return result
    }
}