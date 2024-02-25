package com.example.rowdyhacks.cde.network

import com.example.rowdyhacks.cde.model.CdeData
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CdeApiInterface {
    @GET
    suspend fun getCdeData(
        @Url url: String,
        @Query(value = "from") from: String,
        @Query(value = "to") to: String,
    ): List<CdeData>
}