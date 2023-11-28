package com.education.brcmeducorn.activites

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.education.brcmeducorn.R
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.AppPreferences

class SplashActivity : AppCompatActivity() {
    lateinit var background: ImageView
    private val textToDisplay = "BRCM Educon"
    private val handler = Handler(Looper.getMainLooper())
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val splashText = findViewById<TextView>(R.id.autoText)
        splashText.text = ""

        // Delay before starting auto text generation
        handler.postDelayed({
            splashText.visibility = TextView.VISIBLE
            generateAutoText(splashText)
        }, 1000) // You can adjust the delay as needed

        background = findViewById(R.id.background)
        background.alpha = 0.25f
    }

    private fun generateAutoText(textView: TextView) {
        if (currentIndex < textToDisplay.length) {
            val currentText = textToDisplay.substring(0, currentIndex + 1)
            textView.text = currentText
            currentIndex++

            // login_main_utils the delay and speed of text generation
            handler.postDelayed({ generateAutoText(textView) }, 100)
        } else {

            if (AppPreferences(this).getToken().isEmpty()) {
                val intent = Intent(this@SplashActivity, LoginMainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                navigateDashboard()
                finish()

            }
        }
    }

    private fun navigateDashboard() {

        if (AppPreferences(this).getRoll() == "student") {
            val intent = Intent(this, StudentDashboardActivity::class.java)
            ContextCompat.startActivity(this, intent, null)

        } else if (AppPreferences(this).getRoll() == "faculty") {
            val intent = Intent(this, FacultyDashboardActivity::class.java)
            ContextCompat.startActivity(this, intent, null)

        } else if (AppPreferences(this).getRoll() == "admin") {
            val intent = Intent(this, AdminDashboardActivity::class.java)
            ContextCompat.startActivity(this, intent, null)
        }
    }

}