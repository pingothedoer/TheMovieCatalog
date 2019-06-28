package com.pingo.tmdb.app.detail.data

import com.pingo.tmdb.shared.models.MovieDetail
import retrofit2.Response

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Bridge between Data Source (Cloud/LocalDB) and the View Model
 * Fetches movie details and sends it to the view model
 **/

interface MovieDetailRepo {
    /**
     * Fetch movie details from THE MOVIE DB
     * @param movieId String
     * @return Response<MovieDetail>
     */
    suspend fun getMovies(movieId: String): Response<MovieDetail>
}
