package com.vitaliimalone.simpletodo.presentation.settings.common

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitaliimalone.simpletodo.R
import kotlinx.android.synthetic.main.pick_theme_dialog.*

class PickThemeDialog(context: Context) : BottomSheetDialog(context, R.style.TransparentBottomSheet) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pick_theme_dialog)
        behavior.isHideable = false
        pickThemeRecyclerView.adapter
    }
}