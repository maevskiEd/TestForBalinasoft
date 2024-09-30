package ed.maevski.testbalinasoft.domain.irepository

import ed.maevski.testbalinasoft.domain.models.Comment
import ed.maevski.testbalinasoft.domain.models.Image

interface ICommentRepository {
    suspend fun upload(imageId: Int, comment: Comment): Boolean

}