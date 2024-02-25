package com.example.rowdyhacks.cde

import android.util.Log
import com.example.rowdyhacks.Globals.CDE_BASE_URL
import com.example.rowdyhacks.cde.model.PersonalData
import com.example.rowdyhacks.cde.model.PossessionData
import com.example.rowdyhacks.cde.network.CdeApiInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class CdeRepository(
    private val api: CdeApiInterface,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
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

    suspend fun getPersonalSafetyActual(
        ori: String,
        offense: List<String>
    ): Result<Int> {
        return try {
            withContext(defaultDispatcher) {
                val actual = async {
                    val personalData = getPersonalSafetyData(ori, offense).getOrThrow()

                    with(personalData) {
                        aggravatedAssault + violentCrime + homicide + rape
                    }
                }

                return@withContext Result.success(actual.await())
            }
        } catch (e: Exception) {
            Log.e("CarSafetyRepository", "getPersonalSafetyActual: $e")
            Result.failure(e)
        }
    }

    private suspend fun getPersonalSafetyData(
        ori: String,
        offense: List<String>
    ): Result<PersonalData> {
        return try {
            withContext(defaultDispatcher) {
                val actual = async {
                    val personalDataList: MutableList<Int> = mutableListOf(0,0,0,0)
                    for (i in offense.indices) {
                        val actualList = api.getCdeData(
                            "${CDE_BASE_URL}${ori}/${offense[i]}","2017", "2019"
                        )

                        var actualData = 0
                        for (j in actualList) {
                            actualData += j.actual
                        }

                        personalDataList[i] = actualData
                    }

                    PersonalData(
                        personalDataList[0],
                        personalDataList[1],
                        personalDataList[2],
                        personalDataList[3]
                    )
                }

                return@withContext Result.success(actual.await())
            }
        } catch (e: Exception) {
            Log.e("CarSafetyRepository", "getPersonalSafetyData: $e")
            Result.failure(e)
        }
    }

    suspend fun getPossessionSafetyActual(
        ori: String,
        offense: List<String>
    ): Result<Int> {
        return try {
            withContext(ioDispatcher) {
                val actual = async {
                    val possessionData = getPossessionSafetyData(ori, offense).getOrThrow()

                    with(possessionData) {
                        burglary + propertyCrime + larceny + robbery
                    }
                }

                return@withContext Result.success(actual.await())
            }
        } catch (e: Exception) {
            Log.e("CarSafetyRepository", "getPossessionSafetyActual: $e")
            Result.failure(e)
        }
    }

    private suspend fun getPossessionSafetyData(
        ori: String,
        offense: List<String>
    ): Result<PossessionData> {
        return try {
            withContext(ioDispatcher) {
                val actual = async {
                    val possessionDataList: MutableList<Int> = mutableListOf(0, 0, 0, 0)
                    for (i in offense.indices) {
                        val actualList = api.getCdeData(
                            "${CDE_BASE_URL}${ori}/${offense[i]}", "2017", "2019"
                        )

                        var actualData = 0
                        for (j in actualList) {
                            actualData += j.actual
                        }

                        possessionDataList[i] = actualData
                    }

                    PossessionData(
                        possessionDataList[0],
                        possessionDataList[1],
                        possessionDataList[2],
                        possessionDataList[3]
                    )
                }

                return@withContext Result.success(actual.await())
            }
        } catch (e: Exception) {
            Log.e("CarSafetyRepository", "getPossessionSafetyData: $e")
            Result.failure(e)
        }
    }
}