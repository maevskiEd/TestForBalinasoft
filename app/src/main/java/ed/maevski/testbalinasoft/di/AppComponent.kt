package ed.maevski.testbalinasoft.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ed.maevski.testbalinasoft.App
import ed.maevski.testbalinasoft.di.modules.AppModule
import ed.maevski.testbalinasoft.di.modules.DataModule
import ed.maevski.testbalinasoft.di.modules.DatabaseModule
import ed.maevski.testbalinasoft.di.modules.DomainModule
import ed.maevski.testbalinasoft.di.modules.MainModule
import ed.maevski.testbalinasoft.di.modules.MappersModule
import ed.maevski.testbalinasoft.di.modules.RemoteModule
import ed.maevski.testbalinasoft.di.modules.SharedPreferencesModule
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
        AndroidInjectionModule::class,
        MainModule::class,
        AppModule::class,
        DatabaseModule::class,
        DataModule::class,
        DomainModule::class,
        MappersModule::class,
        RemoteModule::class,
        SharedPreferencesModule::class,
    ]
)

interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withContext(context: Context): Builder
        fun build(): AppComponent
    }
}

