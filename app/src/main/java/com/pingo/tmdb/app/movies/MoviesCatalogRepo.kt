package com.pingo.tmdb.app.movies

import com.pingo.tmdb.shared.models.MoviesCatalog
import com.pingo.tmdb.shared.network.api.MovieListService
import com.pingo.tmdb.shared.network.api.Params
import com.pingo.tmdb.shared.utils.DateUtil
import retrofit2.Response

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Bridge between Data Source (Cloud/LocalDB) and the View Model
 * Fetches movies list and sends it to the view model
 **/

interface MoviesCatalogRepo {
    /**
     * Fetch movies from The Movie DB
     * @param isFiltered Boolean
     * @param time Long?
     * @param page Int
     * @return Observable<MoviesCatalog>
     */
    suspend fun getMovies(isFiltered: Boolean, time: Long?, page: Int = 1): Response<MoviesCatalog>
}

class MoviesCatalogRepoImp(private val apiService: MovieListService) : MoviesCatalogRepo {


    /**
     * Fetch movies from The Movie DB
     * @param isFiltered Boolean
     * @param time Long?
     * @param page Int
     * @return Observable<MoviesCatalog>
     */
    override suspend fun getMovies(isFiltered: Boolean, time: Long?, page: Int): Response<MoviesCatalog> {

        val releasedTill: String
        val releasedFrom: String

        when {
            isFiltered -> {
                releasedFrom = DateUtil.getFormattedDate(time)
                releasedTill = DateUtil.getFormattedDate(time)
            }
            else -> {
                releasedFrom = ""
                releasedTill = DateUtil.getFormattedDate()
            }
        }

        return apiService.getMovies(
            Params.Values.LANGUAGE_EN_US,
            Params.Values.LANGUAGE_EN,
            Params.Values.SORT_RELEASE_DATE_DESC,
            releasedFrom,
            releasedTill,
            includeAdult = false,
            includeVideo = true,
            voteAverage = 1,
            page = page
        )
    }

}