package com.pingo.tmdb.app.movies.adapter

import com.pingo.tmdb.shared.models.Movie

/**
 * Listener that will dispatch click event to the Activity = with item data on which the user has clicked
 */
interface MovieItemListener {
    fun onMovieItemClicked(result: Movie)
}