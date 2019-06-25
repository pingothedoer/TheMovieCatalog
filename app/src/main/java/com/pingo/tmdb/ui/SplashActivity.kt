package com.pingo.tmdb.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.pingo.tmdb.MainActivity

/**
 * Created by : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 *
 * A Simple Splash screen showing app logo , with the delay of 3 seconds.
 * The Delay is just a template, here we can actually perform some operation at launch if necessary.
 */
class SplashActivity : AppCompatActivity() {

    companion object {
        private const val START_DELAY = 3000L
    }

    private var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(Handler()) {
            handler = this
            postDelayed({
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                overridePendingTransition(android.R.anim.fade_in, 0)
                finish()
            }, START_DELAY)
        }
    }

    override fun onDestroy() {
        handler?.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}