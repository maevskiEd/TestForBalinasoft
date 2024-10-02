package ed.maevski.testbalinasoft.domain.usecases

import ed.maevski.testbalinasoft.domain.irepository.ICommentRepository
import ed.maevski.testbalinasoft.domain.models.Comment

class GetCommentsByIdImageFromDbUseCase(
    private val repository: ICommentRepository
) {

    suspend operator fun invoke(id: Int): List<Comment> {
        val result = repository.getComments(id)
        return result
    }
}