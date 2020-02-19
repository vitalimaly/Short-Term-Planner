package com.vitaliimalone.simpletodo.presentation.utils.extensions

import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun EditText.clearFocusOnDoneClick(onDoneClick: () -> Unit = {}) = this.setOnEditorActionListener { _, actionId, _ ->
    if (actionId == EditorInfo.IME_ACTION_DONE) {
        this.clearFocus()
        hideKeyboard()
        onDoneClick.invoke()
        true
    } else {
        false
    }
}

