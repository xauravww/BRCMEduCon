package com.education.brcmeducorn.api.apiModels

data class Subject(
    val endTime: String,
    val isFree: Boolean,
    val isPresent: Boolean,
    val period: String,
    val roomNo: String,
    val semester: String,
    val startTime: String,
    val subjectName: String
)