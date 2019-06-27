package com.pingo.tmdb.shared.network.interceptor

import com.pingo.tmdb.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ----------------------------------------------
 *
 * Interceptor to add "api_key" as Query Parameter in all requests.
 */
class ApiKeyInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}