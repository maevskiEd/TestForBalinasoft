package ed.maevski.testbalinasoft.di.modules

import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideTestOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        //Настраиваем таймауты для медленного интернета
        .callTimeout(HALF_MINUTE_FOR_SLOW_INTERNET, TimeUnit.SECONDS)
        .readTimeout(HALF_MINUTE_FOR_SLOW_INTERNET, TimeUnit.SECONDS)
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        //Указываем базовый URL из констант
        .baseUrl(BASE_URL)
        //Добавляем конвертер
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        //Добавляем кастомный клиент
        .client(okHttpClient)
        .build()

    companion object {
        private const val HALF_MINUTE_FOR_SLOW_INTERNET = 30L
        const val BASE_URL = "https://junior.balinasoft.com/v2/api-docs?group=api1"
    }
}