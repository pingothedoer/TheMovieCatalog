package com.pingo.tmdb.app.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pingo.tmdb.R
import com.pingo.tmdb.shared.ext.showImage
import com.pingo.tmdb.shared.models.Movie
import com.pingo.tmdb.shared.utils.DateUtil
import kotlinx.android.synthetic.main.item_movie_list.view.*

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ----------------------------------------------
 *
 * [RecyclerView.Adapter] displaying movies catalog.
 */
class MoviesAdapter(var movieItemListener: MovieItemListener) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private var results = ArrayList<Movie>()

    /**
     * Init View Holder
     * @param viewGroup ViewGroup
     * @param viewType Int
     * @return MoviesViewHolder
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.item_movie_list, viewGroup, false
            )
        )
    }

    /**
     * Binds data with view holder
     * @param viewHolder MoviesViewHolder
     * @param position Int
     */
    override fun onBindViewHolder(viewHolder: MoviesViewHolder, position: Int) {
        viewHolder.bind(results[position])
    }

    /**
     * Item Count
     * @return Int
     */
    override fun getItemCount() = results.size


    /**
     * Replace results with the new ones
     * @param results List<Movie>
     */
    fun setResults(results: List<Movie>) {
        this.results = results as ArrayList<Movie>
        notifyDataSetChanged()
    }


    /**
     * Appends existing results with the new ones
     * @param results List<Movie>
     */
    fun updateResults(results: List<Movie>) {
        this.results.addAll(results)
        notifyDataSetChanged()
    }


    /**
     * Clears all results
     */
    fun clearResults() {
        this.results.clear()
        notifyDataSetChanged()
    }


    /**
     * View holder
     * @property view View
     * @constructor
     */
    inner class MoviesViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {

        // bind data with views
        fun bind(result: Movie) {
            // set movie title
            view.tv_title.text = result.title
            view.tv_release_date.text = DateUtil.getFormattedDate(result.release_date)
            view.tv_user_score_value.text = result.vote_average.toString()
            // load movie poster
            view.img_poster.showImage(result.poster_path, R.mipmap.ic_launcher_foreground)

            view.setOnClickListener { movieItemListener.onMovieItemClicked(result) }
        }
    }
}