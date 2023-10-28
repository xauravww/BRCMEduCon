package com.education.brcmeducorn.activites

import android.Manifest
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import com.education.brcmeducorn.R
import com.education.brcmeducorn.activites.AdminDashboardActivity
import com.education.brcmeducorn.activites.FacultyDashboardActivity
import com.education.brcmeducorn.activites.StudentDashboardActivity
import com.education.brcmeducorn.api.apiModels.LoginResponse
import com.education.brcmeducorn.api.apiModels.RegisterRequest
import com.education.brcmeducorn.utils.ApiUtils
import com.education.brcmeducorn.utils.SharedPrefs
import com.github.dhaval2404.imagepicker.ImagePicker
import com.hbb20.CountryCodePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RegisterActivity : AppCompatActivity() {
    private lateinit var txtBranch: Spinner
    private lateinit var txtSemester: Spinner
    private lateinit var imgUploadBtn: Button
    private lateinit var imgStudent: ImageView
    private lateinit var txtName: EditText
    private lateinit var txtbatch: EditText
    private lateinit var txtRegistrationNo: EditText
    private lateinit var txtUserMail: EditText
    private lateinit var txtPhoneNo: EditText
    private lateinit var countryCode: CountryCodePicker
    private lateinit var txtAddress: EditText
    private lateinit var txtPassword: EditText
    private lateinit var txtRollNo: EditText
    private lateinit var txtFather: EditText
    private lateinit var txtDOB: EditText
    private lateinit var btnUpdateDetails: Button

    private var editTextDOB: EditText? = null
    private var dobCalendar: Calendar? = null
    private var branchArray = arrayOf("Branch", "Cse", "Civil", "Mechanical", "Electrical")
    private var semesterArray = arrayOf(
        "Semester", "Sem1", "Sem2", "Sem3", "Sem4", "Sem5", "Sem6", "Sem7", "Sem8"
    )
    lateinit var prefs: SharedPrefs

    companion object {
        var student_user = 1
        var faculty_user = 0
        var admin_user = 0
        var roll: String = "student"
        var selectedBranch: String = "branch"
        var selectedSemester: String = "sem"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        imgUploadBtn = findViewById(R.id.imgUploadBtn)
        imgStudent = findViewById(R.id.imgStudent)
        txtName = findViewById(R.id.txtName)
        txtBranch = findViewById(R.id.txtBranch)
        txtSemester = findViewById(R.id.txtSemester)
        txtbatch = findViewById(R.id.txtbatch)
        txtRegistrationNo = findViewById(R.id.txtRegistrationNo)
        txtUserMail = findViewById(R.id.txtUserMail)
        txtPhoneNo = findViewById(R.id.txtPhoneNo)
        countryCode = findViewById(R.id.countryCode)
        txtAddress = findViewById(R.id.txtAddress)
        txtFather = findViewById(R.id.txtFather)
        txtDOB = findViewById(R.id.txtDOB)
        txtPassword = findViewById(R.id.txtUserPass)
        txtRollNo = findViewById(R.id.txtRollNo)
        btnUpdateDetails = findViewById(R.id.btnUpdateDetails)
        val branchAdapter =
            ArrayAdapter(this, R.layout.spinner_item, branchArray)
        val semAdapter =
            ArrayAdapter(this, R.layout.spinner_item, semesterArray)

        txtBranch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                selectedBranch = branchArray[position]
                Toast.makeText(
                    this@RegisterActivity,
                    selectedBranch,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        txtSemester.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                selectedSemester = semesterArray[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Kuch nahi karna
            }
        }

        txtBranch.adapter = branchAdapter
        txtSemester.adapter = semAdapter

        txtDOB.setOnClickListener {
            showDatePickerDialog()
        }
        btnUpdateDetails.setOnClickListener {
            registerRequest(this)

        }
        imgUploadBtn.setOnClickListener {

            if (checkPermissions()) {
                ImagePicker.with(this)
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
                    this,
                    " Please allow camera permission",
                    Toast.LENGTH_SHORT
                ).show()
            }


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        imgStudent.setImageURI(data?.data)

    }

    private fun checkPermissions(): Boolean {
        val cameraPermissionRequest = Manifest.permission.CAMERA
        val cameraPermissionGranted = ContextCompat.checkSelfPermission(
            this,
            cameraPermissionRequest
        ) == PackageManager.PERMISSION_GRANTED
        if (cameraPermissionGranted) {
            return true
        }
        ActivityCompat.requestPermissions(
            this,
            arrayOf(cameraPermissionRequest),
            100
        )
        return false
    }

    private fun registerRequest(context: Context) {
        val email = txtUserMail.text?.toString()?.trim() ?: ""
        val phone = txtPhoneNo.text?.toString() ?: ""
        val countryCode = countryCode.selectedCountryCodeAsInt
        val pass = txtPassword.text?.toString()?.trim() ?: ""
        val role = "student"
        val rollno = txtRollNo.text?.toString()?.trim()?.uppercase() ?: ""
        val name = txtName.text?.toString()?.trim() ?: ""
        val semester = selectedSemester.trim()
        val branch = selectedBranch.trim()
        val imageUrl = "img link"
        prefs = SharedPrefs(context)

        val address = txtAddress.text?.toString() ?: ""
        val batchYear = txtbatch.text?.toString()?.toIntOrNull() ?: 0
        val fathername = txtFather.text?.toString() ?: ""
        val registrationNo = txtRegistrationNo.text?.toString() ?: ""
        val dateOfBirth = txtDOB.text?.toString() ?: ""
        val age = 20
        val isValid = isUserValid(
            email, phone, countryCode, pass, role,
            rollno, name, "$branch|$semester", imageUrl,
            address, batchYear, fathername,
            registrationNo, dateOfBirth, age
        )
        if (isValid) {

            CoroutineScope(Dispatchers.Main).launch {
                val endpoint = "register"
                val method = "REGISTER"
                val userRequest = RegisterRequest(
                    email, phone.toLong(), countryCode,
                    pass, role, rollno, name, "$branch|$semester",
                    imageUrl, address, batchYear, fathername,
                    registrationNo, dateOfBirth, age
                )
                Log.d("hloo", userRequest.toString())

                val result = ApiUtils.fetchData(endpoint, method, userRequest)
                Log.d("hlooo", result.toString())

                if (result is LoginResponse) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "your register request has been sent successfully please wait until verify",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("hlooo", result.toString())
                }

            }

        } else {
            Toast.makeText(
                this@RegisterActivity,
                "Invalid input. Please check your details.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun isUserValid(
        email: String, phone: String, countryCode: Int,
        pass: String, role: String, rollno: String, name: String,
        semester: String, imageUrl: String, address: String,
        batchYear: Int, fathername: String, registrationNo: String,
        dateOfBirth: String, age: Int
    ): Boolean {
        var isValid = true

        if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(
                this@RegisterActivity,
                "Please enter a valid email address",
                Toast.LENGTH_SHORT
            ).show()
            isValid = false
        }

        if (phone.isBlank() || phone.length != 10) {
            Toast.makeText(
                this@RegisterActivity,
                "Please enter a valid phone number",
                Toast.LENGTH_SHORT
            ).show()
            isValid = false
        }

        if (pass.isBlank() || pass.length < 6) {
            Toast.makeText(
                this@RegisterActivity,
                "Please enter a password of at least 6 characters",
                Toast.LENGTH_SHORT
            ).show()
            isValid = false
        }

        if (name.isBlank() || name.length > 16) {
            Toast.makeText(
                this@RegisterActivity,
                "Please enter a valid name (up to 16 characters)",
                Toast.LENGTH_SHORT
            ).show()
            isValid = false
        }

        if (batchYear.toString().isBlank() || batchYear.toString().length != 4) {
            Toast.makeText(
                this@RegisterActivity,
                "Please enter a valid batch year (4 digits)",
                Toast.LENGTH_SHORT
            ).show()
            isValid = false
        }
        val dobPattern = Regex("""^\d{4}-\d{2}-\d{2}$""")
        if (dateOfBirth.isBlank() || !dateOfBirth.matches(dobPattern)) {
            Toast.makeText(
                this@RegisterActivity,
                "Please enter a valid date of birth (yyyy-MM-dd)",
                Toast.LENGTH_SHORT
            ).show()
            isValid = false
        }
        if (age < 0) {
            Toast.makeText(
                this@RegisterActivity,
                "Please enter a valid age",
                Toast.LENGTH_SHORT
            ).show()
            isValid = false
        }

        // Add similar checks for other fields

        return isValid
    }

    private fun savePrefs(response: LoginResponse) {

        prefs.saveString("token", response.token)
        prefs.saveString("name", response.member.name)
        prefs.saveString("rollNo", response.member.rollno)
        prefs.saveString("roll", response.member.role)

    }

    private fun checkRoll(response: LoginResponse): Boolean {
        return if (response.member.role == "admin" && response.member.role == "admin") {
            Log.d("hii", true.toString())
            true
        } else if (response.member.role == "student" && response.member.role == "student") {
            true
        } else response.member.role == "faculty" && response.member.role == "faculty"
    }

    private fun navigateDashboard(context: Context, msg: String, isTrue: Boolean) {
        if (isTrue) {
            if (student_user == 1 && faculty_user == 0 && admin_user == 0) {
                val intent = Intent(context, StudentDashboardActivity::class.java)
                ContextCompat.startActivity(context, intent, null)
            } else if (faculty_user == 1 && student_user == 0 && admin_user == 0) {
                val intent = Intent(context, FacultyDashboardActivity::class.java)
                ContextCompat.startActivity(context, intent, null)
            } else if (admin_user == 1 && faculty_user == 0 && student_user == 0) {
                val intent = Intent(context, AdminDashboardActivity::class.java)
                ContextCompat.startActivity(context, intent, null)
            } else {
                Toast.makeText(
                    context, msg, Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(context, "please select your correct roll", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        val datePickerDialog = DatePickerDialog(
            this,
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
