package com.vitaliimalone.simpletodo.presentation.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitaliimalone.simpletodo.R

abstract class BaseBottomSheetDialog(
    context: Context, @LayoutRes private val layoutResId: Int
) : BottomSheetDialog(context, R.style.TransparentBottomSheet) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
    }
}