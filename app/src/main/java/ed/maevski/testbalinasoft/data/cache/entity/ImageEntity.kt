package ed.maevski.testbalinasoft.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "IMAGES")
data class ImageEntity(
    @field: ColumnInfo(name = "id") @field:PrimaryKey var id: Int,
    @field: ColumnInfo(name = "url") val url: String,
    @field: ColumnInfo(name = "date") val date: Long,
    @field: ColumnInfo(name = "lat") val lat: Double?,
    @field: ColumnInfo(name = "lng") val lng: Double?,
)
