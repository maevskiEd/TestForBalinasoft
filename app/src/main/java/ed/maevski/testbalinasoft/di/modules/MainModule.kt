package ed.maevski.testbalinasoft.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ed.maevski.testbalinasoft.view.AuthActivity
import ed.maevski.testbalinasoft.view.MainActivity
import ed.maevski.testbalinasoft.view.auth.AuthFragment
import ed.maevski.testbalinasoft.view.auth.signin.SigninFragment
import ed.maevski.testbalinasoft.view.auth.signup.SignupFragment
import ed.maevski.testbalinasoft.view.imagedetail.ImageDetailFragment
import ed.maevski.testbalinasoft.view.map.MapFragment
import ed.maevski.testbalinasoft.view.photos.PhotosFragment

@Module
interface MainModule {
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
    @ContributesAndroidInjector
    fun bindAuthActivity(): AuthActivity
    @ContributesAndroidInjector
    fun bindAuthFragment(): AuthFragment
    @ContributesAndroidInjector
    fun bindSigninFragment(): SigninFragment
    @ContributesAndroidInjector
    fun bindSignupFragment(): SignupFragment
    @ContributesAndroidInjector
    fun bindMapFragment(): MapFragment
    @ContributesAndroidInjector
    fun bindPhotosFragment(): PhotosFragment
    @ContributesAndroidInjector
    fun bindImageDetailFragment(): ImageDetailFragment

}