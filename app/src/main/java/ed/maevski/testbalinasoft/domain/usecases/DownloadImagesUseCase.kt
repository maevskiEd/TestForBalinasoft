package ed.maevski.testbalinasoft.domain.usecases

import ed.maevski.testbalinasoft.domain.irepository.IImageRepository
import ed.maevski.testbalinasoft.domain.models.Image

class DownloadImagesUseCase(
    private val repository: IImageRepository,
) {
    suspend operator fun invoke(): Boolean {
        val result = repository.download()
        return result
    }
}