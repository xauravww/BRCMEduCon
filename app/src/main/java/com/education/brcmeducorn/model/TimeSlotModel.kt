package com.education.brcmeducorn.model

data class TimeSlotModel (
    val personName: String,
    val personPosition: String,
    val personDescription: String,
    val personImg: Int, // Assuming you'll pass an image resource ID here
    val timeStart: String,
    val timeEnd: String
)