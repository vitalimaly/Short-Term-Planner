package com.vitaliimalone.simpletodo.presentation.base

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vitaliimalone.simpletodo.R

abstract class BaseBottomSheetDialogFragment(
    @LayoutRes
    private val layoutResId: Int,
    private val dialogIsCancelable: Boolean = true,
    @StyleRes
    private val dialogTheme: Int = R.style.TransparentBottomSheet
) : BottomSheetDialogFragment() {
    abstract fun onDialogCreated(dialog: BottomSheetDialog)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = dialogIsCancelable
        return BottomSheetDialog(requireContext(), dialogTheme).apply {
            behavior.isHideable = false
            layoutInflater.inflate(layoutResId, null, false).apply {
                setContentView(this)
            }
        }
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        onDialogCreated(dialog as BottomSheetDialog)
    }
}