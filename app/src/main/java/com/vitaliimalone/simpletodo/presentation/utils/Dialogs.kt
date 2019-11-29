package com.vitaliimalone.simpletodo.presentation.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.presentation.utils.duedatepopup.DueDatePopup
import kotlinx.android.synthetic.main.add_new_task_dialog.view.*
import kotlinx.android.synthetic.main.delete_task_dialog.view.*
import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset

object Dialogs {
    fun showAddNewTaskDialog(context: Context, date: OffsetDateTime, onAddClick: ((Task) -> Unit)) {
        val task = Task(dueTo = date)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.add_new_task_dialog, null, false)
        val dialog = BottomSheetDialog(context, R.style.TransparentBottomSheet)
        dialog.setContentView(dialogView)
        dialogView.apply {
            addImageView.setOnClickListener {
                onAddClick.invoke(task)
                dialog.dismiss()
            }
            addImageView.setEnabledWithAlpha(false)
            titleEditText.addTextChangedListener(onTextChanged = { text, _, _, _ ->
                addImageView.setEnabledWithAlpha(text.trimmed.isNotEmpty())
                task.title = text.trimmed
            })
            titleEditText.setOnEditorActionListener { v, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (titleEditText.text.trimmed.isNotEmpty()) {
                        v.clearFocus()
                        v.hideKeyboard()
                        onAddClick.invoke(task)
                        dialog.dismiss()
                    }
                    true
                } else {
                    false
                }
            }
            dueDateTextView.text = Res.string(R.string.due_to_date, DateTimeUtils.getTaskDueDate(task.dueTo))
            dueDateTextView.setOnClickListener {
                DueDatePopup(context, it, task.dueTo) { pickedDate ->
                    task.dueTo = pickedDate
                    dueDateTextView.text = Res.string(R.string.due_to_date, DateTimeUtils.getTaskDueDate(task.dueTo))
                }
            }
        }
        dialog.behavior.isHideable = false
        dialog.show()
    }

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
            datePicker.show(it, Constants.DATE_PICKER_TAG)
        }
    }

    fun showDeleteTaskDialog(context: Context, onPositiveClick: (() -> Unit)) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.delete_task_dialog, null, false)
        val dialog = BottomSheetDialog(context, R.style.TransparentBottomSheet)
        dialog.setContentView(dialogView)
        dialogView.apply {
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