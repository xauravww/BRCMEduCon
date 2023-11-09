package com.education.brcmeducorn.utils

import android.util.Log
import com.education.brcmeducorn.api.ApiService
import com.education.brcmeducorn.api.apiModels.CreateAssignmentReq
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
//        .baseUrl("https://backend-brcm-edu-con.vercel.app/api/v1/")
        .baseUrl("http://192.168.43.247:4000/api/v1/")
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
                    "LOGIN" -> apiService.loginPost(endpoint, requestBody)
                    "ASSIGNMENT_CREATE" -> apiService.createAssignment(endpoint, requestBody)

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
                                requestBody.branch,
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

    suspend fun assignment(
        endpoint: String,
        method: String,
        requestBody: Any,
        filepath: String
    ): Any {
//        Log.d("anmol assignment", filepath)
//        val file = File(filepath)
//        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
//        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        return withContext(Dispatchers.IO) {
            try {
                val response = when (method) {
                    "ASSIGNMENT" -> {
                        Log.d("done", "done")
                        if (requestBody is CreateAssignmentReq) {
                            apiService.assignmentPost(
                                endpoint,
//                                body,
                                requestBody.attachment,
                                requestBody.description,
                                requestBody.dueDate,
                                requestBody.givenDate,
                                requestBody.grades,
                                requestBody.lateSubmission,
                                requestBody.priority,
                                requestBody.status,
                                requestBody.studentName,
                                requestBody.studentRollNo,
                                requestBody.subject,
                                requestBody.submissionDate,
                                requestBody.semester,
//                                requestBody.tags,
                                requestBody.teacherName,
                                requestBody.title
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


}
