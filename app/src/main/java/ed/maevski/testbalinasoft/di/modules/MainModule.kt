package ed.maevski.testbalinasoft.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ed.maevski.testbalinasoft.view.MainActivity
import ed.maevski.testbalinasoft.view.auth.AuthFragment

@Module
interface MainModule {
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
    @ContributesAndroidInjector
    fun bindAuthFragment(): AuthFragment

}