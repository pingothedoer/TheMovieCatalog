package com.pingo.tmdb.shared.di.module

import com.pingo.tmdb.shared.network.api.MovieDetailService
import com.pingo.tmdb.shared.network.api.MovieListService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Provides all API services
 */
@Module
class ServiceModule {

    /**
     * Provides the [MovieDetailService] service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    fun provideMovieDetailsAPIs(retrofit: Retrofit) = retrofit.create(MovieDetailService::class.java)!!


    /**
     * Provides the [MovieListService] service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    fun provideMovieListAPIs(retrofit: Retrofit) = retrofit.create(MovieListService::class.java)!!
}