package com.pingo.tmdb.shared.models

import com.squareup.moshi.Json

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Base Error to parse errors sent from the movie db api
 */
class BaseError {

    @field:Json(name = "status_message")
    var statusMessage: String? = null

    @field:Json(name = "success")
    var success: Boolean? = null


    @field:Json(name = "status_code")
    var statusCode: Int? = null

}



