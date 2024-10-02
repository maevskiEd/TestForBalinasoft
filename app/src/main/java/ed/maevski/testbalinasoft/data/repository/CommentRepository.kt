package ed.maevski.testbalinasoft.data.repository

import ed.maevski.testbalinasoft.data.api.CommentApi
import ed.maevski.testbalinasoft.data.cache.dao.CommentsDao
import ed.maevski.testbalinasoft.data.cache.entity.CommentEntity
import ed.maevski.testbalinasoft.data.dto.comment.request.CommentDtoIn
import ed.maevski.testbalinasoft.data.dto.comment.responce.CommentDtoOut
import ed.maevski.testbalinasoft.data.storage.TokenStorage
import ed.maevski.testbalinasoft.domain.irepository.ICommentRepository
import ed.maevski.testbalinasoft.domain.models.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**** Вынести маппер в отдельный модуль*/
class CommentRepository(
    private val commentApi: CommentApi,
    private val tokenStorage: TokenStorage,
    private val commentsDao: CommentsDao,

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

    override suspend fun download(imageId: Int): Boolean {
        var count = 0
        val token = tokenStorage.get()
        if (token.isEmpty()) return false
        val result = commentApi.download(
            token = token,
            imageId = imageId,
            page = 0
        )

        println("CommentRepository: download result = $result")

        if (result.isSuccess) {
            val response = result.getOrNull() ?: return false
            println("CommentRepository: download response = $response")
            if (response.data.isNotEmpty()) {
                val listId = withContext(Dispatchers.IO) {
                    commentsDao.trunc(imageId)
                    commentsDao.insertAll(mapperImagesDtoOutToImagesEntity(response.data, imageId))
                }
                count = listId.size
            }
        }
        return count != 0
    }

    override suspend fun getComments(imageId: Int): List<Comment> {
        val comments = withContext(Dispatchers.IO) {
            commentsDao.getComments(imageId)
        }
        return mapperCommentsEntityToComments(comments)
    }

    override suspend fun delFromApi(imageId: Int, commentId: Int): Boolean {
        val token = tokenStorage.get()
        if (token.isEmpty()) return false
        val result = commentApi.del(token = token, imageId = imageId, commentId = commentId)
        return result.isSuccess
    }


    private fun mapperCommentToCommentDtoIn(comment: Comment): CommentDtoIn {
        return CommentDtoIn(
            text = comment.text,
        )
    }

    private fun mapperImagesDtoOutToImagesEntity(
        listCommentDtoOut: List<CommentDtoOut>,
        imageId: Int
    ): List<CommentEntity> {
        return listCommentDtoOut.map {
            CommentEntity(
                commentId = it.id,
                imageId = imageId,
                date = it.date * 1000,
                text = it.text,
            )
        }
    }

    private fun mapperCommentsEntityToComments(
        listCommentEntity: List<CommentEntity>,
    ): List<Comment> {
        return listCommentEntity.map {
            Comment(
                commentId = it.commentId,
                date = it.date,
                text = it.text,
            )
        }
    }
}