package com.education.brcmeducorn.utils

import com.education.brcmeducorn.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtils {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://backend-brcm-edu-con.vercel.app/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    suspend fun fetchData(endpoint: String, method: String, requestBody: Any): Any {
        return withContext(Dispatchers.IO) {
            try {
                val response = when (method) {
                    "GET" -> apiService.get(endpoint)
                    "LOGIN" -> {
                        apiService.loginPost(endpoint, requestBody)
                    }

                    else -> throw IllegalArgumentException("Invalid method: $method")
                }
                if (response.isSuccessful) {
                    response.body()?:"no data"
                }
                else {
                    response.body()?: "error something"
                }
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
