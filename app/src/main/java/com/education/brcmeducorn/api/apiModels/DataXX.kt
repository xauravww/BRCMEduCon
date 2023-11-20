package com.education.brcmeducorn.api.apiModels

data class DataXX(
    val __v: Int,
    val _id: String,
    val attendanceData: MutableList<AttendanceData>,
    val branch: String,
    val date: String,
    val semester: String
)