package com.pingo.tmdb.shared.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Data Model to parse Movie data
 * @property vote_count Int
 * @property id Int
 * @property video Boolean
 * @property vote_average Double
 * @property title String
 * @property popularity Double
 * @property poster_path String
 * @property original_language String
 * @property original_title String
 * @property genre_ids List<Int>
 * @property backdrop_path String
 * @property adult Boolean
 * @property overview String
 * @property release_date String
 * @constructor
 */
@Parcelize
data class Movie(
    val vote_count: Int,
    val id: Int,
    val video: Boolean,
    val vote_average: Double,
    val title: String,
    val popularity: Double,
    val poster_path: String,
    val original_language: String,
    val original_title: String,
    val genre_ids: List<Int>,
    val backdrop_path: String,
    val adult: Boolean,
    val overview: String,
    val release_date: String
) : Parcelable