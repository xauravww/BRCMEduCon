package com.education.brcmeducorn.api.apiModels

data class Data(
    val __v: Int,
    val _id: String,
    val branch: String,
    val description: String,
    val dueDate: String,
    val givenDate: String,
    val grades: Any,
    val lateSubmission: Boolean,
    val priority: String,
    val semester: String,
    var status: String,
    val subject: String,
    val submissionDate: Any,
    val submissions: List<Submission>,
    val tags: List<String>,
    val teacherName: String,
    val title: String
)