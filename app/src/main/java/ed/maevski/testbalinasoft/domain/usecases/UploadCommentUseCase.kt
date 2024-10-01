package ed.maevski.testbalinasoft.domain.usecases

import ed.maevski.testbalinasoft.domain.irepository.ICommentRepository
import ed.maevski.testbalinasoft.domain.models.Comment
import ed.maevski.testbalinasoft.domain.models.Image

class UploadCommentUseCase(
    private val repository: ICommentRepository,
) {
    suspend operator fun invoke(image: Image, comment: Comment): Boolean {
        if (image.id == null) return false
        return repository.upload(comment)
    }
}