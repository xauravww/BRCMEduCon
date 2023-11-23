package com.education.brcmeducorn.utils

import android.util.Log
import com.education.brcmeducorn.api.ApiService
import com.education.brcmeducorn.api.apiModels.AddGalleryReq
import com.education.brcmeducorn.api.apiModels.RegisterRequest
import com.education.brcmeducorn.api.apiModels.SubmitAssignmentReq
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
        .baseUrl("https://backend-brcm-edu-con.vercel.app/api/v1/")
//        .baseUrl("http://192.168.43.247:4000/api/v1/")
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
                    "GET_ALL_GALLERY" -> apiService.getAllGallery(endpoint)
                    "LOGIN" -> apiService.loginPost(endpoint, requestBody)
                    "ASSIGNMENT_CREATE" -> apiService.createAssignment(endpoint, requestBody)
                    "GET_ASSIGNMENTS" -> apiService.getAssignments(endpoint, requestBody)
                    "GET_STUDENT_ID_CARD" -> {
                        // this endpoint is rollNo that is param of our req
                        apiService.idCardByStudentRollNo(endpoint, requestBody)
                    }

                    "GET_OLD_ATTENDANCE" -> {
                        apiService.getOldAttendance(endpoint, requestBody)
                    }

                    "UPDATE_ATTENDANCE" -> {
                        apiService.updateAttendance(endpoint, requestBody)
                    }

                    "GET_ALL_STUDENTS_FOR_ATTENDANCE" -> {
                        apiService.getAllStudentsForAttendance(endpoint, requestBody)
                    }

                    "CREATE_ATTENDANCE" -> {
                        Log.d("hi anmol", "")
                        apiService.createAttendance(endpoint, requestBody)
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

    suspend fun reqMultipart(
        endpoint: String,
        method: String,
        requestBody: Any,
        path: String
    ): Any {
        Log.d("anmol util", path)
        val file = File(path)
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
                    "ADD_GALLERY" -> {
                        if (requestBody is AddGalleryReq) {
                            apiService.addGallery(
                                endpoint,
                                body,
                                requestBody.description,
                                requestBody.token,
                                requestBody.tags,
                            )
                        } else {
                            return@withContext "ase hi"
                        }
                    }

                    "SUBMIT_ASSIGNMENT" -> {
                        if (requestBody is SubmitAssignmentReq) {
                            apiService.submitAssignment(
                                requestBody.id,
                                body,
                                requestBody.studentName,
                                requestBody.studentRollNo,
                                requestBody.token
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