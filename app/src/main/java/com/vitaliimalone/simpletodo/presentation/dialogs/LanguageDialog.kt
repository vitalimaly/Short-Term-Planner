package com.vitaliimalone.simpletodo.presentation.dialogs

import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.base.BaseBottomSheetDialogFragment
import com.vitaliimalone.simpletodo.presentation.screens.settings.common.LanguageAdapter
import com.vitaliimalone.simpletodo.presentation.utils.LanguageUtils
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