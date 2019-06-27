package com.pingo.tmdb.app.detail

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.pingo.tmdb.R
import com.pingo.tmdb.app.App
import com.pingo.tmdb.shared.models.MovieDetail
import com.pingo.tmdb.shared.models.ProgressModel
import com.pingo.tmdb.shared.ui.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ----------------------------------------------
 *
 * Bridging [MovieDetailFragment] and Data Source [MovieDetailRepo]
 */
open class MovieDetailViewModel @Inject constructor(private val repo: MovieDetailRepo, context: App) :
    BaseViewModel(context) {


    var movieId: String? = null
    val movieDetail: MutableLiveData<MovieDetail> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getMovieDetails() {

        repo.getMovies(movieId!!)
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
                { result -> movieDetail.postValue(result) },
                { error -> onBaseError(error) }
            )

    }

}