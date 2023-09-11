package com.education.brcmeducorn.main_login_page

import android.content.Context
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.education.brcmeducorn.R

class Adjust {

    fun adjustColor(context: Context, role:TextView, studentBtn:Button, facultyBtn:Button, adminBtn:Button)
    {
        //        managing the click on the login users
//        1 -> student
        studentBtn.setOnClickListener {

            role.text = "Student Login"
            //set the color

            studentBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.white))
            studentBtn.setTextColor(ContextCompat.getColor(context,R.color.black))

            // set bg color of other users to appPrimary
            adminBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.appPrimary))
            facultyBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.appPrimary))

            // set the text color of other users to white
            adminBtn.setTextColor(ContextCompat.getColor(context,R.color.white))
            facultyBtn.setTextColor(ContextCompat.getColor(context,R.color.white))

        }

//        2 -> faculty
        facultyBtn.setOnClickListener {

            role.text = "Faculty Login"
            // set color
            facultyBtn.setTextColor(ContextCompat.getColor(context,R.color.black))
            facultyBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.white))


            // set bg color of other users to appPrimary
            studentBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.appPrimary))
            adminBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.appPrimary))

            // set the text color of other users to white
            adminBtn.setTextColor(ContextCompat.getColor(context,R.color.white))
            studentBtn.setTextColor(ContextCompat.getColor(context,R.color.white))

        }

//        3 -> admin
        adminBtn.setOnClickListener {


            role.text = "Admin Login"
            //set the color
            adminBtn.setTextColor(ContextCompat.getColor(context,R.color.black))
            adminBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.white))

            // set color of other user to appPrimary
            facultyBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.appPrimary))
            studentBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.appPrimary))

            // set the text color of other users to white
            facultyBtn.setTextColor(ContextCompat.getColor(context,R.color.white))
            studentBtn.setTextColor(ContextCompat.getColor(context,R.color.white))

        }
    }
}