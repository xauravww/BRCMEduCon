package com.education.brcmeducorn.api.apiModels

import okhttp3.RequestBody

data class RegisterRequest(
    val email: RequestBody,
    val phone: RequestBody,
    val countryCode: RequestBody,
    val pass: RequestBody,
    val role: RequestBody,
    val rollno: RequestBody,
    val name: RequestBody,
    val semester: RequestBody,
    val photo: String,
    val address: RequestBody,
    val batchYear: RequestBody,
    val fathername: RequestBody,
    val registrationNo: RequestBody,
    val dateOfBirth: RequestBody,
    val age: RequestBody
)