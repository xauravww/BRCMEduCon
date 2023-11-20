package com.education.brcmeducorn.utils

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.pm.PackageManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.github.dhaval2404.imagepicker.ImagePicker
import okhttp3.MultipartBody
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object ValidRegistration {


     fun isUserValid(
        email: String, phone: String, countryCode: Int,
        pass: String, role: String, rollno: String, name: String,
        semester: String, photo: String, address: String,
        batchYear: Int, fathername: String, registrationNo: String,
        dateOfBirth: String, age: Int, context: Context
    ): Boolean {
        var isValid = true

        if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(
                context,
                "Please enter a valid email address",
                Toast.LENGTH_SHORT
            ).show()
            isValid = false
        }

        if (phone.isBlank() || phone.length != 10) {
            Toast.makeText(
                context,
                "Please enter a valid phone number",
                Toast.LENGTH_SHORT
            ).show()
            isValid = false
        }

        if (pass.isBlank() || pass.length < 6) {
            Toast.makeText(
                context,
                "Please enter a password of at least 6 characters",
                Toast.LENGTH_SHORT
            ).show()
            isValid = false
        }

        if (name.isBlank() || name.length > 16) {
            Toast.makeText(
                context,
                "Please enter a valid name (up to 16 characters)",
                Toast.LENGTH_SHORT
            ).show()
            isValid = false
        }

        if (batchYear.toString().isBlank() || batchYear.toString().length != 4) {
            Toast.makeText(
                context,
                "Please enter a valid batch year (4 digits)",
                Toast.LENGTH_SHORT
            ).show()
            isValid = false
        }
        val dobPattern = Regex("""^\d{4}-\d{2}-\d{2}$""")
        if (dateOfBirth.isBlank() || !dateOfBirth.matches(dobPattern)) {
            Toast.makeText(
                context,
                "Please enter a valid date of birth (yyyy-MM-dd)",
                Toast.LENGTH_SHORT
            ).show()
            isValid = false
        }
        if (age < 0) {
            Toast.makeText(
                context,
                "Please enter a valid age",
                Toast.LENGTH_SHORT
            ).show()
            isValid = false
        }

        // Add similar checks for other fields

        return isValid
    }
    fun pickAndCropImage(context:Activity) {
        if (checkPermissions(context)) {
            ImagePicker.with(context)
                .cameraOnly()
                .crop() //Crop image(Optional), Check Customization for more option
                .compress(1024) //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                ) //Final image resolution will be less than 1080 x 1080(Optional)
                .start()

        } else {
            Toast.makeText(
                context,
                " Please allow camera permission",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    private fun checkPermissions(context: Activity): Boolean {
        val cameraPermissionRequest = Manifest.permission.CAMERA
        val cameraPermissionGranted = ContextCompat.checkSelfPermission(
            context,
            cameraPermissionRequest
        ) == PackageManager.PERMISSION_GRANTED
        if (cameraPermissionGranted) {
            return true
        }
        ActivityCompat.requestPermissions(
            context,
            arrayOf(cameraPermissionRequest),
            100
        )
        return false
    }
      fun showDatePickerDialog(context: Context,txtDOB:EditText) {
        var dobCalendar: Calendar? = null
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(year, month, dayOfMonth)

                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

                dobCalendar = selectedCalendar
                txtDOB.setText(sdf.format(selectedCalendar.time))
            }, year, month, day
        )

        datePickerDialog.show()
    }
    }