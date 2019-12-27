package com.vitaliimalone.simpletodo.presentation.settings.theme

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Themes
import kotlinx.android.synthetic.main.theme_dialog.*

class ThemeDialog(
    private val activity: FragmentActivity
) : BottomSheetDialog(activity, R.style.TransparentBottomSheet) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.theme_dialog)
        behavior.isHideable = false
        themeRecyclerView.adapter =
            ThemeAdapter {
                Themes.setTheme(activity, it)
                activity.recreate()
                dismiss()
            }
    }
}