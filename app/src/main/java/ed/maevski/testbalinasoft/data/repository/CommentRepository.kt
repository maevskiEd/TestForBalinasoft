package ed.maevski.testbalinasoft.data.repository

import ed.maevski.testbalinasoft.data.api.CommentApi
import ed.maevski.testbalinasoft.data.dto.comment.request.CommentDtoIn
import ed.maevski.testbalinasoft.data.storage.TokenStorage
import ed.maevski.testbalinasoft.domain.irepository.ICommentRepository
import ed.maevski.testbalinasoft.domain.models.Comment

/**** Вынести маппер в отдельный модуль*/
class CommentRepository(
    private val commentApi: CommentApi,
    private val tokenStorage: TokenStorage,

    ) : ICommentRepository {

    override suspend fun upload(comment: Comment): Boolean {
        val token = tokenStorage.get()
        if (token.isEmpty() || comment.imageId == null) return false
        val result = commentApi.upload(
            token = token,
            commentDtoIn = mapperCommentToCommentDtoIn(comment),
            imageId = comment.imageId
        )
        return result.isSuccess
    }

    private fun mapperCommentToCommentDtoIn(comment: Comment): CommentDtoIn {
        return CommentDtoIn(
            text = comment.text,
        )
    }
}