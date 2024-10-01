package ed.maevski.testbalinasoft.domain.usecases

import ed.maevski.testbalinasoft.domain.irepository.ICommentRepository

class DownloadCommentsUseCase(
    private val repository: ICommentRepository,
) {
    suspend operator fun invoke(idImage: Int): Boolean {
        return repository.download(idImage)
    }
}