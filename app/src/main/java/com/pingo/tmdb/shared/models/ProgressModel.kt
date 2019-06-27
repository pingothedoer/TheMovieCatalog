package com.pingo.tmdb.shared.models

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * UI MODEL to show and hide progress dialog
 * @property title String
 * @property message String
 * @property show Boolean
 * @constructor
 */
data class ProgressModel(
    val title: String = "",
    val message: String = "",
    val show: Boolean = true
)