package com.education.brcmeducorn.api.apiModels

data class LoginResponse(
    val member: Member,
    val success: Boolean,
    val token: String
)