package com.pingo.tmdb.shared.models


import com.squareup.moshi.Json

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Data Model
 * @property id String
 * @property iso31661 String
 * @property iso6391 String
 * @property key String
 * @property name String
 * @property site String
 * @property size Int
 * @property type String
 * @constructor
 */
data class Result(
    @field:Json(name = "id")
    val id: String = "",
    @field:Json(name = "iso_3166_1")
    val iso31661: String = "",
    @field:Json(name = "iso_639_1")
    val iso6391: String = "",
    @field:Json(name = "key")
    val key: String = "",
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "site")
    val site: String = "",
    @field:Json(name = "size")
    val size: Int = 0,
    @field:Json(name = "type")
    val type: String = ""
)