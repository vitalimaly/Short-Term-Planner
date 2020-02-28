package com.vitaliimalone.shorttermplanner.presentation.dialogs

import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.presentation.utils.Res
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
            .setTitleText(Res.string(R.string.date_picker_dialog_title))
            .setCalendarConstraints(calendarConstraints)
            .setSelection(currentDate.toInstant().toEpochMilli())
            .build()
        datePicker.addOnPositiveButtonClickListener {
            val pickedDate = OffsetDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneOffset.UTC)
            onDateSet.invoke(pickedDate)
        }
        datePicker.show(fragmentManager, this.javaClass.simpleName)
    }
}