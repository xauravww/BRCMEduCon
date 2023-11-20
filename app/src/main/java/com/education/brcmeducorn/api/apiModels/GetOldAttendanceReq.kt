package com.education.brcmeducorn.api.apiModels

data class GetOldAttendanceReq(
    val branch: String,
    val date: String,
    val semester: String,
    val token: String,
    val subject: String
)