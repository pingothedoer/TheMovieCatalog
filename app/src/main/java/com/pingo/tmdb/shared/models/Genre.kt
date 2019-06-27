package com.pingo.tmdb.shared.models


import com.squareup.moshi.Json

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Data Model
 * @property id Int?
 * @property name String?
 * @constructor
 */
data class Genre(
    @field:Json(name = "id")
    val id: Int? = 0,
    @field:Json(name = "name")
    val name: String? = ""
)