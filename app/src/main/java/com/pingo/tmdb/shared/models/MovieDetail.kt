package com.pingo.tmdb.shared.models


import com.squareup.moshi.Json

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Data model to parse Movie Details data
 * @property backdropPath String?
 * @property genres List<Genre>?
 * @property id Int?
 * @property overview String?
 * @property popularity Double?
 * @property posterPath String?
 * @property releaseDate String?
 * @property title String?
 * @property video Boolean?
 * @property voteAverage Double?
 * @property videos Videos
 * @property voteCount Int?
 * @constructor
 */
data class MovieDetail(
    @field:Json(name = "backdrop_path")
    val backdropPath: String? = "",
    @field:Json(name = "genres")
    val genres: List<Genre>? = emptyList(),
    @field:Json(name = "id")
    val id: Int? = 0,
    @field:Json(name = "overview")
    val overview: String? = "",
    @field:Json(name = "popularity")
    val popularity: Double? = 0.0,
    @field:Json(name = "poster_path")
    val posterPath: String? = "",
    @field:Json(name = "release_date")
    val releaseDate: String? = "",
    @field:Json(name = "title")
    val title: String? = "",
    @field:Json(name = "video")
    val video: Boolean? = false,
    @field:Json(name = "vote_average")
    val voteAverage: Double? = 0.0,
    @field:Json(name = "videos")
    val videos: Videos = Videos(),
    @field:Json(name = "vote_count")
    val voteCount: Int? = 0
)