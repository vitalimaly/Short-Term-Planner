package com.vitaliimalone.simpletodo.presentation.settings.common

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.ThemeUtils
import kotlinx.android.synthetic.main.theme_dialog.*

class ThemeDialog(
    private val activity: FragmentActivity
) : BottomSheetDialog(activity, R.style.TransparentBottomSheet) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.theme_dialog)
        behavior.isHideable = false
        themeRecyclerView.adapter = ThemeAdapter {
            ThemeUtils.setTheme(activity, it)
            activity.recreate()
            dismiss()
        }
    }
}