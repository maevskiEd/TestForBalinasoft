package ed.maevski.testbalinasoft.data.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ed.maevski.testbalinasoft.data.cache.dao.ImagesDao
import ed.maevski.testbalinasoft.data.cache.entity.ImageEntity

@Database(
    entities = [ImageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TestDatabase : RoomDatabase() {
    abstract fun meetingDao(): ImagesDao
}