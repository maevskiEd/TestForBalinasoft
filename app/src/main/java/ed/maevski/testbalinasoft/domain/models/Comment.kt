package ed.maevski.testbalinasoft.domain.models

data class Comment(
    val commentId: Long? = null,
    val imageId: Long? = null,
    val date: Long,
    val text: String,
)