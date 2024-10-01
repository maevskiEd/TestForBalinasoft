package ed.maevski.testbalinasoft.domain.usecases

import ed.maevski.testbalinasoft.domain.irepository.ICommentRepository
import ed.maevski.testbalinasoft.domain.models.Comment

class SendCommentUseCase(
    private val repository: ICommentRepository,
) {
    suspend operator fun invoke(comment: Comment): Boolean {
        val result = repository.upload(comment = comment)
        return result
    }
}