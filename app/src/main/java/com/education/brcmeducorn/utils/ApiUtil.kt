package com.education.brcmeducorn.utils

import android.util.Log
import com.education.brcmeducorn.api.ApiService
import com.education.brcmeducorn.api.apiModels.RegisterRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


object ApiUtils {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.43.247:4000/api/v1/")
//        .baseUrl("https://backend-brcm-edu-con.vercel.app/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    suspend fun fetchData(
        endpoint: String,
        method: String,
        requestBody: Any
    ): Any {
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
                    response.body() ?: "no data"
                } else {
                    response.body() ?: "waiting"
                }
            } catch (e: Exception) {
                e.printStackTrace()
                "Error fetching data"
            }
        }
    }

    suspend fun register(
        endpoint: String,
        method: String,
        requestBody: Any,
        imagePath: String
    ): Any {
        Log.d("anmol util", imagePath)
        val file = File(imagePath)
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        return withContext(Dispatchers.IO) {
            try {
                val response = when (method) {
                    "REGISTER" -> {
                        if (requestBody is RegisterRequest) {
                            apiService.registerPost(
                                endpoint,
                                body,
                                requestBody.email,
                                requestBody.phone,
                                requestBody.countryCode,
                                requestBody.pass,
                                requestBody.role,
                                requestBody.rollno,
                                requestBody.name,
                                requestBody.semester,
                                requestBody.address,
                                requestBody.batchYear,
                                requestBody.fathername,
                                requestBody.registrationNo,
                                requestBody.dateOfBirth,
                                requestBody.age
                            )
                        } else {
                            return@withContext "ase hi"
                        }
                    }
                    else -> throw IllegalArgumentException("Invalid method: $method")
                }
                if (response.isSuccessful) {
                    response.body() ?: "no data"
                } else {
                    response.body() ?: "error something"
                }
            } catch (e: Exception) {
                e.printStackTrace()
                "Error$e"
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
