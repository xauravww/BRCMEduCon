package com.education.brcmeducorn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.education.brcmeducorn.main_login_page.Adjust
import com.google.android.material.textfield.TextInputEditText


class LoginMain : AppCompatActivity() {
    lateinit var btnLogin:Button
    lateinit var studentBtn:Button
    lateinit var facultyBtn:Button
    lateinit var adminBtn:Button
    lateinit var role:TextView
    lateinit var password: TextInputEditText
    lateinit var userId:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)
        btnLogin= findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val intent  = Intent(this@LoginMain,DashboardActivity::class.java)
            startActivity(intent)
        }

//        fetching the login users
        studentBtn = findViewById(R.id.studentBtn)
        facultyBtn = findViewById(R.id.facultyBtn)
        adminBtn   = findViewById(R.id.adminBtn)

//        fetching the role text of the user
        role = findViewById(R.id.txtTitleLoginRole)

//        fetching the password & email or userid

        userId   = findViewById(R.id.edtUseridOrEmail)
        password = findViewById(R.id.edtPassword)

//        manageTheVisibilityOfPassword()


        // adjust the colors of the each user
        // Adjust class is created for convenience in main_login_page
        Adjust().adjustColor(this,role,studentBtn,facultyBtn,adminBtn)




    }


}