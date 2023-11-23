package com.education.brcmeducorn.fragments.student_dashboard_fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.GalleryRecyclerAdapter
import com.education.brcmeducorn.api.apiModels.DataXXX
import com.education.brcmeducorn.api.apiModels.GetAllGalleryRes
import com.education.brcmeducorn.utils.ApiUtils
import com.education.brcmeducorn.utils.CustomProgressDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    private lateinit var galleryAdapter: GalleryRecyclerAdapter
    lateinit var layoutManager: LayoutManager
    private lateinit var imageUrlList: List<DataXXX>
    private var paginationUrlList: MutableList<DataXXX> = mutableListOf()
    private var customProgressDialog: CustomProgressDialog? = null
    private var newImageUrls: List<DataXXX> = listOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        recyclerView = view.findViewById(R.id.stGalleryRecyclerView)

        // Set up RecyclerView Adapter
        getGallery()
//
//         Add scroll listener for pagination


        return view
    }

    private fun loadImages() {
        // Simulate loading data (replace this with your actual logic)
        if (imageUrlList.isNotEmpty()) {

            if (paginationUrlList.size != imageUrlList.size) {
                if (paginationUrlList.isEmpty() && imageUrlList.size >= 2) {
                    newImageUrls = imageUrlList.subList(0, 2)
                    val startPosition = paginationUrlList.size
                    val itemCount = 3
                    // Add the loaded data to the list
                    paginationUrlList.addAll(newImageUrls)
                    galleryAdapter.notifyItemRangeInserted(startPosition, itemCount)
                }
                else{
                    newImageUrls =
                        imageUrlList.subList(paginationUrlList.size, paginationUrlList.size + 1)
                    val startPosition = paginationUrlList.size
                    val itemCount = 1
                    // Add the loaded data to the list
                    paginationUrlList.addAll(newImageUrls)
                    galleryAdapter.notifyItemRangeInserted(startPosition, itemCount)
                }
            }
        }
    }


    private fun getGallery() {
        CoroutineScope(Dispatchers.Main).launch {
            customProgressDialog = CustomProgressDialog(requireContext())
            customProgressDialog!!.setMessage("wait registering ...")
            customProgressDialog!!.show()

            val endpoint = "gallery"
            val method = "GET_ALL_GALLERY"
            val result = ApiUtils.fetchData(endpoint, method, "")

            if (result is GetAllGalleryRes) {
                if (result.success) {
                    imageUrlList = result.data
                    val layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    recyclerView.layoutManager = layoutManager
                    galleryAdapter = GalleryRecyclerAdapter(requireContext(), paginationUrlList)
                    recyclerView.adapter = galleryAdapter
                    loadImages()
                    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            val visibleItemCount = layoutManager.childCount
                            val totalItemCount = layoutManager.itemCount
                            val firstVisibleItemPositions =
                                layoutManager.findFirstVisibleItemPositions(null)
                            // Load more items when the user is about to reach the end
                            if (visibleItemCount + firstVisibleItemPositions[0] >= totalItemCount - 3) {
                                loadImages()// Load more data

                            }
                        }
                    })
                }
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

}