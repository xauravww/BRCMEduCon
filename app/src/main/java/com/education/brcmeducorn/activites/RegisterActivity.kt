package com.education.brcmeducorn.activites

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.education.brcmeducorn.R
import com.education.brcmeducorn.api.apiModels.LoginResponse
import com.education.brcmeducorn.api.apiModels.RegisterRequest
import com.education.brcmeducorn.fragments.admin_dashboard_fragments.AddOrRemoveMembersFragment
import com.education.brcmeducorn.utils.ApiUtils
import com.education.brcmeducorn.utils.CustomProgressDialog
import com.education.brcmeducorn.utils.RealPathUtil
import com.education.brcmeducorn.utils.SharedPrefs
import com.education.brcmeducorn.utils.ValidRegistration
import com.hbb20.CountryCodePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody


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
    private var branchArray = arrayOf("Branch", "Cse", "Civil", "Mechanical", "Electrical")
    private var semesterArray = arrayOf(
        "Semester", "Sem1", "Sem2", "Sem3", "Sem4", "Sem5", "Sem6", "Sem7", "Sem8"
    )
    lateinit var prefs: SharedPrefs
    private lateinit var selectedImageUri: Uri
    private lateinit var imagePicker: ActivityResultLauncher<Intent>
    private var customProgressDialog: CustomProgressDialog? = null
    private lateinit var imagePath: String

    companion object {
        var student_user = 1
        var faculty_user = 0
        var admin_user = 0
        var roll: String = "student"
        var selectedBranch: String = "Branch"
        var selectedSemester: String = "sem"
        private const val PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1

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
        val branchAdapter = ArrayAdapter(this, R.layout.spinner_item, branchArray)
        val semAdapter = ArrayAdapter(this, R.layout.spinner_item, semesterArray)
        getItemFromSpinner(branchArray, semesterArray)
        txtBranch.adapter = branchAdapter
        txtSemester.adapter = semAdapter

        imagePicker =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    val imageUri = data?.data
                    if (imageUri != null) {
                        selectedImageUri = imageUri
                        imagePath = RealPathUtil.getRealPath(this, selectedImageUri).toString()
                        Log.d("impath",imagePath)
                        val bitmap = BitmapFactory.decodeFile(imagePath)
                        imgStudent.setImageBitmap(bitmap)

                    }


                }
            }
        txtDOB.setOnClickListener {
            ValidRegistration.showDatePickerDialog(this, txtDOB)
        }
        btnUpdateDetails.setOnClickListener {
            customProgressDialog = CustomProgressDialog(this)
            customProgressDialog!!.setMessage("wait registering ...")
            customProgressDialog!!.show();
            if (::selectedImageUri.isInitialized) {
                registerRequest(this)
            } else {
                Toast.makeText(this, "please select an image", Toast.LENGTH_SHORT).show()
                customProgressDialog!!.dismiss()
            }
        }
        imgUploadBtn.setOnClickListener {

            if (Build.VERSION.SDK_INT >= 33) {
                if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    imagePicker.launch(intent)
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE

                )
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    imagePicker.launch(intent)

            }


            }
            else {

                if (ContextCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    imagePicker.launch(intent)
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE

                    )

                }


            }
            }

    }


    private fun registerRequest(context: Context) {
        prefs = SharedPrefs(context)

        val email = txtUserMail.text?.toString()?.trim() ?: ""
        val phone = txtPhoneNo.text?.toString() ?: ""
        val countryCode = countryCode.selectedCountryCodeAsInt
        val pass = txtPassword.text?.toString()?.trim() ?: ""
        val role = "student"
        val rollno = txtRollNo.text?.toString()?.trim()?.uppercase() ?: ""
        val name = txtName.text?.toString()?.trim() ?: ""
        val semester = selectedSemester.trim()
        val branch = selectedBranch.trim()
        val address = txtAddress.text?.toString() ?: ""
        val batchYear = txtbatch.text?.toString()?.toIntOrNull() ?: 0
        val fathername = txtFather.text?.toString() ?: ""
        val registrationNo = txtRegistrationNo.text?.toString() ?: ""
        val dateOfBirth = txtDOB.text?.toString() ?: ""
        val age = 20

        val isValid = ValidRegistration.isUserValid(
            email, phone, countryCode, pass, role,
            rollno, name, semester,
            "photo", address, batchYear, fathername, registrationNo,
            dateOfBirth, age, this
        )
        if (isValid) {
            val emailRequestBody = email.toRequestBody("text/plain".toMediaTypeOrNull())
            val phoneRequestBody = phone.toRequestBody("text/plain".toMediaTypeOrNull())
            val countryCodeRequestBody =
                countryCode.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val passRequestBody = pass.toRequestBody("text/plain".toMediaTypeOrNull())
            val roleRequestBody = role.toRequestBody("text/plain".toMediaTypeOrNull())
            val rollnoRequestBody = rollno.toRequestBody("text/plain".toMediaTypeOrNull())
            val nameRequestBody = name.toRequestBody("text/plain".toMediaTypeOrNull())
            val semesterRequestBody = semester.toRequestBody("text/plain".toMediaTypeOrNull())
            val branchRequestBody = branch.toRequestBody("text/plain".toMediaTypeOrNull())
            val addressRequestBody = address.toRequestBody("text/plain".toMediaTypeOrNull())
            val batchYearRequestBody =
                batchYear.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val fathernameRequestBody = fathername.toRequestBody("text/plain".toMediaTypeOrNull())
            val registrationNoRequestBody =
                registrationNo.toRequestBody("text/plain".toMediaTypeOrNull())
            val dateOfBirthRequestBody = dateOfBirth.toRequestBody("text/plain".toMediaTypeOrNull())
            val ageRequestBody = age.toString().toRequestBody("text/plain".toMediaTypeOrNull())




            CoroutineScope(Dispatchers.Main).launch {
                val endpoint = "register"
                val method = "REGISTER"
                Log.d("branch",branchRequestBody.toString())
                val userRequest = RegisterRequest(
                    emailRequestBody, phoneRequestBody, countryCodeRequestBody,
                    passRequestBody, roleRequestBody, rollnoRequestBody, nameRequestBody,
                    branchRequestBody, semesterRequestBody, "photo",
                    addressRequestBody, batchYearRequestBody, fathernameRequestBody,
                    registrationNoRequestBody, dateOfBirthRequestBody, ageRequestBody
                )
                val result = ApiUtils.reqMultipart(endpoint, method, userRequest, imagePath)

                if (result is LoginResponse) {
                    if (checkRoll(result)) {
                        savePrefs(result)

                        navigateDashboard(context, "$roll successfully login", true)

                    } else {
                        navigateDashboard(context, "$roll is  is not yours ", false)
                    }
                    Toast.makeText(
                        this@RegisterActivity,
                        "your register request has been sent successfully please wait until verify",
                        Toast.LENGTH_SHORT
                    ).show()
                    customProgressDialog!!.dismiss()
                    Log.d("result", result.toString())
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                    customProgressDialog!!.dismiss()
                }

            }

        } else {
            Toast.makeText(
                this@RegisterActivity,
                "Invalid input. Please check your details.",
                Toast.LENGTH_SHORT
            ).show()
            customProgressDialog!!.dismiss()

        }
    }

    private fun savePrefs(response: LoginResponse) {
        prefs.saveString("token", response.token)
        prefs.saveString("name", response.member.name)
        prefs.saveString("rollNo", response.member.rollno)
        prefs.saveString("roll", response.member.role)
        prefs.saveString("semester", response.member.semester)
        prefs.saveString("branch", response.member.branch)
        prefs.saveString("imageUrl", response.member.imageurl.url)
        prefs.saveString("address", response.member.address)
        prefs.saveString("fatherName", response.member.fathername)
        prefs.saveString("DOB", response.member.dateOfBirth)
        prefs.saveString("email", response.member.email)
        prefs.saveString("mobile", response.member.phone.toString())
        prefs.saveString("regNo", response.member.registrationNo)
        prefs.saveString("batch", response.member.batchYear.toString())

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
                customProgressDialog!!.dismiss()

            } else if (faculty_user == 1 && student_user == 0 && admin_user == 0) {
                val intent = Intent(context, FacultyDashboardActivity::class.java)
                ContextCompat.startActivity(context, intent, null)
                customProgressDialog!!.dismiss()

            } else if (admin_user == 1 && faculty_user == 0 && student_user == 0) {
                val intent = Intent(context, AdminDashboardActivity::class.java)
                ContextCompat.startActivity(context, intent, null)
                customProgressDialog!!.dismiss()

            } else {
                Toast.makeText(
                    context, msg, Toast.LENGTH_SHORT
                ).show()
                customProgressDialog!!.dismiss()

            }
        } else {
            Toast.makeText(context, "please select your correct roll", Toast.LENGTH_SHORT).show()
            customProgressDialog!!.dismiss()

        }
    }


    private fun getItemFromSpinner(branchArray: Array<String>, semesterArray: Array<String>) {
        txtBranch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                selectedBranch = branchArray[position]

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
    }

}
