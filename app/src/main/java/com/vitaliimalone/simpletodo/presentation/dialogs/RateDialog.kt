package com.vitaliimalone.simpletodo.presentation.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Pref
import com.vitaliimalone.simpletodo.presentation.utils.Res
import kotlinx.android.synthetic.main.rate_dialog.view.*
import org.threeten.bp.OffsetDateTime

class RateDialog : BottomSheetDialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = false
        return BottomSheetDialog(requireContext(), R.style.TransparentBottomSheet).apply {
            layoutInflater.inflate(R.layout.rate_dialog, null, false).apply {
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
                setContentView(this)
            }
        }
    }
}