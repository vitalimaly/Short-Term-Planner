package com.vitaliimalone.simpletodo.presentation.views

import android.content.Context
import android.graphics.drawable.InsetDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.vitaliimalone.simpletodo.R

class DefaultDividerItemDecoration(
    context: Context,
    drawableResourceId: Int? = null,
    marginLeft: Int = 0,
    marginTop: Int = 0,
    marginRight: Int = 0,
    marginBottom: Int = 0
) : DividerItemDecoration(context, VERTICAL) {

    init {
        val drawable = ContextCompat.getDrawable(context, drawableResourceId ?: R.drawable.divider_default)!!
        val insetDivider = InsetDrawable(drawable, marginLeft, marginTop, marginRight, marginBottom)
        setDrawable(insetDivider)
    }
}