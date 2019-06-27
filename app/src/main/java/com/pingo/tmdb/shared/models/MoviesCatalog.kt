package com.pingo.tmdb.shared.models


/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * UI Model for Movies Catalog
 */
data class MoviesCatalog(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<Movie>
)