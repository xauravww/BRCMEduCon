package com.education.brcmeducorn.api

import com.education.brcmeducorn.api.apiModels.LoginResponse
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
        @Part("semester") semester: RequestBody,
        @Part("address") address: RequestBody,
        @Part("batchYear") batchYear: RequestBody,
        @Part("fathername") fathername: RequestBody,
        @Part("registrationNo") registrationNo: RequestBody,
        @Part("dateOfBirth") dateOfBirth: RequestBody,
        @Part("age") age: RequestBody
    ): Response<LoginResponse>

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
