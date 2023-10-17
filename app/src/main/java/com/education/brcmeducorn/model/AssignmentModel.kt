package com.education.brcmeducorn.model

class AssignmentModel (
        val title: String,
        val description: String,
        val givenDate: String,
        val dueDate: String,
        val studentName: String,
        val studentRollNo: String,
        val teacherName: String,
        val subject: String,
        val status: String,
        val attachment: String?,
        val feedback: String?,
        val grades: String?,
        val submissionDate: String?,
        val lateSubmission: Boolean,
        val priority: String,
        val tags: List<String>,
        val semester: String
)
