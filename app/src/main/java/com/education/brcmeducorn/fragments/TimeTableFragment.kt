package com.education.brcmeducorn.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.TimeSlotAdapter
import com.education.brcmeducorn.customview.CustomView
import com.education.brcmeducorn.model.TimeSlotModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*


class TimeTableFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TimeSlotAdapter
    private lateinit var movingView: CustomView
    private lateinit var currentTimeTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_time_table, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        movingView = view.findViewById(R.id.movingArrowView)
        currentTimeTextView = view.findViewById(R.id.currentTimeTextView)

        val periodItems = getPeriodItems()

        adapter = TimeSlotAdapter(periodItems)
        recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        val currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 60 + Calendar.getInstance().get(Calendar.MINUTE)
        val initialProgress = (currentTime - 540) * 100 / (540 + (45 * (periodItems.size - 1)))
        movingView.setProgress(initialProgress)

        val job = viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            while (true) {
                movingView.moveDown()
                updateCurrentTime()
                delay(1000) // Move every second
            }
        }

        viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                job.cancel()
            }
        })

        return view
    }

    private fun getPeriodItems(): List<TimeSlotModel> {
        val periodItems = mutableListOf<TimeSlotModel>()

        periodItems.add(TimeSlotModel("John Doe", "Developer", "Room 215", R.drawable.brcm_logo_big, "9:00 AM", "9:45 AM"))
        periodItems.add(TimeSlotModel("Jane Doe", "Designer", "Room 216", R.drawable.brcm_logo_big, "10:00 AM", "10:45 AM"))
        periodItems.add(TimeSlotModel("Jane Doe", "Designer", "Room 216", R.drawable.brcm_logo_big, "10:00 AM", "10:45 AM"))
        periodItems.add(TimeSlotModel("Jane Doe", "Designer", "Room 216", R.drawable.brcm_logo_big, "10:00 AM", "10:45 AM"))
        periodItems.add(TimeSlotModel("Jane Doe", "Designer", "Room 216", R.drawable.brcm_logo_big, "10:00 AM", "10:45 AM"))
        periodItems.add(TimeSlotModel("Jane Doe", "Designer", "Room 216", R.drawable.brcm_logo_big, "10:00 AM", "10:45 AM"))
        periodItems.add(TimeSlotModel("Jane Doe", "Designer", "Room 216", R.drawable.brcm_logo_big, "10:00 AM", "10:45 AM"))
        periodItems.add(TimeSlotModel("Jane Doe", "Designer", "Room 216", R.drawable.brcm_logo_big, "10:00 AM", "10:45 AM"))
        periodItems.add(TimeSlotModel("Jane Doe", "Designer", "Room 216", R.drawable.brcm_logo_big, "10:00 AM", "10:45 AM"))
        periodItems.add(TimeSlotModel("Jane Doe", "Designer", "Room 216", R.drawable.brcm_logo_big, "10:00 AM", "10:45 AM"))
        // Add more items as needed

        return periodItems
    }

    private fun updateCurrentTime() {
        val currentTime = Calendar.getInstance()
        val formattedTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(currentTime.time)
        currentTimeTextView.text = formattedTime
    }
}



