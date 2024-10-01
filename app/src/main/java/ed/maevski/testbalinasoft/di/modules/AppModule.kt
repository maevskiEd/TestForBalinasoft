package ed.maevski.testbalinasoft.di.modules

import dagger.Module
import dagger.Provides
import ed.maevski.testbalinasoft.domain.usecases.DelImageApiUseCase
import ed.maevski.testbalinasoft.domain.usecases.DownloadImagesUseCase
import ed.maevski.testbalinasoft.domain.usecases.GetImageByIdFromDbUseCase
import ed.maevski.testbalinasoft.domain.usecases.GetImagesFromDbUseCase
import ed.maevski.testbalinasoft.domain.usecases.GetUserNameFromStorageUseCase
import ed.maevski.testbalinasoft.domain.usecases.SaveImageToDbUseCase
import ed.maevski.testbalinasoft.domain.usecases.SendCommentUseCase
import ed.maevski.testbalinasoft.domain.usecases.SignInUseCase
import ed.maevski.testbalinasoft.domain.usecases.SignUpUseCase
import ed.maevski.testbalinasoft.domain.usecases.UploadImageUseCase
import ed.maevski.testbalinasoft.view.MainActivityViewModel
import ed.maevski.testbalinasoft.view.auth.signin.SigninViewModel
import ed.maevski.testbalinasoft.view.auth.signup.SignupViewModel
import ed.maevski.testbalinasoft.view.imagedetail.ImageDetailViewModel
import ed.maevski.testbalinasoft.view.map.MapViewModel
import ed.maevski.testbalinasoft.view.photos.PhotosViewModel

@Module
class AppModule() {
//    @Provides
//    fun provideAuthViewModelFactory(
//    ) = AuthViewModel.Factory(
//    )

    @Provides
    fun provideMainActivityViewModelFactory(
        getUserNameFromStorageUseCase: GetUserNameFromStorageUseCase,
        saveImageUseCase: SaveImageToDbUseCase,
        uploadImageUseCase: UploadImageUseCase
    ) = MainActivityViewModel.Factory(
        getUserNameFromStorageUseCase = getUserNameFromStorageUseCase,
        saveImageUseCase = saveImageUseCase,
        uploadImageUseCase = uploadImageUseCase
    )

    @Provides
    fun provideSigninViewModelFactory(
        signInUseCase: SignInUseCase
    ) = SigninViewModel.Factory(
        signInUseCase = signInUseCase
    )

    @Provides
    fun provideSignupViewModelFactory(
        signUpUseCase: SignUpUseCase
    ) = SignupViewModel.Factory(
        signUpUseCase = signUpUseCase
    )

    @Provides
    fun provideMapViewModelFactory(
    ) = MapViewModel.Factory(
    )

    @Provides
    fun providePhotosViewModelFactory(
        getImagesFromDbUseCase: GetImagesFromDbUseCase,
        downloadImagesUseCase: DownloadImagesUseCase,
        delImageApiUseCase: DelImageApiUseCase,
        ) = PhotosViewModel.Factory(
        getImagesFromDbUseCase = getImagesFromDbUseCase,
        downloadImagesUseCase = downloadImagesUseCase,
        delImageApiUseCase = delImageApiUseCase,
    )

    @Provides
    fun provideImageDetailViewModelFactory(
        getImageByIdFromDbUseCase: GetImageByIdFromDbUseCase,
        sendCommentUseCase: SendCommentUseCase,

        ) = ImageDetailViewModel.Factory(
        getImageByIdFromDbUseCase = getImageByIdFromDbUseCase,
        sendCommentUseCase = sendCommentUseCase,
    )
}
