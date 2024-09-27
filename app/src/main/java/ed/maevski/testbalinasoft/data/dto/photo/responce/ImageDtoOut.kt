package ed.maevski.testbalinasoft.data.dto.photo.responce

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageDtoOut(
    @SerialName("id") val id: Int,
    @SerialName("url") val url: String,
    @SerialName("date") val date : Long,
    @SerialName("lat") val lat: Double,
    @SerialName("lng") val lng: Double,
 )

