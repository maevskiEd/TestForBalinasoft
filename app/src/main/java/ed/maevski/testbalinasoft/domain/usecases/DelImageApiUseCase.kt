package ed.maevski.testbalinasoft.domain.usecases

import ed.maevski.testbalinasoft.domain.irepository.IImageRepository

class DelImageApiUseCase(
    private val repository: IImageRepository,
) {
    suspend operator fun invoke(id: Int): Boolean {
        val result = repository.delFromApi(id)
        return result
    }
}