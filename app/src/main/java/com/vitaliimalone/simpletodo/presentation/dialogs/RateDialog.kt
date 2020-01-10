package com.vitaliimalone.simpletodo.presentation.dialogs

import android.content.Context
import android.os.Bundle
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.base.BaseBottomSheetDialog

class RateDialog(
    context: Context
) : BaseBottomSheetDialog(context, R.layout.language_dialog) {
    override fun onCreate(savedInstanceState: Bundle?) {
        behavior.isHideable = false
    }
}