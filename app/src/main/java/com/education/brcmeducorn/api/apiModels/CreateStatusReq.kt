package com.education.brcmeducorn.api.apiModels

data class CreateStatusReq(
    val attendancePercentage: Int,
    val bookBank: Int,
    val fine: Int,
    val lateFee: Int,
    val name: String,
    val pendingFees: Int,
    val rollNo: String,
    val semester: String,
    val title: String,
    val token: String,
    val totalFees: Int
)