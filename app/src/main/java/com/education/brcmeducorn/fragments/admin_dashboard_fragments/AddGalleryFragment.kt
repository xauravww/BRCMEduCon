package com.education.brcmeducorn.fragments.admin_dashboard_fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.education.brcmeducorn.R
import com.education.brcmeducorn.api.apiModels.AddGalleryReq
import com.education.brcmeducorn.api.apiModels.Success
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.AppPreferences
import com.education.brcmeducorn.utils.ApiUtils
import com.education.brcmeducorn.utils.CustomProgressDialog
import com.education.brcmeducorn.utils.RealPathUtil
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class AddGalleryFragment : Fragment() {

    private lateinit var imageLauncher: ActivityResultLauncher<Intent>
    private lateinit var selectedImageUri: Uri
    private lateinit var imagePath: String
    private lateinit var uploadImage: TextView
    private lateinit var chooseImgText: TextView
    private lateinit var chooseImgTags: TextView
    private lateinit var chooseImg: RoundedImageView
    private lateinit var imgDescription: EditText
    private var customProgressDialog: CustomProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_gallery, container, false)
        chooseImg = view.findViewById(R.id.chooseImg)
        chooseImgText = view.findViewById(R.id.chooseImgText)
        chooseImgTags = view.findViewById(R.id.imgTag)
        imgDescription = view.findViewById(R.id.imgDescription)
        uploadImage = view.findViewById(R.id.uploadImgText)

        imageLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    val imageUri = data?.data
                    if (imageUri != null) {
                        selectedImageUri = imageUri
                        imagePath =
                            RealPathUtil.getRealPath(requireContext(), selectedImageUri).toString()
                        val bitmap = BitmapFactory.decodeFile(imagePath)
                        chooseImg.setImageBitmap(bitmap)

                    }


                }
            }
        uploadImage.setOnClickListener {
            customProgressDialog = CustomProgressDialog(requireContext())
            customProgressDialog!!.setMessage("wait uploading ...")
            customProgressDialog!!.show();
            if (::selectedImageUri.isInitialized) {
                uploadImage()
            } else {
                Toast.makeText(
                    requireContext(),
                    "please select an pic from your gallery",
                    Toast.LENGTH_SHORT
                ).show()
                customProgressDialog!!.dismiss()
            }
        }
        chooseImgText.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                imageLauncher.launch(intent)
            } else {
                ActivityCompat.requestPermissions(
                    requireContext() as Activity,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE

                )
            }

        }

        return view
    }

    private fun uploadImage() {
        CoroutineScope(Dispatchers.Main).launch {
            val endpoint = "admin/gallery"
            val method = "ADD_GALLERY"
            val desRequestBody = imgDescription.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val token = AppPreferences(requireContext()).getToken().toRequestBody("text/plain".toMediaTypeOrNull())
            val hashtags = getTagListFromText(chooseImgTags.text.toString())

            val addGalleryReq = AddGalleryReq(
                description = desRequestBody,
                token,
                tags = hashtags
            )

            val result = ApiUtils.reqMultipart(endpoint, method, addGalleryReq, imagePath)

            if (result is Success) {
                Toast.makeText(
                    requireContext(),
                    "your image is uploaded successfully",
                    Toast.LENGTH_SHORT
                ).show()
                customProgressDialog!!.dismiss()
                Log.d("result", result.toString())
            } else {
                Toast.makeText(
                    requireContext(),
                    "something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
                customProgressDialog!!.dismiss()
            }

        }
    }

    companion object {
        private const val PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2
    }

    private fun getTagListFromText(text: String): RequestBody {
        // Check if text contains #
        val newText = if (!text.contains("#")) {
            "#$text" // Add # in the beginning if not present
        } else {
            text
        }

        // Split the text by spaces
        val words = newText.split("\\s+".toRegex())

        // Filter and collect words starting with #
        val hashtags = words.filter { it.startsWith("#") }

        // Create a requestBody from hashtags
        return hashtags.toString().toRequestBody("text/plain".toMediaTypeOrNull())
    }




}