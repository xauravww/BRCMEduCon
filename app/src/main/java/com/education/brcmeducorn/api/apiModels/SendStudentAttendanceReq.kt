package com.education.brcmeducorn.api.apiModels

data class AttendanceData(
    val memberId: String = "6545fa30dff4a0f25a8b1091",
    val name: String = "6545fa30dff4a0f25a8b1091",
    val rollno: String = "6545fa30dff4a0f25a8b1091",
    val status: String = "absent",
    val remarks: String = "leave"

)

data class SendStudentAttendanceReq(
    val attendanceData: MutableList<AttendanceData>,
    val date: String,
    val branch: String,
    val semester: String,
    val token: String,
    val subject:String,
    // we are using it for both create and update so in crete no use of this but in update
    // it helps to find the id of that attendance that we want to update in server so for id we dont need to crete a new model for update
    val id: String

)