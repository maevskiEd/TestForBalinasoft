package ed.maevski.testbalinasoft.domain.irepository

import ed.maevski.testbalinasoft.domain.models.Comment
import ed.maevski.testbalinasoft.domain.models.Image

interface ICommentRepository {
    suspend fun upload(comment: Comment): Boolean
    suspend fun download(idImage: Int): Boolean
    suspend fun getComments(id: Int): List<Comment>
    suspend fun delFromApi(imageId: Int, commentId: Int): Boolean
}