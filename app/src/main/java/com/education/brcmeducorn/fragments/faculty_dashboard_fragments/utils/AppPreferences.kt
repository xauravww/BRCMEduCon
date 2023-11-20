package com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils

import android.content.Context
import com.education.brcmeducorn.utils.SharedPrefs

class AppPreferences(context: Context) {
    private var prefs: SharedPrefs = SharedPrefs(context)

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

    fun getAddress(): String {
        return prefs.getString("address", "") ?: ""
    }

    fun getFatherName(): String {
        return prefs.getString("fatherName", "") ?: ""
    }

    fun getDOB(): String {
        return prefs.getString("DOB", "") ?: ""
    }

    fun getEmail(): String {
        return prefs.getString("email", "") ?: ""
    }

    fun getMobileNumber(): String {
        return prefs.getString("mobile", "") ?: ""
    }

    fun getRegistrationNo(): String {
        return prefs.getString("regNo", "") ?: ""
    }

    fun getBatch(): String {
        return prefs.getString("batch", "") ?: ""
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
