package com.education.brcmeducorn.studentdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "AttendenceTable")
data  class StudentAttendenceEntity(
    @ColumnInfo("Sname") val Sname:String,
    @PrimaryKey val Srollno:Int,
    @ColumnInfo("isPresent") val isPresent:Boolean,
)