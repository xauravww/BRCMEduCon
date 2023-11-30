package com.education.brcmeducorn.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.api.apiModels.DataXXXX
import com.education.brcmeducorn.api.apiModels.Success
import com.education.brcmeducorn.api.apiModels.VerifyMemberAdminReq
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.AppPreferences
import com.education.brcmeducorn.utils.ApiUtils
import com.education.brcmeducorn.utils.CustomProgressDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date


class MembersVerifyAdapter(
    private val userDataList: List<DataXXXX>,
    private val context: Context
) : RecyclerView.Adapter<MembersVerifyAdapter.ViewHolder>() {
    private val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private val outputFormat = SimpleDateFormat("dd/MM/yyyy")
    private lateinit var prefs: AppPreferences
    private var customProgressDialog: CustomProgressDialog? = null


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtBranch: TextView = itemView.findViewById(R.id.txtBranch)
        var txtSemester: TextView = itemView.findViewById(R.id.txtSemester)
        var imgStudent: ImageView = itemView.findViewById(R.id.imgStudent)
        var txtName: TextView = itemView.findViewById(R.id.txtName)
        var txtbatch: TextView = itemView.findViewById(R.id.txtbatch)
        var txtRegistrationNo: TextView = itemView.findViewById(R.id.txtRegistrationNo)
        var txtUserMail: TextView = itemView.findViewById(R.id.txtUserMail)
        var txtPhoneNo: TextView = itemView.findViewById(R.id.txtPhoneNo)
        var txtAddress: TextView = itemView.findViewById(R.id.txtAddress)
        var txtRollNo: TextView = itemView.findViewById(R.id.txtRollNo)
        var txtFather: TextView = itemView.findViewById(R.id.txtFather)
        var txtDOB: TextView = itemView.findViewById(R.id.txtDOB)
        var verifyBtn: Button = itemView.findViewById(R.id.verifyBtn)
        var middleSection: RelativeLayout = itemView.findViewById(R.id.middleSection)
        var checkMore: TextView = itemView.findViewById(R.id.checkMore)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.member_verify_item, parent, false)
        prefs = AppPreferences(context)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userDataList = userDataList[position]
        val date: Date = inputFormat.parse(userDataList.dateOfBirth)!!
        val formattedDate: String = outputFormat.format(date)
        holder.txtName.text = userDataList.name
        holder.txtRollNo.text = userDataList.rollno
        holder.txtDOB.text = formattedDate

        holder.txtBranch.text = userDataList.branch
        holder.txtbatch.text = userDataList.batchYear.toString()
        holder.txtSemester.text = userDataList.semester
        holder.txtRegistrationNo.text = userDataList.registrationNo
        holder.txtUserMail.text = userDataList.email
        holder.txtFather.text = userDataList.fathername
        holder.txtPhoneNo.text = "${userDataList.countryCode}${userDataList.phone}"
        holder.txtAddress.text = userDataList.address


        holder.checkMore.setOnClickListener {
            if (holder.middleSection.isVisible) {
                holder.middleSection.visibility = View.GONE
                holder.checkMore.text = "view more ..."

            } else {
                holder.middleSection.visibility = View.VISIBLE
                holder.checkMore.text = "view less"


            }
        }
        holder.verifyBtn.setOnClickListener {
            customProgressDialog = CustomProgressDialog(context)
            customProgressDialog!!.setMessage("verifying")
            customProgressDialog!!.show();
            CoroutineScope(Dispatchers.Main).launch {
                val endpoint = "admin/verify"
                val method = "ADMIN_VERIFY_MEMBER"
                val verifyData =
                    VerifyMemberAdminReq(userDataList._id, prefs.getToken(), true)
                val result = ApiUtils.fetchData(endpoint, method, verifyData)

                if (result is Success) {
                    Log.d("result", result.toString())
                    customProgressDialog!!.dismiss()
                    Toast.makeText(
                        context,
                        "user is verified now",
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

    override fun getItemCount(): Int {
        return userDataList.size
    }


}
