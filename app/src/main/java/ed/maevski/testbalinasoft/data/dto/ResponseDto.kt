package ed.maevski.testbalinasoft.data.dto

import ed.maevski.testbalinasoft.data.dto.user.response.SignUserOutDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto<T>(
    @SerialName("status") val status: Int,
    @SerialName("data") val data: T,
 )

