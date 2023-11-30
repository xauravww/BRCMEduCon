package com.education.brcmeducorn.api.apiModels

data class VerifyMemberAdminReq(
    val id: String,
    val token: String,
    val verified: Boolean
)