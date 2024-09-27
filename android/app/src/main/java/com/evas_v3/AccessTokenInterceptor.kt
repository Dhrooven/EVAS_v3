package com.evas_v3

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AccessTokenInterceptor : Interceptor {

    companion object {
        private const val API_KEY_PARAM = "api_key"
        private const val API_KEY_VALUE = "OLA_API_KEY" // Replace with your actual API key
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Modify the URL to include the API key as a query parameter
        val urlWithApiKey = originalRequest.url.newBuilder()
            .addQueryParameter(API_KEY_PARAM, API_KEY_VALUE)
            .build()

        // Create a new request with the modified URL
        val newRequest = originalRequest.newBuilder()
            .url(urlWithApiKey)
            .build()

        // Proceed with the modified request
        return chain.proceed(newRequest)
    }
}
