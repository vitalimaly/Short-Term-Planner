package com.vitaliimalone.shorttermplanner.presentation.dialogs

import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.presentation.base.BaseBottomSheetDialogFragment
import com.vitaliimalone.shorttermplanner.presentation.screens.settings.common.ThemeAdapter
import com.vitaliimalone.shorttermplanner.presentation.utils.ThemeUtils
import kotlinx.android.synthetic.main.theme_dialog.*

class ThemeDialog : BaseBottomSheetDialogFragment(R.layout.theme_dialog) {
    override fun onDialogCreated(dialog: BottomSheetDialog) {
        dialog.themeRecyclerView.adapter = ThemeAdapter {
            ThemeUtils.setTheme(it)
            findNavController().popBackStack()
        }
    }
}