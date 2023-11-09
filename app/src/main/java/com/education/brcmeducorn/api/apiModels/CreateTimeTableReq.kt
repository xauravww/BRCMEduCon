package com.education.brcmeducorn.api.apiModels

data class CreateTimeTableReq(
    val classCoordinator: String,
    val day: String,
    val subjects: List<Subject>,
    val teacherID: String,
    val teacherName: String,
    val token: String
)