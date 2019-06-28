package com.pingo.tmdb.shared.di.module

import com.pingo.tmdb.BuildConfig
import com.pingo.tmdb.BuildConfig.BASE_URL
import com.pingo.tmdb.app.App
import com.pingo.tmdb.shared.di.scope.AppScope
import com.pingo.tmdb.shared.network.interceptor.ApiKeyInterceptor
import com.pingo.tmdb.shared.network.interceptor.ConnectivityInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Module which provides all required dependencies about network
 */
@Module(includes = [RepositoryModule::class])
class NetworkModule {

    companion object {
        private const val CONNECTION_TIMEOUT_MS = 10 * 1000
        private const val READ_TIMEOUT_MS = 10 * 1000
        private const val WRITE_TIMEOUT_MS = 60 * 1000
    }


    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @AppScope
    fun provideRetrofit(httpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(httpClient)
        .build()!!


    @Provides
    @AppScope
    fun provideOkHttpClient(app: App): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        when {
            BuildConfig.DEBUG -> interceptor.level = HttpLoggingInterceptor.Level.BODY
            else -> interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        val userHttpClientBuilder = OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT_MS.toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT_MS.toLong(), TimeUnit.MILLISECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(ConnectivityInterceptor())
            .addInterceptor(ApiKeyInterceptor())
            .connectTimeout(CONNECTION_TIMEOUT_MS.toLong(), TimeUnit.MILLISECONDS)

        return userHttpClientBuilder.build()
    }
}