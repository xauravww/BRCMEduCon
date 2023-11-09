package com.education.brcmeducorn.api.apiModels

data class CreateEventReq(
    val date: String,
    val eventLink: String,
    val forSemester: String,
    val image: String,
    val lastdate: String,
    val organisedBy: String,
    val title: String,
    val token: String
)