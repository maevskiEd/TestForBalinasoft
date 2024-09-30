package ed.maevski.testbalinasoft.data.cache.entity

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "IMAGES")
data class ImageEntity(
    @field: ColumnInfo(name = "id") @field:PrimaryKey(autoGenerate = true) var id: Int = 0,
    @field: ColumnInfo(name = "uri") val uri: String? = null,
    @field: ColumnInfo(name = "url") val url: String? = null,
    @field: ColumnInfo(name = "date") val date: Long? = null,
    @field: ColumnInfo(name = "lat") val lat: Double? = null,
    @field: ColumnInfo(name = "lng") val lng: Double? = null,
)
