package com.education.brcmeducorn.api.apiModels

data class AllStudentsForAttendanceRes (
    val `data`: List<Student>
)
data class Student(
    val _id: String,
    val name: String,
    val rollno: String,
    val imageurl: imageData
)