package ed.maevski.testbalinasoft.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "COMMENTS")
data class CommentEntity(
    @field: ColumnInfo(name = "commentId") @field:PrimaryKey(autoGenerate = true) var commentId: Int = 0,
    @field: ColumnInfo(name = "imageId") val imageId: Int,
    @field: ColumnInfo(name = "date") val date: Long,
    @field: ColumnInfo(name = "text") val text: String,
)
