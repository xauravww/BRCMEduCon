package com.education.brcmeducorn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat

class LoginMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)
//        val window: Window = this.window
//        val background =ContextCompat.getDrawable(this@LoginMain, R.drawable.status_bar_gradient)
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//
//        window.statusBarColor = ContextCompat.getColor(this@LoginMain,android.R.color.transparent)
//        window.navigationBarColor = ContextCompat.getColor(this@LoginMain,android.R.color.transparent)
//        window.setBackgroundDrawable(background)

    }
}