package com.education.brcmeducorn.api

import com.education.brcmeducorn.api.apiModels.AllStudentsForAttendanceRes
import com.education.brcmeducorn.api.apiModels.GetAllGalleryRes
import com.education.brcmeducorn.api.apiModels.GetAssignmentRes
import com.education.brcmeducorn.api.apiModels.GetOldAttendanceRes
import com.education.brcmeducorn.api.apiModels.LoginResponse
import com.education.brcmeducorn.api.apiModels.StudentIdCardRes
import com.education.brcmeducorn.api.apiModels.Success
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    @GET
    suspend fun getAllGallery(
        @Url url: String,
    ): Response<GetAllGalleryRes>

    @POST
    suspend fun getAssignments(
        @Url url: String,
        @Body requestBody: Any
    ): Response<GetAssignmentRes>
    @POST
    suspend fun getAssignmentsToCheck(
        @Url url: String,
        @Body requestBody: Any
    ): Response<GetAssignmentRes>

    @POST
    suspend fun loginPost(
        @Url url: String,
        @Body requestBody: Any,
    ): Response<LoginResponse>
    @POST
    suspend fun updateSubmissionAssignmentByFaculty(
        @Url url: String,
        @Body requestBody: Any,
    ): Response<Success>

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

    @Multipart
    @POST
    suspend fun addGallery(
        @Url url: String,
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
        @Part("token") token: RequestBody,
        @Part("tags") tags:RequestBody
    ): Response<Success>

    @POST
    suspend fun createAssignment(
        @Url url: String,
        @Body requestBody: Any,
    ): Response<Success>

    @POST("student/id-card/{rollNo}")
    suspend fun idCardByStudentRollNo(
        @Path("rollNo") rollNo: String,
        @Body requestBody: Any
    ): Response<StudentIdCardRes>

    @POST
    suspend fun getOldAttendance(
        @Url url: String,
        @Body requestBody: Any
    ): Response<GetOldAttendanceRes>


    @POST
    suspend fun getAllStudentsForAttendance(
        @Url url: String,
        @Body requestBody: Any
    ): Response<AllStudentsForAttendanceRes>

    @POST
    suspend fun createAttendance(
        @Url url: String,
        @Body requestBody: Any
    ): Response<Success>

    @Multipart
    @PUT("submit/assignment/{id}")
    suspend fun submitAssignment(
        @Path("id") id: String,
        @Part file: MultipartBody.Part,
        @Part("studentName") studentName: RequestBody,
        @Part("studentRollNo") studentRollNo: RequestBody,
        @Part("token") token: RequestBody,
    ): Response<Success>

    @PUT
    suspend fun updateAttendance(
        @Url url: String,
        @Body requestBody: Any
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
