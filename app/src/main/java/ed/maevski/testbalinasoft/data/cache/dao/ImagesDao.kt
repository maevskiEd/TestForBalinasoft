package ed.maevski.testbalinasoft.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ed.maevski.testbalinasoft.data.cache.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ImagesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(imageEntity: ImageEntity): Long

    @Query("DELETE FROM IMAGES WHERE id = :id")
    fun del(id: Int)

    @Query("SELECT * FROM IMAGES")
    fun getImages(): List<ImageEntity>

    @Query("SELECT * FROM IMAGES  WHERE id = :id")
    fun getImageById(id: Long): ImageEntity

}