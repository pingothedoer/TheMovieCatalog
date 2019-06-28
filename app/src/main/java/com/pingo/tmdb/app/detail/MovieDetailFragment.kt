package com.pingo.tmdb.app.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.material.chip.Chip
import com.pingo.tmdb.R
import com.pingo.tmdb.databinding.FragmentMovieDetailBinding
import com.pingo.tmdb.shared.ext.add
import com.pingo.tmdb.shared.ext.setToolbar
import com.pingo.tmdb.shared.ext.showImage
import com.pingo.tmdb.shared.models.MovieDetail
import com.pingo.tmdb.shared.ui.BaseFragment
import com.pingo.tmdb.shared.utils.DateUtil
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.fragment_movie_detail.*

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Fragment to display movie details
 * @property key String?
 * @property viewModelClass KClass<MovieDetailViewModel>
 */
class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding, MovieDetailViewModel>(R.layout.fragment_movie_detail) {

    private var key: String? = ""
    override val viewModelClass = MovieDetailViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModelObj.movieId = it.getString(MOVIE_ID)
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // set binding
        binding.apply {
            viewModel = viewModelObj
            lifecycleOwner = this@MovieDetailFragment
        }

        // init all views
        initViews()

        // register observers
        initObservables()

        // fetch movie details
        viewModelObj.getMovieDetails()
    }

    /**
     * Initializing All Views
     */
    private fun initViews() {
        // set toolbar
        activity?.setToolbar(toolbar, true)

        // set fab
        fab.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("$YOUTUBE_LINK$key")))
        }

    }

    /**
     * Start listening [androidx.lifecycle.Observer]
     */
    private fun initObservables() {
        viewModelObj.movieDetail.observe(this, Observer {
            populateViews(it)
        })

        // listen for connection error
        viewModelObj.connectionError.observe(
            this,
            Observer { Toast.makeText(activity!!, "Connection Error", Toast.LENGTH_LONG).show() })


        // listen for  error
        viewModelObj.errorMessage.observe(
            this,
            Observer { Toast.makeText(activity!!, it, Toast.LENGTH_LONG).show() })

        // listen for  progress
        viewModelObj.showProgress.observe(
            this,
            Observer {
                when {
                    it.show -> startProgress(it.title, it.message)
                    else -> stopProgress()
                }
            })
    }


    /**
     * Movie details are available now. Show all data on view
     * @param detail MovieDetail
     */
    private fun populateViews(detail: MovieDetail) {
        when {
            !detail.videos.results.isNullOrEmpty() -> key = detail.videos.results[0].key
        }

        tv_overview_title.text = getString(R.string.overview)
        toolbar_layout.title = detail.title
        tv_overview.text = detail.overview
        tv_release_date.text = getString(
            R.string.placeholder_release_date,
            DateUtil.getFormattedDate(detail.releaseDate ?: System.currentTimeMillis().toString())
        )
        tv_rating.text = getString(R.string.placeholder_rating, detail.voteAverage.toString())

            // Load image

        // add genres chips

        // Load image
        img_backdrop.showImage(url = detail.backdropPath)

        // add genres chips
        detail.genres?.forEach {
            genreLayout.addView(Chip(context).add(it.name ?: ""))
        }
    }


    companion object {

        private const val MOVIE_ID = "movie_id"
        private const val YOUTUBE_LINK = "http://www.youtube.com/watch?v="

        /**
         * @param movieId String movie Id
         * @return A new instance of fragment MovieDetailFragment.
         */
        @JvmStatic
        fun newInstance(movieId: String) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(MOVIE_ID, movieId)
                }
            }
    }
}
