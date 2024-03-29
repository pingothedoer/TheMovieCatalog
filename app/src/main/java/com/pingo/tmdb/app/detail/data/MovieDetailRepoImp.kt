package com.pingo.tmdb.app.detail.data

import com.pingo.tmdb.shared.models.MovieDetail
import com.pingo.tmdb.shared.network.api.MovieDetailService
import com.pingo.tmdb.shared.network.api.Params
import retrofit2.Response

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Implementation of [MovieDetailRepo] , Bridge between Data Source (Cloud/LocalDB) and the View Model
 * Fetches movie details and sends it to the view model
 **/

open class MovieDetailRepoImp(private val apiService: MovieDetailService) : MovieDetailRepo {

    /**
     * Fetch movie details from THE MOVIE DB
     * @param movieId String
     * @return Observable<MovieDetail>
     */
    override suspend fun getMovies(movieId: String): Response<MovieDetail> {

        return apiService.getMovie(
            movieId,
            Params.Values.LANGUAGE_EN_US,
            Params.Values.APPEND_VIDEOS
        )
    }


}