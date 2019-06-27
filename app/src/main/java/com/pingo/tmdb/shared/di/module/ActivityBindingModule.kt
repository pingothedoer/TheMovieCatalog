/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pingo.tmdb.shared.di.module

import com.pingo.tmdb.app.detail.MovieDetailActivity
import com.pingo.tmdb.app.detail.MovieDetailModule
import com.pingo.tmdb.app.movies.MoviesCatalogActivity
import com.pingo.tmdb.app.movies.MoviesCatalogModule
import com.pingo.tmdb.shared.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module
 * ActivityBindingModule is on, in our case that will be [com.pingo.tmdb.shared.di.AppComponent]. You never
 * need to tell [com.pingo.tmdb.shared.di.AppComponent] that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that [com.pingo.tmdb.shared.di.AppComponent] exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the
 * specified modules and be aware of a scope annotation [@ActivityScoped].
 * When Dagger.Android annotation processor runs it will create 2 subcomponents for us.
 */
@Module(
    includes = [
        MoviesCatalogModule::class,
        MovieDetailModule::class
    ]
)
abstract class ActivityBindingModule {


    /**
     * Binds Modules for [MoviesCatalogActivity]
     * @return MoviesCatalogActivity
     */
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            MoviesCatalogModule::class
        ]
    )
    internal abstract fun movieCatalogActivity(): MoviesCatalogActivity


    /**
     * Binds Modules for [MovieDetailActivity]
     * @return MovieDetailActivity
     */
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            MovieDetailModule::class
        ]
    )
    internal abstract fun movieDetailActivity(): MovieDetailActivity


}