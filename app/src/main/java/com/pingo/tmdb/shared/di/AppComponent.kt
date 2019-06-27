package com.pingo.tmdb.shared.di

import com.pingo.tmdb.app.App
import com.pingo.tmdb.shared.di.module.ActivityBindingModule
import com.pingo.tmdb.shared.di.module.AppModule
import com.pingo.tmdb.shared.di.module.ViewModelBindingModule
import com.pingo.tmdb.shared.di.scope.AppScope
import com.pingo.tmdb.shared.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ----------------------------------------------
 *
 * combines all the module classes used in the app for compilation by the Dagger 2 library providing inject() methods for presenters.
 */

@AppScope
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        ViewModelBindingModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
}
