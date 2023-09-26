package com.education.brcmeducorn.studentdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AttendenceDAO {


    @Insert
    fun insertAttendenceTable(attendenceEntity: StudentAttendenceEntity)

    @Delete
    fun deleteAttendenceTable(attendenceEntity: StudentAttendenceEntity)

    @Query("SELECT * FROM AttendenceTable")
    fun getlist(): List<StudentAttendenceEntity>

    @Query("UPDATE AttendenceTable SET isPresent = :isPresent WHERE Srollno = :rollNumber")
    suspend fun updateAttendance(rollNumber: Int, isPresent: Boolean)


    @Query("UPDATE AttendenceTable SET isPresent = 0")
    suspend fun updateAllStudentsToFalse()

    @Query("UPDATE AttendenceTable SET isPresent = 1")
    suspend fun updateAllStudentsToTrue()
    @Query("SELECT * FROM AttendenceTable WHERE isPresent=1")
    fun getAllPresent():List<StudentAttendenceEntity>


    @Query("SELECT * FROM AttendenceTable WHERE isPresent=0")
    fun getAllAbsent():List<StudentAttendenceEntity>
}