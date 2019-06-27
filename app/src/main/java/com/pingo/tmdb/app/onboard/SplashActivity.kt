package com.pingo.tmdb.app.onboard

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.pingo.tmdb.app.movies.MoviesCatalogActivity

/**
 * Created by : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ----------------------------------------------
 *
 * A Simple Splash screen showing app logo , with the delay of 3 seconds.
 * The Delay is just a template, here we can actually perform some operation at launch if necessary.
 */
class SplashActivity : AppCompatActivity() {

    private val startDelay = 3000L
    private var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(Handler()) {
            handler = this
            postDelayed({
                startActivity(Intent(this@SplashActivity, MoviesCatalogActivity::class.java))
                overridePendingTransition(android.R.anim.fade_in, 0)
                finish()
            }, startDelay)
        }
    }

    override fun onDestroy() {
        handler?.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}