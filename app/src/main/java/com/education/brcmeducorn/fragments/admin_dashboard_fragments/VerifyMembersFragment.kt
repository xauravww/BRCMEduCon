package com.education.brcmeducorn.fragments.admin_dashboard_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.MembersVerifyAdapter
import com.education.brcmeducorn.api.apiModels.DataXXXX
import com.education.brcmeducorn.api.apiModels.GetUnVerifyMemberAdminRes
import com.education.brcmeducorn.api.apiModels.Token
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.AppPreferences
import com.education.brcmeducorn.utils.ApiUtils
import com.education.brcmeducorn.utils.CustomProgressDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VerifyMembersFragment : Fragment() {
    private lateinit var recycleView: RecyclerView
    private lateinit var verifyAdapter: MembersVerifyAdapter
    private var customProgressDialog: CustomProgressDialog? = null
    private lateinit var unVerifiedMemberList: List<DataXXXX>
    private var paginationUnVerifiedMemberList: MutableList<DataXXXX> = mutableListOf()
    private var newDataToAddInPaggi: List<DataXXXX> = listOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_or_remove_members, container, false)
        recycleView = view.findViewById(R.id.recycleView)
        val prefs = AppPreferences(requireContext())

        CoroutineScope(Dispatchers.Main).launch {
            customProgressDialog = CustomProgressDialog(context)
            customProgressDialog!!.setMessage("wait loading unverified members ...")
            customProgressDialog!!.show();
            val endpoint = "admin/members/unverified"
            val method = "GET_UNVERIFIED_MEMBERS"
            val token =
                Token(prefs.getToken())

            val result = ApiUtils.fetchData(endpoint, method, token)

            if (result is GetUnVerifyMemberAdminRes) {
                unVerifiedMemberList = result.data

                val layoutManager = LinearLayoutManager(requireContext())
                recycleView.layoutManager = layoutManager

                verifyAdapter = MembersVerifyAdapter(paginationUnVerifiedMemberList, requireContext())
                recycleView.adapter = verifyAdapter
                loadMoreMember() // Load more data

                recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        val visibleItemCount = layoutManager.childCount
                        val totalItemCount = layoutManager.itemCount
                        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                        // Load more items when the user is about to reach the end
                        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount - 3) {
                            loadMoreMember() // Load more data
                        }
                    }
                })




                if (!result.success) {
                    Toast.makeText(
                        requireContext(),
                        "something error",
                        Toast.LENGTH_SHORT
                    ).show()
                    customProgressDialog!!.dismiss()

                }
                customProgressDialog!!.dismiss()

            } else {
                Toast.makeText(
                    requireContext(),
                    "something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
                customProgressDialog!!.dismiss()

            }

        }


        return view
    }

    private fun loadMoreMember() {
        // Simulate loading data (replace this with your actual logic)
        if (unVerifiedMemberList.isNotEmpty()) {

            if (paginationUnVerifiedMemberList.size != unVerifiedMemberList.size) {
                if (paginationUnVerifiedMemberList.isEmpty() && unVerifiedMemberList.size >= 2) {
                    newDataToAddInPaggi = unVerifiedMemberList.subList(0, 2)
                    val startPosition = paginationUnVerifiedMemberList.size
                    val itemCount = 3
                    // Add the loaded data to the list
                    paginationUnVerifiedMemberList.addAll(newDataToAddInPaggi)
                    verifyAdapter.notifyItemRangeInserted(startPosition, itemCount)
                } else {
                    newDataToAddInPaggi =
                        unVerifiedMemberList.subList(
                            paginationUnVerifiedMemberList.size,
                            paginationUnVerifiedMemberList.size + 1
                        )
                    val startPosition = paginationUnVerifiedMemberList.size
                    val itemCount = 1
                    // Add the loaded data to the list
                    paginationUnVerifiedMemberList.addAll(newDataToAddInPaggi)
                    verifyAdapter.notifyItemRangeInserted(startPosition, itemCount)
                }
            }
        }
    }


}