package ed.maevski.testbalinasoft.di.modules

import dagger.Module
import dagger.Provides
import ed.maevski.testbalinasoft.domain.irepository.ICommentRepository
import ed.maevski.testbalinasoft.domain.irepository.IImageRepository
import ed.maevski.testbalinasoft.domain.irepository.IUserRepository
import ed.maevski.testbalinasoft.domain.istorage.ITokenStorage
import ed.maevski.testbalinasoft.domain.istorage.IUserStorage
import ed.maevski.testbalinasoft.domain.usecases.DelCommentApiUseCase
import ed.maevski.testbalinasoft.domain.usecases.DelImageApiUseCase
import ed.maevski.testbalinasoft.domain.usecases.DownloadCommentsUseCase
import ed.maevski.testbalinasoft.domain.usecases.DownloadImagesUseCase
import ed.maevski.testbalinasoft.domain.usecases.GetCommentsByIdImageFromDbUseCase
import ed.maevski.testbalinasoft.domain.usecases.GetImageByIdFromDbUseCase
import ed.maevski.testbalinasoft.domain.usecases.GetImagesFromDbUseCase
import ed.maevski.testbalinasoft.domain.usecases.GetUserNameFromStorageUseCase
import ed.maevski.testbalinasoft.domain.usecases.SaveImageToDbUseCase
import ed.maevski.testbalinasoft.domain.usecases.SendCommentUseCase
import ed.maevski.testbalinasoft.domain.usecases.SignInUseCase
import ed.maevski.testbalinasoft.domain.usecases.SignUpUseCase
import ed.maevski.testbalinasoft.domain.usecases.UploadCommentUseCase
import ed.maevski.testbalinasoft.domain.usecases.UploadImageUseCase
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideDelCommentApiUseCase(
        repository: ICommentRepository,
    ): DelCommentApiUseCase {
        return DelCommentApiUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideDelImageApiUseCase(
        repository: IImageRepository,
    ): DelImageApiUseCase {
        return DelImageApiUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideDownloadCommentsUseCase(
        repository: ICommentRepository,
    ): DownloadCommentsUseCase {
        return DownloadCommentsUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideDownloadImagesUseCase(
        repository: IImageRepository,
    ): DownloadImagesUseCase {
        return DownloadImagesUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideGetCommentsByIdImageFromDbUseCase(
        repository: ICommentRepository,
    ): GetCommentsByIdImageFromDbUseCase {
        return GetCommentsByIdImageFromDbUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideGetImageByIdFromDbUseCase(
        repository: IImageRepository,
    ): GetImageByIdFromDbUseCase {
        return GetImageByIdFromDbUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideGetImagesFromDbUseCase(
        repository: IImageRepository,
    ): GetImagesFromDbUseCase {
        return GetImagesFromDbUseCase(
            repository = repository,
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

    @Singleton
    @Provides
    fun provideSendCommentUseCase(
        repository: ICommentRepository,
    ): SendCommentUseCase {
        return SendCommentUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideSaveImageUseCase(
        repository: IImageRepository,
    ): SaveImageToDbUseCase {
        return SaveImageToDbUseCase(
            repository = repository,
        )
    }

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
    fun provideUploadCommentUseCase(
        repository: ICommentRepository,
    ): UploadCommentUseCase {
        return UploadCommentUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideUploadImageUseCase(
        repository: IImageRepository,
    ): UploadImageUseCase {
        return UploadImageUseCase(
            repository = repository,
        )
    }
}