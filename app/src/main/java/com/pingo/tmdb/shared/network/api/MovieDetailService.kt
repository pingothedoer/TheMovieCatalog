package com.pingo.tmdb.shared.network.api


import com.pingo.tmdb.shared.models.MovieDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * EndPoint to fetch a movie detail from https://api.themoviedb.org/3/
 **/
interface MovieDetailService {

    @GET("movie/{".plus(Params.Keys.MOVIE_ID).plus("}"))
    suspend fun getMovie(
        @Path(Params.Keys.MOVIE_ID) movieId: String,
        @Query(Params.Keys.LANGUAGE) language: String,
        @Query(Params.Keys.APPEND_RESPONSE) appendData: String
    ): Response<MovieDetail>
}