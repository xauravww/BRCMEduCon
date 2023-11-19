package com.education.brcmeducorn.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.ViewAssignmentAdapter
import com.education.brcmeducorn.api.apiModels.GetAssignmentReq
import com.education.brcmeducorn.api.apiModels.GetAssignmentRes
import com.education.brcmeducorn.api.apiModels.StudentIdCardRes
import com.education.brcmeducorn.api.apiModels.Success
import com.education.brcmeducorn.api.apiModels.Token
import com.education.brcmeducorn.utils.ApiUtils
import com.education.brcmeducorn.utils.SharedPrefs
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.text.SimpleDateFormat
import java.util.Date

class IDCardFragment : Fragment() {
    private lateinit var prefs: SharedPrefs
    private lateinit var imgHeaderImage: ShapeableImageView
    private lateinit var txtTitleClgName: TextView
    private lateinit var txtFullName: TextView
    private lateinit var txtFatherName: TextView
    private lateinit var txtDOB: TextView
    private lateinit var txtRollNumber: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtRegNo: TextView
    private lateinit var txtBatchYear: TextView
    private lateinit var txtPhone: TextView
    private lateinit var txtAddress: TextView
    private val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private val outputFormat = SimpleDateFormat("dd/MM/yyyy")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.student_id_card, container, false)
        prefs = SharedPrefs(requireContext())
        imgHeaderImage = view.findViewById(R.id.imgHeaderImage)
        txtTitleClgName = view.findViewById(R.id.txtTitleClgName)
        txtFullName = view.findViewById(R.id.txtFullName)
        txtFatherName = view.findViewById(R.id.txtFatherName)
        txtDOB = view.findViewById(R.id.txtDOB)
        txtRollNumber = view.findViewById(R.id.txtRollNumber)
        txtEmail = view.findViewById(R.id.txtEmail)
        txtRegNo = view.findViewById(R.id.txtRegNo)
        txtBatchYear = view.findViewById(R.id.txtBatchYear)
        txtPhone = view.findViewById(R.id.txtPhone)
        txtAddress = view.findViewById(R.id.txtAddress)

        val userData = getPrefs()
        Picasso.get().load(userData["imageUrl"]).into(imgHeaderImage)

        CoroutineScope(Dispatchers.Main).launch {
            val endpoint = "4358"//            val endpoint = userData["rollNo"]?:""
            val method = "GET_STUDENT_ID_CARD"
            //update in future token auth to get id card
            val token=Token(userData["token"]?:"")
            val result = ApiUtils.fetchData(endpoint, method, token)
            if (result is StudentIdCardRes) {
                txtFullName.text = result.data.name
                txtFatherName.text = "result.data.name"
                txtEmail.text = result.data.email
                txtBatchYear.text = result.data.batchYear.toString()
                txtDOB.text = outputFormat.format(inputFormat.parse(result.data.dob)!!)
                txtRollNumber.text=result.data.rollno
                txtPhone.text=result.data.mobileNo
                txtAddress.text=result.data.address
                txtRegNo.text=result.data.registrationNo
                if (!result.success) {
                    Toast.makeText(
                        requireContext(),
                        "something error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
        return view
    }

    private fun getPrefs(): Map<String, String> {
        val token = prefs.getString("token", "")
        val rollNo = prefs.getString("rollNo", "")
        val imageUrl = prefs.getString("imageUrl", "")

        return mapOf(
            "token" to token,
            "rollNo" to rollNo,
            "imageUrl" to imageUrl
        )
    }
}