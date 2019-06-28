package com.pingo.tmdb.app.movies.data

import com.pingo.tmdb.shared.models.MoviesCatalog
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
    suspend fun getMovies(isFiltered: Boolean, time: Long?, page: Int = 1): Response<MoviesCatalog>
}
