package com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils

import android.content.Context
import android.os.AsyncTask
import androidx.room.Room
import com.education.brcmeducorn.studentdatabase.StudentAttendenceDatabase
import com.education.brcmeducorn.studentdatabase.StudentAttendenceEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StudentAttendenceAsyncTask(val context: Context,val studentAttendenceEntity: StudentAttendenceEntity,val mode:Int):AsyncTask<Void,Void,Boolean>() {
    val db = Room.databaseBuilder(context,StudentAttendenceDatabase::class.java,"AttendenceTable").fallbackToDestructiveMigration().build()
    override fun doInBackground(vararg params: Void?): Boolean {

        when(mode)
        {
            1->{
                db.AttendenceDao().insertAttendenceTable(studentAttendenceEntity)
                db.close()
                return true
            }
            2->
            {
                db.AttendenceDao().deleteAttendenceTable(studentAttendenceEntity)
                db.close()
                return true
            }

        }
        return false
    }

}

class StudentAttendenceListAsyncTask(val context: Context,val mode:Int):AsyncTask<Void,Void,List<StudentAttendenceEntity>>() {
    val db = Room.databaseBuilder(context,StudentAttendenceDatabase::class.java,"AttendenceTable").fallbackToDestructiveMigration().build()
    override fun doInBackground(vararg params: Void?): List<StudentAttendenceEntity> {

        when(mode)
        {
            3->{
                val list = db.AttendenceDao().getlist()
                db.close()
                return list
            }

        }
        return emptyList()
    }

}


class StudentSetIsPresent(val context: Context,val mode:Int,val rollno:Int):AsyncTask<Void,Void,Boolean>() {
    val db = Room.databaseBuilder(context,StudentAttendenceDatabase::class.java,"AttendenceTable").fallbackToDestructiveMigration().build()
    override fun doInBackground(vararg params: Void?): Boolean {

        when(mode)
        {

            4->
            {
                GlobalScope.launch(){
                    db.AttendenceDao().updateAllStudentsToTrue()


                }
                db.close()
                return true

            }

            5->
            {
                GlobalScope.launch(){
                    db.AttendenceDao().updateAllStudentsToFalse()


                }
                db.close()
                return true

            }

            6->
            {
                GlobalScope.launch(){

                    db.AttendenceDao().updateAttendance(rollno,true)

                }
                db.close()
            }

            7->{
                GlobalScope.launch(){

                    db.AttendenceDao().updateAttendance(rollno,false)

                }
                db.close()
            }

        }
        return false
    }

}