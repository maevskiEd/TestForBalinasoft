package ed.maevski.testbalinasoft.domain.usecases

import ed.maevski.testbalinasoft.domain.irepository.IImageRepository
import ed.maevski.testbalinasoft.domain.models.Image

class GetImageByIdFromDbUseCase(
    private val repository: IImageRepository
) {

    suspend operator fun invoke(id: Int): Image {
        val result = repository.getImageByIdFromDb(id)
        return result
    }
}