package com.pingo.tmdb.app.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pingo.tmdb.R
import com.pingo.tmdb.shared.ext.inTransaction


/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * [AppCompatActivity] to show movie catalog
 */
class MoviesCatalogActivity : AppCompatActivity(R.layout.activity_fragment_template) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.inTransaction {
                add(R.id.fragment_container, MoviesCatalogFragment.newInstance())
            }
        }
    }

}