package com.education.brcmeducorn.studentdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StudentAttendenceEntity::class], version = 8)
abstract  class StudentAttendenceDatabase:RoomDatabase(){

    abstract fun AttendenceDao():AttendenceDAO
}