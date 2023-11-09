package com.education.brcmeducorn.api.apiModels

import okhttp3.RequestBody

data class CreateAssignmentReq(
    val attachment: RequestBody,
    val description: RequestBody,
    val dueDate: RequestBody,
    val branch: RequestBody,
    val givenDate: RequestBody,
    val grades: RequestBody,
    val lateSubmission: RequestBody,
    val priority: RequestBody,
    val semester: RequestBody,
    val status: RequestBody,
    val studentName: RequestBody,
    val studentRollNo: RequestBody,
    val subject: RequestBody,
    val submissionDate: RequestBody,
    val tags: List<RequestBody>,
    val teacherName: RequestBody,
    val title: RequestBody,
    val token: RequestBody
)