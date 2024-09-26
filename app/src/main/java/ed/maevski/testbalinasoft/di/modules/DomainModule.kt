package ed.maevski.testbalinasoft.di.modules

import dagger.Module
import dagger.Provides
import ed.maevski.testbalinasoft.domain.irepository.IUserRepository
import ed.maevski.testbalinasoft.domain.usecases.SignInUseCase
import ed.maevski.testbalinasoft.domain.usecases.SignUpUseCase
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideSignInUseCase(repository: IUserRepository): SignInUseCase {
        return SignInUseCase(repository = repository)
    }

    @Singleton
    @Provides
    fun provideSignUpUseCase(repository: IUserRepository): SignUpUseCase {
        return SignUpUseCase(repository = repository)
    }

}