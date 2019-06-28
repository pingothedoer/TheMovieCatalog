package com.pingo.tmdb.app.detail

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.pingo.tmdb.R
import com.pingo.tmdb.app.App
import com.pingo.tmdb.shared.models.MovieDetail
import com.pingo.tmdb.shared.models.ProgressModel
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
open class MovieDetailViewModel @Inject constructor(private val repo: MovieDetailRepoImp, context: App) :
    BaseViewModel(context) {


    private val scope = CoroutineScope(Dispatchers.Default)

    var movieId: String? = null
    val movieDetail: MutableLiveData<MovieDetail> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getMovieDetails() {

        scope.launch {
            try {

                showProgress.postValue(
                    ProgressModel(
                        title = getString(R.string.fetching_movie_details),
                        message = getString(R.string.please_wait)
                    )
                )

                val response = repo.getMovies(movieId!!)
                when {
                    response.isSuccessful -> {
                        movieDetail.postValue(response.body())
                        showProgress.postValue(ProgressModel(show = false))
                    }
                    else -> onBaseError(response.errorBody())
                }

            } catch (exp: Exception) {
                onBaseError(exp)
            }
        }

    }

}