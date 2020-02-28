package com.vitaliimalone.shorttermplanner.presentation.dialogs

import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.presentation.base.BaseBottomSheetDialogFragment
import com.vitaliimalone.shorttermplanner.presentation.utils.Pref
import com.vitaliimalone.shorttermplanner.presentation.utils.Res
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
                Pref.rateAppNeverShowClicked = true
                val action = RateDialogDirections.actionRateDialogToAppPlayStorePage()
                findNavController().navigate(action)
                findNavController().popBackStack()
            }
            neverButton.setOnClickListener {
                Pref.rateAppNeverShowClicked = true
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