package com.pingo.tmdb.app.movies

import androidx.lifecycle.MutableLiveData
import com.pingo.tmdb.app.movies.data.MoviesCatalogRepoImp
import com.pingo.tmdb.shared.models.Movie
import com.pingo.tmdb.shared.ui.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


/**
 * Created by : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ----------------------------------------------
 *
 * Bridging [MoviesCatalogFragment] and Data Source [MoviesCatalogRepoImp]
 */
open class MoviesCatalogViewModel @Inject constructor(private val repo: MoviesCatalogRepoImp) :
    BaseViewModel() {

    private var pageNumber = 1
    private var filteredDate = System.currentTimeMillis()
    private var isFiltered: Boolean = false

    val movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val filterMovies: MutableLiveData<Boolean> = MutableLiveData()

    /**
     * Coroutine scope
     */
    private val scope = CoroutineScope(Dispatchers.Default)


    /**
     * Fetching movies catalog for the given time range.
     */
    fun fetchMovies() {
        scope.launch {
            try {
                val response = repo.getMovies(isFiltered, filteredDate, pageNumber)
                when {
                    response.isSuccessful -> {
                        pageNumber++
                        movies.postValue(response.body()?.results)
                    }
                    else -> onBaseError(response.errorBody())
                }

            } catch (exp: Exception) {
                onBaseError(exp)
            }
        }
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