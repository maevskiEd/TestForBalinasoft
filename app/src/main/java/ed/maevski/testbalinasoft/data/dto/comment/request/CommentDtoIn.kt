package ed.maevski.testbalinasoft.data.dto.comment.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentDtoIn(
    @SerialName("text") val text: String,
)
