package com.pingo.tmdb.shared.models


import com.squareup.moshi.Json

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Data Model
 * @property results List<Result>
 * @constructor
 */
data class Videos(
    @field:Json(name = "results")
    val results: List<Result> = emptyList()
)