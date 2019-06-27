package com.pingo.tmdb.app.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pingo.tmdb.R
import com.pingo.tmdb.shared.ext.inTransaction
import com.pingo.tmdb.shared.ext.setFullscreen
import com.pingo.tmdb.shared.models.Movie


/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * [AppCompatActivity] to show movie details
 */
class MovieDetailActivity : AppCompatActivity(R.layout.activity_fragment_template) {

    companion object {
        private const val MOVIE_ID = "movie_id"

        @JvmStatic
        fun newInstance(context: Context, movie: Movie) = Intent(context, MovieDetailActivity::class.java).apply {
            putExtra(MOVIE_ID, movie.id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullscreen()
        if (savedInstanceState == null) {
            supportFragmentManager.inTransaction {
                val movieId = intent.getIntExtra(MOVIE_ID, 0)
                add(R.id.fragment_container, MovieDetailFragment.newInstance(movieId.toString()))
            }
        }
    }


}