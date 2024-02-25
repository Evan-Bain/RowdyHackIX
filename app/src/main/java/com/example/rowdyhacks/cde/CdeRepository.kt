package com.example.rowdyhacks.cde

import android.util.Log
import com.example.rowdyhacks.Globals.CDE_BASE_URL
import com.example.rowdyhacks.cde.network.CdeApiInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class CdeRepository(
    private val api: CdeApiInterface,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend fun getCarSafetyActual(
        ori: String,
        offense: String
    ): Result<Int> {
        return try {
            withContext(defaultDispatcher) {
                val actual = async {
                    val actualList = api.getCdeData("${CDE_BASE_URL}${ori}/${offense}","2017", "2019")

                    var actualData = 0
                    for (i in actualList) {
                        actualData += i.actual
                    }

                    actualData
                }

                return@withContext Result.success(actual.await())
            }
        } catch (e: Exception) {
            Log.e("CarSafetyRepository", "getCarSafetyActual: $e")
            Result.failure(e)
        }
    }
}