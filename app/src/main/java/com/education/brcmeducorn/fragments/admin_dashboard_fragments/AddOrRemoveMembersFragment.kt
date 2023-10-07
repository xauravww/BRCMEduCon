package com.education.brcmeducorn.fragments.admin_dashboard_fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.RetryPolicy
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.education.brcmeducorn.R
import com.hbb20.CountryCodePicker
import org.json.JSONObject

class AddOrRemoveMembersFragment : Fragment() {
    lateinit var txtBranch: Spinner
    lateinit var txtSemester: Spinner
    lateinit var edtName: EditText
    lateinit var spinnerBranch: Spinner
    lateinit var spinnerSemester: Spinner
    lateinit var txtBatch: EditText
    lateinit var txtRegistrationNo: EditText
    lateinit var edtRollNo: EditText
    lateinit var txtUserMail: EditText
    lateinit var countryCode: CountryCodePicker
    lateinit var txtPhoneNo: EditText
    lateinit var txtAddress: EditText
    lateinit var btnUpdateDetails: Button
    lateinit var requestQueue: RequestQueue

    var branchArray = arrayOf("Branch", "Cse", "Civil", "Mechanical", "Electrical")
    var semesterArray = arrayOf("Semester", "Sem1", "Sem2", "Sem3", "Sem4", "Sem5", "Sem6", "Sem7", "Sem8")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_or_remove_members, container, false)

        txtBranch = view.findViewById(R.id.spinnerBranch)
        txtSemester = view.findViewById(R.id.spinnerSemester)
        edtName = view.findViewById(R.id.edtName)
        spinnerBranch = view.findViewById(R.id.spinnerBranch)
        spinnerSemester = view.findViewById(R.id.spinnerSemester)
        txtBatch = view.findViewById(R.id.txtBatch)
        txtRegistrationNo = view.findViewById(R.id.txtRegistrationNo)
        edtRollNo = view.findViewById(R.id.edtRollNo)
        txtUserMail = view.findViewById(R.id.txtUserMail)
        countryCode = view.findViewById(R.id.countryCode)
        txtPhoneNo = view.findViewById(R.id.txtPhoneNo)
        txtAddress = view.findViewById(R.id.txtAddress)
        btnUpdateDetails = view.findViewById(R.id.btnUpdateDetails)

        val branchAdapter = ArrayAdapter(activity as Context, R.layout.spinner_item, branchArray)
        val semAdapter = ArrayAdapter(activity as Context, R.layout.spinner_item, semesterArray)

        txtBranch.adapter = branchAdapter
        txtSemester.adapter = semAdapter


        btnUpdateDetails.setOnClickListener {
            sendPostRequest()
        }



        return view
    }

    private fun sendPostRequest() {
//        val url = "http://192.168.0.104:5000/addStudentDetails" for my localhost testing

        val url = "https://backend-brcm-edu-con.vercel.app/addStudentDetails"

        val jsonObject = JSONObject()
        jsonObject.put("name", edtName.text.toString())
        jsonObject.put("rollNo", edtRollNo.text.toString().toInt())
        jsonObject.put("branch", txtBranch.selectedItem.toString())
        jsonObject.put("sem", txtSemester.selectedItem.toString().substring(3).toInt())
        jsonObject.put("batch", txtBatch.text.toString().toInt())
        jsonObject.put("photo", "https://example.com/john_doe.jpg") // You can set the photo URL here
        jsonObject.put("regNo", txtRegistrationNo.text.toString().toInt())
        jsonObject.put("email", txtUserMail.text.toString())
        jsonObject.put("phone", txtPhoneNo.text.toString().toLong())
        jsonObject.put("countryCode", countryCode.selectedCountryCodeAsInt)
        jsonObject.put("addr", txtAddress.text.toString())

        println(jsonObject)
        val requestQueue: RequestQueue = Volley.newRequestQueue(activity)

        try {
            val jsonObjectRequest = object : JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                { response ->
                    val responseText = response.toString()
                    Toast.makeText(activity as Context, "Success", Toast.LENGTH_SHORT).show()
                },
                { error ->
                    val errorMessage = error.toString()
                    println(errorMessage)
                    Toast.makeText(activity as Context, errorMessage, Toast.LENGTH_SHORT).show()
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Content-Type"] = "application/json; charset=utf-8"
                    return headers
                }

//                 used to set timeout for volley requests
                override fun getRetryPolicy(): RetryPolicy {
                    return DefaultRetryPolicy(
                        30000,  // 30 seconds timeout for both connection and socket
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    )
                }
            }

            requestQueue.add(jsonObjectRequest)
        } catch (e: Exception) {
//            e.printStackTrace()
            Toast.makeText(activity as Context, "An error occurred", Toast.LENGTH_SHORT).show()
        }


    }

}
