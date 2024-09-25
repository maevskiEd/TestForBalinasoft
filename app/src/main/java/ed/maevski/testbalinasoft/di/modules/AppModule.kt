package ed.maevski.testbalinasoft.di.modules

import dagger.Module
import dagger.Provides
import ed.maevski.testbalinasoft.view.auth.AuthViewModel

@Module
class AppModule() {
    @Provides
    fun provideAuthViewModelFactory(
    ) = AuthViewModel.Factory(
    )
}
