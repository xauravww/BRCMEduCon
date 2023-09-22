package com.education.brcmeducorn.studentdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StudentAttendenceEntity::class], version = 7)
abstract  class StudentAttendenceDatabase:RoomDatabase(){

    abstract fun AttendenceDao():AttendenceDAO
}