package ed.maevski.testbalinasoft.di.modules

import dagger.Module
import dagger.Provides
import ed.maevski.testbalinasoft.data.api.UserApi
import ed.maevski.testbalinasoft.data.repository.UserRepository
import ed.maevski.testbalinasoft.domain.irepository.IUserRepository
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideUserRepository(userApi: UserApi) : IUserRepository {
        return UserRepository(userApi = userApi)
    }

}