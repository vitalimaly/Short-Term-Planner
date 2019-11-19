package com.vitaliimalone.simpletodo.presentation.utils

import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun View.setEnabledWithAlpha(isEnabled: Boolean) {
    this.isEnabled = isEnabled
    alpha = if (isEnabled) 1.0f else 0.4f
}

fun EditText.clearFocusOnDoneClick(onDoneClick: () -> Unit = {}) {
    this.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            this.clearFocus()
            hideKeyboard()
            onDoneClick.invoke()
            true
        } else {
            false
        }
    }
}

