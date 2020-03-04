package com.vitaliimalone.shorttermplanner.presentation.base

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.presentation.utils.extensions.hideKeyboard

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
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.addBottomSheetCallback(AlwaysExpandedBottomSheetCallback(behavior))
            layoutInflater.inflate(layoutResId, null, false).apply {
                setContentView(this)
            }
        }
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        onDialogCreated(dialog as BottomSheetDialog)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        hideKeyboard()
    }

    // might need to change with custom BottomSheetBehavior
    private inner class AlwaysExpandedBottomSheetCallback(
        private val behavior: BottomSheetBehavior<FrameLayout>
    ) : BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {
        }

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            Log.d("+++", "before ${behavior.state}")
            if (newState != BottomSheetBehavior.STATE_EXPANDED) {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
            Log.d("+++", "after ${behavior.state}")
        }
    }
}