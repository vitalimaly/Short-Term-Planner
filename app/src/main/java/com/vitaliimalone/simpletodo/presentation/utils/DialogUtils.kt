package com.vitaliimalone.simpletodo.presentation.utils

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.extensions.fragmentManager
import kotlinx.android.synthetic.main.delete_task_dialog.view.*
import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset

object DialogUtils {
    const val DATE_PICKER_TAG = "DATE_PICKER_TAG"

    fun showDatePickerDialog(context: Context, currentDate: OffsetDateTime, onDateSet: ((OffsetDateTime) -> Unit)) {
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
        context.fragmentManager?.let {
            datePicker.show(it, DATE_PICKER_TAG)
        }
    }

    fun showDeleteTaskDialog(context: Context, onPositiveClick: (() -> Unit)) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.delete_task_dialog, null, false)
        val dialog = BottomSheetDialog(context, R.style.TransparentBottomSheet)
        dialog.setContentView(dialogView)
        dialogView.apply {
            titleTextView.text = Res.string(R.string.delete_task_dialog_title)
            positiveButton.text = Res.string(R.string.cancel)
            negativeButton.text = Res.string(R.string.yes)
            positiveButton.setOnClickListener {
                onPositiveClick.invoke()
                dialog.dismiss()
            }
            negativeButton.setOnClickListener {
                dialog.dismiss()
            }
        }
        dialog.behavior.isHideable = false
        dialog.show()
    }
}