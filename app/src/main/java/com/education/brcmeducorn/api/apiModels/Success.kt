package com.education.brcmeducorn.api.apiModels

data class Success(
    val success: Boolean,
    val data: AssignmentId
)
data class AssignmentId(
    val _id: String
)
