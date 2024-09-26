package ed.maevski.testbalinasoft.data.dto.user.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUserDtoIn(
    @SerialName("login") val login: String,
    @SerialName("password") val password: String,
)
