package com.pingo.tmdb.shared.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Date formatting Utility
 */
object DateUtil {

    /**
     * Format date from [Long] time-stamp to [String] yyyy-MM-dd
     * @param timestamp Long
     * @return String
     */
    fun getFormattedDate(timestamp: Long? = System.currentTimeMillis()): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(timestamp)
    }

    /**
     * Format date from "dd MMMM yyyy" [String] template to "yyyy-MM-dd"
     * @param date String
     * @return String
     */
    fun getFormattedDate(date: String): String {
        return SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(
            SimpleDateFormat(
                "yyyy-MM-dd",
                Locale.getDefault()
            ).parse(date)
        )
    }
}