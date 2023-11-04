package com.education.brcmeducorn.activites

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.education.brcmeducorn.R
import com.education.brcmeducorn.utils.login_main_utils
import com.google.android.material.textfield.TextInputEditText


class LoginMainActivity : AppCompatActivity() {
    private lateinit var btnLogin: Button
    private lateinit var studentBtn: Button
    private lateinit var facultyBtn: Button
    private lateinit var adminBtn: Button
    private lateinit var role: TextView
    private lateinit var txtRegister: TextView
    private lateinit var password: TextInputEditText
    private lateinit var userId: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)
        btnLogin = findViewById(R.id.btnLogin)


//        fetching the login users
        studentBtn = findViewById(R.id.studentBtn)
        facultyBtn = findViewById(R.id.facultyBtn)
        adminBtn = findViewById(R.id.adminBtn)
        txtRegister = findViewById(R.id.txtRegister)

//        fetching the role text of the user
        role = findViewById(R.id.txtTitleLoginRole)

//        fetching the password & email or userid

        userId = findViewById(R.id.edtUseridOrEmail)
        password = findViewById(R.id.edtPassword)

//        manageTheVisibilityOfPassword()

        //login user
        btnLogin.setOnClickListener {
            login_main_utils().handleLogin(
                this@LoginMainActivity,
                userId.text.toString(),
                password.text.toString()
            )

        }
        //register
        txtRegister.setOnClickListener {
            ContextCompat.startActivity(this, Intent(this, RegisterActivity::class.java), null)
        }

        // adjust the colors of the each user
        // login_main_utils class is created for convenience in utils
        login_main_utils().adjustColor(this, role, studentBtn, facultyBtn, adminBtn)

    }


}