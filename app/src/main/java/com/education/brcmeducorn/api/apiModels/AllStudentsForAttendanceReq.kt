package com.education.brcmeducorn.api.apiModels

data class AllStudentsForAttendanceReq(
    val semester: String,
    val branch: String,
    val token: String
)