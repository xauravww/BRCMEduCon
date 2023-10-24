package com.education.brcmeducorn.api.apiModels

data class RegisterRequest(
    val email: String,
    val phone: Long,
    val countryCode: Int,
    val pass: String,
    val role: String,
    val rollno: String,
    val name: String,
    val semester: String,
    val imageurl: String,
    val address: String,
    val batchYear: Int,
    val fathername: String,
    val registrationNo: String,
    val dateOfBirth: String,
    val age: Int
)