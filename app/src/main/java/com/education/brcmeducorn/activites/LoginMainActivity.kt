package com.education.brcmeducorn.activites

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.education.brcmeducorn.R
import com.education.brcmeducorn.api.apiModels.Login
import com.education.brcmeducorn.api.apiModels.LoginResponse
import com.education.brcmeducorn.utils.ApiUtils
import com.education.brcmeducorn.utils.login_main_utils
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginMainActivity : AppCompatActivity() {
    lateinit var btnLogin: Button
    lateinit var studentBtn: Button
    lateinit var facultyBtn: Button
    lateinit var adminBtn: Button
    lateinit var role: TextView
    lateinit var password: TextInputEditText
    lateinit var userId: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)
        btnLogin = findViewById(R.id.btnLogin)


//        fetching the login users
        studentBtn = findViewById(R.id.studentBtn)
        facultyBtn = findViewById(R.id.facultyBtn)
        adminBtn = findViewById(R.id.adminBtn)

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

            Toast.makeText(this, userId.text.toString(), Toast.LENGTH_SHORT).show()
        }

        // adjust the colors of the each user
        // login_main_utils class is created for convenience in utils
        login_main_utils().adjustColor(this, role, studentBtn, facultyBtn, adminBtn)

    }


}