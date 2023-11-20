package com.education.brcmeducorn.utils

import android.app.Dialog
import android.content.Context
import android.widget.ProgressBar
import android.widget.TextView
import com.education.brcmeducorn.R

class CustomProgressDialog(context: Context?) : Dialog(
    context!!
) {
    var progressBar: ProgressBar
    var progressText: TextView

    init {
        setContentView(R.layout.progress)
        progressBar = findViewById(R.id.progress_bar)
        progressText = findViewById(R.id.loading_message)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        progressBar.max = 100
    }

    fun setMessage(message: String?) {
        progressText.text = message
    }

    fun setMax(max: Int) {
        progressBar.max = max
    }
}
