package com.vitaliimalone.simpletodo.presentation.dialogs

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.settings.common.LanguageAdapter
import com.vitaliimalone.simpletodo.presentation.utils.LanguageUtils
import kotlinx.android.synthetic.main.language_dialog.*

class LanguageDialog(
    private val activity: FragmentActivity
) : BottomSheetDialog(activity, R.style.TransparentBottomSheet) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.language_dialog)
        behavior.isHideable = false
        languageRecyclerView.adapter =
            LanguageAdapter {
                LanguageUtils.setLanguage(activity, it)
                activity.recreate()
                dismiss()
            }
    }
}