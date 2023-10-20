package com.education.brcmeducorn.utils

import com.education.brcmeducorn.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtils {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.publicapis.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    suspend fun fetchData(endpoint: String, method: String, requestBody: Any?): String {
        return withContext(Dispatchers.IO) {
            try {
                val response = when (method) {
                    "GET" -> apiService.get(endpoint
//                        , requestBody
                    )
                    // ...
                    // Add cases for other HTTP methods if needed
                    else -> throw IllegalArgumentException("Invalid method: $method")
                }
                response.body()?.toString() ?: "Response body is null"
            } catch (e: Exception) {
                e.printStackTrace()
                "Error fetching data"
            }
        }

    }

//    to fetch api
//    start a corutine and we provide to this end point of url and method and body (json)
//    CoroutineScope(Dispatchers.Main).launch {
//        val endpoint = "categories"
//        val method = "GET"
//        val requestBody = null
//        val result = ApiUtils.fetchData(endpoint, method, requestBody)
//        textView.text = result
//    }

}
