package com.vitaliimalone.simpletodo.presentation.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Res
import kotlinx.android.synthetic.main.rate_dialog.view.*

class RateDialog : BottomSheetDialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), R.style.TransparentBottomSheet).apply {
            behavior.isHideable = false
            setCancelable(false)
            layoutInflater.inflate(R.layout.rate_dialog, null, false).apply {
                titleTextView.text = Res.string(R.string.rate_dialog_title)
                positiveButton.text = Res.string(R.string.rate_dialog_positive)
                negativeButton.text = Res.string(R.string.rate_dialog_negative)
                neutralButton.text = Res.string(R.string.rate_dialog_neutral)
                positiveButton.setOnClickListener {
                    val action = RateDialogDirections.actionRateDialogToAppPlayStorePage()
                    findNavController().navigate(action)
                    findNavController().popBackStack()
                }
                negativeButton.setOnClickListener {

                    findNavController().popBackStack()
                }
                neutralButton.setOnClickListener {

                    findNavController().popBackStack()
                }
                setContentView(this)
            }
        }
    }
}