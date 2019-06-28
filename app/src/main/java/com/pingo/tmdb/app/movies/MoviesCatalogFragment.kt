package com.pingo.tmdb.app.movies

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pingo.tmdb.R
import com.pingo.tmdb.app.detail.MovieDetailActivity
import com.pingo.tmdb.app.movies.adapter.MovieItemListener
import com.pingo.tmdb.app.movies.adapter.MoviesAdapter
import com.pingo.tmdb.databinding.FragmentMoviesCatalogBinding
import com.pingo.tmdb.shared.ext.resetFilter
import com.pingo.tmdb.shared.ext.setToolbar
import com.pingo.tmdb.shared.ext.setup
import com.pingo.tmdb.shared.ext.showFilter
import com.pingo.tmdb.shared.models.Movie
import com.pingo.tmdb.shared.ui.BaseFragment
import com.pingo.tmdb.shared.ui.DatePickerFragment
import kotlinx.android.synthetic.main.content_movies_catalog.*
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject


/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Fragment to display movies catalog
 * @property viewModelClass KClass<MoviesCatalogViewModel>
 * @property movesAdapter MoviesAdapter
 * @property scroller <no name provided>
 */
class MoviesCatalogFragment :
    BaseFragment<FragmentMoviesCatalogBinding, MoviesCatalogViewModel>(R.layout.fragment_movies_catalog),
    MovieItemListener, // for movie item click
    DatePickerDialog.OnDateSetListener // for date selection from date picker dialog
{

    @Inject
    lateinit var movesAdapter: MoviesAdapter

    /**
     * scroll listener to check if the [RecyclerView] has show the last item
     */
    private val scroller = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = LinearLayoutManager::class.java.cast(recyclerView.layoutManager)
            val totalItemCount = layoutManager!!.itemCount
            val lastVisible = layoutManager.findLastCompletelyVisibleItemPosition()

            val endHasBeenReached = lastVisible + 1 >= totalItemCount
            when {
                totalItemCount > 0 && endHasBeenReached -> viewModelObj.fetchMovies()
            }
        }
    }

    override val viewModelClass = MoviesCatalogViewModel::class

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // set data binding
        binding.apply {
            viewModel = viewModelObj
            lifecycleOwner = this@MoviesCatalogFragment
        }

        // init all view
        initViews()

        // register observers
        initObservers()

        // fetch movies list  as we have started listening changes in movie catalog
        viewModelObj.fetchMovies()

    }

    /**
     * Initializing All Views
     */
    private fun initViews() {
        // set toolbar
        activity?.setToolbar(toolbar)
        // set recycler view
        with(rv_movies.setup()) {
            adapter = movesAdapter
            addOnScrollListener(scroller)
        }
    }


    /**
     * Start listening [androidx.lifecycle.Observer]
     */
    private fun initObservers() {

        // movies list
        viewModelObj.movies.observe(this, Observer {
            movesAdapter.updateResults(it)
        })


        // listen for connection error
        viewModelObj.connectionError.observe(
            this,
            Observer { Toast.makeText(activity!!, "Connection Error", Toast.LENGTH_LONG).show() })


        // listen for  error
        viewModelObj.errorMessage.observe(
            this,
            Observer { Toast.makeText(activity!!, it, Toast.LENGTH_LONG).show() })


        // filter movies or not
        viewModelObj.filterMovies.observe(this, Observer {
            when {
                it -> DatePickerFragment.newInstance(this).also { dialog ->
                    dialog.show(childFragmentManager, "")
                }
                else -> {
                    fab.resetFilter()
                    movesAdapter.clearResults()
                    viewModelObj.removeFilter()
                }
            }
        })

    }


    /**
     * Date has been selected, fetch movies list for the given date.
     * @param view DatePicker
     * @param year Int
     * @param month Int
     * @param dayOfMonth Int
     */
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        fab.showFilter()
        movesAdapter.clearResults()
        viewModelObj.setFilter(year, month, dayOfMonth)
    }


    /**
     * Go to movie details
     * @param result Movie
     */
    override fun onMovieItemClicked(result: Movie) = startActivity(MovieDetailActivity.newInstance(activity!!, result))


    companion object {
        /**
         * @return A new instance of fragment MoviesCatalogFragment.
         */
        @JvmStatic
        fun newInstance() = MoviesCatalogFragment().apply {}
    }
}
