package ed.maevski.testbalinasoft.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ed.maevski.testbalinasoft.data.cache.db.TestDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDb(context: Context) = Room.databaseBuilder(
        context, TestDatabase::class.java, "test_db"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideImagesDao(context: Context) = provideDb(context).imagesDao()

    @Singleton
    @Provides
    fun provideCommentsDao(context: Context) = provideDb(context).commentsDao()
}

