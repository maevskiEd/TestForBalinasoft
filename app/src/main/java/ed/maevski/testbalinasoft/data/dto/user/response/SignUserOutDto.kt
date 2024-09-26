package ed.maevski.testbalinasoft.data.dto.user.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUserOutDto(
    @SerialName("userId") val userId: Int,
    @SerialName("login") val login: String,
    @SerialName("token") val token : String
 )

