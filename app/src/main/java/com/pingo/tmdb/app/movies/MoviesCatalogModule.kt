package com.pingo.tmdb.app.movies

import androidx.lifecycle.ViewModel
import com.pingo.tmdb.app.movies.adapter.MoviesAdapter
import com.pingo.tmdb.shared.di.factory.ViewModelKey
import com.pingo.tmdb.shared.di.scope.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MoviesCatalogModule {


    /**
     * Generates an AndroidInjector for the [MoviesCatalogFragment].
     */
    @FragmentScope
    @ContributesAndroidInjector(modules = [Factory::class])
    internal abstract fun contributeMoviesCatalogFragment(): MoviesCatalogFragment


    @Binds
    @IntoMap
    @ViewModelKey(MoviesCatalogViewModel::class)
    abstract fun bindViewModel(viewModel: MoviesCatalogViewModel): ViewModel


    @Module
    internal class Factory {

        @FragmentScope
        @Provides
        fun provideMoviesAdapter(fragment: MoviesCatalogFragment) = MoviesAdapter(fragment)
    }
}