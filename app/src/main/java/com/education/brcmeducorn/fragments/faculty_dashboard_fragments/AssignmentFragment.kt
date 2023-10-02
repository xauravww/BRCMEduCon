package com.education.brcmeducorn.fragments.faculty_dashboard_fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.education.brcmeducorn.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AssignmentFragment : Fragment() {
    private var editTextAssignmentTitle: EditText? = null
    private var editTextAssignmentDescription: EditText? = null
    private var textViewAssignmentGivenDate: TextView? = null
    private var textViewAssignmentDueDate: TextView? = null
    private var givenCalendar: Calendar? = null
    private var dueCalendar: Calendar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_assignment, container, false)

        editTextAssignmentTitle = view.findViewById(R.id.editTextAssignmentTitle)
        editTextAssignmentDescription = view.findViewById(R.id.editTextAssignmentDescription)
        textViewAssignmentGivenDate = view.findViewById(R.id.textViewAssignmentGivenDate)
        textViewAssignmentDueDate = view.findViewById(R.id.textViewAssignmentDueDate)

        val buttonPickGivenDate = view.findViewById<Button>(R.id.buttonPickGivenDate)
        val buttonPickDueDate = view.findViewById<Button>(R.id.buttonPickDueDate)
        val buttonSubmitAssignment = view.findViewById<Button>(R.id.buttonSubmitAssignment)

        buttonPickGivenDate.setOnClickListener {
            showDatePickerDialog(true)
        }

        buttonPickDueDate.setOnClickListener {
            showDatePickerDialog(false)
        }

        buttonSubmitAssignment.setOnClickListener {
            val title = editTextAssignmentTitle?.text.toString()
            val description = editTextAssignmentDescription?.text.toString()
            val givenDate = textViewAssignmentGivenDate?.text.toString()
            val dueDate = textViewAssignmentDueDate?.text.toString()

            // Handle assignment submission logic here
            // (e.g., store in a database, send to a server, etc.)
        }

        return view
    }

    private fun showDatePickerDialog(isGivenDate: Boolean) {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        val datePickerDialog = DatePickerDialog(requireContext(),
            { _, year, month, dayOfMonth ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(year, month, dayOfMonth)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                if (isGivenDate) {
                    givenCalendar = selectedCalendar
                    textViewAssignmentGivenDate?.text = "Given Date: " + sdf.format(selectedCalendar.time)
                } else {
                    dueCalendar = selectedCalendar
                    textViewAssignmentDueDate?.text = "Due Date: " + sdf.format(selectedCalendar.time)
                }
            }, year, month, day)

        datePickerDialog.show()
    }
}
