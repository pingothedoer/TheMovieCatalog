package com.pingo.tmdb.app.detail

import androidx.lifecycle.ViewModel
import com.pingo.tmdb.shared.di.factory.ViewModelKey
import com.pingo.tmdb.shared.di.scope.ActivityScope
import com.pingo.tmdb.shared.di.scope.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MovieDetailModule {

    /**
     * Generates an AndroidInjector for the [MovieDetailFragment].
     */
    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeMovieDetailFragment(): MovieDetailFragment

    /**
     * The ViewModels are created by Dagger in a map. Via the @ViewModelKey, we define that we
     * want to get a [MovieDetailViewModel] class.
     */
    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindMovieDetailViewModel(viewModel: MovieDetailViewModel): ViewModel

}