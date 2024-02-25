package com.example.rowdyhacks.cde.network

import android.util.Log
import com.example.rowdyhacks.Globals.CDE_API_KEY
import com.example.rowdyhacks.Globals.CDE_BASE_URL
import com.example.rowdyhacks.cde.CarSafetyModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CdeApiClient {

    companion object {

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private val client = OkHttpClient.Builder()
            .addInterceptor { chain -> return@addInterceptor  addApiKeyToClient(chain) }
            .build()

        /** Creates the api for for the Cde Data **/
        fun createApi() : CdeApiInterface {

            return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(CDE_BASE_URL)
                .client(client)
                .build()
                .create(CdeApiInterface::class.java)
        }

        /** Adds the CDE Api key to the Http client**/
        private fun addApiKeyToClient(chain: Interceptor.Chain) : Response {
            val request = chain.request().newBuilder()
            val originalUrl = chain.request().url
            val newUrl = originalUrl.newBuilder()
                .addQueryParameter("API_KEY", CDE_API_KEY).build()
            request.url(newUrl)
            return chain.proceed(request.build())
        }
    }
}