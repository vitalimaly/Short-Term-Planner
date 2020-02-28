package com.vitaliimalone.shorttermplanner.presentation.dialogs

import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.presentation.base.BaseBottomSheetDialogFragment
import com.vitaliimalone.shorttermplanner.presentation.screens.settings.common.LanguageAdapter
import com.vitaliimalone.shorttermplanner.presentation.utils.LanguageUtils
import kotlinx.android.synthetic.main.language_dialog.*

class LanguageDialog : BaseBottomSheetDialogFragment(R.layout.language_dialog) {
    override fun onDialogCreated(dialog: BottomSheetDialog) {
        dialog.languageRecyclerView.adapter = LanguageAdapter {
            LanguageUtils.setLanguage(requireContext(), it)
            requireActivity().recreate()
            findNavController().popBackStack()
        }
    }
}