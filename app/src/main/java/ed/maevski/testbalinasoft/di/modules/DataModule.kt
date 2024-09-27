package ed.maevski.testbalinasoft.di.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ed.maevski.testbalinasoft.data.api.UserApi
import ed.maevski.testbalinasoft.data.repository.UserRepository
import ed.maevski.testbalinasoft.data.storage.ImageStorage
import ed.maevski.testbalinasoft.data.storage.TokenStorage
import ed.maevski.testbalinasoft.data.storage.UserStorage
import ed.maevski.testbalinasoft.domain.irepository.IUserRepository
import ed.maevski.testbalinasoft.domain.istorage.IImageStorage
import ed.maevski.testbalinasoft.domain.istorage.ITokenStorage
import ed.maevski.testbalinasoft.domain.istorage.IUserStorage
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideUserRepository(userApi: UserApi) : IUserRepository {
        return UserRepository(userApi = userApi)
    }

    @Provides
    @Singleton
    fun provideUserStorage(sharedPreferences: SharedPreferences) : IUserStorage {
        return UserStorage(sharedPreferences = sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideTokenStorage(sharedPreferences: SharedPreferences) : ITokenStorage {
        return TokenStorage(sharedPreferences = sharedPreferences)
    }

    @Provides
    @Singleton
    fun providePhotoStorage(context: Context) : IImageStorage {
        return ImageStorage(context = context)
    }

}