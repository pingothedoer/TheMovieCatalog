package com.pingo.tmdb.app.detail

import com.pingo.tmdb.shared.models.MovieDetail
import com.pingo.tmdb.shared.network.api.MovieDetailService
import com.pingo.tmdb.shared.network.api.Params
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Bridge between Data Source (Cloud/LocalDB) and the View Model
 * Fetches movie details and sends it to the view model
 **/
class MovieDetailRepo(private val apiService: MovieDetailService) {

    /**
     * Fetch movie details from THE MOVIE DB
     * @param movieId String
     * @return Observable<MovieDetail>
     */
    fun getMovies(movieId: String): Observable<MovieDetail> {

        return apiService.getMovie(
            movieId,
            Params.Values.LANGUAGE_EN_US,
            Params.Values.APPEND_VIDEOS
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}