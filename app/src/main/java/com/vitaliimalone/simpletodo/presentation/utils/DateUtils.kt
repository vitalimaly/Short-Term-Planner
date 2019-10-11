package com.vitaliimalone.simpletodo.presentation.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun getFullCurrentDate(): String {
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return dateFormat.format(currentDate)
    }
}