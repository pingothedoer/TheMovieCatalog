package com.pingo.tmdb.app.movies

import com.pingo.tmdb.shared.models.MoviesCatalog
import com.pingo.tmdb.shared.network.api.MovieListService
import com.pingo.tmdb.shared.network.api.Params
import com.pingo.tmdb.shared.utils.DateUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Bridge between Data Source (Cloud/LocalDB) and the View Model
 * Fetches movies list and sends it to the view model
 **/
class MoviesCatalogRepo(private val apiService: MovieListService) {


    var releasedTill = ""
    var releasedFrom = ""

    /**
     * Fetch movies from The Movie DB
     * @param isFiltered Boolean
     * @param time Long?
     * @param page Int
     * @return Observable<MoviesCatalog>
     */
    fun getMovies(isFiltered: Boolean, time: Long?, page: Int = 1): Observable<MoviesCatalog> {

        if (isFiltered) {
            releasedFrom = DateUtil.getFormattedDate(time)
            releasedTill = DateUtil.getFormattedDate(time)
        } else {
            releasedFrom = ""
            releasedTill = DateUtil.getFormattedDate()
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
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}