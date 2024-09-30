package ed.maevski.testbalinasoft.domain.models

import android.net.Uri

data class Image(
    val id: Int? = null,
    val uri: Uri? = null,
    val url: String? = null,
    val date: Long,
    val lat: Double? = null,
    val lng: Double? = null,
)