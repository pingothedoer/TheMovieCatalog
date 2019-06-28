package com.pingo.tmdb.app.detail

import androidx.lifecycle.MutableLiveData
import com.pingo.tmdb.app.detail.data.MovieDetailRepoImp
import com.pingo.tmdb.shared.models.MovieDetail
import com.pingo.tmdb.shared.ui.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ----------------------------------------------
 *
 * Bridging [MovieDetailFragment] and Data Source [MovieDetailRepoImp]
 */
open class MovieDetailViewModel @Inject constructor(private val repo: MovieDetailRepoImp) :
    BaseViewModel() {


    var movieId: String? = null
    val movieDetail: MutableLiveData<MovieDetail> = MutableLiveData()

    /**
     * Coroutine scope
     */
    private val scope = CoroutineScope(Dispatchers.Default)

    /**
     * Fetch movie details from The Movie DB
     */
    fun getMovieDetails() {

        scope.launch {
            try {

                val response = repo.getMovies(movieId!!)
                when {
                    response.isSuccessful -> {
                        movieDetail.postValue(response.body())
                    }
                    else -> onBaseError(response.errorBody())
                }

            } catch (exp: Exception) {
                onBaseError(exp)
            }
        }

    }

}