package com.vitaliimalone.simpletodo.presentation.utils

import android.view.LayoutInflater
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitaliimalone.simpletodo.R
import kotlinx.android.synthetic.main.add_note_dialog.view.*

fun Fragment.showAddNoteDialog(onSendClick: (title: String) -> Unit) {
    val dialogView = LayoutInflater.from(requireActivity()).inflate(R.layout.add_note_dialog, null, false)
    val dialog = BottomSheetDialog(requireContext(), R.style.TransparentBottomSheet)
    dialog.setContentView(dialogView)
    dialogView.sendButton.setOnClickListener {
        val text = dialogView.titleTextView.text.toString().trim()
        if (text.isNotEmpty()) {
            onSendClick.invoke(text)
            dialog.dismiss()
        }
    }
    dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    dialog.show()
}
