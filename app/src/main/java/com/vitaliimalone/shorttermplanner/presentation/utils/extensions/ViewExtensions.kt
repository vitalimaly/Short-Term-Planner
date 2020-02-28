package com.vitaliimalone.shorttermplanner.presentation.utils.extensions

import android.content.Context
import android.graphics.Point
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.setEnabledWithAlpha(isEnabled: Boolean) {
    this.isEnabled = isEnabled
    alpha = if (isEnabled) 1.0f else 0.4f
}

fun View.showKeyboard() {
    val inputManager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.showSoftInput(this, 0)
}

fun View.hideKeyboard() {
    val inputManager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.forceShowKeyboard() {
    val inputManager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun View.setOnLongClickListenerWithPoint(action: (Point) -> Unit) {
    val coordinates = Point()
    val screenPosition = IntArray(2)
    setOnTouchListener { v, event ->
        if (event.action == MotionEvent.ACTION_DOWN) {
            v.getLocationOnScreen(screenPosition)
            coordinates.set(event.x.toInt() + screenPosition[0], event.y.toInt() + screenPosition[1])
        }
        false
    }
    setOnLongClickListener {
        action.invoke(coordinates)
        true
    }
}

fun View.setOnClickListenerWithPoint(action: (Point) -> Unit) {
    val coordinates = Point()
    val screenPosition = IntArray(2)
    setOnTouchListener { v, event ->
        if (event.action == MotionEvent.ACTION_DOWN) {
            v.getLocationOnScreen(screenPosition)
            coordinates.set(event.x.toInt() + screenPosition[0], event.y.toInt() + screenPosition[1])
        }
        false
    }
    setOnClickListener {
        action.invoke(coordinates)
    }
}