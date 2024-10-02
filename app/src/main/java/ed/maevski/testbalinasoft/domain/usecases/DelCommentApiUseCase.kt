package ed.maevski.testbalinasoft.domain.usecases

import ed.maevski.testbalinasoft.domain.irepository.ICommentRepository

class DelCommentApiUseCase(
    private val repository: ICommentRepository,
) {
    suspend operator fun invoke(imageId: Int, commentId: Int): Boolean {
        val result = repository.delFromApi(imageId, commentId)
        return result
    }
}