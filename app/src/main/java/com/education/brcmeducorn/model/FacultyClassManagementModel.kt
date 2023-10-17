package com.education.brcmeducorn.model

data class FreeTeacher(val name: String)

data class FreeClassAndPeriod(
    val semester: String,
    val period: String,
    val startTime: String,
    val endTime: String
)

data class AbsentTeacher(val name: String)

