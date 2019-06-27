package com.pingo.tmdb.shared.network.api

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-27.
 * ---------------------------------------------
 *
 * Parameters for Movies Catalog and Movie Details
 */
object Params {

    /**
     * keys used in this request's Query parameter
     */
    object Keys {
        const val MOVIE_ID = "movie_id"
        const val LANGUAGE: String = "language"
        const val ORIGINAL_LANGUAGE = "with_original_language"
        const val SORT_BY: String = "sort_by"
        const val INCLUDE_ADULT: String = "include_adult"
        const val INCLUDE_VIDEO: String = "include_video"
        const val RELEASE_DATE_FROM: String = "primary_release_date.gte"
        const val RELEASE_DATE_TILL: String = "primary_release_date.lte"
        const val PAGE: String = "page"
        const val VOTE_AVG = "vote_average.gte"
        const val APPEND_RESPONSE = "append_to_response"
    }

    /**
     * values  used in this request's Query parameter
     */
    object Values {
        const val LANGUAGE_EN_US = "en-US"
        const val LANGUAGE_EN = "en"
        const val SORT_RELEASE_DATE_DESC = "release_date.desc"
        const val APPEND_VIDEOS = "videos"
    }

}