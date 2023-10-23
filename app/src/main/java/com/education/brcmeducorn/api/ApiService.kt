package com.education.brcmeducorn.api

import com.education.brcmeducorn.api.apiModels.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET
    suspend fun get(
        @Url url: String,
//        @Body requestBody: Any? = null
    ): Response<Any>
    @POST
    suspend fun  loginPost(
        @Url url: String,
        @Body requestBody: Any,
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
