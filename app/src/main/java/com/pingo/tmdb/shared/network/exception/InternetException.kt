package com.pingo.tmdb.shared.network.exception

import java.io.IOException

/**
 * Created by : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ----------------------------------------------
 *
 * Custom exception for Internet connections
 */

class InternetException : IOException(){
    override val message: String?
        get() = "Please check your internet connection"
}