package com.vitaliimalone.simpletodo.presentation.dialogs

import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.base.BaseBottomSheetDialogFragment
import com.vitaliimalone.simpletodo.presentation.settings.common.ThemeAdapter
import com.vitaliimalone.simpletodo.presentation.utils.ThemeUtils
import kotlinx.android.synthetic.main.theme_dialog.*

class ThemeDialog : BaseBottomSheetDialogFragment(R.layout.theme_dialog) {
    override fun onDialogCreated(dialog: BottomSheetDialog) {
        dialog.themeRecyclerView.adapter = ThemeAdapter {
            ThemeUtils.setTheme(requireActivity(), it)
            requireActivity().recreate()
            dismiss()
        }
    }
}