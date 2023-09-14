package com.education.brcmeducorn.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.utils.login_main_utils
import com.google.android.material.textfield.TextInputEditText


class LoginMainActivity : AppCompatActivity() {
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
            login_main_utils().handleLogin(this@LoginMainActivity)
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
        // login_main_utils class is created for convenience in utils
        login_main_utils().adjustColor(this,role,studentBtn,facultyBtn,adminBtn)




    }


}