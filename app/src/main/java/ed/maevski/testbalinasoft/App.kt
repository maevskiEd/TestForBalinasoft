package ed.maevski.testbalinasoft

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ed.maevski.testbalinasoft.di.DaggerAppComponent

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<App> =
        DaggerAppComponent.builder().withContext(applicationContext).build()
}