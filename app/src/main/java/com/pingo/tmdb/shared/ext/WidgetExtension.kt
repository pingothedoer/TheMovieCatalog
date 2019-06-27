@file:Suppress("NOTHING_TO_INLINE")

package com.pingo.tmdb.shared.ext

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.pingo.tmdb.BuildConfig
import com.pingo.tmdb.R
import com.squareup.picasso.Picasso

/**
 * Created by : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ----------------------------------------------
 *
 * Widget's Extension methods
 */

/**
 * Remove Filter : Rename it to "Filter" and change icon back to calendar
 * @receiver ExtendedFloatingActionButton
 */
inline fun ExtendedFloatingActionButton.resetFilter() {
    text = context.getString(R.string.filter)
    icon = ContextCompat.getDrawable(context, R.drawable.ic_date_range_black_24dp)
}


/**
 * Start Filter : Rename it to "Remove Filter" and change icon to cross icon
 * @receiver ExtendedFloatingActionButton
 */
inline fun ExtendedFloatingActionButton.showFilter() {
    text = context.getString(R.string.remove_filter)
    icon = ContextCompat.getDrawable(context, R.drawable.ic_cancel_black_24dp)
}


inline fun ImageView.showImage(url: String?, @DrawableRes placeholder: Int = R.drawable.bg_placeholder) {
    Picasso.get()
        .load(BuildConfig.BASE_IMAGE_URL.plus(url))
        .placeholder(placeholder)
        .into(this)
}


/**
 * Widgets
 */
inline fun RecyclerView.setup(fixed: Boolean = true, nestedScroll: Boolean = false): RecyclerView {
    setHasFixedSize(fixed)
    setItemViewCacheSize(10)
    isNestedScrollingEnabled = nestedScroll
    return this
}


inline fun Chip.add(value: String): Chip {
    text = value
    chipBackgroundColor = ContextCompat.getColorStateList(context, R.color.LightSlateGray)
    return this
}