package com.pingo.tmdb.shared.utils

import android.content.Context
import com.pingo.tmdb.R
import com.pingo.tmdb.shared.models.BaseError
import com.pingo.tmdb.shared.network.exception.InternetException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * Created by : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ----------------------------------------------
 *
 * Utility to parse error response to [BaseError]
 */
object ErrorHandler {

    const val INTERNET_ERROR = "net"

    /**
     * Parse error using [ErrorUtil] Moshi converters
     */
    fun getError(context: Context, error: Throwable?): String {

        try {
            when (error) {
                is InternetException, is UnknownHostException, is SocketTimeoutException -> return INTERNET_ERROR
                is IOException -> error.message?.let { return it }
                is HttpException -> {
                    val errorBody = error.response()?.errorBody()?.string()
                    val errors: BaseError? = ErrorUtil(BaseError::class.java).getError(errorBody)
                    return errors?.statusMessage ?: context.getString(R.string.error_unknown)
                }
            }
        } catch (exp: Exception) {
            error?.printStackTrace()
            return context.getString(R.string.error_unknown)
        }
        return context.getString(R.string.error_unknown)
    }


    /**
     * Parse error using [ErrorUtil] Moshi converters
     */
    fun getError(context: Context, error: String?): String {
        return try {
            val errors: BaseError? = ErrorUtil(BaseError::class.java).getError(error)
            errors?.statusMessage ?: context.getString(R.string.error_unknown)
        } catch (exp: Exception) {
            context.getString(R.string.error_unknown)
        }
    }

}
