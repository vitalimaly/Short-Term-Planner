package com.vitaliimalone.simpletodo.presentation.dialogs

import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset

object DatePickerDialog {
    fun show(fragmentManager: FragmentManager, currentDate: OffsetDateTime, onDateSet: ((OffsetDateTime) -> Unit)) {
        val calendarConstraints = CalendarConstraints.Builder()
            .setStart(OffsetDateTime.now().toInstant().toEpochMilli())
            .setOpenAt(currentDate.toInstant().toEpochMilli())
            .build()
        val datePicker = MaterialDatePicker.Builder
            .datePicker()
            .setCalendarConstraints(calendarConstraints)
            .setSelection(currentDate.toInstant().toEpochMilli())
            .build()
        datePicker.addOnPositiveButtonClickListener {
            val pickedDate = OffsetDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneOffset.UTC)
            onDateSet.invoke(pickedDate)
        }
        datePicker.show(fragmentManager, null)
    }
}