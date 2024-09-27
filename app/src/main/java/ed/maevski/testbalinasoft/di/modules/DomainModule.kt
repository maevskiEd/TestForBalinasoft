package ed.maevski.testbalinasoft.di.modules

import dagger.Module
import dagger.Provides
import ed.maevski.testbalinasoft.domain.irepository.IUserRepository
import ed.maevski.testbalinasoft.domain.istorage.ITokenStorage
import ed.maevski.testbalinasoft.domain.istorage.IUserStorage
import ed.maevski.testbalinasoft.domain.usecases.GetUserNameFromStorageUseCase
import ed.maevski.testbalinasoft.domain.usecases.SignInUseCase
import ed.maevski.testbalinasoft.domain.usecases.SignUpUseCase
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideSignInUseCase(
        repository: IUserRepository,
        userSt: IUserStorage,
        tokenSt: ITokenStorage,
    ): SignInUseCase {
        return SignInUseCase(
            repository = repository,
            userSt = userSt,
            tokenSt = tokenSt,
        )
    }

    @Singleton
    @Provides
    fun provideSignUpUseCase(
        repository: IUserRepository,
        userSt: IUserStorage,
        tokenSt: ITokenStorage,
    ): SignUpUseCase {
        return SignUpUseCase(
            repository = repository,
            userSt = userSt,
            tokenSt = tokenSt,
        )
    }

    @Singleton
    @Provides
    fun provideGetUserNameFromStorageUseCase(
        userSt: IUserStorage,
    ): GetUserNameFromStorageUseCase {
        return GetUserNameFromStorageUseCase(
            userSt = userSt,
        )
    }

}