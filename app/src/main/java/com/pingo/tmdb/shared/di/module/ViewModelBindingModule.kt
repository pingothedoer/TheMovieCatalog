package com.pingo.tmdb.shared.di.module

import androidx.lifecycle.ViewModelProvider
import com.pingo.tmdb.shared.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Binds viewmodel factory
 */
@Module
abstract class ViewModelBindingModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}