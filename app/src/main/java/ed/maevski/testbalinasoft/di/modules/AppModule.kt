package ed.maevski.testbalinasoft.di.modules

import dagger.Module
import dagger.Provides
import ed.maevski.testbalinasoft.domain.usecases.SignInUseCase
import ed.maevski.testbalinasoft.domain.usecases.SignUpUseCase
import ed.maevski.testbalinasoft.view.auth.AuthViewModel
import ed.maevski.testbalinasoft.view.auth.signin.SigninViewModel
import ed.maevski.testbalinasoft.view.auth.signup.SignupViewModel
import ed.maevski.testbalinasoft.view.map.MapViewModel
import ed.maevski.testbalinasoft.view.photos.PhotosViewModel

@Module
class AppModule() {
//    @Provides
//    fun provideAuthViewModelFactory(
//    ) = AuthViewModel.Factory(
//    )

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
    ) = PhotosViewModel.Factory(
    )
}
