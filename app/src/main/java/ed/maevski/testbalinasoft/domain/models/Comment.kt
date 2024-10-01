package ed.maevski.testbalinasoft.domain.models

data class Comment(
    val commentId: Int? = null,
    val imageId: Int? = null,
    val date: Long,
    val text: String,
)