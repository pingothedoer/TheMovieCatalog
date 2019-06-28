package com.pingo.tmdb.shared.network.api


import com.pingo.tmdb.shared.models.MoviesCatalog
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * EndPoint to fetch all movies from https://api.themoviedb.org/3/
 */
interface MovieListService {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query(Params.Keys.LANGUAGE) language: String,
        @Query(Params.Keys.ORIGINAL_LANGUAGE) originalLanguage: String,
        @Query(Params.Keys.SORT_BY) sortBy: String,
        @Query(Params.Keys.RELEASE_DATE_FROM) releaseFrom: String,
        @Query(Params.Keys.RELEASE_DATE_TILL) releaseTill: String,
        @Query(Params.Keys.INCLUDE_ADULT) includeAdult: Boolean,
        @Query(Params.Keys.INCLUDE_VIDEO) includeVideo: Boolean,
        @Query(Params.Keys.VOTE_AVG) voteAverage: Int,
        @Query(Params.Keys.PAGE) page: Int
    ): Response<MoviesCatalog>
}