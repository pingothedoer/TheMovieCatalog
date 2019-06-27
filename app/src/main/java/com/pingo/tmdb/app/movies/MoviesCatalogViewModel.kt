package com.pingo.tmdb.app.movies

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.pingo.tmdb.R
import com.pingo.tmdb.app.App
import com.pingo.tmdb.shared.models.Movie
import com.pingo.tmdb.shared.models.ProgressModel
import com.pingo.tmdb.shared.ui.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

/**
 * Created by : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ----------------------------------------------
 *
 * Bridging [MoviesCatalogFragment] and Data Source [MoviesCatalogRepo]
 */
open class MoviesCatalogViewModel @Inject constructor(private val repo: MoviesCatalogRepo, context: App) :
    BaseViewModel(context) {

    private var pageNumber = 1
    private var filteredDate = System.currentTimeMillis()
    private var isFiltered: Boolean = false

    val movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val filterMovies: MutableLiveData<Boolean> = MutableLiveData()


    /**
     * Fetching movies catalog for the given time range.
     */
    @SuppressLint("CheckResult")
    fun fetchMovies() {

        repo.getMovies(isFiltered, filteredDate, pageNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                showProgress.postValue(
                    ProgressModel(
                        title = getString(R.string.fetching_movies),
                        message = getString(R.string.please_wait)
                    )
                )
            }
            .doOnTerminate { showProgress.postValue(ProgressModel(show = false)) }
            .subscribe(
                { result ->
                    pageNumber++
                    movies.postValue(result.results)
                },
                { error -> onBaseError(error) }
            )
    }


    /**
     * Reset page number and set filter date according to the given date.
     * Loads refreshed data
     * @param year Int
     * @param month Int
     * @param dayOfMonth Int
     */
    fun setFilter(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = GregorianCalendar(year, month, dayOfMonth)
        pageNumber = 1
        filteredDate = calendar.timeInMillis
        fetchMovies()
    }


    /**
     * Reset page number and set filter date according to the current date
     * Loads refreshed data
     */
    fun removeFilter() {
        pageNumber = 1
        filteredDate = System.currentTimeMillis()
        fetchMovies()
    }

    /**
     * Show Data Picker to select the date to fetch movies for
     */
    fun showFilterDialog() {
        isFiltered = !isFiltered
        filterMovies.postValue(isFiltered)
    }
}