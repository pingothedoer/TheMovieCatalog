package com.pingo.tmdb.app.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.pingo.tmdb.R
import com.pingo.tmdb.databinding.FragmentMoviesCatalogBinding
import com.pingo.tmdb.shared.ext.setToolbar
import kotlinx.android.synthetic.main.fragment_movies_catalog.*


/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 * [dagger.android.support.DaggerFragment] to display movies catalog
 */
class MoviesCatalogFragment : Fragment() {

    var binding: FragmentMoviesCatalogBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_catalog, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // set data binding
        binding?.apply {
            lifecycleOwner = this@MoviesCatalogFragment
        }

        // init all view
        initViews()

    }

    /**
     * Initializing All Views
     */
    private fun initViews() {
        // set toolbar
        activity?.setToolbar(toolbar)
    }


    companion object {
        /**
         * @return A new instance of fragment MoviesCatalogFragment.
         */
        @JvmStatic
        fun newInstance() = MoviesCatalogFragment().apply {}
    }
}
