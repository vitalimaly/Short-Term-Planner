package com.vitaliimalone.simpletodo.presentation.utils

import android.view.View

fun View.isEnabled(isEnabled: Boolean) {
    this.isEnabled = isEnabled
    alpha = if (isEnabled) 1.0f else 0.4f
}
