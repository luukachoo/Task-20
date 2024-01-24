package com.example.task20.presentation.extension

import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay

fun View.showSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}


suspend fun TextView.restoreTextColor(colorId: Int) {
    delay(3000)
    setTextColor(colorId)
}