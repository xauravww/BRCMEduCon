package com.education.brcmeducorn.api.apiModels

data class CreateAssignmentReq(
    val attachment: String,
    val description: String,
    val dueDate: String,
    val branch: String,
    val givenDate: String,
    val grades: Any,
    val lateSubmission: Boolean,
    val priority: String,
    val semester: String,
    val status: String,
    val studentName: String,
    val studentRollNo: String,
    val subject: String,
    val submissionDate: Any,
    val tags: List<String>,
    val teacherName: String,
    val title: String,
    val token: String
)