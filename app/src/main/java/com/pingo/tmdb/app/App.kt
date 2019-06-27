package com.pingo.tmdb.app

import com.pingo.tmdb.shared.di.DaggerAppComponent
import dagger.android.support.DaggerApplication


/**
 * Created by : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ----------------------------------------------
 *
 * Application class
 */
class App : DaggerApplication() {
    override fun applicationInjector() = DaggerAppComponent.builder().application(this).build()
}