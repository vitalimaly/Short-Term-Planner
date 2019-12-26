package com.vitaliimalone.simpletodo.presentation.settings.common

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Themes
import kotlinx.android.synthetic.main.pick_theme_dialog.*

class PickThemeDialog(
    private val activity: FragmentActivity
) : BottomSheetDialog(activity, R.style.TransparentBottomSheet) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pick_theme_dialog)
        behavior.isHideable = false
        pickThemeRecyclerView.adapter = PickThemeAdapter {
            Themes.setTheme(activity, it.resId)
            activity.recreate()
            dismiss()
        }
    }
}