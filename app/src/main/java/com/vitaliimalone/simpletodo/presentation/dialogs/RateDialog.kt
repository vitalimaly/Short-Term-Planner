package com.vitaliimalone.simpletodo.presentation.dialogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vitaliimalone.simpletodo.NavGraphDirections
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.base.BaseBottomSheetDialog
import com.vitaliimalone.simpletodo.presentation.utils.Res
import kotlinx.android.synthetic.main.rate_dialog.*

class RateDialog(
    private val fragment: Fragment // todo replace with BottomSheetDialogFragment
) : BaseBottomSheetDialog(fragment.requireContext(), R.layout.rate_dialog) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        behavior.isHideable = false
        setCancelable(false)
        setupViews()
        setupClickListeners()
    }

    private fun setupViews() {
        titleTextView.text = Res.string(R.string.rate_dialog_title)
        positiveButton.text = Res.string(R.string.rate_dialog_positive)
        negativeButton.text = Res.string(R.string.rate_dialog_negative)
        neutralButton.text = Res.string(R.string.rate_dialog_neutral)
    }

    private fun setupClickListeners() {
        positiveButton.setOnClickListener {
            val action = NavGraphDirections.actionGlobalAppPlayStorePage()
            fragment.findNavController().navigate(action)
        }
        negativeButton.setOnClickListener {

        }
        neutralButton.setOnClickListener {
            dismiss()
        }
    }
}