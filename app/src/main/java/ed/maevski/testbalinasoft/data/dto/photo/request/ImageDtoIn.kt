package ed.maevski.testbalinasoft.data.dto.photo.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageDtoIn(
    @SerialName("base64Image") val base64Image: String,
    @SerialName("date") val date: Long,
    @SerialName("lat") val lat: Double,
    @SerialName("lng") val lng: Double,
)
