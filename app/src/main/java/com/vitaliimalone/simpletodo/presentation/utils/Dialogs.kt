package com.vitaliimalone.simpletodo.presentation.utils

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.presentation.utils.duedatepopup.DueDatePopup
import kotlinx.android.synthetic.main.add_new_task_dialog.view.*
import kotlinx.android.synthetic.main.delete_task_dialog.view.*
import org.threeten.bp.OffsetDateTime

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
                DueDatePopup(context, it) { pickedDate ->
                    task.dueTo = pickedDate
                    dueDateTextView.text = Res.string(R.string.due_to_date,
                            DateTimeUtils.getTaskDueDate(task.dueTo))
                }
            }
        }
        dialog.behavior.isHideable = false
        dialog.show()
    }

    fun showDatePickerDialog(context: Context, currentDate: OffsetDateTime, onDateSet: ((OffsetDateTime) -> Unit)) {
        DatePickerDialog(context, { _, year, month, dayOfMonth ->
            val pickedDate = currentDate.withYear(year)
                    .withMonth(month + 1) // Calendar months starts with 0
                    .withDayOfMonth(dayOfMonth)
            onDateSet.invoke(pickedDate)
        }, currentDate.year, currentDate.monthValue - 1, currentDate.dayOfMonth).show()
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