package com.education.brcmeducorn.api.apiModels

data class GetAssignmentReq(
    val semester: String,
    val branch: String,
    val rollNo: String,
    )