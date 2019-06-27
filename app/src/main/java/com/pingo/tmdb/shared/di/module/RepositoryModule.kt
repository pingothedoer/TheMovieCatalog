package com.pingo.tmdb.shared.di.module

import com.pingo.tmdb.shared.network.api.MovieDetailService
import com.pingo.tmdb.shared.network.api.MovieListService
import com.pingo.tmdb.app.detail.MovieDetailRepo
import com.pingo.tmdb.app.movies.MoviesCatalogRepo
import dagger.Module
import dagger.Provides
import dagger.Reusable

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Contains all data source - repositories
 */
@Module(includes = [ServiceModule::class])
class RepositoryModule {

    /**
     * Repositories for on boarding screens
     */
    @Reusable
    @Provides
    fun provideMovieListRepo(apiService: MovieListService) = MoviesCatalogRepo(apiService)

    @Reusable
    @Provides
    fun provideMovieDetailRepo(apiService: MovieDetailService) = MovieDetailRepo(apiService)


}