package com.vitaliimalone.simpletodo.presentation.utils

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Task
import kotlinx.android.synthetic.main.add_new_task_dialog.view.*
import org.threeten.bp.OffsetDateTime

object Dialogs {
    fun showAddNewTaskDialog(context: Context, date: OffsetDateTime, onAddClick: ((Task) -> Unit)) {
        var dueDate = date
        val dialogView = LayoutInflater.from(context).inflate(R.layout.add_new_task_dialog, null, false)
        val dialog = BottomSheetDialog(context, R.style.TransparentBottomSheet)
        dialog.setContentView(dialogView)
        dialogView.addImageView.setOnClickListener {
            val title = dialogView.titleEditText.text?.trim().toString()
            if (title.isEmpty()) {
                return@setOnClickListener
            }
            val task = Task(title = title, dueTo = dueDate)
            onAddClick.invoke(task)
            dialog.dismiss()
        }
        dialogView.dueDateTextView.text = Strings.get(R.string.due_to_date, DateTimeUtils.getTaskDueDate(dueDate))
        dialogView.dueDateTextView.setOnClickListener {
            showDatePickerDialog(context, dueDate) {
                dueDate = it
                dialogView.dueDateTextView.text = Strings.get(R.string.due_to_date, DateTimeUtils.getTaskDueDate(dueDate))
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
}