package com.education.brcmeducorn.fragments.admin_dashboard_fragments

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.education.brcmeducorn.R
import com.github.dhaval2404.imagepicker.ImagePicker

class AddOrRemoveMembersFragment : Fragment() {
    lateinit var txtBranch:Spinner
    lateinit var txtSemester:Spinner
    lateinit var imgUploadBtn:Button
    lateinit var imgStudent:ImageView
    var branchArray = arrayOf("Branch","Cse","Civil","Mechanical","Electrical")
    var semesterArray = arrayOf("Semester","Sem1","Sem2","Sem3","Sem4","Sem5","Sem6","Sem7","Sem8")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_or_remove_members, container, false)

        txtBranch = view.findViewById(R.id.txtBranch)
        txtSemester = view.findViewById(R.id.txtSemester)
        imgUploadBtn = view.findViewById(R.id.imgUploadBtn)
        imgStudent = view.findViewById(R.id.imgStudent)
        val branchAdapter = ArrayAdapter(activity as Context,R.layout.spinner_item,branchArray)
        val semAdapter = ArrayAdapter(activity as Context,R.layout.spinner_item,semesterArray)

        txtBranch.adapter = branchAdapter
        txtSemester.adapter  = semAdapter


        imgUploadBtn.setOnClickListener {

            if(checkPermissions())
            {    ImagePicker.with(this)
                .galleryOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()

            }
            else
            {
                Toast.makeText(activity as Context, " Please allow camera permission", Toast.LENGTH_SHORT).show()
            }


        }
        return view
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        imgStudent.setImageURI(data?.data)

    }

    fun checkPermissions():Boolean
    {
        val cameraPermissionRequest = Manifest.permission.CAMERA
        val cameraPermissionGranted = ContextCompat.checkSelfPermission(activity as Context,cameraPermissionRequest) == PackageManager.PERMISSION_GRANTED
        if(cameraPermissionGranted)
        {
            return true
        }
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(cameraPermissionRequest),100)
        return false
    }
}