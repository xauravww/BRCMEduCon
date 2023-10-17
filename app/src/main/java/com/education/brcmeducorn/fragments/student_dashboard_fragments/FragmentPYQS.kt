package com.education.brcmeducorn.fragments.student_dashboard_fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.GalleryRecyclerAdapter
import com.education.brcmeducorn.adapter.PYQSRecyclerAdapter
import com.education.brcmeducorn.model.PYQS


class FragmentPYQS : Fragment() {

    lateinit var recyclerViewPYQS: RecyclerView
    lateinit var recyclerAdapter: PYQSRecyclerAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager
    val yearWisePapers = arrayListOf<Int>(2020,2021,2022,2023)
    val pyqList = listOf<PYQS>(
        PYQS("NN","PEC - CSE - 401G",yearWisePapers),
        PYQS("ST","PEC - CSE - 413G",yearWisePapers),
        PYQS("SPM","PEC - CSE - 403G",yearWisePapers),
        PYQS("FOM","PEC - 08G",yearWisePapers),
        PYQS("NN","PEC - CSE - 401G",yearWisePapers),
        PYQS("NN","PEC - CSE - 401G",yearWisePapers),
        PYQS("NN","PEC - CSE - 401G",yearWisePapers),
        PYQS("NN","PEC - CSE - 401G",yearWisePapers),
        PYQS("NN","PEC - CSE - 401G",yearWisePapers),
        PYQS("NN","PEC - CSE - 401G",yearWisePapers),
        )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_p_y_q_s, container, false)

        recyclerViewPYQS = view.findViewById(R.id.recyclerViewPYQS)
        layoutManager = GridLayoutManager(activity as Context,3)
        recyclerAdapter = PYQSRecyclerAdapter(activity as Context,pyqList)

        recyclerViewPYQS.adapter = recyclerAdapter
        recyclerViewPYQS.layoutManager = layoutManager
        return view
    }

}