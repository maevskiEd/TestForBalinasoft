package ed.maevski.testbalinasoft.data.dto.user.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto(
    @SerialName("status") val status: Int,
    @SerialName("signUserOutDto") val data: SignUserOutDto,
 )

