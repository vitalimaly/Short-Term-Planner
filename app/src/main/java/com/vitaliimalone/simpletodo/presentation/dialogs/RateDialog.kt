package com.vitaliimalone.simpletodo.presentation.dialogs

import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.base.BaseBottomSheetDialogFragment
import com.vitaliimalone.simpletodo.presentation.utils.Pref
import com.vitaliimalone.simpletodo.presentation.utils.Res
import kotlinx.android.synthetic.main.rate_dialog.*
import org.threeten.bp.OffsetDateTime

class RateDialog : BaseBottomSheetDialogFragment(R.layout.rate_dialog, false) {
    override fun onDialogCreated(dialog: BottomSheetDialog) {
        dialog.apply {
            titleTextView.text = Res.string(R.string.rate_dialog_title)
            positiveButton.text = Res.string(R.string.rate_dialog_positive)
            neverButton.text = Res.string(R.string.rate_dialog_negative)
            laterButton.text = Res.string(R.string.rate_dialog_neutral)
            positiveButton.setOnClickListener {
                Pref.rateAppDontShow = true
                val action = RateDialogDirections.actionRateDialogToAppPlayStorePage()
                findNavController().navigate(action)
                findNavController().popBackStack()
            }
            neverButton.setOnClickListener {
                Pref.rateAppDontShow = true
                findNavController().popBackStack()
            }
            laterButton.setOnClickListener {
                Pref.rateAppLaunchCount = 0
                Pref.rateAppLaunchDateTime = OffsetDateTime.now()
                findNavController().popBackStack()
            }
        }
    }
}