package com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils

import android.content.Context
import com.education.brcmeducorn.utils.SharedPrefs

class AppPreferences(context: Context) {
    private var prefs: SharedPrefs=SharedPrefs(context)

    fun getToken(): String {
        return prefs.getString("token", "") ?: ""
    }

    fun getName(): String {
        return prefs.getString("name", "") ?: ""
    }

    fun getRollNo(): String {
        return prefs.getString("rollNo", "") ?: ""
    }

    fun getRoll(): String {
        return prefs.getString("roll", "") ?: ""
    }

    fun getImageUrl(): String {
        return prefs.getString("imageUrl", "") ?: ""
    }

    fun getSemester(): String {
        return prefs.getString("semester", "") ?: ""
    }

    fun getBranch(): String {
        return prefs.getString("branch", "") ?: ""
    }

    // You can add more methods for other preferences if needed

    // If you want a single method to get all preferences
    fun getAllPrefs(): Map<String, String> {
        return mapOf(
            "token" to getToken(),
            "name" to getName(),
            "rollNo" to getRollNo(),
            "roll" to getRoll(),
            "imageUrl" to getImageUrl(),
            "semester" to getSemester(),
            "branch" to getBranch()
        )
    }
}
