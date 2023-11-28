package com.education.brcmeducorn.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.content.ContextCompat.startActivity
import com.education.brcmeducorn.activites.LoginMainActivity

class SharedPrefs(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "BRCMPrefs"
    }

    fun saveString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String = ""): String {
        return prefs.getString(key, defaultValue) ?: defaultValue
    }

    fun saveInt(key: String, value: Int) {
        prefs.edit().putInt(key, value).apply()
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return prefs.getInt(key, defaultValue)
    }

    fun saveBoolean(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }

     fun logOut(context: Context) {
        saveString("token", "")
        saveString("name", "")
        saveString("rollNo", "")
        saveString("roll", "")
        saveString("semester", "")
        saveString("branch", "")
        saveString("imageUrl", "")
        saveString("address", "")
        saveString("fatherName", "")
        saveString("DOB", "")
        saveString("email", "")
        saveString("mobile", "")
        saveString("regNo", "")
        saveString("batch", "")
         val intent = Intent(context , LoginMainActivity::class.java)
         context.startActivity(intent)
         (context as Activity).finish()
    }
}
