package com.pingo.tmdb.app.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.pingo.tmdb.R
import com.pingo.tmdb.databinding.FragmentMovieDetailBinding
import com.pingo.tmdb.shared.ext.setToolbar
import kotlinx.android.synthetic.main.fragment_movie_detail.*

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * [dagger.android.support.DaggerFragment] to display movie details
 */
class MovieDetailFragment :
    Fragment() {

    private var movieId: String? = ""
    private var binding: FragmentMovieDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getString(MOVIE_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // set binding
        binding?.apply {
            lifecycleOwner = this@MovieDetailFragment
        }

        // init all views
        initViews()

    }

    /**
     * Initializing All Views
     */
    private fun initViews() {
        // set toolbar
        activity?.setToolbar(toolbar, true)

    }


    companion object {

        private const val MOVIE_ID = "movie_id"

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
