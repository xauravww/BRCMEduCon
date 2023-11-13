package com.education.brcmeducorn.api.apiModels

import okhttp3.RequestBody

data class SubmitAssignmentReq(
    val studentName: RequestBody,
    val studentRollNo: RequestBody,
    val token: RequestBody,
    val id: String,
)