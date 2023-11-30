package com.education.brcmeducorn.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.education.brcmeducorn.R
import com.education.brcmeducorn.api.apiModels.Success
import com.education.brcmeducorn.api.apiModels.UpdateSubmissionByFaculty
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.AppPreferences
import com.education.brcmeducorn.utils.ApiUtils
import com.education.brcmeducorn.utils.CustomProgressDialog
import com.github.barteksc.pdfviewer.PDFView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

class PDFViewFragment : Fragment() {
    private lateinit var pdfView: PDFView
    private lateinit var doneBtn: Button
    private lateinit var txtGrade: Spinner
    private var gradeArray = arrayOf("A", "A+", "B", "B+", "C", "C+", "D")
    private var selectedGrade: String = "A"
    private var customProgressDialog: CustomProgressDialog? = null

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_p_d_f_view, container, false)
        pdfView = view.findViewById(R.id.pdfView)
        doneBtn = view.findViewById(R.id.doneBtn)
        txtGrade = view.findViewById(R.id.txtGradeSpinner)

        txtGrade.visibility = View.GONE
        doneBtn.visibility = View.GONE

        val gradeAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, gradeArray)
        getItemFromSpinner()
        txtGrade.adapter = gradeAdapter
        val prefs = AppPreferences(requireContext())
        val studentName = arguments?.getString("studentName") ?: ""
        val studentRollNo = arguments?.getString("studentRollNo") ?: ""
        val assignmentId = arguments?.getString("assignmentId") ?: ""
        val pdfUrl = arguments?.getString("pdfUrl") ?: ""
        Log.d("hi",pdfUrl)
        customProgressDialog = CustomProgressDialog(requireContext())
        customProgressDialog!!.setMessage("wait registering ...")
        customProgressDialog!!.show()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val url = URL(pdfUrl)
                val connection = url.openConnection() as HttpURLConnection

                val inputStream = BufferedInputStream(connection.inputStream)
                val byteArray = inputStream.readBytes()
                launch(Dispatchers.Main) {
                    pdfView.fromBytes(byteArray)
                        .enableSwipe(true)
                        .swipeHorizontal(false)
                        .enableDoubletap(true)
                        .defaultPage(0)
                        .spacing(2)
                        .load()
                    txtGrade.visibility = View.VISIBLE
                    doneBtn.visibility = View.VISIBLE

                }
                customProgressDialog!!.dismiss()

            } catch (e: Exception) {
                // Handle exceptions, log errors, or show an error message
                e.printStackTrace()
                customProgressDialog!!.dismiss()

            }
        }

        doneBtn.setOnClickListener {
            markAsCheck(studentName, studentRollNo, assignmentId)

        }

        return view
    }

    private fun getItemFromSpinner() {
        txtGrade.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                selectedGrade = gradeArray[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedGrade = "A"
            }
        }
    }

    private fun markAsCheck(studentName: String, studentRollNo: String, assignmentId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val endpoint = "faculty/update/submission"
            val method = "UPDATE_SUBMISSION_BY_FACULTY"
            val assignmentData =
                UpdateSubmissionByFaculty(
                    assignmentId,
                    true,
                    studentName,
                    studentRollNo,
                    AppPreferences(requireContext()).getToken()
                )
            val result = ApiUtils.fetchData(endpoint, method, assignmentData)

            if (result is Success) {
                Log.d("result", result.toString())
                customProgressDialog!!.dismiss()
                Toast.makeText(
                    context,
                    "assignment Checked",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context,
                    "something went wrong please try again",
                    Toast.LENGTH_SHORT
                ).show()
                customProgressDialog!!.dismiss()

            }
        }

    }

}