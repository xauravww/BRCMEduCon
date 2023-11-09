package com.education.brcmeducorn.api

import com.education.brcmeducorn.api.apiModels.LoginResponse
import com.education.brcmeducorn.api.apiModels.Success
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET
    suspend fun get(
        @Url url: String,
//        @Body requestBody: Any? = null
    ): Response<Any>

    @POST
    suspend fun loginPost(
        @Url url: String,
        @Body requestBody: Any,
    ): Response<LoginResponse>

    @Multipart
    @POST
    suspend fun registerPost(
        @Url url: String,
        @Part file: MultipartBody.Part,
        @Part("email") email: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("countryCode") countryCode: RequestBody,
        @Part("pass") pass: RequestBody,
        @Part("role") role: RequestBody,
        @Part("rollno") rollno: RequestBody,
        @Part("name") name: RequestBody,
        @Part("branch") branch: RequestBody,
        @Part("semester") semester: RequestBody,
        @Part("address") address: RequestBody,
        @Part("batchYear") batchYear: RequestBody,
        @Part("fathername") fathername: RequestBody,
        @Part("registrationNo") registrationNo: RequestBody,
        @Part("dateOfBirth") dateOfBirth: RequestBody,
        @Part("age") age: RequestBody
    ): Response<LoginResponse>
    @POST
    suspend fun createAssignment(
        @Url url: String,
        @Body requestBody: Any,
    ): Response<Success>
    @Multipart
    @POST
    suspend fun assignmentPost(
        @Url url: String,
//        @Part file: MultipartBody.Part,
        @Part("attachment") attachment: RequestBody,
        @Part("description") description: RequestBody,
        @Part("dueDate") dueDate: RequestBody,
        @Part("givenDate") givenDate: RequestBody,
        @Part("grades") grades: RequestBody,
        @Part("lateSubmission") lateSubmission: RequestBody,
        @Part("priority") priority: RequestBody,
        @Part("status") status: RequestBody,
        @Part("studentName") studentName: RequestBody,
        @Part("studentRollNo") studentRollNo: RequestBody,
        @Part("subject") subject: RequestBody,
        @Part("submissionDate") submissionDate: RequestBody,
        @Part("semester") semester: RequestBody,
//        @Part("tags") tags: List<RequestBody>,
        @Part("teacherName") teacherName: RequestBody,
        @Part("title") title: RequestBody
    ): Response<Success>

//    @POST("posts")
//    suspend fun createPost(@Body post: Post): Response<Post>
//
//    @DELETE("posts/{id}")
//    suspend fun deletePost(@Path("id") postId: Int): Response<Unit>
//
//    @PUT("posts/{id}")
//    suspend fun updatePost(@Path("id") postId: Int, @Body post: Post): Response<Post>

    // Add more functions for other API endpoints if needed
}
