package com.vitaliimalone.simpletodo.presentation.utils

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitaliimalone.simpletodo.R
import kotlinx.android.synthetic.main.delete_task_dialog.view.*

object DialogUtils {
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