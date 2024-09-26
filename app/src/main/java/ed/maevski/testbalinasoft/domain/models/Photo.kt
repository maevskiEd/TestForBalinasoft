package ed.maevski.testbalinasoft.domain.models

import android.net.Uri

data class Photo(
    val uri: Uri,
    val date: Long,
    val lat: Double? = null,
    val lng: Double? = null,
)