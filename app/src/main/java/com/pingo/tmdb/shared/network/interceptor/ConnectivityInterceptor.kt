package com.pingo.tmdb.shared.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ----------------------------------------------
 *
 * Interceptor to check connectivity
 */
class ConnectivityInterceptor : Interceptor {

	@Throws(IOException::class)
	override fun intercept(chain: Interceptor.Chain): Response {
		val builder = chain.request().newBuilder()
		return chain.proceed(builder.build())
	}

}