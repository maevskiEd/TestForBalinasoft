package ed.maevski.testbalinasoft.di.modules

import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import ed.maevski.testbalinasoft.data.api.ImageApi
import ed.maevski.testbalinasoft.data.api.UserApi
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
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideImageApi(retrofit: Retrofit): ImageApi = retrofit.create(ImageApi::class.java)

    companion object {
        private const val HALF_MINUTE_FOR_SLOW_INTERNET = 30L
        const val BASE_URL = "https://junior.balinasoft.com/"
    }
}