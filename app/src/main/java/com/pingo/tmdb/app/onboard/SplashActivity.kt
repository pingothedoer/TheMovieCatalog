package com.pingo.tmdb.app.onboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pingo.tmdb.app.movies.MoviesCatalogActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ----------------------------------------------
 *
 * A Simple Splash screen showing app logo , with the delay of 3 seconds.
 * The Delay is just a template, here we can actually perform some operation at launch if necessary.
 */
class SplashActivity : AppCompatActivity() {

    private val startDelay = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Wait for [startDelay] milliseconds in the background and then go to [MoviesCatalogActivity]
         */
        GlobalScope.launch {
            delay(startDelay)
            startActivity(Intent(this@SplashActivity, MoviesCatalogActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, 0)
            finish()
        }

    }

}