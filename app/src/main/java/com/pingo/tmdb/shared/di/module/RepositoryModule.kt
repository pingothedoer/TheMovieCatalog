package com.pingo.tmdb.shared.di.module

import com.pingo.tmdb.app.detail.data.MovieDetailRepoImp
import com.pingo.tmdb.app.movies.data.MoviesCatalogRepoImp
import com.pingo.tmdb.shared.network.api.MovieDetailService
import com.pingo.tmdb.shared.network.api.MovieListService
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
    fun provideMovieListRepo(apiService: MovieListService) =
        MoviesCatalogRepoImp(apiService)

    @Reusable
    @Provides
    fun provideMovieDetailRepo(apiService: MovieDetailService) =
        MovieDetailRepoImp(apiService)


}