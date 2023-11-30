package com.education.brcmeducorn.api.apiModels

data class Submission(
    val attachment: imageData,
    val studentName: String,
    val studentRollNo: String,
    val isChecked: Boolean,
    val submissionDate: String
)