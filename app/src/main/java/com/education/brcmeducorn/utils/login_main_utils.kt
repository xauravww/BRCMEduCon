package com.education.brcmeducorn.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.education.brcmeducorn.R
import com.education.brcmeducorn.activites.AdminDashboardActivity
import com.education.brcmeducorn.activites.FacultyDashboardActivity
import com.education.brcmeducorn.activites.StudentDashboardActivity
import com.education.brcmeducorn.api.apiModels.Login
import com.education.brcmeducorn.api.apiModels.LoginResponse
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class login_main_utils {
    private lateinit var prefs: SharedPrefs
    private var customProgressDialog: CustomProgressDialog? = null

    private companion object {
        var student_user = 1
        var faculty_user = 0
        var admin_user = 0
        var roll: String = "student"
    }

    fun adjustColor(
        context: Context,
        role: TextView,
        studentBtn: Button,
        facultyBtn: Button,
        adminBtn: Button,

        userId: EditText,
        password: TextInputEditText

    ) {
        //        managing the click on the login users
//        1 -> student
        studentBtn.setOnClickListener {

            userId.setText("anmol@gmail.com")
            password.setText("anmol@123")

            //set the student user to handle the login
            student_user = 1
            //set the faculty and the admin user to zero
            faculty_user = 0
            admin_user = 0

            role.text = "Student Login"
            roll = "student"
            //set the  background color and the text color
            setSelfColor(studentBtn, context);

            //set the other users background color and text color
            setOtherUser(facultyBtn, adminBtn, context);
        }

//        2 -> faculty
        facultyBtn.setOnClickListener {

            userId.setText("faculty@gmail.com")
            password.setText("faculty@123")
            //set the faculty user to handle the login
            faculty_user = 1
            // set the admin and student user to zero
            student_user = 0;
            admin_user = 0;

            role.text = "Faculty Login"
            roll = "faculty"

            //set the  background color and the text color
            setSelfColor(facultyBtn, context);

            //set the other users background color and text color
            setOtherUser(studentBtn, adminBtn, context);
        }

//        3 -> admin
        adminBtn.setOnClickListener {


            userId.setText("admin@gmail.com")
            password.setText("admin@123")

            //set the admin user to handle the login
            admin_user = 1
            // set the faculty and student user to zero
            student_user = 0;
            faculty_user = 0;

            role.text = "Admin Login"
            roll = "admin"

            //set the  background color and the text color
            setSelfColor(adminBtn, context);

            //set the other users background color and text color
            setOtherUser(studentBtn, facultyBtn, context);
        }
    }

    fun setOtherUser(btn1: Button, btn2: Button, context: Context) {
        // set color of other user to appPrimary
        btn1.setBackgroundColor(ContextCompat.getColor(context, R.color.appPrimary))
        btn2.setBackgroundColor(ContextCompat.getColor(context, R.color.appPrimary))

        // set the text color of other users to white
        btn1.setTextColor(ContextCompat.getColor(context, R.color.white))
        btn2.setTextColor(ContextCompat.getColor(context, R.color.white))
    }

    fun setSelfColor(btn: Button, context: Context) {
        btn.setTextColor(ContextCompat.getColor(context, R.color.black))
        btn.setBackgroundColor(ContextCompat.getColor(context, R.color.white))

    }

    fun handleLogin(context: Context, email: String, pass: String) {
        prefs = SharedPrefs(context)
        customProgressDialog = CustomProgressDialog(context)
        customProgressDialog!!.setMessage("wait logging ...")
        customProgressDialog!!.show();
        login(email.trim(), pass.trim(), context)

    }

    private fun login(email: String, pass: String, context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            val endpoint = "login"
            val method = "LOGIN"
            val requestBody = Login(email, pass)

            val result = ApiUtils.fetchData(endpoint, method, requestBody)

// String value store karna
            if (result is LoginResponse) {
                savePrefs(result)
                if (checkRoll(result)) {
                    navigateDashboard(context, "$roll successfully login", true)
                } else {
                    navigateDashboard(context, "$roll is  is not yours ", false)
                }
                Log.d("hi", result.toString())
            } else {
                customProgressDialog?.dismiss()
                Toast.makeText(
                    context,
                    "send a valid input and check your internet connection",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }
    }

    private fun savePrefs(response: LoginResponse) {

        prefs.saveString("token", response.token)
        prefs.saveString("name", response.member.name)
        prefs.saveString("rollNo", response.member.rollno)
        prefs.saveString("roll", response.member.role)
        prefs.saveString("semester", response.member.semester)
        prefs.saveString("branch", response.member.branch)
        prefs.saveString("imageUrl", response.member.imageurl.url)
        prefs.saveString("address", response.member.address)
        prefs.saveString("fatherName", response.member.fathername)
        prefs.saveString("DOB", response.member.dateOfBirth)
        prefs.saveString("email", response.member.email)
        prefs.saveString("mobile", response.member.phone.toString())
        prefs.saveString("regNo", response.member.registrationNo)
        prefs.saveString("batch", response.member.batchYear.toString())

    }

    private fun checkRoll(response: LoginResponse): Boolean {
        return if (roll == "admin" && response.member.role == "admin") {
            Log.d("hii", true.toString())
            true
        } else if (roll == "student" && response.member.role == "student") {
            true
        } else roll == "faculty" && response.member.role == "faculty"
    }

    private fun navigateDashboard(context: Context, msg: String, isTrue: Boolean) {
        if (isTrue) {
            if (student_user == 1 && faculty_user == 0 && admin_user == 0) {
                val intent = Intent(context, StudentDashboardActivity::class.java)
                startActivity(context, intent, null)
                customProgressDialog?.dismiss()

            } else if (faculty_user == 1 && student_user == 0 && admin_user == 0) {
                val intent = Intent(context, FacultyDashboardActivity::class.java)
                startActivity(context, intent, null)
                customProgressDialog?.dismiss()

            } else if (admin_user == 1 && faculty_user == 0 && student_user == 0) {
                val intent = Intent(context, AdminDashboardActivity::class.java)
                startActivity(context, intent, null)
                customProgressDialog?.dismiss()

            } else {
                Toast.makeText(
                    context, msg, Toast.LENGTH_SHORT
                ).show()
                customProgressDialog?.dismiss()

            }
        } else {
            customProgressDialog?.dismiss()

            Toast.makeText(context, "please select your correct roll", Toast.LENGTH_SHORT).show()
        }
    }
}
