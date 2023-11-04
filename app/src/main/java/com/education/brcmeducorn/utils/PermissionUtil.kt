package com.education.brcmeducorn.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtil {

    private fun checkPermission(activity: Activity, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(activity: Activity, permission: String, requestCode: Int) {
        ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
    }

    private fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }

    private fun shouldShowRequestPermissionRationale(activity: Activity, permission: String): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
    }

    fun requestAllRequiredPermissions(activity: Activity, permissions: Array<String>, requestCode: Int) {
        val neededPermissions = permissions.filter {
            !checkPermission(activity, it)
        }.toTypedArray()
        requestPermissions(activity, neededPermissions, requestCode)
    }

    fun shouldShowAllRequestPermissionRationale(activity: Activity, permissions: Array<String>): Boolean {
        return permissions.any {
            shouldShowRequestPermissionRationale(activity, it)
        }
    }

}
