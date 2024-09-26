package ed.maevski.testbalinasoft.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ed.maevski.testbalinasoft.view.AuthActivity
import ed.maevski.testbalinasoft.view.MainActivity
import ed.maevski.testbalinasoft.view.auth.AuthFragment
import ed.maevski.testbalinasoft.view.auth.signin.SigninFragment
import ed.maevski.testbalinasoft.view.auth.signup.SignupFragment

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

}